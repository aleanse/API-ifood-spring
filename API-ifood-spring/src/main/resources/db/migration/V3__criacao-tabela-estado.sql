CREATE TABLE estado (
    id  BIGSERIAL not null PRIMARY KEY,
	nome varchar(60) not null
	);

insert into estado (nome) select distinct
nome from cidade;

alter table cidade add column estado_id BIGSERIAL not null;

update cidade c SET estado_id =
(select e.id from estado e where e.nome = c.nome);

alter table cidade add constraint fk_cidade_estado
foreign key (estado_id) references estado(id);
