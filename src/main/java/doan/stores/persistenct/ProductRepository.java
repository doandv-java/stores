package doan.stores.persistenct;

import doan.stores.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByCategoryId(long categoryId);

    List<Product> findProductsByCategoryIdIsAndDeletedIsOrderByPriceAscNameDesc(long categoryId, int deleted);

    List<Product> findProductsByProducerIsAndDeletedIsOrderByPriceAscNameDesc(String producer, int deleted);

    List<Product> findProductsByDeletedIsOrderByPriceAsc(int deleted);

    Product findProductById(Long id);

    List<Product> findProductsByIdInAndDeletedIsOrderByPriceAscNameDesc(Long[] productList, int deleted);

    Product findProductByNameIsAndDeletedIs(String name, int deleted);

    List<Product> findProductsByDeletedIs(int deleted, Pageable page);

    List<Product> findProductsByCategoryIdIsOrProducerIsAndDeletedIs(Long categoryID, String producer, int deleted, Pageable page);

    List<Product> findProductsByNameContainsOrCategoryIdInAndDeletedIsOrderByPriceAsc(String name, List<Long> categoryId, int deleted);

    List<Product> getProductsByNameContainsAndDeletedIsOrderByPriceAsc(String name, int deleted);

    List<Product> getProductsByProducerContainingOrderByPriceAsc(String nameProducer);

}
