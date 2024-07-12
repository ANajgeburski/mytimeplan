package pl.mytimeplan.mtp_app.repository;

import org.springframework.stereotype.Repository;
import pl.mytimeplan.mtp_app.model.Star;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class StarRepository {
    private static final AtomicInteger ID = new AtomicInteger(0);
    private final ConcurrentMap<Integer, Star> stars = new ConcurrentHashMap<>();

    public Star findById(int id) {
        return stars.get(id);
    }

    public int create(Star star) {
        int id = ID.incrementAndGet();
        stars.put(id, star);
        return id;
    }

    public void update(int id, Star star) {
        if (stars.containsKey(id)) {
            stars.put(id, star);
        } else {
            throw new IllegalArgumentException("Star with given id doesn't exist");
        }
    }

    public void delete(int id) {
        stars.remove(id);
    }
}
