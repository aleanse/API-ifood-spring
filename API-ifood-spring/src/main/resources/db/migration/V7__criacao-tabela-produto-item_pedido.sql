create table produto(
	id serial primary key,
	nome varchar(100) not null,
	descricao varchar(100) not null,
	preco decimal not null,
	ativo boolean not null,
	restaurante_id int,
	foreign key(restaurante_id) references restaurante(id)
);

create table item_pedido(
	id serial primary key,
	quantidade int not null,
	preco_unitario decimal not null,
	preco_total decimal not null,
	observacao varchar(100),
	produto_id int,
	pedido_id int,
	foreign key(produto_id) references produto(id),
	foreign key(pedido_id) references pedido(id)
);