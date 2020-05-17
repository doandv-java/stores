package doan.stores.bussiness;

import doan.stores.domain.Order;
import doan.stores.domain.OrderDetail;
import doan.stores.domain.Product;

import java.util.List;

public interface OrderService {
    List<Order> findListOrder();

    List<Order> findOrdersByUserId(Long userID);

    boolean updateStatus(Long orderId, int status);

    Order findOrderById(Long orderId);

    List<OrderDetail> getItemInOrderByOrderId(Long orderId);

    boolean cancelOrder(Long orderId);

    List<Product> top12ProductHot();

    long  getTotal();


}
