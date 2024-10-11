insert into cozinha(nome) values ('Brasileira');
insert into cozinha(nome) values ('Japonesa');
insert into estado(nome) values ('maranhao');
insert into estado(nome) values ('sao paulo');
insert into estado(nome) values ('bahia');
insert into estado(nome) values ('para');
insert into cidade(nome,estado_id) values ('sao luis',1);
insert into cidade(nome,estado_id) values ('sao paulo',2);
insert into forma_pagamento(descricao) values ('dinheiro'), ('cartao de credito'), ('pix'), ('cartao de debito');

insert into restaurante(nome,taxa_frete,cozinha_id) values ('restaurante1',5,1);
insert into restaurante(nome,taxa_frete,cozinha_id) values ('restaurante2',10,2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2);