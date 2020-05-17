package doan.stores.bussiness.implement;

import doan.stores.bussiness.OrderItemService;
import doan.stores.bussiness.WarehouseService;
import doan.stores.domain.Order;
import doan.stores.domain.OrderDetail;
import doan.stores.domain.Product;
import doan.stores.dto.response.ProductHot;
import doan.stores.persistenct.OrderDetailRepositosy;
import doan.stores.persistenct.OrderRepository;
import doan.stores.persistenct.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderDetailRepositosy orderDetailDao;

    @Autowired
    private ProductRepository productDao;

    @Autowired
    private OrderRepository orderDao;

    @Autowired
    private WarehouseService warehouseService;

    @Override
    public void addCart(Long productId, Long orderId) {
        OrderDetail orderDetail = orderDetailDao.findOrderDetailByOrderIdIsAndProductIdIs(orderId, productId);
        if (orderDetail == null) {
            Product product = productDao.findProductById(productId);
            Order order = orderDao.findOrderById(orderId);
            orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setProduct(product);
            orderDetail.setOrder(order);
            orderDetail.setQuantity(1);
            orderDetail.setProductId(productId);
        } else {
            Product product = productDao.findProductById(productId);
            Order order = orderDao.findOrderById(orderId);
            int new_quantity = orderDetail.getQuantity() + 1;
            orderDetail.setQuantity(new_quantity);
        }
        orderDetailDao.save(orderDetail);
        //cap nhat warehouse
        warehouseService.addCart(productId, 1);

    }

    @Override
    public void addCart(Long productId, Long orderId, int quantity) {
        OrderDetail orderDetail = orderDetailDao.findOrderDetailByOrderIdIsAndProductIdIs(orderId, productId);
        if (orderDetail == null) {
            Product product = productDao.findProductById(productId);
            Order order = orderDao.findOrderById(orderId);
            orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setProduct(product);
            orderDetail.setOrder(order);
            orderDetail.setQuantity(quantity);
            orderDetail.setProductId(productId);

        } else {
            Product product = productDao.findProductById(productId);
            Order order = orderDao.findOrderById(orderId);
            int new_quantity = orderDetail.getQuantity() + quantity;
            orderDetail.setQuantity(new_quantity);
        }
        orderDetailDao.save(orderDetail);
        //cap nhat warehouse
        warehouseService.addCart(productId, quantity);
    }

    @Override
    public List<ProductHot> topProductHot() {
        Long[] listProductId = orderDetailDao.findTop12ProductId();
        if (listProductId == null) {
            return Collections.EMPTY_LIST;
        } else {
            List<ProductHot> result = new ArrayList<>();
            Arrays.stream(listProductId).forEach(productId -> {
                Product product = productDao.findProductById(productId);
                ProductHot productHot = new ProductHot();
                productHot.setProductId(productId);
                productHot.setName(product.getName());
                productHot.setProducer(product.getProducer());
                productHot.setCountOrder(orderDetailDao.countOrderDetailsByProductId(productId));
                result.add(productHot);
            });
            return result;
        }
    }
}
