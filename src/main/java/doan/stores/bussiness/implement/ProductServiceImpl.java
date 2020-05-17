package doan.stores.bussiness.implement;

import doan.stores.bussiness.CategoryService;
import doan.stores.bussiness.ImageService;
import doan.stores.bussiness.ProductService;
import doan.stores.bussiness.SupplyService;
import doan.stores.domain.Category;
import doan.stores.domain.Product;
import doan.stores.domain.Supply;
import doan.stores.dto.request.ProductRequest;
import doan.stores.persistenct.ProductRepository;
import doan.stores.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private SupplyService supplyService;

    @Override
    public List<Product> findProductsByDeleted(int deleted) {
        List<Product> products = productRepository.findProductsByDeletedIs(deleted);
        return products;
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId, int deleted) {
        List<Product> products = productRepository.findProductsByCategoryIdIsAndDeletedIs(categoryId, deleted);
        return products;
    }

    @Override
    public List<Product> findProductsBySupplyId(Long supplyId, int deleted) {
        Supply supply = supplyService.findSupplyById(supplyId);
        List<Product> products = productRepository.findProductsByProducerIsAndDeletedIs(supply.getName(), deleted);
        products.sort((product, t1) -> {
            if (product.getPrice() > t1.getPrice()) {
                return 1;
            } else if (product.getPrice() == t1.getPrice()) {
                return 0;
            } else {
                return -1;
            }
        });
        return products;
    }

    @Override
    public List<Product> findProductsByPageRequestAndDeletedAndCategoryOrSupply(Pageable pageable, int deleted, Long categoryId, Long supplyId) {
        Supply supply = supplyService.findSupplyById(supplyId);
        List<Product> products = productRepository.findProductsByCategoryIdIsOrProducerIsAndDeletedIs(categoryId, supply.getName(), deleted, pageable);
        return products;
    }

    @Override
    public void saveProduct(ProductRequest request) {
        Product product;
        if (request.getId() == null) {
            product = new Product();
            product.setDeleted(Constants.DELETE.FALSE);
        } else {
            product = productRepository.findProductById(request.getId());
        }
        product.setName(request.getName());
        product.setProducer(request.getProducer());
        product.setPrice(Double.parseDouble(request.getPrice()));
        Category category = categoryService.findCategoryById(Long.parseLong(request.getCategoryId()));
        product.setCategory(category);
        product.setCategoryId(category.getId());
        product.setDetail(request.getDetail());
        product.setImageLink(imageService.saveImage(request.getImage(), request.getImageLink()));
        product.setOs(request.getOs());
        product.setCpu(request.getCpu());
        product.setGpu(request.getGpu());
        product.setRam(request.getRam());
        product.setScreen(request.getScreen());
        product.setStorage(request.getStorage());
        if (StringUtils.isEmpty(request.getWeight())) {
            product.setWeight(0);
        } else {
            product.setWeight(Double.parseDouble(request.getWeight()));
        }
        product.setReleaseYear(request.getReleaseYear());
        productRepository.save(product);
    }

    @Override
    public ProductRequest getProductById(Long id) {
        if (id == null) {
            return new ProductRequest();
        } else {
            Product product = productRepository.findProductById(id);
            ProductRequest request = new ProductRequest();
            request.setId(product.getId());
            request.setName(product.getName());
            request.setProducer(product.getProducer());
            request.setPrice(Double.toString(product.getPrice()));
            request.setCategoryId(product.getCategory().getId().toString());
            request.setDetail(product.getDetail());
            request.setImageLink(product.getImageLink());
            request.setOs(product.getOs());
            request.setCpu(product.getCpu());
            request.setGpu(product.getGpu());
            request.setRam(product.getRam());
            request.setScreen(product.getScreen());
            request.setStorage(product.getStorage());
            request.setWeight(Double.toString(product.getWeight()));
            request.setReleaseYear(product.getReleaseYear());
            request.setDeleted(product.getDeleted());
            request.setNameOld(product.getName());
            return request;
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findProductById(id);
        product.setDeleted(Constants.DELETE.TRUE);
        productRepository.save(product);
    }

    @Override
    public Product findProductByName(String name) {
        Product product = productRepository.findProductByNameIsAndDeletedIs(name, Constants.DELETE.FALSE);
        return product;
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public boolean existProduct(String name, String nameOld) {
        if (!StringUtils.isEmpty(name)) {
            Product product = productRepository.findProductByNameIsAndDeletedIs(name, Constants.DELETE.FALSE);
            if (StringUtils.isEmpty(nameOld)) {
                return product != null;
            } else {
                boolean check = name.equals(nameOld) || product == null;
                return !check;
            }
        }
        return false;
    }
}
