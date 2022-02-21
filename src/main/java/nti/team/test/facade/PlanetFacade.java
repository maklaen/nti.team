package nti.team.test.facade;

import nti.team.test.dto.PlanetDto;
import nti.team.test.model.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetFacade {

    public PlanetDto planetToDto(Planet planet) {
        PlanetDto dto = new PlanetDto();
        dto.setName(planet.getName());
        dto.setLordName(planet.getLord() == null ? "Lack of lord" : planet.getLord().getName());

        return dto;
    }
}

