package Controller.GameController.MapControllers;

import Controller.PreGameController.LoginAndRegisterController;
import Model.CivlizationRelated.Civilization;
import Model.Enums.MapEnum;
import Model.MapRelated.GameMap;
import Model.TileRelated.Tile.Tile;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MapFunctions {
    private static MapFunctions mapFunctions;
    private MapFunctions(){}
    public static MapFunctions getInstance(){
        if(mapFunctions == null)
            mapFunctions = new MapFunctions();
        return mapFunctions;
    }
    public int NonConventionalCoordinatesY(Tile tile){
        if(tile.getX() % 2 == 0){
            return (MapEnum.HEXSIDESHORT.amount * 2) * tile.getY();
        }
        return tile.getY() * MapEnum.HEXSIDESHORT.amount * 2 + MapEnum.HEXSIDESHORT.amount;

    }

    public int NonConventionalCoordinatesX(Tile tile){
        if(tile.getX() % 2 == 0){
            return (MapEnum.HEXSIDESHORT.amount * 2 + MapEnum.HEXSIDELONG.amount * 2) * tile.getX() / 2;
        }
        return (tile.getX() - 1) * (MapEnum.HEXSIDESHORT.amount * 2 + MapEnum.HEXSIDELONG.amount * 2) / 2 + (MapEnum.HEXSIDESHORT.amount + MapEnum.HEXSIDELONG.amount);
    }
    public Civilization getLoggedInUserCiv(){
        for (Civilization civilization:GameMap.getInstance().getCivilizations()) {
            if(civilization.getUser().getUsername().equals(LoginAndRegisterController.getInstance().getLoggedInUser().getUsername()))
                return civilization;
        }
        return null;
    }
    public boolean isMyTurn(){
        return GameMap.getInstance().getPlayerTurn() == getLoggedInUserCiv();
    }
    public ArrayList<Tile> getSurroundings(Tile tile){ //Be careful some tiles might be null!
        int first,second;
        if(tile.getX() % 2 == 0){first = -1;second = 0;}else{first = 0;second = 1;}
        ArrayList <Tile> surroundings = new ArrayList<>(){
            {
                add(getTile(tile.getX() , tile.getY() +1)); //index 0 is down
                add(getTile(tile.getX() , tile.getY() -1)); //index 1 is up
                add(getTile(tile.getX()+1 , tile.getY() + first)); //index 2 is up right
                add(getTile(tile.getX()-1 , tile.getY() + first)); //index 3 is up left
                add(getTile(tile.getX()+1 , tile.getY() + second)); //index 4 is down right
                add(getTile(tile.getX()-1 , tile.getY() + second)); //index 5 is down left
            }
        };
        return surroundings;
    }

    public Color getCivColor(Civilization civilization){
        int index = 0;
        for (Civilization civ:GameMap.getInstance().getCivilizations()) {
            if(civ.getUser().getUsername().equals(civilization.getUser().getUsername())){
                index = GameMap.getInstance().getCivilizations().indexOf(civ);
                break;
            }
        }
        return MapGenerator.getInstance().getColors().get(index);
    }
    public String getCivName(Civilization civilization){
        int index = 0;
        for (Civilization civ:GameMap.getInstance().getCivilizations()) {
            if(civ.getUser().getUsername().equals(civilization.getUser().getUsername())){
                index = GameMap.getInstance().getCivilizations().indexOf(civ);
                break;
            }
        }
        return MapGenerator.getInstance().getColorNames().get(index);
    }
    public Tile getTile(int x , int y){

        for (Tile key: GameMap.getInstance().getTiles()) {
            if(key.getX() == x && key.getY() == y)
                return key;
        }
        return null;
    }

}
