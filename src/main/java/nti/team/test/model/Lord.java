package nti.team.test.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Lord")
@Getter
@Setter
@RequiredArgsConstructor
public class Lord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private int age;

    @OneToMany(mappedBy = "lord", fetch = FetchType.EAGER)
    private Set<Planet> planets = new HashSet<>();

    public void addPlanet(Planet planet){
        planets.add(planet);
        planet.setLord(this);
    }

    public void removePlanet(Planet planet){
        planets.remove(planet);
        planet.setLord(null);
    }
}