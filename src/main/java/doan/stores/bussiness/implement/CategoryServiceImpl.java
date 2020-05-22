package doan.stores.bussiness.implement;

import doan.stores.bussiness.CategoryService;
import doan.stores.domain.Category;
import doan.stores.domain.Product;
import doan.stores.dto.request.CategoryRequest;
import doan.stores.persistenct.CategoryRepository;
import doan.stores.persistenct.ProductRepository;
import doan.stores.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Category> findCategoriesByActive(int active) {
        log.info("active:{}", active);
        return categoryRepository.findCategoriesByActiveIs(active);
    }


    @Override
    public void saveCategory(CategoryRequest request) {
        Category category;
        if (request.getId() == null) {
            category = new Category();
        } else {
            category = categoryRepository.getOne(request.getId());
        }
        category.setId(request.getId());
        String name = StringUtils.capitalize(StringUtils.trimToEmpty(request.getName()).toLowerCase());
        category.setName(name);
        category.setDetail(request.getDetail());
        category.setActive(request.getActive());
        categoryRepository.save(category);
    }

    @Override
    public Category findCategoryByNameAndActive(String name, int active) {
        return categoryRepository.findCategoryByNameEqualsAndActiveIs(name, active);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByNameIs(name);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.getOne(id);
        List<Product> products = productRepository.findProductsByCategoryId(category.getId());
        if (!products.isEmpty()) {
            products.forEach(product -> product.setDeleted(Constants.DELETE.TRUE));
        }
        productRepository.saveAll(products);
        category.setActive(Constants.DELETE.TRUE);
        categoryRepository.save(category);
    }

    public List<Long> getCategoryId(String name) {
        name = StringUtils.capitalize(StringUtils.trimToEmpty(name).toLowerCase());
        List<Category> categories = categoryRepository.findCategoriesByNameIsContaining(name);
        if (categories.isEmpty()) {
            return null;
        } else {
            ArrayList<Long> ids = new ArrayList<>();
            for (Category category : categories) {
                ids.add(category.getId());
            }
            return ids;
        }

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
