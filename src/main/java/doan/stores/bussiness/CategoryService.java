package doan.stores.bussiness;

import doan.stores.domain.Category;
import doan.stores.dto.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    List<Category> findCategoriesByActive(int active);

    void saveCategory(CategoryRequest request);

    Category findCategoryByNameAndActive(String name, int active);

    Category findCategoryById(Long id);

    void deleteCategory(Long id);

    boolean existCategory(String name, String nameOld);

}
