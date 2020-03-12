package doan.stores.dto.response;

import doan.stores.domain.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String detail;
    private int active;
    List<Product> products;
}
