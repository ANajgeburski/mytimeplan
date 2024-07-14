package pl.mytimeplan.mtp_app.model;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Star{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return distance == star.distance && Objects.equals(name, star.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, distance);
    }
}
