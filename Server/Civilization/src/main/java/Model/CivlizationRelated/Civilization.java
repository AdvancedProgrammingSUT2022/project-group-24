package Model.CivlizationRelated;
import java.util.*;

import Model.Technology.TechnologyType;
import Model.TileRelated.Building.Building;
import Model.TileRelated.Feature.Feature;
import Model.TileRelated.Improvement.Improvement;
import Model.Technology.Technology;
import Model.TileRelated.Resource.Resource;
import Model.TileRelated.Resource.ResourceType;
import Model.TileRelated.Road.Road;
import Model.TileRelated.Tile.Tile;
import Model.Units.NonCombat.Settler;
import Model.Units.Unit;
import Model.User.User;
import com.google.gson.annotations.Expose;

public class Civilization {
    @Expose
    private User user;
    @Expose
    private ArrayList<City> cities = new ArrayList<>();
    @Expose
    private int gold;
    @Expose
    private int goldPerTurn;
    @Expose
    private int sciencePerTurn;
    @Expose
    private double happiness = 10;
    @Expose
    private int totalCoal;
    @Expose
    private int currentCoal;
    @Expose
    private int totalHorses;
    @Expose
    private int currentHorses;
    @Expose
    private int totalIron;
    @Expose
    private int currentIron;
    @Expose
    private int roadMaintenance;
    @Expose
    private ArrayList<Tile> tiles = new ArrayList<>();
    @Expose
    private ArrayList<Improvement> improvementsUnderConstruction = new ArrayList<>();
    @Expose
    private ArrayList<Road> roadsUnderConstruction = new ArrayList<>();
    @Expose
    private ArrayList<Feature> featuresBeingCleared = new ArrayList<>();
    private HashMap<Tile, Integer> seenBy = new HashMap<>();
    @Expose
    private ArrayList<Tile> savingSeenByTile = new ArrayList<>();
    @Expose
    private ArrayList<Integer> savingSeenByInteger = new ArrayList<>();
    @Expose
    private ArrayList<Technology> technologies = new ArrayList<>() {{
        add(new Technology(TechnologyType.Agriculture));
    }};
    @Expose
    private ArrayList<Unit> units = new ArrayList<Unit>();
    @Expose
    private TechnologyType currentResearchProject;
    @Expose
    private int researchTurns = 0;

    public HashMap<Civilization, DiplomaticTie> getDiplomacyRelation() {
        return diplomacyRelation;
    }


    public void declareWar(Civilization civilization){
        diplomacyRelation.put(civilization,DiplomaticTie.War);
    }

    public void makePeace(Civilization civilization){
        diplomacyRelation.put(civilization,DiplomaticTie.Peace);
    }

    public void setDiplomacyRelation(HashMap<Civilization, DiplomaticTie> diplomacyRelation) {
        this.diplomacyRelation = diplomacyRelation;
    }

    private HashMap<Civilization,DiplomaticTie> diplomacyRelation = new HashMap<>();
    private ArrayList<String> Notification = new ArrayList<>();
    private ArrayList<ResourceType> foundedLuxuryRecourses = new ArrayList<>();
    private HashMap<ResourceType,Integer> luxuryResourceCount = new HashMap<>(){{
        for (ResourceType resourceType:ResourceType.values()) {
            put(resourceType,0);
        }
    }};

    public HashMap<ResourceType, Integer> getLuxuryResourceCount() {
        return luxuryResourceCount;
    }

    public void addLuxuryResourceCount(ResourceType resourceType){
        luxuryResourceCount.put(resourceType,luxuryResourceCount.get(resourceType)+1);
    }

    public ArrayList<ResourceType> getFoundedLuxuryRecourses() {
        return foundedLuxuryRecourses;
    }

    public int getRoadMaintenance() {
        return roadMaintenance;
    }
    public void changeRoadMaintenance(int amount){
        roadMaintenance += amount;
    }

    public void addLuxuryRecourse(ResourceType resourceType){
        foundedLuxuryRecourses.add(resourceType);
    }

    public void setResearchTurns(int researchTurns) {
        this.researchTurns = researchTurns;
    }

    public int getResearchTurns() {
        return researchTurns;
    }

    public Integer getSeenBy(Tile tile){
        for (Tile key:seenBy.keySet()) {
            if(key.getX() == tile.getX() && key.getY() == tile.getY())
                return seenBy.get(key);
        }
        return 1;
    }

    public ArrayList<Improvement> getImprovementsUnderConstruction(){
        return improvementsUnderConstruction;
    }

    public ArrayList<Road> getRoadsUnderConstruction() {
        return roadsUnderConstruction;
    }

    public void addImprovementUnderConstruction(Improvement improvement){
        improvementsUnderConstruction.add(improvement);
    }

    public void addRoadUnderConstruction(Road road){
        roadsUnderConstruction.add(road);
    }

    public ArrayList<Feature> getFeaturesBeingCleared() {
        return featuresBeingCleared;
    }
    public void addFeaturesBeingCleared(Feature feature){
        featuresBeingCleared.add(feature);
    }
    public void removeFeaturesBeingCleared(Feature feature){
        featuresBeingCleared.remove(feature);
    }
    public ArrayList<Tile> getSavingSeenByTile() {
        return savingSeenByTile;
    }

