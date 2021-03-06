package Model.TileRelated.Ruin;

import Controller.GameController.GameController;
import Controller.GameController.MapControllers.TileVisibilityController;
import Model.CivlizationRelated.Citizen;
import Model.CivlizationRelated.City;
import Model.CivlizationRelated.Civilization;
import Model.MapRelated.GameMap;
import Model.TileRelated.Tile.Tile;
import com.google.gson.annotations.Expose;

public class Ruin {
    @Expose
    private boolean gold;
    @Expose
    private boolean visibility;
    @Expose
    private boolean citizen;
    private Tile tile;

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public String getBenefit(Civilization civilization){
        if(gold) {
            return giftCoin(civilization);
        }else if(visibility){
            TileVisibilityController.getInstance().changeVision(this.tile, civilization.getSeenBy(), 1, 1);
            return "You have found a ruin! you get to see the tiles around the ruin as a reward!";
        }else if(citizen){
            if(civilization.getCities().size() == 0){
                return giftCoin(civilization);
            }else{
                City city = civilization.getCities().get(0);
                city.getCitizens().add(new Citizen(city));
                return "You have found a ruin! you get a new citizen in " + city.getName() + " city as a reward";
            }
        }
        return null;
    }

    private String giftCoin(Civilization civilization){
        int amount = GameMap.getInstance().getRandom().nextInt(20) + 10;
        civilization.setGold(civilization.getGold() + amount);
        return "You have found a ruin! you get " + amount + " gold coins as a reward!";
    }

    public Ruin(Tile tile) {
        int whichBenefit = GameMap.getInstance().getRandom().nextInt(3);
        switch (whichBenefit) {
            case 0 -> gold = true;
            case 1 -> visibility = true;
            case 2 -> citizen = true;
        }
        this.tile = tile;
    }
}
