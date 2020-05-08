package doan.stores.dto.request;

import doan.stores.domain.Product;
import lombok.Data;

@Data
public class WarehouseRequest {

    private Long id;

    private Long productId;

    private Long quantity;

    private Product product;

    private String action;

}
