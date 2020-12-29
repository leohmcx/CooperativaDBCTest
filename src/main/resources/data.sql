INSERT INTO ASSOCIADO (nome, cpf, data_cadastro) values ('Alex Manoel', '14547231060', CURRENT_TIMESTAMP);
INSERT INTO ASSOCIADO (nome, cpf, data_cadastro) values ('Francisto Almeida Ramos', '33271976015', CURRENT_TIMESTAMP);
INSERT INTO ASSOCIADO (nome, cpf, data_cadastro) values ('Teste Fracionario', '28970513027', CURRENT_TIMESTAMP);
INSERT INTO ASSOCIADO (nome, cpf, data_cadastro) values ('Olha outro teste aqui', '59139016064', CURRENT_TIMESTAMP);

INSERT INTO PAUTA (assunto, descricao, data_criacao) values ('Reforma', 'Banheiros em comum', CURRENT_TIMESTAMP);
INSERT INTO PAUTA (assunto, descricao, data_criacao) values ('Troca do Síndicato', 'Sindicato esta deixando a desejar', CURRENT_TIMESTAMP);
INSERT INTO PAUTA (assunto, descricao, data_criacao) values ('Goteiras', 'Apartamentos do últimos andar com tricas no teto', CURRENT_TIMESTAMP);
INSERT INTO PAUTA (assunto, descricao, data_criacao) values ('Troca de lampadas', 'Troca de lampadas comuns por de LED visando economia de energia', CURRENT_TIMESTAMP);

INSERT INTO ASSEMBLEIA (pauta_id, inicio, fim) values (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + (20 ||' minutes')::interval);
INSERT INTO ASSEMBLEIA (pauta_id, inicio, fim) values (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + (20 ||' minutes')::interval);

INSERT INTO ASSEMBLEIA_ASSOCIADO (assembleia_id, associado_id, voto) values (1, 1, 'N');
INSERT INTO ASSEMBLEIA_ASSOCIADO (assembleia_id, associado_id, voto) values (1, 2, 'S');
INSERT INTO ASSEMBLEIA_ASSOCIADO (assembleia_id, associado_id, voto) values (1, 3, 'S');
INSERT INTO ASSEMBLEIA_ASSOCIADO (assembleia_id, associado_id, voto) values (1, 4, 'S');
