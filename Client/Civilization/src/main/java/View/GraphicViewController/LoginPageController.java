package View.GraphicViewController;

import Controller.NetworkController;
import Controller.PreGameController.LoginAndRegisterController;
import Controller.PreGameController.MainMenuController;
import Controller.SavingDataController.DataSaver;
import Model.Enums.Menus;
import Model.NetworkRelated.Request;
import Model.NetworkRelated.RequestType;
import Model.NetworkRelated.Response;
import Model.NetworkRelated.ResponseType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import main.java.Main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class LoginPageController implements Initializable {

    @FXML private TextField username;
    @FXML private TextField nickname;
    @FXML private PasswordField password;
    @FXML private Label error;


    @FXML
    Label menuName;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuName.setText(LoginAndRegisterController.getInstance().showCurrentMenu());
        file = new File("./src/main/resources/media/civMedia.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
    }
    
    public void buttonSizeIncrease(MouseEvent mouseEvent) {
        Button button =(Button) mouseEvent.getSource();
        button.setStyle("-fx-font-size: 25;-fx-background-color: rgba(231,231,121,0.83);");
    }

    public void buttonSizeDecrease(MouseEvent mouseEvent) {
        Button button =(Button) mouseEvent.getSource();
        button.setStyle("-fx-font-size: 18; -fx-background-color: rgba(201, 238, 221, 0.7);");
    }

    public void login(MouseEvent mouseEvent) {
        Response responseUser = NetworkController.getInstance().send(new Request(RequestType.Users,new ArrayList<>()));
        DataSaver.getInstance().setUsersFromJsonString(responseUser.getMessage());
        ArrayList<String> params = new ArrayList<>(){{
            add(username.getText());
            add(password.getText());
        }};
        Response response = NetworkController.getInstance().send(new Request(RequestType.Login,params));
        error.setText(response.getMessage());
        error.setVisible(true);
        LoginAndRegisterController.getInstance().login(username.getText(),password.getText());
        if(error.getText().equals("user logged in successfully!")){
            mediaPlayer.stop();
            main.java.Main.changeMenu(Menus.MAIN_MENU.value);
        }
    }

    public void register(MouseEvent mouseEvent) {
        error.setText(LoginAndRegisterController.getInstance().register(username.getText(),password.getText(),nickname.getText()));
        error.setVisible(true);
    }

    public void quit(ActionEvent actionEvent) {
//        Main.scene.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent windowEvent) {
//                close();
//            }
//        });
    }
}