package Controller.PreGameController;

import java.util.ArrayList;
import java.util.regex.Matcher;

import Controller.Controller.Controller;
import Controller.SavingDataController.DataSaver;
import Model.User.User;

public class LoginAndRegisterController extends Controller{
    private static LoginAndRegisterController loginAndRegisterController;
    private LoginAndRegisterController(){}
    public static LoginAndRegisterController getInstance(){
        if(loginAndRegisterController == null)
            loginAndRegisterController = new LoginAndRegisterController();
        return loginAndRegisterController;
    }
    private ArrayList<User> users = new ArrayList<>();

    public User getUser(String username){
        for (User key:users) {
            if(key.getUsername().equals(username))
                return key;
        }
        return null;
    }

    public User getUser(User user){
        for (User key:users) {
            if(key.getUsername().equals(user.getUsername()))
                return key;
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    public void setUsers(ArrayList<User> createdUsers){
        users = createdUsers;
    }


    public String register(String username , String password , String nickname){
        if(getUser(username) == null) {
            for (User key:users) {
                if(key.getNickname().equals(nickname))
                    return "user with nickname " + key.getNickname() +" already exists";
            }
            User user = new User();
            user.setNickname(nickname);
            user.setUsername(username);
            user.setPassword(password);
            users.add(user);
            DataSaver.getInstance().saveUsers();
            return "user created successfully";
        }
        else return "user with username " + username + " already exists";
    }


    @Override
    public String showCurrentMenu() {
        return "Login menu";
    }
    @Override
    public String enterMenu(Matcher matcher) {
        if(matcher.group("menu").equals("Main_Menu"))return "done!";
        return "menu navigation is not possible";
    }
}
