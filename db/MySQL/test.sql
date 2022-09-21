-- ---------------------------------------------------------------------------
-- Nota: Inserindo com ID para garantir que a ordem não afete outros inserções
-- ---------------------------------------------------------------------------

-- Lembre de usar create.sql antes

-- Editoras
INSERT INTO Editora (id, nome) VALUES (1, 'Companhia das Letras');
INSERT INTO Editora (id, nome) VALUES (2, 'Moderna');

-- Categorias de Obras
INSERT INTO CategoriaObra (codigo, descricao, maximoDiasEmprestimo, taxaMulta)
                   VALUES (1, 'Obra comum', 6, 1.5);
INSERT INTO CategoriaObra (codigo, descricao, maximoDiasEmprestimo, taxaMulta)
                   VALUES (2, 'Obra única', 3, 2.5);
INSERT INTO CategoriaObra (codigo, descricao, maximoDiasEmprestimo, taxaMulta)
                   VALUES (3, 'Obra especial', 1, 4.5);

-- Obras
INSERT INTO Obra (codigo, isbn, titulo, categoriaObra_id, palavrasChave, dataPublicacao, edicao, editora_id, numPaginas, status)
          VALUES (1, 9786559790883, 'Fundamentos da Psicanálise de Freud a Lacan', 1, 'Sigmund, Freud, Lacan, Psicanálise', '2022-11-09', '4', 1, 360, 'DISPONIVEL');
INSERT INTO Obra (codigo, isbn, titulo, categoriaObra_id, palavrasChave, dataPublicacao, edicao, editora_id, numPaginas, status)
          VALUES (2, 9788535909555, 'A Revolução dos Bichos', 1, 'Orwell, 1984, Revolução', '2007-01-10', '9', 1, 152, 'DISPONIVEL');
INSERT INTO Obra (codigo, isbn, titulo, categoriaObra_id, palavrasChave, dataPublicacao, edicao, editora_id, numPaginas, status)
          VALUES (3, 9788516122393, 'Esopo: Fábulas Completas', 2, 'Esopo, Fábulas, Lebre, Tartaruga', '2022-02-13', '3', 2, 191, 'DISPONIVEL');

-- Cópias
INSERT INTO Copia (id, estado, obra_id) VALUES (1, 'Disponivel', 1);
INSERT INTO Copia (id, estado, obra_id) VALUES (2, 'Disponivel', 1);
INSERT INTO Copia (id, estado, obra_id) VALUES (3, 'Emprestado', 1);
INSERT INTO Copia (id, estado, obra_id) VALUES (4, 'Disponivel', 2);
INSERT INTO Copia (id, estado, obra_id) VALUES (5, 'Disponivel', 3);
INSERT INTO Copia (id, estado, obra_id) VALUES (6, 'Disponivel', 3);
INSERT INTO Copia (id, estado, obra_id) VALUES (7, 'Disponivel', 3);

-- Endereços
INSERT INTO Endereco (id, logradouro, numero, cep, cidade, estado)
              VALUES (1, 'Rua das Andorinhas', '192', '13221553', 'São Carlos', 'SP');
INSERT INTO Endereco (id, logradouro, numero, cep, cidade, estado)
              VALUES (2, 'Rua Matilde Bannwart', '23', '18706670', 'São Carlos', 'SP');
INSERT INTO Endereco (id, logradouro, numero, cep, cidade, estado)
              VALUES (3, 'Rua Luciano Lunardi', '563', '18608490', 'São Carlos', 'SP');

-- Usuários
INSERT INTO Usuario (id, nome, telefone, dataNascimento, endereco_id, role)
             VALUES (1, 'Isadora Jéssica', '(19) 98522-6133', '1975-05-20', 1, 'LEITOR');
INSERT INTO Usuario (id, nome, telefone, dataNascimento, endereco_id, role)
             VALUES (2, 'Yuri Calebe', '(19) 98103-8871', '1942-08-13', 2, 'LEITOR');
INSERT INTO Usuario (id, nome, telefone, dataNascimento, endereco_id, role)
             VALUES (3, 'Thales Guilherme', '(11) 98627-9594', '1999-10-09', 3, 'FUNC');

-- Categorias de Leitores
INSERT INTO CategoriaLeitor (id, maximoDiasEmprestimo, descricao)
                     VALUES (1, 10, 'Leitor comum');
INSERT INTO CategoriaLeitor (id, maximoDiasEmprestimo, descricao)
                     VALUES (2, 20, 'Leitor especial');
INSERT INTO CategoriaLeitor (id, maximoDiasEmprestimo, descricao)
                     VALUES (3, 1, 'Leitor com pendências');

-- Leitores
INSERT INTO Leitor (idUsuario, email, documentoId, categoria_id, grupoAcademico)
            VALUES (1, 'isadora.jessica@estudante.ufscar.br', "MG-123.456.789", 1, true);
INSERT INTO Leitor (idUsuario, email, documentoId, categoria_id, grupoAcademico)
            VALUES (2, 'yuri.calebe@estudante.ufscar.br', "SP-987.654.321", 3, true);

-- Empréstimos
INSERT INTO Emprestimo (id, dataEmprestimo, dataPrevistaDevolucao, funcionarioResponsavel, leitor, codigoCopia, atrasado)
                VALUES (1, '2022-09-21', '2022-09-26', 3, 1, 3, false);