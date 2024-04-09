INSERT INTO Authors (AuthorID, FirstName, LastName)
VALUES ('A0000000', 'Howard Phillips', 'Lovecraft'),
       ('B0000000', 'Stephen', 'King'),
       ('C0000000', 'Jane', 'Austen'),
       ('G0000000', 'Homer', NULL),
       ('J1111111', 'John', 'Grisham'),
       ('M1111111', 'Maya', 'Angelou'),
       ('N2222222', 'J.K', 'Rowling'),
       ('R2222222', 'George R.R', 'Martin');

INSERT INTO Users (UserID, Username, PW, FullName, EmailAddress, HomeAddress)
VALUES ('U0000000', 'jandoe01', 'password321', 'Jane Doe', 'jandoe01@gmail.com', '123 Smith St'),
       ('U1111111', 'alxsmit02', 'password333', 'Alex Smith', 'alxsmit02@gmail.com', '987 Birch St'),
       ('U2222222', 'jamwest03', 'password271', 'James West', 'jamwest03@gmail.com', '555 Acacia Rd'),
       ('U3333333', 'rachgrn04', 'password314', 'Rachel Green', 'rachgrn04@gmail.com', '256 Spruce Ln'),
       ('U4444444', 'isablan05', 'password!', 'Isabella Blanco', 'isablan05@gmail.com', '777 Peace Ct'),
       ('U5555555', 'destroy06', 'notpassword', 'Destroyer of Worlds', 'destroy06@gmail.com', '575 Fire Rd');

INSERT INTO Books (ISBN, BookName, BookDesc, Price, Genre, Publisher, YearPublished, CopiesSold, AuthorID)
VALUES ('9780345337795', 'The Dream-Quest of Unknown Kaddath', 'Psychological science-fiction horror story', 19.99,
        'Horror', 'Arkham House', 1943, 5000, 'A0000000'),
       ('9780743210898', 'The Green Mile', 'Dark Fantasy by Stephen King', 14.99, 'Dark Fantasy', 'Signet House', 2000,
        10000, 'B0000000'),
       ('9780140449112', 'The Odyssey', 'Ancient Greek epic', 14.99, 'Epic', 'Penguin Classics', 2003, 2000,
        'G0000000'),
       ('9780141040349', 'Pride and Prejudice', 'Famous Jane Austen Romance novel', 17.99, 'Romance',
        'Penguin Classics', 2009, 5500, 'C0000000'),
       ('9780440245919', 'A Time to Kill', 'Legal thriller by John Grisham', 12.99, 'Thriller', 'Vintage', 1989, 12000,
        'J1111111'),
       ('9780345514400', 'I Know Why The Caged Bird Sings',
        'Autobiography of Maya Angelous childhood and social experiences', 15.99, 'Autobiography', 'Penguin Classics',
        2009, 11500, 'M1111111'),
       ('9780439064873', 'Harry Potter and the Chamber of Secrets', 'Second installment in Harry Potter series', 10.99,
        'Fantasy', 'Scholastic', 2000, 25000, 'N2222222'),
       ('9780553582017', 'A Dance with Dragons', 'Fifth installment in the famous A Song of Ice and Fire series', 7.99,
        'Fantasy', 'Bantam', 2013, 24000, 'R2222222');

