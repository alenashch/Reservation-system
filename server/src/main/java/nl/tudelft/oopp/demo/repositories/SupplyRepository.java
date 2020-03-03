package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
    Optional<Supply> findByBuildingAndName(long building, String name);
    Optional<List<Supply>>findByBuilding(long building);
}