package doan.stores.bussiness;

import doan.stores.domain.Warehouse;
import doan.stores.dto.request.WarehouseRequest;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> getWarehouses();

    void saveWarehouse(WarehouseRequest warehouseRequest);

    List<Warehouse> top5Warehouse();
}
