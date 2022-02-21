package nti.team.test;

import nti.team.test.dto.LordDto;
import nti.team.test.dto.NameLordAndPlanetDto;
import nti.team.test.dto.PlanetDto;
import nti.team.test.model.Lord;
import nti.team.test.model.Planet;
import nti.team.test.repository.LordRepository;
import nti.team.test.repository.PlanetRepository;
import nti.team.test.service.LordService;
import nti.team.test.service.PlanetService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;


@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TestApplicationTests {

	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer()
			.withUsername("test")
			.withPassword("password")
			.withDatabaseName("test");

	@Autowired
	private LordService lordService;
	@Autowired
	private PlanetService planetService;
	@Autowired
	private PlanetRepository planetRepository;
	@Autowired
	private LordRepository lordRepository;

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

	@Test
	@Order(1)
	void addPlanet() {
		PlanetDto dto = new PlanetDto();
		dto.setName("testPlanet");

		planetService.addPlanet(dto);

		Planet planet = planetRepository.findAll().get(0);
		Assertions.assertEquals(dto.getName(), planet.getName(), "Error in add planet!");

		System.out.println("Planet added successfully!");
	}

	@Test
	@Order(2)
	void addLord() {
		LordDto lord = new LordDto();
		lord.setName("testLord");
		lord.setAge(22);

		lordService.addLord(lord);

		Lord lord1 = lordRepository.findAll().get(0);
		Assertions.assertEquals(lord.getName(), lord1.getName(), "Error in add lord!");

		System.out.println("Lord added successfully!");
	}

	@Test
	@Order(3)
	void setLordToPlanet() {
		NameLordAndPlanetDto dto = new NameLordAndPlanetDto();
		dto.setLordName("testLord");
		dto.setPlanetName("testPlanet");

		lordService.addPlanetToLord(dto);

		Lord lord = lordRepository.findAll().get(0);
		Planet planet = planetRepository.findAll().get(0);

		Assertions.assertEquals(lord.getName(), planet.getLord().getName(), "Error in set planet to lord!");

		System.out.println("Lord get planet successfully!");
	}

	@Test
	@Order(4)
	void deletePlanet() {
		PlanetDto dto = new PlanetDto();
		dto.setName("testPlanet");

		planetService.deletePlanet(dto);

		Assertions.assertTrue(planetRepository.findAll().isEmpty());

		System.out.println("Planet deleted successfully!");
	}

	// Заполнение базы данных тестовыми данными для тестов (масло масленное)
	@Test
	@Order(4)
	void fillLordsBd() {
		LordDto lordDto = new LordDto();
		PlanetDto planetDto = new PlanetDto();

		lordDto.setName("Lord 1");
		lordDto.setAge(18);
		planetDto.setName("Planet 1");

		NameLordAndPlanetDto lordAndPlanetDto = new NameLordAndPlanetDto();
		lordAndPlanetDto.setLordName(lordDto.getName());
		lordAndPlanetDto.setPlanetName(planetDto.getName());

		lordService.addLord(lordDto);
		planetService.addPlanet(planetDto);
		lordService.addPlanetToLord(lordAndPlanetDto);

		planetDto.setName("Planet 2");
		lordDto.setName("Lord 2");
		lordAndPlanetDto.setLordName(lordDto.getName());
		lordAndPlanetDto.setPlanetName(planetDto.getName());
		lordService.addLord(lordDto);
		planetService.addPlanet(planetDto);
		lordService.addPlanetToLord(lordAndPlanetDto);

		lordDto.setName("Lord 1 without planet");
		lordService.addLord(lordDto);

		planetDto.setName("Planet 3");
		lordDto.setName("Lord 3");
		lordDto.setAge(22);
		lordAndPlanetDto.setLordName(lordDto.getName());
		lordAndPlanetDto.setPlanetName(planetDto.getName());
		lordService.addLord(lordDto);
		planetService.addPlanet(planetDto);
		lordService.addPlanetToLord(lordAndPlanetDto);

		lordDto.setName("Lord 12 without planet");
		lordDto.setAge(18);
		lordService.addLord(lordDto);

		planetDto.setName("Planet 4");
		lordDto.setName("Lord 4");
		lordAndPlanetDto.setLordName(lordDto.getName());
		lordAndPlanetDto.setPlanetName(planetDto.getName());
		lordService.addLord(lordDto);
		planetService.addPlanet(planetDto);
		lordService.addPlanetToLord(lordAndPlanetDto);

		lordDto.setName("Lord 3 without planet");
		lordService.addLord(lordDto);

		lordDto.setName("Lord 4 without planet");
		lordService.addLord(lordDto);

		lordDto.setName("Lord 5 without planet");
		lordService.addLord(lordDto);

		lordDto.setName("Lord 6 without planet");
		lordService.addLord(lordDto);

		lordDto.setName("Lord 7 without planet");
		lordService.addLord(lordDto);
	}

	@Test
	@Order(5)
	void getAllJoblessLords() {

		// 7 Безработных лордов и +1 первоначльный тестовый лорд, планета которого была уничтожена
		int countOfJoblessLords = 8;

		int count = 0;

		List<LordDto> lords = lordService.getAllJoblessLords();
		for (LordDto dto : lords) {
			if (dto.getPlanets().isEmpty()) {
				count++;
			}
		}

		Assertions.assertEquals(count, countOfJoblessLords, "Test failed");

		System.out.println("Jobless lords got successfully!");
	}

	@Test
	@Order(6)
	void getTopTenYoungLords() {

		List<LordDto> lords = lordService.getTo10YongLords();

		for (LordDto lord : lords) {
			// Всего 12 лордов, у 10 из них 18 лет, остальные 22 года.
			// В базе данных они расположены без порядка, в данном тесте я проверяют только тех, у кого 18 лет, т.к их всего 10
			Assertions.assertEquals(18, lord.getAge());
		}

		System.out.println("Test passed!");
	}

}
