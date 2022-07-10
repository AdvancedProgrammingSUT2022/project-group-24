package Controller.GameController;
import Model.CivlizationRelated.City;
import Model.CivlizationRelated.Civilization;
import Model.MapRelated.GameMap;
import Model.Technology.Technology;
import Model.Technology.TechnologyType;
import Model.TileRelated.Building.Building;
import Model.TileRelated.Feature.Feature;
import Model.TileRelated.Feature.FeatureType;
import Model.TileRelated.Improvement.Improvement;
import Model.TileRelated.Resource.ResourceMainTypes;
import Model.TileRelated.Resource.ResourceType;
import Model.TileRelated.Tile.Tile;
import Model.TileRelated.Road.Road;
import Model.User.User;
import Model.Units.Unit;
import Controller.GameController.MapControllers.MapGenerator;
import java.util.*;
import java.util.regex.Matcher;

public class GameController{
    private int turn = 1;
    private Civilization playerTurn;
    private Unit selectedUnit;
    private City selectedCity;
    private City selectedCityToAttack;
    private static GameController gameController; 
    private GameMap map = GameMap.getInstance();
    private GameController(){}
    public City getSelectedCityToAttack() {
        return selectedCityToAttack;
    }
    public void setSelectedCityToAttack(City selectdCityToAttack) {
        this.selectedCityToAttack = selectdCityToAttack;
    }
    public static GameController getInstance(){
        if(gameController == null)
            gameController = new GameController();
        return gameController;
    }
    public Civilization getPlayerTurn() {
        return playerTurn;
    }
    public void setPlayerTurn(Civilization playerTurn) {
        this.playerTurn = playerTurn;
    }
    public GameMap getMap(){
        return this.map;
    }
    public Unit getSelectedUnit(){
        return selectedUnit;
    }
    public void setSelectedUnit(Unit selectedUnit){
        this.selectedUnit = selectedUnit;
    }
    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }
    public String nextTurn(){
        changePlayer();
        UnitController.getInstance().updateAllUnitData();
        restoreMovementLefts();
        reducingTurnOfTheUnitsAndBuildings();
        CivilizationController.getInstance().calculateProducts(playerTurn);
        reduceTurnOfImprovements();
        reduceTurnOfFeaturesBeingCleared();
        reduceTurnOfRoads();
        reducingTurnOfTheTechnologies();
        selectedUnit = null;
        selectedCity = null;
        // graph init is a heavy method
        GameMap.getInstance().setInitialGraph(Movement.getInstance().graphInit());
        if(playerTurn.equals(this.map.getCivilizations().get(0))) turn++;
        return "next player turn!";
    }


    public void reduceTurnOfFeaturesBeingCleared(){
        ArrayList<Feature> Constructions = playerTurn.getFeaturesBeingCleared();
        for (int i=0;i<Constructions.size() ; i++) {
            if(Constructions.get(i).getWorker() == null)return;
            Constructions.get(i).changeDaysToClear(-1);
            Constructions.get(i).getWorker().setMovementsLeft(0);
            if(Constructions.get(i).getDaysToClear() == 0){
                if(Constructions.get(i) != null &&Constructions.get(i).getFeatureType().equals(FeatureType.Marsh))Constructions.get(i).getWorker().getTile().setFeature(null);
                if(Constructions.get(i) != null && Constructions.get(i).getFeatureType().equals(FeatureType.Jungle))Constructions.get(i).getWorker().getTile().setFeature(null);
                if(Constructions.get(i) != null && Constructions.get(i).getFeatureType().equals(FeatureType.Forest))Constructions.get(i).getWorker().getTile().setFeature(null);
                Constructions.get(i).setWorker(null);
                playerTurn.removeFeaturesBeingCleared(Constructions.get(i));
                i--;
            }
        }
    }

    public void reduceTurnOfImprovements(){
        ArrayList<Improvement> Constructions = playerTurn.getImprovementsUnderConstruction();
        for (int i=0;i<Constructions.size() ; i++) {
            if(Constructions.get(i).getWorker() == null)return;
            Constructions.get(i).changeDaysToComplete(-1);
            Constructions.get(i).getWorker().setMovementsLeft(0);
            if(Constructions.get(i).getDaysToComplete() == 0){
                Constructions.get(i).setWorker(null);
                if(Constructions.get(i).getTile().getFeature() != null &&Constructions.get(i).getTile().getFeature().getFeatureType().equals(FeatureType.Marsh))Constructions.get(i).getTile().setFeature(null);
                if(Constructions.get(i).getTile().getFeature() != null && Constructions.get(i).getTile().getFeature().getFeatureType().equals(FeatureType.Jungle))Constructions.get(i).getTile().setFeature(null);
                if(Constructions.get(i).getTile().getFeature() != null && Constructions.get(i).getTile().getFeature().getFeatureType().equals(FeatureType.Forest))Constructions.get(i).getTile().setFeature(null);
                if(Constructions.get(i).getTile().getResource() != null) {
                    for (ResourceType resource : Constructions.get(i).getImprovementType().ImprovesThisResources) {
                        if(Constructions.get(i).getTile().getResource().getResourceType().equals(resource)){
                            Constructions.get(i).getTile().getResource().setAvailable(true);
                            luxuryAndStrategicRecourses(resource);
                        }
                    }
                }
                playerTurn.removeFromImprovementsUnderConstruction(Constructions.get(i));
                i--;
            }
        }
    }

    public void reduceTurnOfRoads(){
        ArrayList<Road> Constructions = playerTurn.getRoadsUnderConstruction();
        for (int i=0;i<Constructions.size() ; i++) {
            if(Constructions.get(i).getWorker() == null)return;
            Constructions.get(i).changeDaysToComplete(-1);
            Constructions.get(i).getWorker().setMovementsLeft(0);
            if(Constructions.get(i).getDaysToComplete() == 0){
                Constructions.get(i).setWorker(null);
                playerTurn.removeFromRoadsUnderConstruction(Constructions.get(i));
                i--;
                playerTurn.changeRoadMaintenance(1);
            }
        }
    }

    public String civilizationOutPut(){
        StringBuilder output = new StringBuilder();
        output.append("Gold : " + playerTurn.getGold() + " + " + playerTurn.getGoldPerTurn() + "\n");
        output.append("Science Per Turn : " + playerTurn.getSciencePerTurn() + "\n");
        output.append("Happiness : " + playerTurn.getHappiness() + "\n");
        output.append("Iron : " + playerTurn.getTotalIron() + " (used : " + (playerTurn.getTotalIron()-playerTurn.getCurrentIron()) + ")" + "\n");
        output.append("Horse : " + playerTurn.getTotalHorses() + " (used : " + (playerTurn.getTotalHorses()-playerTurn.getCurrentHorses()) + ")" + "\n");
        output.append("Coal : " + playerTurn.getTotalCoal() + " (used : " + (playerTurn.getTotalCoal()-playerTurn.getCurrentCoal()) + ")");
        return String.valueOf(output);
    }

    public void luxuryAndStrategicRecourses(ResourceType resource){
        if(resource.equals(ResourceType.Iron)){
            playerTurn.changeTotalIron(2);
            playerTurn.changeCurrentIron(2);
        }
        if(resource.equals(ResourceType.Horses)){
            playerTurn.changeTotalHorses(2);
            playerTurn.changeCurrentHorses(2);
        }
        if(resource.equals(ResourceType.Coal)){
            playerTurn.changeTotalCoal(2);
            playerTurn.changeCurrentCoal(2);
        }
        if(resource.mainType.equals(ResourceMainTypes.LuxuryResources)){
            boolean alreadyHaveIt = false;
            for (ResourceType founded:playerTurn.getFoundedLuxuryRecourses()) {
                if(resource.equals(founded)){
                    alreadyHaveIt = true;
                    break;
                }
            }
            if(!alreadyHaveIt){
                playerTurn.addLuxuryRecourse(resource);
                playerTurn.changeHappiness(2);
            }
        }
    }

    public void reducingTurnOfTheTechnologies(){
        if(getPlayerTurn().getCurrentResearchProject() != null){
            int turn = GameController.getInstance().playerTurn.getResearchTurns() - 1;
            getPlayerTurn().setResearchTurns(turn);
            TechnologyType technologyType = getPlayerTurn().getCurrentResearchProject();
            if(turn <= 0){
                Technology technology = new Technology(technologyType);
                getPlayerTurn().addTechnology(technology);
                getPlayerTurn().setCurrentResearchProject(null);
            }
        }
    }
    public void reducingTurnOfTheUnitsAndBuildings(){
        if(getPlayerTurn().getCities() != null){
            for(City city : GameController.getInstance().getPlayerTurn().getCities()){
                reducingTurnOFUnits(city);
                reducingTurnOfTheBuildings(city);
            }
        }
    }
    private void reducingTurnOFUnits(City city){
        if(city.getUnderConstructionUnit() != null && city.getUnitTurn() != 0){
            int turn = city.getUnitTurn() - 1;
            city.setUnitTurn(turn);
        }
        if(city.getUnderConstructionUnit() != null && city.getUnitTurn() == 0){
            //              city.addUnit(unit);
            Unit unit = new Unit();
            city.getCityTiles().get(0).addUnit(unit);
            unit.setUnitType(city.getUnderConstructionUnit());
            Tile tile = city.getCityTiles().get(0);
            UnitController.getInstance().makeUnit(city.getUnderConstructionUnit(),city.getCivilization(), tile);
            city.setUnderConstructionUnit(null);
            city.setUnitTurn(0);
        }
    }
    public void reducingTurnOfTheBuildings(City city){
        if(city.getUnderConstructionBuilding() != null && city.getBuildingTurn() != 0){
            int turn = city.getBuildingTurn() - 1;
            city.setBuildingTurn(turn);
        }
        if(city.getUnderConstructionBuilding() != null && city.getBuildingTurn() == 0){
            Building building = new Building(city.getUnderConstructionBuilding());
            city.addBuilding(building);
            GameMap.getInstance().addBuiltBuilding(building);
            city.setUnderConstructionBuilding(null);
            city.setBuildingTurn(0);
        }
    }
    private void changePlayer(){
        int turnIndex = this.map.getCivilizations().indexOf(playerTurn);
        if(turnIndex == this.map.getCivilizations().size() - 1){turnIndex = 0;}
        else turnIndex++;
        playerTurn = this.map.getCivilizations().get(turnIndex);
    }

    private void restoreMovementLefts(){
        UnitController.getInstance().restoreUnitMovementLeft();
    }
    public String printMap(){
        if(selectedCity != null ){
            System.out.println(selectedCity.getPopulation());
            System.out.println(selectedCity.getGoldPerTurn());
            System.out.println(selectedCity.getFoodPerTurn());
            System.out.println(selectedCity.getProductionPerTurn());
        }
        return this.map.printMap();
    }
    public String initMoveUnit(Matcher matcher) {
        return UnitController.getInstance().initMoveUnit(matcher); 
    }
    public String attack(Matcher matcher){
        return UnitController.getInstance().combat(matcher);
    }

    public int getTurn() {
        return turn;
    }
    public String deleteUnit(){
        return UnitController.getInstance().removeUnitFromGame(selectedUnit);
    }
    public char[] wake() {
        return null;
    }
}
