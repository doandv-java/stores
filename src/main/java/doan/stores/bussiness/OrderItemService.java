package doan.stores.bussiness;

import doan.stores.dto.response.ProductHot;

import java.util.List;

public interface OrderItemService {
    void addCart(Long productId, Long orderId);

    void addCart(Long productId, Long orderId, int quantity);

    List<ProductHot> topProductHot();
}
