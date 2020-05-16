package doan.stores.persistenct;

import doan.stores.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByStatusIsAndUserIdIs(int status, Long userId);

    Order findOrderById(Long id);
}
