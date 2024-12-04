INSERT INTO tb_persons (name, email) VALUES ('Carlos Silva', 'carlos.silva@example.com');
INSERT INTO tb_persons (name, email) VALUES ('Mariana Costa', 'mariana.costa@example.com');
INSERT INTO tb_persons (name, email) VALUES ('João Pereira', 'joao.pereira@example.com');
INSERT INTO tb_persons (name, email) VALUES ('Ana Souza', 'ana.souza@example.com');
INSERT INTO tb_persons (name, email) VALUES ('Paulo Oliveira', 'paulo.oliveira@example.com');

INSERT INTO tb_books (title, author, total_copies) VALUES ('Dom Quixote', 'Miguel de Cervantes', 1);
INSERT INTO tb_books (title, author, total_copies) VALUES ('1984', 'George Orwell', 1);
INSERT INTO tb_books (title, author, total_copies) VALUES ('O Senhor dos Anéis', 'J.R.R. Tolkien', 1);
INSERT INTO tb_books (title, author, total_copies) VALUES ('A Revolução dos Bichos', 'George Orwell', 1);
INSERT INTO tb_books (title, author, total_copies) VALUES ('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 1);

INSERT INTO tb_books_copy (book_id, status) VALUES (1, 'AVAILABLE'); -- Dom Quixote
INSERT INTO tb_books_copy (book_id, status) VALUES (2, 'AVAILABLE'); -- 1984
INSERT INTO tb_books_copy (book_id, status) VALUES (3, 'AVAILABLE'); -- O Senhor dos Anéis
INSERT INTO tb_books_copy (book_id, status) VALUES (4, 'AVAILABLE'); -- A Revolução dos Bichos
INSERT INTO tb_books_copy (book_id, status) VALUES (5, 'AVAILABLE'); -- O Pequeno Príncipe
