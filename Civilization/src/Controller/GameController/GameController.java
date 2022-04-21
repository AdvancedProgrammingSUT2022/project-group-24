package Controller.GameController;

import Model.CivlizationRelated.Civilization;
import Model.Enums.Color;
import Model.Enums.MapEnum;
import Model.TileRelated.Building.Building;
import Model.TileRelated.Building.BuildingType;
import Model.TileRelated.Feature.Feature;
import Model.TileRelated.Feature.FeatureType;
import Model.TileRelated.Improvement.Improvement;
import Model.TileRelated.Improvement.ImprovementType;
import Model.TileRelated.Resource.Resource;
import Model.TileRelated.Resource.ResourceType;
import Model.TileRelated.Terraine.TerrainType;
import Model.TileRelated.Tile.Tile;
import Model.TileRelated.Tile.TileVisibility;
import Model.Units.Unit;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

public class GameController {
    private ArrayList<Civilization> civilizations = new ArrayList<>();
    private Civilization playerTurn;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private Unit selectedUnit;
    
    public void generateRandomMap(int civilizationCount,int countTile){
        Civilization first = new Civilization();
        Civilization second = new Civilization();
        Civilization third = new Civilization();
        civilizations.add(first);
        civilizations.add(second);
        civilizations.add(third);
        Tile a = new Tile();
        a.setX(0);
        a.setY(0);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert);
        a.setCivilization(first);
        tiles.add(a);
        a = new Tile();
        a.setX(1);
        a.setY(0);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert);
        a.setCivilization(first);
        tiles.add(a);
        a = new Tile();
        a.setX(2);
        a.setY(0);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert);
        a.setCivilization(first);
        tiles.add(a);
        a = new Tile();
        a.setX(3);
        a.setY(0);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert);
        a.setCivilization(first);
        tiles.add(a);
        a = new Tile();
        a.setX(4);
        a.setY(0);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert);
        a.setCivilization(first);
        tiles.add(a);
        a = new Tile();
        a.setX(0);
        a.setY(1);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert);
        a.setCivilization(first);
        tiles.add(a);
        a = new Tile();
        a.setX(1);
        a.setY(1);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert);
        a.setCivilization(first);
        tiles.add(a);
        a = new Tile();
        a.setX(2);
        a.setY(1);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setResource(new Resource(ResourceType.Bananas));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert);
        a.setCivilization(first);
        tiles.add(a);
        a = new Tile();
        a.setX(3);
        a.setY(1);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert); 
        a.setCivilization(second);
        tiles.add(a);
        a = new Tile();
        a.setX(0);
        a.setY(2);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setResource(new Resource(ResourceType.Bananas));
        a.setTerrain(TerrainType.Grassland);
        a.setCivilization(first);
        tiles.add(a);
        a = new Tile();
        a.setX(1);
        a.setY(2);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Hill);
        a.setCivilization(second);
        a.setTileVisibility(TileVisibility.FOGOFWAR);
        a.setFeature(new Feature(FeatureType.Jungle));
        a.setResource(new Resource(ResourceType.Bananas));
        tiles.add(a);
        a = new Tile();
        a.setX(2);
        a.setY(2);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setFeature(new Feature(FeatureType.Forest));
        a.setTerrain(TerrainType.Desert);
        a.setCivilization(third);
        tiles.add(a);
        a = new Tile();
        a.setX(3);
        a.setY(2);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert);
        a.setResource(new Resource(ResourceType.Bananas));
        a.setCivilization(third);
        tiles.add(a);
        a = new Tile();
        a.setX(4);
        a.setY(2);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert);
        a.setCivilization(third);
        a.setResource(new Resource(ResourceType.Bananas));
        tiles.add(a);
        a = new Tile();
        a.setX(4);
        a.setY(1);
        a.setBuilding(new Building(BuildingType.Armory));
        a.setImprovement(new Improvement(ImprovementType.Camp));
        a.setTerrain(TerrainType.Desert);
        a.setCivilization(third);
        tiles.add(a);
    }
    public String printMap(){
        String[][] map = new String[MapEnum.MAPHEIGHT.amount * MapEnum.HEXSIDESHORT.amount * 2 + MapEnum.HEXSIDESHORT.amount + 1][MapEnum.MAPWIDTH.amount * (MapEnum.HEXSIDELONG.amount + MapEnum.HEXSIDESHORT.amount) + MapEnum.HEXSIDESHORT.amount];
        blankMap(map);
        for (int i = 0; i < tiles.size(); i++) {
            createHexagon(tiles.get(i), map);
        }
        return printArray(map);
    }
    public String printArray(String array[][]){
        String returnString = new String();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                returnString += array[i][j];
            }
            if(i != array.length - 1)
                returnString += "\n";
        }
        return returnString;
    }
    public void blankMap(String map[][]){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = " ";
            }
        }
    }
    public String assignCharToCivilization(Civilization civilization){
        int asciiA = (int)'A';
        char returnChar = (char)(asciiA + civilizations.indexOf(civilization));
        return Character.toString(returnChar);
    }
    public void nullify(String map[][],int startIndex,int length,int y){
        for (int i = startIndex; i < startIndex + length; i++) {
            map[y][i] = "";          
        }
    }
       public void createHexagon(Tile tile,String map[][]){
        int x,y;
        if(tile.getX() % 2 == 0){
            x = (MapEnum.HEXSIDESHORT.amount * 2 + MapEnum.HEXSIDELONG.amount * 2) * tile.getX() / 2;
            y = (MapEnum.HEXSIDESHORT.amount * 2) * tile.getY();
        }else{
            x = (tile.getX() - 1) * (MapEnum.HEXSIDESHORT.amount * 2 + MapEnum.HEXSIDELONG.amount * 2) / 2 + (MapEnum.HEXSIDESHORT.amount + MapEnum.HEXSIDELONG.amount);
            y = tile.getY() * MapEnum.HEXSIDESHORT.amount * 2 + MapEnum.HEXSIDESHORT.amount;
        }
        String backgroundColor = Color.getBackgroundColor(tile.getTerrain().ordinal());
        String reset = Color.ANSI_RESET;
        if(tile.getTileVisibility() != null && tile.getTileVisibility().equals(TileVisibility.FOGOFWAR))
            backgroundColor = Color.ANSI_WHITE_BACKGROUND;
        if(y == 0){for (int i = MapEnum.HEXSIDESHORT.amount; i < MapEnum.HEXSIDELONG.amount + MapEnum.HEXSIDESHORT.amount ; i++)map[y][x + i] = "_";}
        for (int i = 1; i < MapEnum.HEXSIDESHORT.amount * 2 + 1; i++) {
            String chap,rast;
            int tmp;
            if(i <= MapEnum.HEXSIDESHORT.amount){
                tmp = Math.abs(MapEnum.HEXSIDESHORT.amount - i);
                chap = "/";
                rast = "\\";
            }
            else{
                tmp = Math.abs(MapEnum.HEXSIDESHORT.amount - i) - 1;
                chap = "\\";
                rast = "/";
            }
            map[y + i][x + tmp] = chap;map[y + i][x + tmp + 1] = backgroundColor + " ";map[y + i][x + MapEnum.HEXSIDELONG.amount + MapEnum.HEXSIDESHORT.amount * 2 - tmp - 2] = " " + reset;map[y + i][x + MapEnum.HEXSIDELONG.amount + MapEnum.HEXSIDESHORT.amount * 2 - tmp - 1] = rast;
        }
        int textDistance = MapEnum.HEXSIDESHORT.amount * 2 / 5;
        map[y + MapEnum.HEXSIDESHORT.amount * 2][x + MapEnum.HEXSIDESHORT.amount - 1]= "\\";map[y + MapEnum.HEXSIDESHORT.amount * 2][x + MapEnum.HEXSIDESHORT.amount] = backgroundColor + "_";for (int i = MapEnum.HEXSIDESHORT.amount + 1; i < MapEnum.HEXSIDELONG.amount + MapEnum.HEXSIDESHORT.amount  - 1; i++) {map[y + MapEnum.HEXSIDESHORT.amount * 2][x + i] = "_";}map[y + MapEnum.HEXSIDESHORT.amount * 2][x + MapEnum.HEXSIDELONG.amount + MapEnum.HEXSIDESHORT.amount - 1] = "_" + reset;map[y + MapEnum.HEXSIDESHORT.amount * 2][x + MapEnum.HEXSIDELONG.amount + MapEnum.HEXSIDESHORT.amount] = "/";
        if(tile.getTileVisibility() != TileVisibility.FOGOFWAR){
            if(tile.getCivilization() != null)
                map[y + textDistance][x + (MapEnum.HEXSIDESHORT.amount * 2 + MapEnum.HEXSIDELONG.amount) / 2] = assignCharToCivilization(tile.getCivilization());
            textDistance += MapEnum.HEXSIDESHORT.amount * 2 / 5;
                if(tile.getFeature() != null){
                nullify(map, x + ((MapEnum.HEXSIDESHORT.amount + MapEnum.HEXSIDESHORT.amount * 2) / 2) -  tile.getFeature().getFeatureType().name().length() / 2 + 1,tile.getFeature().getFeatureType().name().length(), y + textDistance);
                map[y + textDistance][x + ((MapEnum.HEXSIDESHORT.amount + MapEnum.HEXSIDESHORT.amount * 2) / 2) -  tile.getFeature().getFeatureType().name().length() / 2 + 1] = tile.getFeature().getFeatureType().name();
            }
            textDistance += MapEnum.HEXSIDESHORT.amount * 2 / 5;
            nullify(map, x + ((MapEnum.HEXSIDESHORT.amount + MapEnum.HEXSIDESHORT.amount * 2) / 2) -  tile.getTerrain().name().length() / 2 + 1,tile.getTerrain().name().length(), y + textDistance);
            map[y + textDistance][x + ((MapEnum.HEXSIDESHORT.amount + MapEnum.HEXSIDESHORT.amount * 2) / 2) -  tile.getTerrain().name().length() / 2 + 1] = tile.getTerrain().name();
            if(tile.getResource() != null){
                textDistance += MapEnum.HEXSIDESHORT.amount * 2 / 5;
            nullify(map, x + ((MapEnum.HEXSIDESHORT.amount + MapEnum.HEXSIDESHORT.amount * 2) / 2) -  tile.getResource().getResourceType().name().length() / 2 + 1,tile.getResource().getResourceType().name().length(), y + textDistance);
            map[y + textDistance][x + ((MapEnum.HEXSIDESHORT.amount + MapEnum.HEXSIDESHORT.amount * 2) / 2) -  tile.getResource().getResourceType().name().length() / 2 + 1] = tile.getResource().getResourceType().name();
            }
        }
    }
    
    public ArrayList<Tile> generateMap(int Civilization){
        Tile currentTile = new Tile();
        Random randomSeed = new Random();
        int MapSeed = randomSeed.nextInt(1000);
        Random random = new Random(MapSeed);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Tile tile = new Tile();
                TerrainType terrainType = getARandomTerrainType();
                tile.setTerrain(terrainType);
                if(!tile.checkType(TerrainType.Snow) && !tile.checkType(TerrainType.Mountain) && !tile.checkType(TerrainType.Ocean) && !tile.checkType(TerrainType.Tundra)){
                    Resource resource = getARandomResource();
                    Feature feature = getARandomFeature();
                    int test = random.nextInt(10);
                    if (test < 3) tile.setFeature(feature);
                    else if (test > 7) tile.setResource(resource);
                    else {
                        tile.setFeature(feature);
                        tile.setResource(resource);
                    }
                } else if(tile.checkType(TerrainType.Snow) || tile.checkType(TerrainType.Tundra)){
                    Resource resource =getARandomResource();
                    tile.setResource(resource);
                }
                tile.setTileVisibility(TileVisibility.FOGOFWAR);
                this.tiles.add(tile);
            }
        }
        return this.tiles;
    }
    
    public Resource getARandomResource(){
        int pickResource = new Random().nextInt(ResourceType.values().length);
        return new Resource(ResourceType.values()[pickResource]);
    }
    
    public TerrainType getARandomTerrainType(){
        int pickTerrain = new Random().nextInt(TerrainType.values().length);
        return TerrainType.values()[pickTerrain];
    }
    
    public Feature getARandomFeature(){
        int pickFeature = new Random().nextInt(FeatureType.values().length);
        return new Feature(FeatureType.values()[pickFeature]);
    }
    public Tile getTerrain(int x , int y){
        return null;
    }

    public String changeTurn(){
        return "";
    }

    public String move(Matcher matcher){
        return "";
    }

    public String moveUnit(){
        return "";
    }

    public String attack(Tile terrain){
        return "";
    }

    public String selectUnit(){
        return "";
    }

    public String assignTerrainState(Tile terrain){
        return "";
    }

    public String sleep(){
        return "";
    }
    public void updateData(){

    }

    public void updateCurrentBuildingProject(){

    }

    public void updateCurrentTechnologyProject(){

    }
}
