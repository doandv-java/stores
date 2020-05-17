package doan.stores.bussiness;

import doan.stores.domain.Warehouse;
import doan.stores.dto.request.WarehouseRequest;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> getWarehouses();

    void saveWarehouse(WarehouseRequest warehouseRequest);

    Warehouse findWarehouse(Long productID);

    List<Warehouse> top12Warehouse();

    void addCart(Long productId, int quantity);

    void updateQuantity(Long productId, int quantity);

    void cancelOrder(Long orderId);
}
