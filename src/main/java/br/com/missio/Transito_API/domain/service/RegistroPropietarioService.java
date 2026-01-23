package br.com.missio.Transito_API.domain.service;

import br.com.missio.Transito_API.domain.exception.DomainException;
import br.com.missio.Transito_API.domain.model.Owner;
import br.com.missio.Transito_API.domain.repository.OwnerReposiotry;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistroPropietarioService {

    private final OwnerReposiotry ownerReposiotry;

    @Transactional
    public Owner save(Owner owner) {
        boolean emailExists = ownerReposiotry.findByEmail(owner.getEmail())
                .filter(p -> !p.equals(owner))
                .isPresent();

        if(emailExists){
            throw new DomainException("Já existe um proprietário cadastrado com este email.");
        }

        return ownerReposiotry.save(owner);

    }

    @Transactional
    public void delete(Long ownerId) {
        ownerReposiotry.deleteById(ownerId);
    }
}
