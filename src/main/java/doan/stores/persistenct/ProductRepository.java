package doan.stores.persistenct;

import doan.stores.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByCategoryId(long categoryId);

    List<Product> findProductsByDeletedIs(int deleted);

    Product findProductById(Long id);

    Product findProductByNameIsAndDeletedIs(String name, int deleted);


}
