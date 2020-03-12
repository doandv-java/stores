package doan.stores.persistenct;

import doan.stores.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByNameEqualsAndActiveIs(String name, int active);

    List<Category> findCategoriesByActiveIs(int active);
}
