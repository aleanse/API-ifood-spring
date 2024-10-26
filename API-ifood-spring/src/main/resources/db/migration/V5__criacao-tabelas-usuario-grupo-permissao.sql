create table usuario(
	id serial primary key,
	nome varchar(100),
	email varchar(100),
	senha varchar(100),
	data_cadastro TIMESTAMP
);


create table grupo(
	id serial primary key,
	nome varchar(100) not null
);

create table permissao(
	id serial primary key,
	nome varchar(100) not null ,
	descricao varchar(100) not null
);

CREATE TABLE grupo_permissao (
    grupo_id INT,
    permissao_id INT,
    PRIMARY KEY (grupo_id, permissao_id),
    FOREIGN KEY (grupo_id) REFERENCES grupo(id),
    FOREIGN KEY (permissao_id) REFERENCES permissao(id)
);

CREATE TABLE usuario_grupo (
    usuario_id INT,
    grupo_id INT,
    PRIMARY KEY (usuario_id, grupo_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (grupo_id) REFERENCES grupo(id)
);
