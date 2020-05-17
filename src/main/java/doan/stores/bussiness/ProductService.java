package doan.stores.bussiness;

import doan.stores.domain.Product;
import doan.stores.dto.request.ProductRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> findProductsByDeleted(int deleted);

    List<Product> findProductsByCategoryId(Long categoryId, int deleted);

    List<Product> findProductsBySupplyId(Long supplyId, int deleted);

    List<Product> findProductsByPageRequestAndDeletedAndCategoryOrSupply(Pageable pageable, int deleted,
                                                                         Long categoryId, Long supplyId);

    void saveProduct(ProductRequest request);

    ProductRequest getProductById(Long id);

    void deleteProduct(Long id);

    Product findProductByName(String name);

    Product findProductById(Long id);

    boolean existProduct(String name, String nameOld);


}
