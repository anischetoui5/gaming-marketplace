package gaming_app.repository;


import gaming_app.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByUserId(Long userId);
    Boolean existsByUserIdAndGameId(Long userId, Long gameId);
    Boolean existsByUserIdAndProductId(Long userId, Long productId);
}
