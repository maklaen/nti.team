package nti.team.test.service;

import lombok.AllArgsConstructor;
import nti.team.test.dto.PlanetDto;
import nti.team.test.facade.PlanetFacade;
import nti.team.test.mapper.PlanetMapper;
import nti.team.test.model.Lord;
import nti.team.test.model.Planet;
import nti.team.test.repository.PlanetRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlanetService {

    private final PlanetRepository planetRepository;
    private final PlanetMapper planetMapper;
    private final PlanetFacade planetFacade;

    public List<PlanetDto> allPlanets() {
        List<Planet> planetList = planetRepository.findAll();
        List<PlanetDto> dtoList = new ArrayList<>();
        for (Planet planet : planetList) {
            dtoList.add(planetFacade.planetToDto(planet));
        }
        return dtoList;
    }

    public void addPlanet(PlanetDto dto) {
        if (dto.getName() == null) {
            throw new IllegalArgumentException("The name should not be empty!");
        }
        if (planetRepository.findByName(dto.getName()) != null) {
            throw new IllegalArgumentException("Planet already exist!");
        }

        Planet planet = planetMapper.dtoToPlanet(dto);
        planetRepository.save(planet);
    }

    public void deletePlanet(PlanetDto dto) {
        Planet planet = planetRepository.findByName(dto.getName());
        if (planet != null) {
            if (planet.getLord() != null) {
                Lord lord = planet.getLord();
                lord.removePlanet(planet);
            }
            planetRepository.delete(planet);
        } else {
            throw new EntityNotFoundException("Planet not found!");
        }
    }
}
