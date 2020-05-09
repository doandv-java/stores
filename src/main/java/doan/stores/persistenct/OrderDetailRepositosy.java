package doan.stores.persistenct;

import doan.stores.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepositosy extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> getOrderDetailsByOrderIdIs(Long orderId);

    OrderDetail findOrderDetailByOrderIdIsAndProductIdIs(Long orderId, Long productId);
}
