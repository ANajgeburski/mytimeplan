package pl.mytimeplan.mtp_app.model;

public class Star {

    private String name;
    private long distance;

    public Star(String name, long distance) {
        this.name = name;
        this.distance = distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getDistance() {
        return distance;
    }
}
