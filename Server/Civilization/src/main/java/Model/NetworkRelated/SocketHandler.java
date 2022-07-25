package Model.NetworkRelated;

import Controller.PreGameController.LoginAndRegisterController;
import Controller.PreGameController.MainMenuController;
import Controller.PreGameController.ProfileMenuController;
import Controller.SavingDataController.DataSaver;
import Model.User.User;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class SocketHandler extends Thread {
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    private User loggedInUser;

    public SocketHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request =  new Gson().fromJson(this.dataInputStream.readUTF(),Request.class);
                Response response = handleRequest(request);
                this.dataOutputStream.writeUTF(response.toJson());
                this.dataOutputStream.flush();
            } catch (SocketException e) {
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Response handleRequest(Request request) {
        String response = null;
        switch (request.getRequestType()){
            case Login -> response = login(request.getParams().get(0),request.getParams().get(1));
            case Register -> response = LoginAndRegisterController.getInstance().register(request.getParams().get(0),request.getParams().get(1),request.getParams().get(2));
            case Logout -> response = MainMenuController.getInstance().userLogout(loggedInUser);
            case Users -> response = DataSaver.getInstance().loadUsers();
            case ChangeNickname -> response = ProfileMenuController.getInstance().changeNickname(request.getParams().get(0),loggedInUser);
            case ChangePassword -> response = ProfileMenuController.getInstance().changeCurrentPassword(request.getParams().get(0),request.getParams().get(1),loggedInUser);
            case NextProfilePic -> ProfileMenuController.getInstance().increaseImageIndex(Integer.parseInt(request.getParams().get(0)),loggedInUser);
            case PrevProfilePic -> ProfileMenuController.getInstance().decreaseImageIndex(Integer.parseInt(request.getParams().get(0)),loggedInUser);
            case ChoosePic ->  loggedInUser.setProfPicIndex(Integer.parseInt(request.getParams().get(0)));
        }
        return new Response(response);
    }

    public String login(String username , String password){
        if(username.equals(""))return "enter username!";
        User user = LoginAndRegisterController.getInstance().usernameCheck(username);
        if(user == null)return "Username and password didn’t match!";
        if(!user.getPassword().equals(password))return "Username and password didn’t match!";
        loggedInUser = user;
        return "user logged in successfully!";
    }

}
