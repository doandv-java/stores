package doan.stores.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductHot {
    private Long productId;
    private String name;
    private String producer;
    private int countOrder;
}
