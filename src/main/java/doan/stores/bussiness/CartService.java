package doan.stores.bussiness;

import doan.stores.domain.Order;
import doan.stores.domain.OrderDetail;

import java.util.List;

public interface CartService {
    void addCart(Long productId);

    Order viewCart();

    List<OrderDetail> viewDetailCart();

    boolean updateQuantity(Long itemId, int quantity);

    void deleteItemCart(Long orderDetailId);

    void changeQuantityCart(Long orderDetailId, boolean add);

    void payCart();
}
