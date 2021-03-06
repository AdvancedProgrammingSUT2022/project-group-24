package Model.Units.Combat;
import Controller.GameController.UnitController;
import Controller.GameController.MapControllers.TileVisibilityController;
import Model.CivlizationRelated.City;
import Model.CivlizationRelated.Civilization;
import Model.MapRelated.GameMap;
import Model.TileRelated.Tile.Tile;
import Model.Units.Unit;
import Model.Units.TypeEnums.UnitType;
import com.google.gson.annotations.Expose;

public class Combat extends Unit {
    @Expose
    protected int Xp;
    @Expose
    protected double hitPoints = 8;
    @Expose
    private int fortifiedTurnCount = 0;
    @Expose
    private boolean hasAttacked;

    public Combat(Civilization civilization, Tile tile, UnitType unitType) {
        super(civilization, tile, unitType);
        //TODO Auto-generated constructor stub
    }
    public boolean isHasAttacked() {
        return hasAttacked;
    }
    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }
    public Combat(){
        super();
    }
    public Combat(double hitPoints){
        super();
        this.hitPoints = hitPoints;
    }
    public int getFortifiedTurnCount() {
        return fortifiedTurnCount;
    }

    public void setFortifiedTurnCount(int fortifiedTurnCount) {
        this.fortifiedTurnCount = fortifiedTurnCount;
    }

    public double getMovementsLeft() {
        return movementsLeft;
    }

    public void setMovementsLeft(int movementsLeft) {
        this.movementsLeft = movementsLeft;
    }

    public double getHitPoints() {
        return hitPoints;
    }
    public double cityAttackDamage(City city){
        return UnitController.getInstance().calculateDamageDeltToCity(this, city);
    }
    public void setHitPoints(double hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getXp() {
        return Xp;
    }

    public void addXp(int xp){
        this.Xp += xp;
    }
    
    public double attackDamage(Combat enemy){
        return UnitController.getInstance().calculateDamageDeltToDefendingUnit(this,enemy);
    }
    public double defendDamage(Combat enemy){
        return UnitController.getInstance().calculateDamageDealtToAttacker(enemy, this); 
    }
    public void changeHitPoint(double changeAmount){
        this.hitPoints += changeAmount;
    }

    public double getMaxDamage(){
        if(this instanceof Ranged)
            this.maxDamage = ((Ranged)(this)).unitType.rangedCombatStrength;
        else 
            this.maxDamage = (this.unitType.combatStrength);
        return this.maxDamage;
    }

    public void captureCivilian(GameMap gameMap , Unit nonCombatUnit) {
        nonCombatUnit.getCivilization().getUnits().remove(nonCombatUnit);
        this.civilization.getUnits().add(nonCombatUnit);
        nonCombatUnit.setCivilization(this.civilization);
        nonCombatUnit.getCivilization().getUnits().remove(nonCombatUnit);
        this.tile.getUnits().remove(this);
        TileVisibilityController.getInstance().changeVision(gameMap , getTile(), civilization.getSeenBy(), -1, 2);
        this.tile = nonCombatUnit.getTile();
        this.tile.getUnits().add(this);
        TileVisibilityController.getInstance().changeVision(gameMap , getTile(), civilization.getSeenBy(), 1, 2);
    }
}
