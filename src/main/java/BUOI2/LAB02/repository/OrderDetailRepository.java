package BUOI2.LAB02.repository;
import BUOI2.LAB02.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    void deleteByProductId(Long productId);
}