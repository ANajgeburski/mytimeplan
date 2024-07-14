package pl.mytimeplan.mtp_app.controller;

import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.mytimeplan.mtp_app.exceptionhandler.StarNotFoundException;
import pl.mytimeplan.mtp_app.model.Star;
import pl.mytimeplan.mtp_app.service.StarService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
public class StarController {

    private final StarService service;

    public StarController(StarService service) {
        this.service = service;
    }

    @GetMapping("/api/stars/{id}")
    public Optional<Star> findById(@PathVariable("id") @Min(1) int id) {
        return Optional.ofNullable(service.findById(id).orElseThrow(() -> new StarNotFoundException(id)));
    }

    @PostMapping("/api/stars")
    public int create(@RequestBody Star star) {
        return service.createStar(star);
    }

    @PutMapping("/api/stars/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Star star) {
        service.updateStar(id, star);
    }

    @DeleteMapping("/api/stars/{id}")
    public void delete(@PathVariable("id") int id) {
        service.deleteStar(id);
    }

    @GetMapping("/api/stars/find-closest-stars/{size}")
    public List<Star> findClosestStars(@RequestBody List<Star> stars, @PathVariable("size") int size) {
        return service.findClosestStars(stars, size);
    }

    @GetMapping("/api/stars/get-number-of-stars-by-distances")
    public Map<Long, Integer> getNumberOfStarsByDistances(@RequestBody List<Star> stars) {
        return service.getNumberOfStarsByDistances(stars);
    }

    @GetMapping("/api/stars/get-unique-stars")
    public Collection<Star> getUniqueStars(@RequestBody Collection<Star> stars) {
        return service.getUniqueStars(stars);
    }

}
