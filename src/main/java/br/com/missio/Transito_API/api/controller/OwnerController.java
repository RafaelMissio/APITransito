package br.com.missio.Transito_API.api.controller;

import br.com.missio.Transito_API.domain.model.Owner;
import br.com.missio.Transito_API.domain.repository.OwnerReposiotry;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

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
    public Owner save(@RequestBody Owner owner){
        return ownerReposiotry.save(owner);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<Owner> update(@PathVariable Long ownerId, @RequestBody Owner owner){

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

        ownerReposiotry.deleteById(ownerId);
        return ResponseEntity.noContent().build();


    }

}
