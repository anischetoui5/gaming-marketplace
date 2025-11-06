package gaming_app.repository;


import gaming_app.model.CoinPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CoinPackageRepository extends JpaRepository<CoinPackage, Long> {
    List<CoinPackage> findByIsActiveTrue();
}
