package br.com.missio.Transito_API.api.controller;

import br.com.missio.Transito_API.domain.model.Owner;
import br.com.missio.Transito_API.domain.repository.OwnerReposiotry;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerReposiotry ownerReposiotry;

    @GetMapping
    public List<Owner> getAllOwners(){
        return ownerReposiotry.findByNameContaining("el");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> buscarPorId(@PathVariable Long id){
       return ownerReposiotry.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
