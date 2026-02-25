-- EXAM
INSERT INTO tb_exam (category)
VALUES ('geografia');

-- QUESTION 1
INSERT INTO tb_question (question_id, description, category)
VALUES ('9f3b7c2a-8e41-4d5a-b7f1-2c6e9a1d3f40','Qual é a capital do Brasil?', 'geografia');

INSERT INTO tb_exam_question (exam_id, question_id)
VALUES (1, '9f3b7c2a-8e41-4d5a-b7f1-2c6e9a1d3f40');

-- ALTERNATIVAS
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Manaus', false, '9f3b7c2a-8e41-4d5a-b7f1-2c6e9a1d3f40');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Rio de Janeiro', false, '9f3b7c2a-8e41-4d5a-b7f1-2c6e9a1d3f40');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('São Paulo', false, '9f3b7c2a-8e41-4d5a-b7f1-2c6e9a1d3f40');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Fortaleza', false, '9f3b7c2a-8e41-4d5a-b7f1-2c6e9a1d3f40');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Brasília', true, '9f3b7c2a-8e41-4d5a-b7f1-2c6e9a1d3f40');

-- QUESTION 2
INSERT INTO tb_question (question_id, description, category)
VALUES ('a4c1e8d9-2b73-4f6e-9a21-5d8c7b3f1e62','Qual é o maior país do mundo em extensão territorial?', 'geografia');

INSERT INTO tb_exam_question (exam_id, question_id)
VALUES (1, 'a4c1e8d9-2b73-4f6e-9a21-5d8c7b3f1e62');

INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Canadá', false, 'a4c1e8d9-2b73-4f6e-9a21-5d8c7b3f1e62');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Estados Unidos', false, 'a4c1e8d9-2b73-4f6e-9a21-5d8c7b3f1e62');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('China', false, 'a4c1e8d9-2b73-4f6e-9a21-5d8c7b3f1e62');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Brasil', false, 'a4c1e8d9-2b73-4f6e-9a21-5d8c7b3f1e62');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Rússia', true, 'a4c1e8d9-2b73-4f6e-9a21-5d8c7b3f1e62');

-- QUESTION 3
INSERT INTO tb_question (question_id, description, category)
VALUES ('7b2d4f91-6c8a-4e3b-a9f0-1d2e7c5b8a34','Qual é o maior oceano do planeta?', 'geografia');

INSERT INTO tb_exam_question (exam_id, question_id)
VALUES (1, '7b2d4f91-6c8a-4e3b-a9f0-1d2e7c5b8a34');

INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Oceano Atlântico', false, '7b2d4f91-6c8a-4e3b-a9f0-1d2e7c5b8a34');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Oceano Índico', false, '7b2d4f91-6c8a-4e3b-a9f0-1d2e7c5b8a34');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Oceano Ártico', false, '7b2d4f91-6c8a-4e3b-a9f0-1d2e7c5b8a34');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Oceano Antártico', false, '7b2d4f91-6c8a-4e3b-a9f0-1d2e7c5b8a34');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Oceano Pacífico', true, '7b2d4f91-6c8a-4e3b-a9f0-1d2e7c5b8a34');

-- QUESTION 4
INSERT INTO tb_question (question_id, description, category)
VALUES ('c6e9a3f1-4b72-4d8c-9f21-7a3b5d1e8c60','Qual é o rio mais extenso do mundo?', 'geografia');

INSERT INTO tb_exam_question (exam_id, question_id)
VALUES (1, 'c6e9a3f1-4b72-4d8c-9f21-7a3b5d1e8c60');

INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Rio Mississippi', false, 'c6e9a3f1-4b72-4d8c-9f21-7a3b5d1e8c60');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Rio Yangtzé', false, 'c6e9a3f1-4b72-4d8c-9f21-7a3b5d1e8c60');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Rio Nilo', true, 'c6e9a3f1-4b72-4d8c-9f21-7a3b5d1e8c60');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Rio Amazonas', false, 'c6e9a3f1-4b72-4d8c-9f21-7a3b5d1e8c60');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Rio Danúbio', false, 'c6e9a3f1-4b72-4d8c-9f21-7a3b5d1e8c60');

-- QUESTION 5
INSERT INTO tb_question (question_id, description, category)
VALUES ('2d7f1a8c-9b34-4e6d-a2c1-5f8b3e7d9a10','Qual continente possui o maior número de países?', 'geografia');

INSERT INTO tb_exam_question (exam_id, question_id)
VALUES (1, '2d7f1a8c-9b34-4e6d-a2c1-5f8b3e7d9a10');

INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Europa', false, '2d7f1a8c-9b34-4e6d-a2c1-5f8b3e7d9a10');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Ásia', false, '2d7f1a8c-9b34-4e6d-a2c1-5f8b3e7d9a10');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('África', true, '2d7f1a8c-9b34-4e6d-a2c1-5f8b3e7d9a10');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('América do Sul', false, '2d7f1a8c-9b34-4e6d-a2c1-5f8b3e7d9a10');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('América do Norte', false, '2d7f1a8c-9b34-4e6d-a2c1-5f8b3e7d9a10');

