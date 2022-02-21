package nti.team.test.mapper;

import nti.team.test.dto.LordDto;
import nti.team.test.model.Lord;
import nti.team.test.model.Planet;
import org.springframework.stereotype.Component;

@Component
public class LordMapper {

    public Lord dtoToLord(LordDto dto) {
        Lord lord = new Lord();
        if (dto.getName().length() < 2) {
            throw new IllegalArgumentException("Name of lord must be more than 1 character");
        }
        if (dto.getAge() < 18) {
            throw new IllegalArgumentException("Lord too young. Age must be above 18");
        }
        lord.setName(dto.getName());
        lord.setAge(dto.getAge());

        return lord;
    }

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
