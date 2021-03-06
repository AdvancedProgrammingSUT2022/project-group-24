package View;

import javafx.scene.image.Image;

public enum Pics {
    cloud(new Image("/images/Map/cloud.png")),
    citizen(new Image("/images/statusBar/citizen.png")),
    questionMark(new Image("/images/statusBar/questionMark.png")),
    coin(new Image("/images/statusBar/coin.png")),
    ruin(new Image("/images/Map/ruin.png")),
    city1(new Image("images/Cities/1.png"));

    public Image image;

    Pics(Image image) {
        this.image = image;
    }
}
