package doan.stores.bussiness.implement;

import doan.stores.bussiness.ProductService;
import doan.stores.bussiness.WarehouseService;
import doan.stores.domain.OrderDetail;
import doan.stores.domain.Product;
import doan.stores.domain.User;
import doan.stores.domain.Warehouse;
import doan.stores.dto.request.WarehouseRequest;
import doan.stores.persistenct.OrderDetailRepositosy;
import doan.stores.persistenct.WarehouseRepository;
import doan.stores.utils.Dates;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private OrderDetailRepositosy orderDetailDao;

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
        Product product = productService.findProductById(warehouseRequest.getProductId());
        warehouse.setProduct(product);
        warehouse.setProductId(product.getId());
        warehouse.setLastUpdate(Dates.now());
        User user = commonService.getPrincipal();
        warehouse.setUser(user);
        warehouse.setUserId(user.getId());
        warehouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> top12Warehouse() {
        List<Warehouse> warehouses = warehouseRepository.top12Warehouse();
        return warehouses;
    }

    @Override
    public Warehouse findWarehouse(Long productID) {
        Warehouse warehouse = warehouseRepository.findWarehouseByProductIdIs(productID);
        return warehouse;
    }

    @Override
    public void addCart(Long productId, int quantity) {
        Warehouse warehouse = warehouseRepository.findWarehouseByProductIdIs(productId);
        if (warehouse == null) {
            log.error("Product has Id:{} not in warehouse", productId);
        } else {
            long quantityNew = warehouse.getQuantity() - quantity;
            warehouse.setQuantity(quantityNew);
            warehouseRepository.save(warehouse);
        }
    }

    @Override
    public void updateQuantity(Long productId, int quantity) {
        Warehouse warehouse = warehouseRepository.findWarehouseByProductIdIs(productId);
        if (warehouse == null) {
            log.error("Product has Id:{} not in warehouse", productId);
        } else {
            long quantityNew = warehouse.getQuantity() + quantity;
            warehouse.setQuantity(quantityNew);
            warehouseRepository.save(warehouse);
        }
    }

    @Override
    public void cancelOrder(Long orderId) {
        List<OrderDetail> items = orderDetailDao.getOrderDetailsByOrderIdIs(orderId);
        if (!items.isEmpty()) {
            items.forEach(item -> {
                updateQuantity(item.getProductId(), item.getQuantity());
            });
        } else {
            log.error("No items in order has id:{}", orderId);
        }
    }
}
