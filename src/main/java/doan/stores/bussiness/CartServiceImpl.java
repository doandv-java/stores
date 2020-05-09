package doan.stores.bussiness;

import doan.stores.bussiness.implement.CommonService;
import doan.stores.domain.Order;
import doan.stores.domain.OrderDetail;
import doan.stores.domain.Product;
import doan.stores.domain.User;
import doan.stores.enums.StatusEnum;
import doan.stores.persistenct.OrderDetailRepositosy;
import doan.stores.persistenct.OrderRepository;
import doan.stores.utils.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CartServiceImpl implements OrderService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepositosy orderDetailRepositosy;

    @Override
    public void addCart(Long productId) {
        User user = commonService.getPrincipal();
        Order order = orderRepository.findOrderByStatusIsAndUserIdIs(StatusEnum.CART.getCode(), user.getId());
        //Chua co cart
        if (order == null) {
            order = new Order();
            order.setUserId(user.getId());
            order.setStatus(StatusEnum.CART.getCode());
            order.setLastUpdate(Dates.now());
            order.setCreateDay(Dates.now());
            order = orderRepository.save(order);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(productId);
            orderDetail.setOrderId(order.getId());
            orderDetail.setOrder(order);
            Product product = productService.findProductById(productId);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(1);
            orderDetailRepositosy.save(orderDetail);
        } else {
            order.setLastUpdate(Dates.now());
            OrderDetail orderDetail = orderDetailRepositosy.findOrderDetailByOrderIdIsAndProductIdIs(order.getId(), productId);
            //chua co them moi
            Product product = productService.findProductById(productId);
            if (orderDetail == null) {
                orderDetail = new OrderDetail();
                orderDetail.setOrderId(order.getId());
                orderDetail.setProduct(product);
                orderDetail.setOrderId(order.getId());
                orderDetail.setOrder(order);
                orderDetail.setProductId(productId);
                orderDetail.setQuantity(1);
            } else {
                orderDetail.setQuantity(orderDetail.getQuantity() + 1);
            }
            orderRepository.save(order);
            orderDetailRepositosy.save(orderDetail);
        }

    }

    @Override
    public Order viewCart() {
        User user = commonService.getPrincipal();
        Order order = orderRepository.findOrderByStatusIsAndUserIdIs(StatusEnum.CART.getCode(), user.getId());
        return order;
    }

    @Override
    public List<OrderDetail> viewDetailCart() {
        Order order = viewCart();
        if (order == null) {
            return Collections.emptyList();
        } else {
            List<OrderDetail> orderDetails = orderDetailRepositosy.getOrderDetailsByOrderIdIs(order.getId());
            return orderDetails;
        }
    }

    @Override
    public void deleteItemCart(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepositosy.getOne(orderDetailId);
        Order order = orderDetail.getOrder();
        orderDetailRepositosy.delete(orderDetail);
        List<OrderDetail> list = orderDetailRepositosy.getOrderDetailsByOrderIdIs(order.getId());
        if (list.isEmpty()) {
            orderRepository.delete(order);
        } else {
            return;
        }
    }

    @Override
    public void changeQuantityCart(Long orderDetailId, boolean add) {
        int value = add ? 1 : -1;
        OrderDetail orderDetail = orderDetailRepositosy.getOne(orderDetailId);
        orderDetail.setQuantity(orderDetail.getQuantity() + value);
        orderDetailRepositosy.save(orderDetail);
    }

    @Override
    public void payCart() {
        User user = commonService.getPrincipal();
        Order order = orderRepository.findOrderByStatusIsAndUserIdIs(StatusEnum.CART.getCode(), user.getId());
        order.setStatus(StatusEnum.PAYED.getCode());
        orderRepository.save(order);
    }
}
