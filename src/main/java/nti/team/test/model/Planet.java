package nti.team.test.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Planet")
@Getter
@Setter
@RequiredArgsConstructor
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lord_id")
    private Lord lord;
}
