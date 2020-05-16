package doan.stores.controller.api;

import doan.stores.bussiness.ProductService;
import doan.stores.domain.Product;
import doan.stores.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ResponseBody
    private Map<String, Object> getAllProduct() {
        Map<String, Object> map = new HashMap<>();
        List<Product> products = productService.findProductsByDeleted(Constants.DELETE.FALSE);
        if (products.isEmpty()) {
            map.put("status", 200);
            map.put("products", products);
        } else {
            map.put("status", 101);
        }
        return map;
    }

    @DeleteMapping("/{id}")
    private Map<String, Object> deleteProduct(@PathVariable("id") Long productId) {
        Map<String, Object> map = new HashMap<>();
        Product product = productService.findProductById(productId);
        if (product == null) {
            map.put("status", 101);
            map.put("products", "Product không tồn tại");
        } else {
            map.put("status", 200);
            productService.deleteProduct(productId);
        }
        return map;
    }
}
