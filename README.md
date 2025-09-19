## Database Design (ERD)

![Library Management System ERD](images/libraryERD.PNG)

The Library Management System database is designed to efficiently manage books, borrowers, users, and borrowing transactions. Below is a summary of the Entity Relationship Diagram (ERD):  

- **Books**:  
  Stores detailed metadata including title, ISBN, edition, publication year, summary, language, cover image, total copies, and available copies.  
  Each book is linked to:  
  - **Authors** (Many-to-Many): a book can have multiple authors, and an author can write multiple books.  
  - **Categories** (Many-to-Many): a book can belong to multiple categories/genres, and a category can contain multiple books.  
  - **Publisher** (One-to-Many): a book has one publisher, but a publisher can publish many books.  

- **Authors**:  
  Stores information about book authors, including their names and short biography.  

- **Categories**:  
  Used to classify books. Keeps the design simple (no subcategories for now).  

- **Borrowers**:  
  Represents library members who borrow books. Each borrower has personal details (name, email, phone, address) and an **is_active** status for membership management.  

- **BorrowRecords**:  
  Tracks borrowing transactions. Each record links a **borrower** to a **book** with borrow date, due date, return date, status (e.g., borrowed, returned, overdue), and any applicable fines.  

- **Users**:  
  Represents system users (e.g., Administrator, Librarian, Staff). Includes role-based access control with roles stored as an ENUM.  

- **UserActivityLogs**:  
  Tracks system user activities such as **login, borrow, and return**, ensuring accountability and auditing.  

---

📌 **Why this design?**  
- Normalization ensures data consistency (e.g., no duplicate authors or categories).  
- Many-to-Many relationships allow flexibility for books with multiple authors or categories.  
- Role-based access in `Users` ensures security and proper permission control.  
- `BorrowRecords` decouples book copies from members, making borrowing/returning efficient.  
- `UserActivityLogs` provides transparency on system usage and borrowing activities. 




