package br.com.missio.Transito_API.api.controller;

import br.com.missio.Transito_API.domain.exception.DomainException;
import br.com.missio.Transito_API.domain.model.Owner;
import br.com.missio.Transito_API.domain.repository.OwnerReposiotry;
import br.com.missio.Transito_API.domain.service.RegistroPropietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final RegistroPropietarioService registroPropietarioService;
    private final OwnerReposiotry ownerReposiotry;

    @GetMapping
    public List<Owner> getAllOwners(){
        return ownerReposiotry.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> buscarPorId(@PathVariable Long id){
       return ownerReposiotry.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Owner save(@Valid @RequestBody Owner owner){
        return registroPropietarioService.save(owner);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<Owner> update(@PathVariable Long ownerId,
                                        @Valid @RequestBody Owner owner){

        if(!ownerReposiotry.existsById(ownerId)){
            return ResponseEntity.notFound().build();
        }

        owner.setId(ownerId);
        Owner ownerUpdate = ownerReposiotry.save(owner);

        return ResponseEntity.ok(ownerUpdate);
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Void> delete(@PathVariable Long ownerId){

        if(!ownerReposiotry.existsById(ownerId)){
            return ResponseEntity.notFound().build();
        }
        registroPropietarioService.delete(ownerId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> capture(DomainException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
