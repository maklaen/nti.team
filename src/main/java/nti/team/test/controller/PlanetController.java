package nti.team.test.controller;

import lombok.AllArgsConstructor;
import nti.team.test.dto.PlanetDto;
import nti.team.test.model.Planet;
import nti.team.test.service.PlanetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planet")
@AllArgsConstructor
public class PlanetController {

    private final PlanetService planetService;

    @GetMapping("/")
    public List<PlanetDto> allPlanets() {
        return planetService.allPlanets();
    }

    @PostMapping("/")
    public String addPlanet(@RequestBody PlanetDto dto) {
        planetService.addPlanet(dto);
        return "Planet " + dto.getName() + " added successfully";
    }

    @DeleteMapping("/")
    public String deletePlanet(@RequestBody PlanetDto dto) {
        planetService.deletePlanet(dto);
        return "Planet " + dto.getName() + " deleted successfully";
    }
}
