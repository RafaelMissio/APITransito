package br.com.missio.Transito_API.domain.repository;

import br.com.missio.Transito_API.domain.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


    Optional<Vehicle> findByPlate(String plate);
}
