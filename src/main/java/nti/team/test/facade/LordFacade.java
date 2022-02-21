package nti.team.test.facade;

import nti.team.test.dto.LordDto;
import nti.team.test.model.Lord;
import nti.team.test.model.Planet;
import org.springframework.stereotype.Component;

@Component
public class LordFacade {

    public LordDto lordToDto(Lord lord) {
        LordDto dto = new LordDto();
        dto.setAge(lord.getAge());
        dto.setName(lord.getName());

        for (Planet planet : lord.getPlanets()) {
            dto.getPlanets().add(planet.getName());
        }

        return dto;
    }
}
