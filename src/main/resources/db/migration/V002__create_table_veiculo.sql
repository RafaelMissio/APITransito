create table veiculo(
                        id BIGSERIAL PRIMARY KEY,
                        proprietario_id BIGINT not null references propietario(id),
                        modelo varchar(20) not null,
                        marca varchar(20) not null,
                        placa varchar(10) unique not null,
                        status varchar(20) not null,
                        data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        data_apreensao TIMESTAMP
);

alter table veiculo add constraint fk_veiculo_propietario foreign key (proprietario_id) references propietario(id);

alter table veiculo add constraint uk_veiculo_placa unique (placa);