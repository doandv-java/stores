package doan.stores.bussiness;

import doan.stores.domain.Order;
import doan.stores.domain.OrderDetail;

import java.util.List;

public interface OrderService {
    List<Order> findListOrder();

    boolean updateStatus(Long orderId, int status);

    Order findOrderById(Long orderId);

    List<OrderDetail> getItemInOrderByOrderId(Long orderId);
}
