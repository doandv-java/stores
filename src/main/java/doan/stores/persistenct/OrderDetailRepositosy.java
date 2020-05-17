package doan.stores.persistenct;

import doan.stores.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepositosy extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> getOrderDetailsByOrderIdIs(Long orderId);

    OrderDetail findOrderDetailByOrderIdIsAndProductIdIs(Long orderId, Long productId);

    @Query(value = "select product_id from (\nSELECT order_detail.product_id,\n    sum(order_detail.quantity) AS sum\n   FROM order_detail\n  GROUP BY order_detail.product_id\n  ORDER BY (sum(order_detail.quantity)) DESC) AS TOP\n\tLIMIT 12", nativeQuery = true)
    Long[] findTop12ProductId();

    int countOrderDetailsByProductId(Long productId);
}
