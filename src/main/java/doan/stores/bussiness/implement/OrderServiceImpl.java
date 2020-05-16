package doan.stores.bussiness.implement;

import doan.stores.bussiness.OrderService;
import doan.stores.domain.Order;
import doan.stores.domain.OrderDetail;
import doan.stores.persistenct.OrderDetailRepositosy;
import doan.stores.persistenct.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepositosy orderDetailRepositosy;

    @Override
    public List<Order> findListOrder() {
        return orderRepository.findAll();
    }

    @Override
    public boolean updateStatus(Long orderId, int status) {
        Order order = orderRepository.findOrderById(orderId);
        order.setStatus(status);
        orderRepository.save(order);
        return true;
    }

    @Override
    public Order findOrderById(Long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        return order;
    }

    @Override
    public List<OrderDetail> getItemInOrderByOrderId(Long orderId) {
        List<OrderDetail> orderDetails = orderDetailRepositosy.getOrderDetailsByOrderIdIs(orderId);
        return orderDetails;
    }
}
