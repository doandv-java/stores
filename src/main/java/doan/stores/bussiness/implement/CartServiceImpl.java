package doan.stores.bussiness.implement;

import doan.stores.bussiness.CartService;
import doan.stores.bussiness.OrderItemService;
import doan.stores.bussiness.ProductService;
import doan.stores.bussiness.WarehouseService;
import doan.stores.domain.*;
import doan.stores.enums.StatusEnum;
import doan.stores.persistenct.OrderDetailRepositosy;
import doan.stores.persistenct.OrderRepository;
import doan.stores.utils.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepositosy orderDetailRepositosy;
    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public boolean addCart(Long productId) {
        User user = commonService.getPrincipal();
        Order order = orderRepository.findOrderByStatusIsAndUserIdIs(StatusEnum.CART.getCode(), user.getId());
        //Chua co cart
        if (order == null) {
            Product product = productService.findProductById(productId);
            if (!checkAddCartQuantity(productId, 1)) {
                return false;
            } else {
                order = new Order();
                order.setUserId(user.getId());
                order.setStatus(StatusEnum.CART.getCode());
                order.setLastUpdate(Dates.now());
                order.setCreateDay(Dates.now());
                order.setUser(user);
                order.setTotal((long) (product.getPrice() * 1));
                order = orderRepository.save(order);
                orderItemService.addCart(productId, order.getId());
                return true;
            }
        } else {
            Warehouse warehouse = warehouseService.findWarehouse(productId);
            if (warehouse == null) {
                return false;
            } else if (warehouse.getQuantity() - 1 < 0) {
                return false;
            } else {
                order.setLastUpdate(Dates.now());

                orderItemService.addCart(productId, order.getId());
                Product product = productService.findProductById(productId);
                order.setTotal(order.getTotal() + (long) (product.getPrice() * 1));
                orderRepository.save(order);
                return true;
            }
        }

    }

    @Override
    public boolean addCart(Long productId, int quantity) {
        User user = commonService.getPrincipal();
        Order order = orderRepository.findOrderByStatusIsAndUserIdIs(StatusEnum.CART.getCode(), user.getId());
        //Chua co cart
        if (order == null) {

            Product product = productService.findProductById(productId);
            if (!checkAddCartQuantity(productId, quantity)) {
                return false;
            } else {
                order = new Order();
                order.setUserId(user.getId());
                order.setStatus(StatusEnum.CART.getCode());
                order.setLastUpdate(Dates.now());
                order.setCreateDay(Dates.now());
                order.setUser(user);
                order.setTotal((long) (product.getPrice() * quantity));
                order = orderRepository.save(order);
                orderItemService.addCart(productId, order.getId(), quantity);
                return true;
            }
        } else {
            Warehouse warehouse = warehouseService.findWarehouse(productId);
            if (warehouse.getQuantity() - quantity >= 0) {
                order.setLastUpdate(Dates.now());
                orderItemService.addCart(productId, order.getId(), quantity);
                Product product = productService.findProductById(productId);
                order.setTotal(order.getTotal() + (long) (product.getPrice() * quantity));
                orderRepository.save(order);
                return true;
            } else {
                return false;
            }
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
        warehouseService.updateQuantity(orderDetail.getProductId(), orderDetail.getQuantity());
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
    public boolean updateQuantity(Long itemId, int quantity) {
        OrderDetail orderDetail = orderDetailRepositosy.getOne(itemId);
        int quantityOld = orderDetail.getQuantity();
        Warehouse warehouse = warehouseService.findWarehouse(orderDetail.getProductId());
        long warehouseQuantity = warehouse.getQuantity();
        if ((warehouseQuantity + quantityOld - quantity) >= 0) {
            orderDetail.setQuantity(quantity);
            orderDetail = orderDetailRepositosy.save(orderDetail);
            Order order = orderDetail.getOrder();
            order.setTotal(getTotal(orderDetail.getOrderId()));
            orderRepository.save(order);
            //update quantity of warehouse
            int changeQuantity = quantityOld - quantity;
            warehouseService.updateQuantity(orderDetail.getProductId(), changeQuantity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void payCart() {
        User user = commonService.getPrincipal();
        Order order = orderRepository.findOrderByStatusIsAndUserIdIs(StatusEnum.CART.getCode(), user.getId());
        order.setCreateDay(Dates.now());
        order.setLastUpdate(Dates.now());
        order.setStatus(StatusEnum.ORDER.getCode());
        orderRepository.save(order);
    }

    private Long getTotal(Long orderId) {
        List<OrderDetail> items = orderDetailRepositosy.getOrderDetailsByOrderIdIs(orderId);
        long total;
        if (!items.isEmpty()) {
            total = items.stream().mapToLong(item -> (long) (item.getProduct().getPrice() * item.getQuantity())).sum();
            return total;
        } else {
            return 0L;
        }
    }

    private boolean checkAddCartQuantity(Long productId, int quantity) {
        Warehouse warehouse = warehouseService.findWarehouse(productId);
        if (warehouse == null) {
            return false;
        } else return warehouse.getQuantity() - quantity >= 0;
    }

}
