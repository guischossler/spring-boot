INSERT INTO tb_persons (name, email) VALUES ('Carlos Silva', 'carlos.silva@example.com');
INSERT INTO tb_persons (name, email) VALUES ('Mariana Costa', 'mariana.costa@example.com');
INSERT INTO tb_persons (name, email) VALUES ('João Pereira', 'joao.pereira@example.com');
INSERT INTO tb_persons (name, email) VALUES ('Ana Souza', 'ana.souza@example.com');
INSERT INTO tb_persons (name, email) VALUES ('Paulo Oliveira', 'paulo.oliveira@example.com');

INSERT INTO tb_books (name, author, total_copies) VALUES ('Dom Quixote', 'Miguel de Cervantes', 1);
INSERT INTO tb_books (name, author, total_copies) VALUES ('1984', 'George Orwell', 1);
INSERT INTO tb_books (name, author, total_copies) VALUES ('O Senhor dos Anéis', 'J.R.R. Tolkien', 1);
INSERT INTO tb_books (name, author, total_copies) VALUES ('A Revolução dos Bichos', 'George Orwell', 1);
INSERT INTO tb_books (name, author, total_copies) VALUES ('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 1);

INSERT INTO tb_rentals (book_id, person_id) VALUES (3, 2)