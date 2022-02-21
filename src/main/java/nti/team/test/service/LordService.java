package nti.team.test.service;

import lombok.AllArgsConstructor;
import nti.team.test.dto.LordDto;
import nti.team.test.dto.NameLordAndPlanetDto;
import nti.team.test.facade.LordFacade;
import nti.team.test.mapper.LordMapper;
import nti.team.test.model.Lord;
import nti.team.test.model.Planet;
import nti.team.test.repository.LordRepository;
import nti.team.test.repository.PlanetRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class LordService {

    private final LordRepository lordRepository;
    private final PlanetRepository planetRepository;
    private final LordMapper lordMapper;
    private final LordFacade lordFacade;

    public List<LordDto> allLords() {
        List<Lord> lordList = lordRepository.findAll();
        List<LordDto> dtoList = new ArrayList<>();
        for (Lord lord : lordList) {
            dtoList.add(lordFacade.lordToDto(lord));
        }
        return dtoList;
    }

    public void addLord(LordDto dto) {
        if (dto.getName() == null || dto.getAge() < 18) {
            throw new IllegalArgumentException("The name should not be empty and age should be above 18!");
        }
        if (lordRepository.findByName(dto.getName()) != null) {
            throw new IllegalArgumentException("Lord already exist!");
        }

        Lord lord = lordMapper.dtoToLord(dto);
        lordRepository.save(lord);
    }

    public void addPlanetToLord(NameLordAndPlanetDto dto) {
        Lord lord = lordRepository.findByName(dto.getLordName());
        Planet planet = planetRepository.findByName(dto.getPlanetName());

        if (lord == null || planet == null) {
            throw new EntityNotFoundException("Lord or planet not found!");
        }

        Set<Planet> planets = lord.getPlanets();
        int planetSizeBefore = planets.size();
        planets.add(planet);
        if (planetSizeBefore == planets.size()) {
            throw new EntityExistsException("Lord already has that planet!");
        } else if (planet.getLord() != null) {
            throw new EntityExistsException("Planet already has the lord!");
        }
        lord.addPlanet(planet);
        lordRepository.save(lord);
        planetRepository.save(planet);

    }

    public List<LordDto> getAllJoblessLords() {
        List<Lord> lordList = lordRepository.findLordsByPlanetsNull();
        List<LordDto> dtoList = new ArrayList<>();
        for (Lord lord : lordList) {
            dtoList.add(lordFacade.lordToDto(lord));
        }
        return dtoList;
    }

    public List<LordDto> getTo10YongLords() {
        List<Lord> lordList = lordRepository.findFirst10ByOrderByAgeAsc();
        List<LordDto> dtoList = new ArrayList<>();
        for (Lord lord : lordList) {
            dtoList.add(lordFacade.lordToDto(lord));
        }
        return dtoList;
    }

}
