package nti.team.test.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class LordDto {
    private String name;
    private int age;

    private Set<String> planets = new HashSet<>();
}