    public void setSavingSeenByTile(ArrayList<Tile> savingSeenByTile) {
        this.savingSeenByTile = savingSeenByTile;
    }
    public void newSavingSeenByTile(Set<Tile> keySet) {
        this.savingSeenByTile = new ArrayList<>(keySet);
    }

    public ArrayList<Integer> getSavingSeenByInteger() {
        return savingSeenByInteger;
    }

    public void setSavingSeenByInteger(ArrayList<Integer> savingSeenByInteger) {
        this.savingSeenByInteger = savingSeenByInteger;
    }
    public void newSavingSeenByInteger(Set<Integer> set) {
        this.savingSeenByInteger = new ArrayList<>();
    }
    public void removeFromImprovementsUnderConstruction(Improvement improvement){
        improvementsUnderConstruction.remove(improvement);
    }

    public void removeFromRoadsUnderConstruction(Road road){
        roadsUnderConstruction.remove(road);
    }

    public boolean hasTechnology(TechnologyType givenTechnology){
        for (Technology technology:technologies) {
            if(givenTechnology.equals(technology.getTechnologyType()))return true;
        }
        return false;
    }

    public HashMap<Tile, Integer> getSeenBy() {
        return seenBy;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public void changeGold(int gold) {
        this.gold += gold;
    }
    public double getHappiness() {
        return happiness;
    }
    public void changeHappiness(double happiness) {
        this.happiness += happiness;
    }
    public void addNotification(String notification){
        Notification.add(notification);
    }
    public ArrayList<String> getNotification() {
        return Notification;
    }
    public void addUnit(Unit unit){
        units.add(unit);
    }
    public void addGold(int gold){
        this.gold += gold;
    }


    public void checkGoldRunningOut(){
        if(gold <= 0){
            sciencePerTurn -= 2 ;
        }
    }

    public ArrayList<TechnologyType> searchableTechnologiesTypes(){
        Set<TechnologyType> searchableTechnologiesTypes = new HashSet<>();

        for (Technology technology:technologies) {
            if(technology.getTechnologyType().LeadsToTechs != null) {
                for (TechnologyType technologyType : technology.getTechnologyType().LeadsToTechs) {
                    if (technologyType != null && !hasTechnology(technologyType))
                        searchableTechnologiesTypes.add(technologyType);
                }
            }
        }

        return new ArrayList<>(searchableTechnologiesTypes);
    }

    public int getGoldPerTurn() {
        return goldPerTurn;
    }

    public int getSciencePerTurn() {
        return sciencePerTurn;
    }

    public void setSciencePerTurn(int sciencePerTurn) {
        this.sciencePerTurn = sciencePerTurn;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public ArrayList<City> getCities() {
        return cities;
    }
    public ArrayList<Tile> getTiles() {
        return tiles;
    }
    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }
//    public ArrayList<Resource> getResources() {
//        return resources;
//    }
//    public void setResources(ArrayList<Resource> resources) {
//        this.resources = resources;
//    }
    public ArrayList<Technology> getTechnologies() {
        return technologies;
    }
    public void setTechnologies(ArrayList<Technology> technologies) {
        this.technologies = technologies;
    }
    public ArrayList<Unit> getUnits() {
        return units;
    }
    public void setGoldPerTurn(int goldPerTurn){
        this.goldPerTurn = goldPerTurn;
    }
    public void changeGoldPerTurn(int goldPerTurn){
        this.goldPerTurn += goldPerTurn;
    }
    public void changeSciencePerTurn(int sciencePerTurn){
        this.sciencePerTurn += sciencePerTurn;
    }
    public TechnologyType getCurrentResearchProject() {
        return currentResearchProject;
    }
    public void setCurrentResearchProject(TechnologyType currentResearchProject) {
        this.currentResearchProject = currentResearchProject;
    }
    public void addTechnology(Technology technology){
        this.technologies.add(technology);
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    public int getUserScore(){
        return user.getScore();
    }

    public int getTotalCoal() {
        return totalCoal;
    }

    public int getCurrentCoal() {
        return currentCoal;
    }

    public int getTotalHorses() {
        return totalHorses;
    }

    public int getCurrentHorses() {
        return currentHorses;
    }

    public int getTotalIron() {
        return totalIron;
    }

    public int getCurrentIron() {
        return currentIron;
    }

    public void changeTotalCoal(int totalCoal) {
        this.totalCoal += totalCoal;
    }

    public void changeCurrentCoal(int currentCoal) {
        this.currentCoal += currentCoal;
    }

    public void changeTotalHorses(int totalHorses) {
        this.totalHorses += totalHorses;
    }

    public void changeCurrentHorses(int currentHorses) {
        this.currentHorses += currentHorses;
    }

    public void changeTotalIron(int totalIron) {
        this.totalIron += totalIron;
    }

    public void changeCurrentIron(int currentIron) {
        this.currentIron += currentIron;
    }
    public int getTilesSize(){
        return tiles.size();
    }

    public String getUserName(){
        return this.user.getUsername();
    }

    public Settler getSettler(){
        for (Unit unit:this.units) {
            if(unit instanceof Settler)
                return ((Settler)unit);
        }
        return  null;
    }

    public void setSeenBy(HashMap<Tile, Integer> seenBy) {
        this.seenBy = seenBy;
    }
}
