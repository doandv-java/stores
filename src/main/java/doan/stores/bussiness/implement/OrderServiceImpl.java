package doan.stores.bussiness.implement;

import doan.stores.bussiness.OrderService;
import doan.stores.bussiness.WarehouseService;
import doan.stores.domain.Order;
import doan.stores.domain.OrderDetail;
import doan.stores.domain.Product;
import doan.stores.dto.response.StaticsOrderTotal;
import doan.stores.enums.StatusEnum;
import doan.stores.persistenct.OrderDetailRepositosy;
import doan.stores.persistenct.OrderRepository;
import doan.stores.persistenct.ProductRepository;
import doan.stores.utils.Constants;
import doan.stores.utils.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        List<Product> products = productRepository.findProductsByIdInAndDeletedIsOrderByPriceAscNameDesc(listProductId, Constants.DELETE.FALSE);
        return products;
    }

    @Override
    public StaticsOrderTotal getTotal() {
        Date now = Dates.now();
        String month = Dates.format(now, "MM");
        String day = Dates.format(now, "yyyy-MM-dd");
        String year = Dates.format(now, "yyyy");

        List<Order> orders = orderRepository.findOrdersByStatusIs(StatusEnum.SUCCESS.getCode());
        StaticsOrderTotal orderTotal = new StaticsOrderTotal();
        if (orders.isEmpty()) {
            orderTotal.setTotalDay(0L);
            orderTotal.setTotalMonth(0L);
            orderTotal.setTotalYear(0L);
        }else{
            Long totalYear=orders.stream().filter(order ->Dates.format(order.getCreateDay(),"yyyy").equals(year)).mapToLong(Order::getTotal).sum();
            Long totalMonth=orders.stream().filter(order ->Dates.format(order.getCreateDay(),"MM").equals(month)).mapToLong(Order::getTotal).sum();
            Long totalDay=orders.stream().filter(order ->Dates.format(order.getCreateDay(),"yyyy-MM-dd").equals(day)).mapToLong(Order::getTotal).sum();
            orderTotal.setTotalDay(totalDay);
            orderTotal.setTotalMonth(totalMonth);
            orderTotal.setTotalYear(totalYear);
        }
        return orderTotal;
    }

    private long total(List<Order> orders) {
        long total;
        if (orders.isEmpty()) {
            total = 0;
        } else {
            total = orders.stream().mapToLong(Order::getTotal).sum();
        }
        return total;
    }
}
