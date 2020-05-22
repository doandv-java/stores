package doan.stores.persistenct;

import doan.stores.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByNameEqualsAndActiveIs(String name, int active);

    List<Category> findCategoriesByActiveIs(int active);

    Category findCategoryByNameIs(String name);

    Category findCategoryById(Long id);
//    @Query(name="SELECT c FROM Categories c WHERE UPPER(c.name) LIKE CONCAT('%',:name,'%')",nativeQuery = true)
    List<Category> findCategoriesByNameIsContaining(String name);
}
