create table Users
(
    UserID       varchar(8) NOT NULL UNIQUE,
    PRIMARY KEY (UserID),
    Username     varchar(32),
    UNIQUE (Username),
    PW           varchar(32),
    FullName     varchar(32),
    EmailAddress varchar(32),
    HomeAddress  varchar(64)
);

create table Authors
(
    AuthorID  varchar(8) NOT NULL,
    PRIMARY KEY (AuthorID),
    FirstName varchar(16),
    LastName  varchar(16)
);

create table Books
(
    ISBN          varchar(13) UNIQUE,
    PRIMARY KEY (ISBN),
    BookName      varchar(64),
    BookDesc      varchar(500),
    Price         DOUBLE,
    Genre         varchar(16),
    Publisher     varchar(32),
    YearPublished int,
    CopiesSold    int,
    AuthorID      varchar(8) NOT NULL,
    FOREIGN KEY (AuthorID) REFERENCES Authors (AuthorID)
);

create table CreditCards
(
    CardNum BIGINT     NOT NULL,
    CVV     int        NOT NULL,
    ExpDate DATE       NOT NULL,
    UserID  varchar(8) NOT NULL,
    PRIMARY KEY (CardNum),
    FOREIGN KEY (UserID) REFERENCES Users (UserID)
);

create table ShoppingCart
(
    cartId   bigint auto_increment primary key,
    Quantity bigint      not null,
    ISBN     varchar(13) not null,
    UserID   varchar(8)  not null,
    constraint UniqueCartItem unique (UserID, ISBN),
    constraint BooksFK foreign key (UserID) references geektext.Users (UserID) on delete cascade,
    constraint UsersFK foreign key (ISBN) references geektext.Books (ISBN) on delete cascade
);

create table WishLists
(
    WishID BIGINT     NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (WishID),
    UserID varchar(8) NOT NULL
);

create table WishListItems
(
    WishListItemID BIGINT      NOT NULL AUTO_INCREMENT,
    WishID         BIGINT      NOT NULL,
    ISBN           varchar(13) NOT NULL,
    PRIMARY KEY (WishListItemID),
    FOREIGN KEY (ISBN) REFERENCES Books (ISBN),
    FOREIGN KEY (WishID) REFERENCES WishLists (WishID)
);

create table Ratings
(
    RatingID  BIGINT      NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (RatingID),
    UserID    varchar(8)  NOT NULL,
    ISBN      varchar(13) NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES Books (ISBN),
    Rating    DOUBLE,
    Datestamp DATE,
    CHECK (1 <= Rating AND Rating <= 5)
);

create table Comments
(
    CommentID   BIGINT      NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (CommentID),
    UserID      varchar(8)  NOT NULL,
    ISBN        varchar(13) NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES Books (ISBN),
    FOREIGN KEY (UserID) REFERENCES Users (UserID),
    UserComment varchar(2500),
    Datestamp   DATE
);

create table Publishers
(
    PubID   BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (PubID),
    PubName varchar(32)
);






	
	


	
	