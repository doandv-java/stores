package doan.stores.bussiness.implement;

import doan.stores.bussiness.CategoryService;
import doan.stores.domain.Category;
import doan.stores.dto.request.CategoryRequest;
import doan.stores.persistenct.CategoryRepository;
import doan.stores.persistenct.ProductRepository;
import doan.stores.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Category> findCategoriesByActive(int active) {
        return categoryRepository.findCategoriesByActiveIs(active);
    }


    @Override
    public void saveCategory(CategoryRequest request) {
        Category category;
        if (request.getId()==null) {
            category = new Category();
        } else {
            category = categoryRepository.getOne(request.getId());
        }
        category.setId(request.getId());
        category.setName(request.getName());
        category.setDetail(request.getDetail());
        category.setActive(request.getActive());
        categoryRepository.save(category);
    }

    @Override
    public Category findCategoryByNameAndActive(String name, int active) {
        return categoryRepository.findCategoryByNameEqualsAndActiveIs(name, active);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.getOne(id);
        category.setActive(Constants.DELETE.TRUE);
        categoryRepository.save(category);
    }

    @Override
    public boolean existCategory(String name, String nameOld) {
        if (!StringUtils.isEmpty(name)) {
            Category category = categoryRepository.findCategoryByNameEqualsAndActiveIs(name, Constants.DELETE.FALSE);
            if (StringUtils.isEmpty(nameOld)) {
                return category != null;
            } else {
                boolean check = name.equals(nameOld) || category == null;
                return !check;
            }
        }
        return false;
    }
}
