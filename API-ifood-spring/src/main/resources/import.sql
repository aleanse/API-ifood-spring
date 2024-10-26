

insert into cidade(nome,estado_id) values ('sao luis',1);
insert into cidade(nome,estado_id) values ('sao paulo',2);
insert into forma_pagamento(descricao) values ('dinheiro'), ('cartao de credito'), ('pix'), ('cartao de debito');

insert into restaurante(nome,taxa_frete,cozinha_id,endereco_cep,endereco_logradouro,endereco_numero,endereco_complemento,endereco_bairro,endereco_cidade_id,data_cadastro,data_atualizacao) values ('restaurante1',5,1,'65000000','rua 1','10','casa 1','bairro 1',1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

insert into restaurante(nome,taxa_frete,cozinha_id,data_cadastro,data_atualizacao) values ('restaurante2',10,2,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2,3), (2,1);
insert into produto(nome,descricao,preco,ativo,restaurante_id) values ('produto1','descricao produto 1',10,true,1);
insert into produto(nome,descricao,preco,ativo,restaurante_id) values ('produto2','descricao produto 1',10,true,2);