package Model.TileRelated.Improvement;
import Model.TileRelated.Tile.Tile;
import Model.Units.NonCombat.Worker;
import com.google.gson.annotations.Expose;

public class Improvement {
    @Expose
    private ImprovementType improvementType;
    @Expose
    private int daysToComplete;
    @Expose
    private Worker worker;
    private Tile tile;
    @Expose
    private boolean ruined = false;
    public Improvement(ImprovementType improvementType) {
        this.setImprovementType(improvementType);
    }
    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }


    public boolean isRuined() {
        return ruined;
    }

    public void setRuined(boolean ruined) {
        this.ruined = ruined;
    }


    public int getDaysToComplete() {
        return daysToComplete;
    }

    public void changeDaysToComplete(int daysToComplete) {
        this.daysToComplete += daysToComplete;
    }

    public ImprovementType getImprovementType() {
        return improvementType;
    }

    public void setImprovementType(ImprovementType improvementType) {
        this.improvementType = improvementType;
    }
}
