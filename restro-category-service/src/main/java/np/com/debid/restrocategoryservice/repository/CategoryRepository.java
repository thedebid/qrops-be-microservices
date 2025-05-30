package np.com.debid.restrocategoryservice.repository;

import np.com.debid.restrocategoryservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByRestaurantId(UUID restaurantId);
}
