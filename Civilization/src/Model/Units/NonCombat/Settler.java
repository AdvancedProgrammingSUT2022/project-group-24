package Model.Units.NonCombat;

import java.util.ArrayList;

import Model.City;
import Model.Civilization;
import Model.Technology.Technology;
import Model.TileAndFeatures.Resource.Resource;
import Model.TileAndFeatures.Tile.Tile;

public class Settler extends NonCombat {

    public Settler(Civilization civilization, City city, Tile tile, int movement, int cost,
            Technology technologyRequired, ArrayList<Resource> resourcesRequired) {
        super(civilization, city, tile, movement, cost, technologyRequired, resourcesRequired);
        //TODO Auto-generated constructor stub
    }
    public void buildCity(){
        
    }
    @Override
    public void updateDataAfterAction(City city){

    }
}
