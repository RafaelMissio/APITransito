package br.com.missio.Transito_API.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "veiculo")
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private Owner owner;

    @Column(name = "marca")
    private String manufacturer;

    @Column(name = "modelo")
    private String model;

    @Column(name = "placa")
    private String plate;

    @JsonProperty(access = Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private VehicleStatus status;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "data_cadastro")
    private LocalDateTime registrationDate;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "data_apreensao")
    private LocalDateTime seizureDate;

}
