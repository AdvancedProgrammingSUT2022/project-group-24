package Model.CivlizationRelated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import Model.Technology.TechnologyMainType;
import Model.Technology.TechnologyType;
import Model.TileRelated.Building.Building;
import Model.TileRelated.Feature.Feature;
import Model.TileRelated.Improvement.Improvement;
import Model.Technology.Technology;
import Model.TileRelated.Resource.Resource;
import Model.TileRelated.Tile.Tile;
import Model.Units.Unit;
import Model.User.User;

public class Civilization {
    private User user;
    private ArrayList<City> cities = new ArrayList<>();
    private int gold;
    private int goldPerTurn;
    private int sciencePerTurn;
    private int happiness = 10;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private HashMap<Tile, Integer> seenBy = new HashMap<>();
    private HashMap<Tile, Feature> revealedFeatures = new HashMap<>();
    private HashMap<Tile, Resource> revealedResources = new HashMap<>();
    private HashMap<Tile, Improvement> revealedImprovements = new HashMap<>();
    private HashMap<Tile, Building> revealedBuildings = new HashMap<>();
    private ArrayList<Resource> resources;
    private ArrayList<Technology> technologies = new ArrayList<Technology>();
    private ArrayList<Unit> units = new ArrayList<Unit>();
    private LinkedHashMap<TechnologyType, Integer> researchProjects = new LinkedHashMap<TechnologyType, Integer>();
    private TechnologyType currentResearchProject;
    private int researchTurns = 0;
    private ArrayList<DiplomaticTie> diplomaticTies; 
    private ArrayList<String> notifHistory;

    public LinkedHashMap<TechnologyType, Integer> getResearchProjects() {
        return researchProjects;
    }

    public void addResearchProject(TechnologyType technologyType, int turn){
        this.researchProjects.put(technologyType, turn);
    }
    public void setResearchTurns(int researchTurns) {
        this.researchTurns = researchTurns;
    }

    public int getResearchTurns() {
        return researchTurns;
    }

    public HashMap<Tile, Building> getRevealedBuildings() {
        return revealedBuildings;
    }

    public void setRevealedBuildings(HashMap<Tile, Building> revealedBuildings) {
        this.revealedBuildings = revealedBuildings;
    }
    public void addRevealBuilding(Tile tile, Building building){
        this.revealedBuildings.put(tile, building);
    }
    public HashMap<Tile, Resource> getRevealedResources() {
        return revealedResources;
    }

    public void setRevealedResources(HashMap<Tile, Resource> revealedResources) {
        this.revealedResources = revealedResources;
    }
    public HashMap<Tile, Improvement> getRevealedImprovements() {
        return revealedImprovements;
    }
    public void setRevealedImprovements(HashMap<Tile, Improvement> revealedImprovements) {
        this.revealedImprovements = revealedImprovements;
    }
    public HashMap<Tile, Feature> getRevealedFeatures() {
        return revealedFeatures;
    }
    public void setRevealedFeatures(HashMap<Tile, Feature> revealedFeatures) {
        this.revealedFeatures = revealedFeatures;
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
    public int getHappiness() {
        return happiness;
    }
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }
    public void addNotif(String notif){
        notifHistory.add(notif);
    }
    public void addDiplomaticTie(DiplomaticTie diplomaticTie){
        diplomaticTies.add(diplomaticTie);
    }
    public void addUnit(Unit unit){
        units.add(unit);
    }
    public void addGold(int gold){
        this.gold += gold;
    }

    public ArrayList<DiplomaticTie> getDiplomaticTies() {
        return diplomaticTies;
    }
    public void setDiplomaticTies(ArrayList<DiplomaticTie> diplomaticTies) {
        this.diplomaticTies = diplomaticTies;
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
    public ArrayList<Resource> getResources() {
        return resources;
    }
    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }
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
    public Technology getTechnology(){
        return null;
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



}