-- QUESTION 6
INSERT INTO tb_question (question_id, description, category)
VALUES ('e8c1b4d7-3a9f-4c2e-b6d1-0f7a5c3e8b92','Qual é o deserto mais extenso do mundo?', 'geografia');

INSERT INTO tb_exam_question (exam_id, question_id)
VALUES (1, 'e8c1b4d7-3a9f-4c2e-b6d1-0f7a5c3e8b92');

INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Deserto do Saara', false, 'e8c1b4d7-3a9f-4c2e-b6d1-0f7a5c3e8b92');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Deserto da Arábia', false, 'e8c1b4d7-3a9f-4c2e-b6d1-0f7a5c3e8b92');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Deserto de Gobi', false, 'e8c1b4d7-3a9f-4c2e-b6d1-0f7a5c3e8b92');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Antártida', true, 'e8c1b4d7-3a9f-4c2e-b6d1-0f7a5c3e8b92');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Deserto do Atacama', false, 'e8c1b4d7-3a9f-4c2e-b6d1-0f7a5c3e8b92');

-- QUESTION 7
INSERT INTO tb_question (question_id, description, category)
VALUES ('5a3e7d1c-8f92-4b6a-a1d3-9c2e7f4b8d65','Qual é a capital da Argentina?', 'geografia');

INSERT INTO tb_exam_question (exam_id, question_id)
VALUES (1, '5a3e7d1c-8f92-4b6a-a1d3-9c2e7f4b8d65');

INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Montevidéu', false, '5a3e7d1c-8f92-4b6a-a1d3-9c2e7f4b8d65');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Santiago', false, '5a3e7d1c-8f92-4b6a-a1d3-9c2e7f4b8d65');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Buenos Aires', true, '5a3e7d1c-8f92-4b6a-a1d3-9c2e7f4b8d65');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Lima', false, '5a3e7d1c-8f92-4b6a-a1d3-9c2e7f4b8d65');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Assunção', false, '5a3e7d1c-8f92-4b6a-a1d3-9c2e7f4b8d65');

-- QUESTION 8
INSERT INTO tb_question (question_id, description, category)
VALUES ('b1d9c4e7-6a2f-4d8b-9e31-3f7a5c2d8b40','Qual país é conhecido como a Terra do Sol Nascente?', 'geografia');

INSERT INTO tb_exam_question (exam_id, question_id)
VALUES (1, 'b1d9c4e7-6a2f-4d8b-9e31-3f7a5c2d8b40');

INSERT INTO tb_alternative (description, correct, question_id) VALUES ('China', false, 'b1d9c4e7-6a2f-4d8b-9e31-3f7a5c2d8b40');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Coreia do Sul', false, 'b1d9c4e7-6a2f-4d8b-9e31-3f7a5c2d8b40');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Japão', true, 'b1d9c4e7-6a2f-4d8b-9e31-3f7a5c2d8b40');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Tailândia', false, 'b1d9c4e7-6a2f-4d8b-9e31-3f7a5c2d8b40');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Vietnã', false, 'b1d9c4e7-6a2f-4d8b-9e31-3f7a5c2d8b40');

-- QUESTION 9
INSERT INTO tb_question (question_id, description, category)
VALUES ('4c8e2a7d-1f35-4b9c-a6d2-8e3f7b1a5d60','Qual é a montanha mais alta do mundo?', 'geografia');

INSERT INTO tb_exam_question (exam_id, question_id)
VALUES (1, '4c8e2a7d-1f35-4b9c-a6d2-8e3f7b1a5d60');

INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Monte Kilimanjaro', false, '4c8e2a7d-1f35-4b9c-a6d2-8e3f7b1a5d60');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Monte Everest', true, '4c8e2a7d-1f35-4b9c-a6d2-8e3f7b1a5d60');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Monte Fuji', false, '4c8e2a7d-1f35-4b9c-a6d2-8e3f7b1a5d60');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Monte Aconcágua', false, '4c8e2a7d-1f35-4b9c-a6d2-8e3f7b1a5d60');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Monte McKinley', false, '4c8e2a7d-1f35-4b9c-a6d2-8e3f7b1a5d60');

-- QUESTION 10
INSERT INTO tb_question (question_id, description, category)
VALUES ('f7a5d3c1-9e24-4b6f-b8d1-2c7e3a9d4f80','Qual é o país mais populoso do mundo?', 'geografia');

INSERT INTO tb_exam_question (exam_id, question_id)
VALUES (1, 'f7a5d3c1-9e24-4b6f-b8d1-2c7e3a9d4f80');

INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Estados Unidos', false, 'f7a5d3c1-9e24-4b6f-b8d1-2c7e3a9d4f80');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Índia', true, 'f7a5d3c1-9e24-4b6f-b8d1-2c7e3a9d4f80');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('China', false, 'f7a5d3c1-9e24-4b6f-b8d1-2c7e3a9d4f80');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Indonésia', false, 'f7a5d3c1-9e24-4b6f-b8d1-2c7e3a9d4f80');
INSERT INTO tb_alternative (description, correct, question_id) VALUES ('Brasil', false, 'f7a5d3c1-9e24-4b6f-b8d1-2c7e3a9d4f80');

