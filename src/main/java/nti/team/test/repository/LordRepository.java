package nti.team.test.repository;

import nti.team.test.model.Lord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface LordRepository extends JpaRepository<Lord, Long> {
    Lord findByName(String name);
    ArrayList<Lord> findLordsByPlanetsNull();
    ArrayList<Lord> findFirst10ByOrderByAgeAsc();
}