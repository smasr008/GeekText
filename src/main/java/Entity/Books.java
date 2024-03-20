package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "BookName")
    private String BookName;

    @Column(name = "AuthorID")
    private String AuthorID;

    // Constructors, getters, and setters

    public Books() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return ISBN;
    }

    public void setIsbn(String isbn) {
        this.ISBN = isbn;
    }

    public String getTitle() {
        return BookName;
    }

    public void setTitle(String title) {
        this.BookName = title;
    }

    public String getAuthor() {
        return AuthorID;
    }

    public void setAuthor(String author) {
        this.AuthorID = author;
    }
}
