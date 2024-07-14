package pl.mytimeplan.mtp_app.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import pl.mytimeplan.mtp_app.model.Star;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class StarRepositoryTest {

    private final StarRepository starRepository = new StarRepository();
    private final ConcurrentMap<Integer, Star> stars = new ConcurrentHashMap<>(
            Map.of(1, new Star("alfa", 1000)));


    @Test
    public void create() {
        Star expectedStar = new Star("alfa", 1000);

        starRepository.create(new Star("alfa", 1000));
        ConcurrentMap<Integer, Star> createdStar = starRepository.getStars();

        Star actualStar = new Star(createdStar.get(1).getName(), createdStar.get(1).getDistance());

        assertEquals(expectedStar, actualStar);
    }

    @Test
    public void findById() {
        Star expectedStar = new Star("alfa", 1000);

        starRepository.setStars(stars);

        Star actualStar = starRepository.findById(1);

        assertEquals(expectedStar, actualStar);
    }

    @Test
    public void update() {
        ConcurrentMap<Integer, Star> updatedStars = new ConcurrentHashMap<>(
                Map.of(1, new Star("alfa centauri", 1000)));

        starRepository.setStars(stars);

        starRepository.update(1, updatedStars.get(1));

        assertEquals(updatedStars, stars);
    }

    @Test
    public void updateShouldThrowException() {
        // updating star that doesn't exist should throw exception

        ConcurrentMap<Integer, Star> updatedStars = new ConcurrentHashMap<>(
                Map.of(1, new Star("alfa centauri", 1000)));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> starRepository.update(1, updatedStars.get(1)));
        assertEquals("Star with given id doesn't exist", exception.getMessage());
    }

    @Test()
    public void delete() {
        starRepository.setStars(stars);

        starRepository.delete(1);

        assertThat(starRepository.getStars()).isEmpty();
    }
}