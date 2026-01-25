package br.com.missio.Transito_API.domain.service;

import br.com.missio.Transito_API.domain.exception.DomainException;
import br.com.missio.Transito_API.domain.model.Owner;
import br.com.missio.Transito_API.domain.repository.OwnerReposiotry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OwnerRegistrationService {

    private final OwnerReposiotry ownerReposiotry;

    public Owner findOrFail(Long ownerId){
        return ownerReposiotry.findById(ownerId)
                .orElseThrow(() -> new DomainException("Proprietário não encontrado"));
    }

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
