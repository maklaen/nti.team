package nti.team.test.mapper;

import nti.team.test.dto.PlanetDto;
import nti.team.test.model.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetMapper {

    public Planet dtoToPlanet(PlanetDto dto) {
        Planet planet = new Planet();
        if (dto.getName().length() < 2) {
            throw new IllegalArgumentException("Name of planet must be more than 1 character");
        }
        planet.setName(dto.getName());

        return planet;
    }

    public PlanetDto planetToDto(Planet planet) {
        PlanetDto dto = new PlanetDto();
        dto.setName(planet.getName());
        dto.setLordName(planet.getLord() == null ? "Lack of lord" : planet.getLord().getName());

        return dto;
    }
}
