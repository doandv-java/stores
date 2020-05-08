package doan.stores.bussiness.implement;

import doan.stores.bussiness.ProductService;
import doan.stores.bussiness.WarehouseService;
import doan.stores.domain.Warehouse;
import doan.stores.dto.request.WarehouseRequest;
import doan.stores.persistenct.WarehouseRepository;
import doan.stores.utils.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ProductService productService;

    @Override
    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public void saveWarehouse(WarehouseRequest warehouseRequest) {
        Warehouse warehouse;
        if (warehouseRequest.getId() == null) {
            //create
            warehouse = warehouseRepository.findWarehouseByProductIdIs(warehouseRequest.getProductId());
            if (warehouse == null) {
                warehouse = new Warehouse();
                warehouse.setProductId(warehouseRequest.getProductId());
                warehouse.setQuantity(warehouseRequest.getQuantity());

            } else {
                long quantityOld = warehouse.getQuantity();
                warehouse.setQuantity(quantityOld + warehouseRequest.getQuantity());
            }

        } else {
            //update
            warehouse = warehouseRepository.getOne(warehouseRequest.getId());
            warehouse.setQuantity(warehouseRequest.getQuantity());
        }
        warehouse.setLastUpdate(Dates.now());
        warehouse.setUserId(commonService.getPrincipal().getId());
        warehouseRepository.save(warehouse);
    }
}
