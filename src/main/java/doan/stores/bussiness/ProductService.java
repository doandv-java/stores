package doan.stores.bussiness;

import doan.stores.domain.Product;
import doan.stores.dto.request.ProductRequest;

import java.util.List;

public interface ProductService {

    List<Product> findProductsByDeleted(int deleted);

    void saveProduct(ProductRequest request);

    ProductRequest getProductById(Long id);

    void deleteProduct(Long id);

    Product findProductByName(String name);

    Product findProductById(Long id);

    boolean existProduct(String name, String nameOld);

}
