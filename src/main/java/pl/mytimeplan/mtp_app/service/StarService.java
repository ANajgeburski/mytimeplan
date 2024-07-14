package pl.mytimeplan.mtp_app.service;

import org.springframework.stereotype.Service;
import pl.mytimeplan.mtp_app.exceptionhandler.NoMatchingStarsException;
import pl.mytimeplan.mtp_app.model.Star;
import pl.mytimeplan.mtp_app.repository.StarRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StarService {

    private final StarRepository starRepository;

    public StarService(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    public Optional<Star> findById(int id) {
        return Optional.ofNullable(starRepository.findById(id));
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
        List<Star> closestToSun;

        if (stars.isEmpty()) {
            throw new NoMatchingStarsException();
        } else {
            closestToSun = stars.stream()
                    .sorted(Comparator.comparingLong(Star::getDistance))
                    .toList()
                    .subList(0, size);
        }
        return closestToSun;
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

        // Sort by keys in ascending order and put it in LinkedHashMap
        // that guarantees insertion order. This gives us stars closest to the Sun first
        LinkedHashMap<Long, Integer> sortedStars = starsByDistances.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        if (sortedStars.isEmpty()) {
            throw new NoMatchingStarsException();
        } else {
            return sortedStars;
        }
    }

    public Collection<Star> getUniqueStars(Collection<Star> stars) {
        // Collect names of all the stars
        List<String> starNames = stars.stream()
                .map(Star::getName)
                .collect(Collectors.toList());

        // Remove names that are not unique
        starNames.removeIf(name -> Collections.frequency(starNames, name) > 1);

        // Return stars with unique names
        List<Star> uniqueStars = stars.stream()
                .filter(star -> starNames.contains(star.getName()))
                .collect(Collectors.toList());

        if (uniqueStars.isEmpty()) {
            throw new NoMatchingStarsException();
        } else {
            return uniqueStars;
        }
    }
}
