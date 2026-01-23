package br.com.missio.Transito_API.domain.repository;

import br.com.missio.Transito_API.domain.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerReposiotry extends JpaRepository <Owner,Long> {

    List<Owner> findByName(String name);
    List<Owner> findByNameContaining(String Name);

    Optional<Owner> findByEmail(String email);
}
