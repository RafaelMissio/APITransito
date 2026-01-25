package br.com.missio.Transito_API.api.controller;

import br.com.missio.Transito_API.domain.exception.DomainException;
import br.com.missio.Transito_API.domain.model.Vehicle;
import br.com.missio.Transito_API.domain.repository.VehicleRepository;
import br.com.missio.Transito_API.domain.service.VehicleRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {

    private VehicleRepository vehicleRepository;
    private VehicleRegistrationService vehicleRegistrationService;

    @GetMapping
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable long id) {
        return vehicleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle registerVehicle(@RequestBody Vehicle vehicle) {
        return vehicleRegistrationService.register(vehicle);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> capture(DomainException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
