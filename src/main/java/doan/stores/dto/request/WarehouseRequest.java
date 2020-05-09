package doan.stores.dto.request;

import lombok.Data;

@Data
public class WarehouseRequest {

    private Long id;

    private Long productId;

    private Long quantity;

}
