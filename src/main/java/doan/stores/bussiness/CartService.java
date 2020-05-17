package doan.stores.bussiness;

import doan.stores.domain.Order;
import doan.stores.domain.OrderDetail;

import java.util.List;

public interface CartService {
    boolean addCart(Long productId);

    boolean addCart(Long productId, int quantity);

    Order viewCart();

    List<OrderDetail> viewDetailCart();

    boolean updateQuantity(Long itemId, int quantity);

    void deleteItemCart(Long orderDetailId);

    void changeQuantityCart(Long orderDetailId, boolean add);

    void payCart();
}
