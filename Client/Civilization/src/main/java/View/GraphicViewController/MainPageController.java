package View.GraphicViewController;

import Controller.GameController.GameController;
import Controller.GameController.MapControllers.MapGenerator;
import Controller.NetworkController;
import Controller.PreGameController.LoginAndRegisterController;
import Controller.PreGameController.MainMenuController;
import Controller.SavingDataController.DataSaver;
import Model.Enums.AutoSave;
import Model.Enums.Menus;
import Model.NetworkRelated.Request;
import Model.NetworkRelated.RequestType;
import Model.NetworkRelated.Response;
import Model.User.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    @FXML private Label menuName;
    @FXML private Label playerNum;
    @FXML private ChoiceBox<String> autoSave;
    private Pane description;
    @FXML Label playerDes;
    @FXML Label inviteDes;
    @FXML Label MapDes;
    @FXML Label autoSaveDes;
    @FXML
    private TextField mapWidth;
    @FXML
    private TextField mapHeight;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private TextField inviteUsername;
    @FXML
    private Label notification;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainMenuController.getInstance().addPlayer(LoginAndRegisterController.getInstance().getLoggedInUser().getUsername());
        menuName.setText(MainMenuController.getInstance().showCurrentMenu());
        autoSave.getItems().add("after every 100 years");
        autoSave.getItems().add("every 200 years");
        autoSave.getItems().add("after every round");
        playerDes.setTooltip(new Tooltip("number of players attending in the game including you"));
        inviteDes.setTooltip(new Tooltip("invite your friends to play with you"));
        MapDes.setTooltip(new Tooltip("customize your own map!"));
        autoSave.setTooltip(new Tooltip("choose on which period you want to save your game"));
        file = new File("./src/main/resources/media/gameMedia3.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.05);
        mediaPlayer.setMute(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
    }


    public void buttonSizeIncrease(MouseEvent mouseEvent) {
        Button button =(Button) mouseEvent.getSource();
        button.setStyle("-fx-font-size: 25;-fx-background-color: rgba(231,231,121,0.83);");
    }

    public void buttonSizeDecrease(MouseEvent mouseEvent) {
        Button button =(Button) mouseEvent.getSource();
        button.setStyle("-fx-font-size: 18; -fx-background-color: rgba(201, 238, 221, 0.7);");
    }

    public void logout(MouseEvent mouseEvent) {
        mediaPlayer.stop();
        NetworkController.getInstance().send(new Request(RequestType.Logout,new ArrayList<>()));
        MainMenuController.getInstance().userLogout();
        main.java.Main.changeMenu(Menus.LOGIN_MENU.value);
    }

    public void scoreBoard(MouseEvent mouseEvent) {
        mediaPlayer.stop();
        main.java.Main.changeMenu(Menus.SCORE_BOARD.value);
    }

    public void decreasePlayer(MouseEvent mouseEvent) {
        if(Integer.parseInt(playerNum.getText()) > 2)
            playerNum.setText(Integer.toString(Integer.parseInt(playerNum.getText()) - 1));
    }

    public void increasePlayer(MouseEvent mouseEvent) {
        if(Integer.parseInt(playerNum.getText()) < 10)
            playerNum.setText(Integer.toString(Integer.parseInt(playerNum.getText()) + 1));
    }

    public void profile(MouseEvent mouseEvent) {
        mediaPlayer.stop();
        main.java.Main.changeMenu(Menus.PROFILE_MENU.value);
    }

    @FXML
    private void startGame(MouseEvent mouseEvent) {
        ArrayList<String> players = MainMenuController.getInstance().getPlayers();
        if(Integer.parseInt(playerNum.getText()) != players.size())notification.setText("player count is invalid!");
        else if(Integer.parseInt(mapHeight.getText()) < 5 || Integer.parseInt(mapWidth.getText()) < 5)notification.setText("map config is invalid");
        else {
            players.add(0,mapHeight.getText());
            players.add(0,mapWidth.getText());
            mediaPlayer.stop();
            NetworkController.getInstance().send(new Request(RequestType.startGame,players));
            //main.java.Main.changeMenu(Menus.GAME_MENU.value);
        }
    }

    @FXML
    private void openChatroom(MouseEvent mouseEvent) {
        mediaPlayer.stop();
        CalledMethods.getInstance().getMethodsName().add("MainPage");
        main.java.Main.changeMenu("ChatPage");
    }

    public void loadGame(ActionEvent actionEvent) throws IOException {
        mediaPlayer.stop();
        main.java.Main.changeMenu(Menus.LoadMenu.value);
    }

    public void AutoSaveSelected(ActionEvent actionEvent) {
        if(autoSave.getValue().equals("after every 100 years"))
            DataSaver.getInstance().setAutoSave(AutoSave.AfterNYears2);
        else if(autoSave.getValue().equals("after every round"))
            DataSaver.getInstance().setAutoSave(AutoSave.AfterEveryTurn);
        else if(autoSave.getValue().equals("every 200 years"))
            DataSaver.getInstance().setAutoSave(AutoSave.EveryNYears);
    }

    @FXML
    private void sendInvite(MouseEvent mouseEvent) {
        notification.setText(NetworkController.getInstance().send(new Request(RequestType.sendInvite,new ArrayList<>(){{
            add(inviteUsername.getText());
        }})).getMessage());
    }
}
