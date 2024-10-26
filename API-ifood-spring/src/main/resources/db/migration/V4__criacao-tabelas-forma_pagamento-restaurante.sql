CREATE TABLE forma_pagamento(
	id SERIAL PRIMARY KEY,
	descricao varchar(100) not null
);


CREATE TABLE restaurante(
	id SERIAL PRIMARY KEY,
	nome varchar(100) not null,
	taxa_frete decimal not null,
	cozinha_id int not null,
	cidade_id int not null,
	FOREIGN KEY (cozinha_id) REFERENCES cozinha(id),
	data_cadastro TIMESTAMP not null,
	data_atualizacao TIMESTAMP not null,
	cep varchar(15) not null,
	numero varchar(100) not null,
	complementento varchar(100),
	bairro varchar(100) not null,
	FOREIGN KEY(cidade_id) REFERENCES cidade(id)
);

CREATE TABLE restaurante_forma_pagamento (
    restaurante_id INT,
    forma_pagamento_id INT,
    PRIMARY KEY (restaurante_id, forma_pagamento_id),
    FOREIGN KEY (restaurante_id) REFERENCES restaurante(id),
    FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id)
);