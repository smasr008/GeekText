package com.example.geektext.Repository;
import com.example.geektext.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepo extends JpaRepository<Books, String>{
   

}
