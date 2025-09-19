-- Insert sample publishers
INSERT INTO publishers (name, phone, email, address)
VALUES 
('Reilly Media', '01041258523', 'info@oreilly.com', '1005 Gravenstein Highway North'),
('Penguin Books', '01099999999', 'contact@penguin.com', '80 Strand, London');

-- Insert sample authors
INSERT INTO authors (first_name, last_name, bio)
VALUES 
('George', 'Orwell', 'English novelist and essayist.'),
('Mark', 'Rowling', 'Author of the Harry Potter series'),
('Yuval', 'Harari', 'Historian and author of Sapiens.');

-- Insert sample categories
INSERT INTO categories (name) 
VALUES ('Fiction'), ('Fantasy'), ('Science Fiction'), ('History');

-- Insert sample books
INSERT INTO books (title, isbn, edition, publication_year, language, summary, cover_image, total_copies, available_copies, publisher_id)
VALUES 
('1984', '9780451524935', '1st', 1949, 'ENGLISH', 'Dystopian social science fiction novel.', NULL, 10, 10, 1),
('Harry Potter and the Sorcerer Stone', '9780590353427', '1st', 1997, 'ENGLISH', 'Fantasy novel about a young wizard.', NULL, 5, 5, 1),
('Sapiens: A Brief History of Humankind', '9780062316097', '1st', 2011, 'ENGLISH', 'Explores the history of humankind.', NULL, 7, 7, 2);

-- Link books to categories
INSERT INTO books_categories (book_id, category_id) 
VALUES 
(1, 3), -- 1984 → Science Fiction
(2, 2), -- Harry Potter → Fantasy
(3, 4); -- Sapiens → History

-- Link books to authors
INSERT INTO books_authors (book_id, author_id) 
VALUES 
(1, 1), -- 1984 → Orwell
(2, 2), -- Harry Potter → Rowling
(3, 3); -- Sapiens → Harari

-- Insert sample borrowers
INSERT INTO borrowers (first_name, last_name, email, phone, address)
VALUES 
('Aya', 'Hathout', 'aya@example.com', '01000000001', 'Cairo, Egypt'),
('Omar', 'Ali', 'omar@example.com', '01111111115', 'Giza, Egypt');

-- Insert sample borrow_records
INSERT INTO borrow_records (book_id, borrower_id, borrow_date, due_date, return_date, status, fine)
VALUES 
(1, 1, '2025-09-01', '2025-09-21', NULL, 'BORROWED', 0.0),
(2, 2, '2025-08-20', '2025-09-05', '2025-09-03', 'RETURNED', 0.0);

-- Insert sample users
INSERT INTO users (user_name, email, password, phone, first_name, last_name, role)
VALUES 
('admin1', 'admin1@example.com', 'hashed_password_1', '01000000001', 'System', 'Admin', 'ADMIN'),
('librarian1', 'librarian1@example.com', 'hashed_password_2', '01000000002', 'Librarian', 'User', 'LIBRARIAN'),
('staff1', 'staff1@example.com', 'hashed_password_3', '01000000003', 'Staff', 'Member', 'STAFF');

