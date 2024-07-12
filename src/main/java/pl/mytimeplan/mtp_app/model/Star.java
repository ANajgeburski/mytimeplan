package pl.mytimeplan.mtp_app.model;

public class Star {

    private final String name;
    private final long distance;

    public String getName() {
        return name;
    }

    public long getDistance() {
        return distance;
    }

    public Star(String name, long distance) {
        this.name = name;
        this.distance = distance;
    }
}
