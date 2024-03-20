package Repository;
import Entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistItemRepo extends JpaRepository<Wishlist, Long> {
}
