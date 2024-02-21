package com.example.geektext.Repository;
import com.example.geektext.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Books, String>{
    List<Books> findByGenre(String genre);

}