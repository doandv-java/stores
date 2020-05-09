package doan.stores.bussiness;

import doan.stores.domain.Order;
import doan.stores.domain.OrderDetail;

import java.util.List;

public interface OrderService {
    void addCart(Long productId);

    Order viewCart();

    List<OrderDetail> viewDetailCart();


    void deleteItemCart(Long orderDetailId);

    void changeQuantityCart(Long orderDetailId, boolean add);

    void payCart();
}
