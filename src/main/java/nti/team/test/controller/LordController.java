package nti.team.test.controller;

import lombok.AllArgsConstructor;
import nti.team.test.dto.LordDto;
import nti.team.test.dto.NameLordAndPlanetDto;
import nti.team.test.model.Lord;
import nti.team.test.service.LordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lord")
@AllArgsConstructor
public class LordController {

    private final LordService lordService;

    @GetMapping("/")
    public List<LordDto> allLords() {
        return lordService.allLords();
    }

    @PostMapping("/")
    public String addLord(@RequestBody LordDto dto) {
        lordService.addLord(dto);
        return "Lord " + dto.getName() + " added";
    }

    @PostMapping("/planet")
    public String addPlanetToLord(@RequestBody NameLordAndPlanetDto dto) {
        lordService.addPlanetToLord(dto);
        return "The lord " + dto.getLordName() + " got the planet - " + dto.getPlanetName();
    }

    @GetMapping("/jobless")
    public List<LordDto> getAllJoblessLords() {
        return lordService.getAllJoblessLords();
    }

    @GetMapping("/topTenYoungest")
    public List<LordDto> getTopTenYoungest() {
        return lordService.getTo10YongLords();
    }

}
