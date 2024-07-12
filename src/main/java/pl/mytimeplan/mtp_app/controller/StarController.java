package pl.mytimeplan.mtp_app.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;
import pl.mytimeplan.mtp_app.model.Star;
import pl.mytimeplan.mtp_app.service.StarService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class StarController {

    private final StarService service;

    public StarController(StarService service) {
        this.service = service;
    }

    @GetMapping("/api/stars{id}")
    public Star findById(@PathParam("id") int id) {
        return service.findById(id);
    }

    @PostMapping("/api/stars")
    public int create(@RequestParam("star") Star star) {
        return service.createStar(star);
    }

    @PutMapping("/api/stars{id}")
    public void update(@PathParam("id") int id, @RequestParam("star") Star star) {
        service.updateStar(id, star);
    }

    @DeleteMapping("/api/stars{id}")
    public void delete(@PathParam("id") int id) {
        service.deleteStar(id);
    }

    @GetMapping("/api/stars/find-closest-stars")
    public List<Star> findClosestStars(@RequestParam("stars") List<Star> stars, @RequestParam("size") int size) {
        return service.findClosestStars(stars, size);
    }

    @GetMapping("/api/stars/get-number-of-stars-by-distances")
    public Map<Long, Integer> getNumberOfStarsByDistances(@RequestParam("stars") List<Star> stars) {
        return service.getNumberOfStarsByDistances(stars);
    }

    @GetMapping("/api/stars/get-unique-stars")
    public Collection<Star> getUniqueStars(@RequestParam("stars") Collection<Star> stars) {
        return service.getUniqueStars(stars);
    }

}
