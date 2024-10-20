package id.co.mii.serverapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import id.co.mii.serverapp.models.Region;

@Repository
// public interface RegionRepository extends JpaRepository<Region, Integer> {}
public interface RegionRepository extends JpaRepository<Region, Integer> {

  // JPQL
  @Query("SELECT r FROM Region r WHERE r.name = ?1")
  public List<Region> searchByName(String name);

  // Native
  @Query(value = "SELECT * FROM tb_region WHERE region_name = :name", nativeQuery = true)
  public List<Region> searchByNameNative(String name);

  // Query Method
  public boolean existsByName(String name);

}