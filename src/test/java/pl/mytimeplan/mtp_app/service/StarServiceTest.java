package pl.mytimeplan.mtp_app.service;

import org.junit.jupiter.api.Test;
import pl.mytimeplan.mtp_app.model.Star;
import pl.mytimeplan.mtp_app.repository.StarRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StarServiceTest {

    private final StarService service = new StarService(new StarRepository());


    @Test
    public void findClosestStars() {
        List<Star> stars = new ArrayList<>(List.of(
                new Star("proxima centauri", 4),
                new Star("alfa centauri", 5),
                new Star("barnards star", 8),
                new Star("lalande", 9),
                new Star("sirius", 10),
                new Star("ross", 13)));

        List<Star> expectedStars = new ArrayList<>(List.of(
                new Star("proxima centauri", 4),
                new Star("alfa centauri", 5),
                new Star("barnards star", 8)));

        List<Star> closestToSun = service.findClosestStars(stars, 3);

        assertEquals(expectedStars, closestToSun);
    }

    @Test
    public void getNumberOfStarsByDistances() {
        List<Star> stars = new ArrayList<>(List.of(
                new Star("cygni", 11),
                new Star("procyon", 11),
                new Star("barnards star", 16),
                new Star("alfa star", 16),
                new Star("lalande", 9),
                new Star("groombridge", 11),
                new Star("ross", 13)));

        Map<Long, Integer> actualStarsWithSameDistance = service.getNumberOfStarsByDistances(stars);

        Map<Long, Integer> expectedStarsWithSameDistance = new HashMap<>(Map.of(
                16L, 2,
                9L, 1,
                11L, 3,
                13L, 1));

        assertEquals(expectedStarsWithSameDistance, actualStarsWithSameDistance);
    }

    @Test
    public void getNumberOfStarsByDistancesIsSorted() {
        List<Star> stars = new ArrayList<>(List.of(
                new Star("cygni", 11),
                new Star("procyon", 11),
                new Star("barnards star", 16),
                new Star("alfa star", 16),
                new Star("lalande", 9),
                new Star("groombridge", 11),
                new Star("ross", 13)));

        List<Long> expectedSortedDistances = new ArrayList<>(List.of(
                9L,
                11L,
                13L,
                16L));

        Map<Long, Integer> actualStarsWithSameDistance = service.getNumberOfStarsByDistances(stars);

        List<Long> actualSortedDistances = new ArrayList<>(actualStarsWithSameDistance.keySet());

        assertEquals(expectedSortedDistances, actualSortedDistances);
    }

    @Test
    public void getUniqueStars() {

        List<Star> stars = new ArrayList<>(List.of(
                new Star("proxima centauri", 4),
                new Star("alfa centauri", 5),
                new Star("alfa centauri", 8),
                new Star("lalande", 9),
                new Star("sirius", 10),
                new Star("lalande", 13)));

        List<Star> expectedUniqueStars = new ArrayList<>(List.of(
                new Star("proxima centauri", 4),
                new Star("sirius", 10)));

        Collection<Star> uniqueStars = service.getUniqueStars(stars);

        assertEquals(expectedUniqueStars, uniqueStars);
    }
}