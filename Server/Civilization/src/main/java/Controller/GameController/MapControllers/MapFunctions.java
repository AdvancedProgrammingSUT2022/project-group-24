package Controller.GameController.MapControllers;

import Model.CivlizationRelated.Civilization;
import Model.Enums.MapEnum;
import Model.MapRelated.GameMap;
import Model.TileRelated.Tile.Tile;

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

    public ArrayList<Tile> getSurroundings(GameMap gameMap , Tile tile){ //Be careful some tiles might be null!
        int first,second;
        if(tile.getX() % 2 == 0){first = -1;second = 0;}else{first = 0;second = 1;}
        ArrayList <Tile> surroundings = new ArrayList<>(){
            {
                add(getTile(gameMap , tile.getX() , tile.getY() +1)); //index 0 is down
                add(getTile(gameMap , tile.getX() , tile.getY() -1)); //index 1 is up
                add(getTile(gameMap ,tile.getX()+1 , tile.getY() + first)); //index 2 is up right
                add(getTile(gameMap ,tile.getX()-1 , tile.getY() + first)); //index 3 is up left
                add(getTile(gameMap ,tile.getX()+1 , tile.getY() + second)); //index 4 is down right
                add(getTile(gameMap ,tile.getX()-1 , tile.getY() + second)); //index 5 is down left
            }
        };
        return surroundings;
    }

    public Tile getTile(GameMap gameMap , int x , int y){

        for (Tile key: gameMap.getTiles()) {
            if(key.getX() == x && key.getY() == y)
                return key;
        }
        return null;
    }

}
