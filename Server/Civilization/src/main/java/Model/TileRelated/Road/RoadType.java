package Model.TileRelated.Road;
import Model.Technology.TechnologyType;

public enum RoadType {
    Road(0.6,TechnologyType.TheWheel),
    RailWay(0.8,TechnologyType.Railroad);

    public final double mpReduction;
    public final TechnologyType PrerequisiteTechnology;

    RoadType(double mpReduction , TechnologyType prerequisiteTechnology ){
        this.mpReduction = mpReduction;
        this.PrerequisiteTechnology = prerequisiteTechnology;
    }
}
