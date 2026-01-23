package br.com.missio.Transito_API.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "propietario")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome")
    @NotBlank
    @Size(max = 60)
    private String name;

    @Column(name = "telefone")
    private String phone;

    @Size(max = 255)
    @Email
    private String email;


}
