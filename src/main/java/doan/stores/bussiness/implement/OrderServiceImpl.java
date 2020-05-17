package doan.stores.bussiness.implement;

import doan.stores.bussiness.OrderService;
import doan.stores.bussiness.WarehouseService;
import doan.stores.domain.Order;
import doan.stores.domain.OrderDetail;
import doan.stores.domain.Product;
import doan.stores.enums.StatusEnum;
import doan.stores.persistenct.OrderDetailRepositosy;
import doan.stores.persistenct.OrderRepository;
import doan.stores.persistenct.ProductRepository;
import doan.stores.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepositosy orderDetailRepositosy;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseService warehouseService;

    @Override
    public List<Order> findListOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findOrdersByUserId(Long userID) {
        return orderRepository.findOrdersByUserIdIs(userID);
    }

    @Override
    public boolean updateStatus(Long orderId, int status) {
        Order order = orderRepository.findOrderById(orderId);
        StatusEnum statusEnum = StatusEnum.ofCode(status);
        //chuyen tu dat hang sang huy
        if (order.getStatus() == StatusEnum.SUCCESS.getCode() && statusEnum == StatusEnum.PEND) {
            warehouseService.cancelOrder(orderId);
        }
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

    @Override
    public boolean cancelOrder(Long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order == null) {
            return false;
        } else {
            order.setStatus(StatusEnum.PEND.getCode());
            warehouseService.cancelOrder(orderId);
            orderRepository.save(order);
            return true;
        }
    }


    @Override
    public List<Product> top12ProductHot() {
        Long[] listProductId = orderDetailRepositosy.findTop12ProductId();
        List<Product> products = productRepository.findProductsByIdInAndDeletedIs(listProductId, Constants.DELETE.FALSE);
        return products;
    }

    @Override
    public long getTotal() {
        long total;
        List<Order> orders = orderRepository.findOrdersByStatusIs(StatusEnum.SUCCESS.getCode());
        if (orders.isEmpty()) {
            total = 0;
        } else {
            total = orders.stream().mapToLong(Order::getTotal).sum();
        }
        return total;
    }
}
