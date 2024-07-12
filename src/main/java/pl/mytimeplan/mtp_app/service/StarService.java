package pl.mytimeplan.mtp_app.service;

import org.springframework.stereotype.Service;
import pl.mytimeplan.mtp_app.model.Star;
import pl.mytimeplan.mtp_app.repository.StarRepository;

import java.util.*;

@Service
public class StarService {

    private final StarRepository starRepository;

    public StarService(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    public Star findById(int id) {
        return starRepository.findById(id);
    }

    public int createStar(Star star) {
        return starRepository.create(star);
    }

    public void updateStar(int id, Star star) {
        starRepository.update(id, star);
    }

    public void deleteStar(int id) {
        starRepository.delete(id);
    }

    public List<Star> findClosestStars(List<Star> stars, int size) {
        List<Star> closestToSun = new ArrayList<>();
        return closestToSun = stars.stream()
                .sorted(Comparator.comparingLong(Star::getDistance))
                .toList()
                .subList(0, size);
    }

    public Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) {
        Map<Long, Integer> starsByDistances = new HashMap<>();
        for (Star star : stars) {
            if (starsByDistances.containsKey(star.getDistance())) {
                int count = starsByDistances.get(star.getDistance());
                starsByDistances.put(star.getDistance(), count + 1);
            } else {
                starsByDistances.put(star.getDistance(), 1);
            }
        }
        return starsByDistances;
    }

    public Collection<Star> getUniqueStars(Collection<Star> stars) {
        Map<String, Star> unique = new HashMap<>();
        for (Star star : stars) {
            unique.put(star.getName(), star);
        }
        return unique.values();
    }
}
