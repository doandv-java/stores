package doan.stores.controller.web;

import doan.stores.bussiness.*;
import doan.stores.bussiness.implement.CommonService;
import doan.stores.domain.*;
import doan.stores.dto.request.UserRequest;
import doan.stores.dto.response.ErrorResponse;
import doan.stores.enums.StatusEnum;
import doan.stores.framework.Settings;
import doan.stores.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * View page of customer
 */
@Controller
public class UserController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private AdvertiseService advertiseService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private Settings settings;



    @GetMapping("/home")
    public ModelAndView viewHome() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        List<Product> products = orderService.top12ProductHot();

        mav.addObject("user", user);
        mav.addObject("categories", categories);
        mav.addObject("supplies", supplies);
        mav.addObject("advertises", advertises);
        mav.addObject("products", products);

        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/profile")
    public ModelAndView viewProfile() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        mav.addObject("user", user);
        mav.addObject("categories", categories);
        mav.addObject("supplies", supplies);
        mav.setViewName("profile");
        return mav;
    }

    @PostMapping("/profile")
    @ResponseBody
    public Map<String, Object> update(@Valid UserRequest request, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        List<ErrorResponse> errors = commonService.bindingResult(result);
        if (errors.isEmpty()) {
            boolean exist = userService.existUser(request.getUserName(), request.getUserNameOld());
            if (exist) {
                errors.add(new ErrorResponse("userName", "Email đã tồn tại"));
                map.put("status", 101);
                map.put("errors", errors);
            } else {
                userService.saveUser(request);
                map.put("status", 200);
            }
        } else {
            map.put("status", 101);
            map.put("errors", errors);
        }
        return map;
    }

    @GetMapping("/change-pass")
    public ModelAndView changePass() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("change_pass");
        return mav;
    }

    @GetMapping("/cart")
    public ModelAndView viewCart() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        if (user == null) {
            mav.setViewName("redirect:/login");
        } else {
            List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
            List<Supply> supplies = supplyService.findSuppliesByDeleted(Constants.DELETE.FALSE);
            Order order = cartService.viewCart();
            List<OrderDetail> orderDetails = cartService.viewDetailCart();
            mav.addObject("user", user);
            mav.addObject("categories", categories);
            mav.addObject("supplies", supplies);
            mav.addObject("order", order);
            mav.addObject("orderDetails", orderDetails);
            mav.setViewName("cart");
        }
        return mav;
    }

    @GetMapping("/cart/{id}/delete")
    public ModelAndView deleteItemCart(@PathVariable("id") Long orderDetailId) {
        ModelAndView mav = new ModelAndView();
        cartService.deleteItemCart(orderDetailId);
        mav.setViewName("redirect:/cart");
        return mav;
    }

    @GetMapping("/cart/{id}/down")
    public ModelAndView downItemCart(@PathVariable("id") Long orderDetailId) {
        ModelAndView mav = new ModelAndView();
        cartService.changeQuantityCart(orderDetailId, false);
        mav.setViewName("redirect:/cart");
        return mav;
    }

    @GetMapping("/cart/{id}/up")
    public ModelAndView upItemCart(@PathVariable("id") Long orderDetailId) {
        ModelAndView mav = new ModelAndView();
        cartService.changeQuantityCart(orderDetailId, true);
        mav.setViewName("redirect:/cart");
        return mav;
    }


    @PostMapping("/cart/pay")
    @ResponseBody
    public Map<String, Object> payCart() {
        Map<String, Object> map = new HashMap<>();
        cartService.payCart();
        map.put("status", 200);
        return map;
    }

    @GetMapping("/checkout")
    public ModelAndView checkOut() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        if (user == null) {
            mav.setViewName("redirect:/login");
        } else {
            List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
            List<Supply> supplies = supplyService.findSuppliesByDeleted(Constants.DELETE.FALSE);
            Order order = cartService.viewCart();
            Settings.Company company = settings.getCompany();
            List<OrderDetail> orderDetails = cartService.viewDetailCart();
            mav.addObject("user", user);
            mav.addObject("categories", categories);
            mav.addObject("supplies", supplies);
            mav.addObject("order", order);
            mav.addObject("orderDetails", orderDetails);
            mav.addObject("company", company);
            mav.setViewName("checkout");
        }
        return mav;
    }

    @GetMapping("/order/history")
    public ModelAndView viewOrderHistory() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Order> orders = orderService.findOrdersByUserId(user.getId());
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        StatusEnum[] statuses = StatusEnum.values();
        mav.addObject("user", user);
        mav.addObject("orders", orders);
        mav.addObject("statuses", statuses);
        mav.addObject("categories", categories);
        mav.addObject("supplies", supplies);
        mav.setViewName("order-history");
        return mav;
    }

    @GetMapping("/order/{id}")
    public ModelAndView viewOrderDetail(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        Order order = orderService.findOrderById(id);
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        List<OrderDetail> orderDetails = orderService.getItemInOrderByOrderId(id);
        mav.addObject("user", user);
        mav.addObject("order", order);
        mav.addObject("items", orderDetails);
        mav.addObject("categories", categories);
        mav.addObject("supplies", supplies);
        mav.setViewName("order-detail");
        return mav;
    }

    @DeleteMapping("/order/{id}")
    @ResponseBody
    public Map<String, Object> deleteOrder(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        if (orderService.cancelOrder(id)) {
            map.put("status", 200);
        } else {
            map.put("status", 101);
        }
        mav.setViewName("order-detail");
        return map;
    }

    @GetMapping("/shop")
    public ModelAndView viewProduct() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        List<Product> products = productService.findProductsByDeleted(Constants.DELETE.FALSE);
        int pageTotal = 0;
        int size = 6;
        List<Product> pageProducts;
        if (products.size() > 0) {
            if (products.size() % size != 0) {
                pageTotal = products.size() / size + 1;
            } else {
                pageTotal = products.size() / size;
            }
        }
        int startItem = 0;
        return getModelAndView(mav, user, categories, supplies, advertises, products, pageTotal, size, startItem);
    }

    private ModelAndView getModelAndView(ModelAndView mav, User user, List<Category> categories, List<Supply> supplies, List<Advertise> advertises, List<Product> products, int pageTotal, int size, int startItem) {
        List<Product> pageProducts;
        if (startItem > products.size()) {
            pageProducts = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + size, products.size());
            pageProducts = products.subList(startItem, toIndex);
        }
        mav.addObject("user", user);
        mav.addObject("categories", categories);
        mav.addObject("supplies", supplies);
        mav.addObject("advertises", advertises);
        mav.addObject("products", pageProducts);
        mav.addObject("pageTotal", pageTotal);
        mav.setViewName("shop");
        return mav;
    }

    @GetMapping("/shop/page/{page}")
    public ModelAndView viewProductPage(@PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        List<Product> products = productService.findProductsByDeleted(Constants.DELETE.FALSE);
        List<Product> pageProducts;
        int pageTotal = 0;
        int size = 6;
        if (products.size() > 0) {
            if (products.size() % size != 0) {
                pageTotal = products.size() / size + 1;
            } else {
                pageTotal = products.size() / size;
            }
        }
        int startItem = (page - 1) * size;
        return getModelAndView(mav, user, categories, supplies, advertises, products, pageTotal, size, startItem);
    }

    @GetMapping("/shop/category/{id}")
    public ModelAndView viewProductCategory(@PathVariable("id") Long categoryId) {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        List<Product> products = productService.findProductsByCategoryId(categoryId, Constants.DELETE.FALSE);
        int pageTotal = 0;
        int size = 6;
        List<Product> pageProducts;
        if (products.size() > 0) {
            if (products.size() % size != 0) {
                pageTotal = products.size() / size + 1;
            } else {
                pageTotal = products.size() / size;
            }
        }
        int startItem = 0;
        return getModelAndViewCategory(categoryId, mav, user, categories, supplies, advertises, products, pageTotal, size, startItem);
    }

    private ModelAndView getModelAndViewCategory(@PathVariable("id") Long categoryId, ModelAndView mav, User user, List<Category> categories, List<Supply> supplies, List<Advertise> advertises, List<Product> products, int pageTotal, int size, int startItem) {
        List<Product> pageProducts;
        if (startItem > products.size()) {
            pageProducts = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + size, products.size());
            pageProducts = products.subList(startItem, toIndex);
        }
        mav.addObject("user", user);
        mav.addObject("categories", categories);
        mav.addObject("supplies", supplies);
        mav.addObject("advertises", advertises);
        mav.addObject("products", pageProducts);
        mav.addObject("pageTotal", pageTotal);
        mav.addObject("categoryId", categoryId);
        mav.setViewName("shop_category");
        return mav;
    }

    @GetMapping("/shop/category/{id}/{page}")
    public ModelAndView viewProductCategoryPage(@PathVariable("id") Long categoryId, @PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        List<Product> products = productService.findProductsByCategoryId(categoryId, Constants.DELETE.FALSE);
        List<Product> pageProducts;
        int pageTotal = 0;
        int size = 6;
        if (products.size() > 0) {
            if (products.size() % size != 0) {
                pageTotal = products.size() / size + 1;
            } else {
                pageTotal = products.size() / size;
            }
        }
        int startItem = (page - 1) * size;
        return getModelAndViewCategory(categoryId, mav, user, categories, supplies, advertises, products, pageTotal, size, startItem);
    }

    @GetMapping("/shop/supply/{id}")
    public ModelAndView viewProductSupply(@PathVariable("id") Long supplyId) {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        List<Product> products = productService.findProductsBySupplyId(supplyId, Constants.DELETE.FALSE);
        int pageTotal = 0;
        int size = 6;
        List<Product> pageProducts;
        if (products.size() > 0) {
            if (products.size() % size != 0) {
                pageTotal = products.size() / size + 1;
            } else {
                pageTotal = products.size() / size;
            }
        }
        int startItem = 0;
        return getModelAndViewSupply(supplyId, mav, user, categories, supplies, advertises, products, pageTotal, size, startItem);

    }

    private ModelAndView getModelAndViewSupply(@PathVariable("id") Long supplyId, ModelAndView mav, User user, List<Category> categories, List<Supply> supplies, List<Advertise> advertises, List<Product> products, int pageTotal, int size, int startItem) {
        List<Product> pageProducts;
        if (startItem > products.size()) {
            pageProducts = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + size, products.size());
            pageProducts = products.subList(startItem, toIndex);
        }
        mav.addObject("user", user);
        mav.addObject("categories", categories);
        mav.addObject("supplies", supplies);
        mav.addObject("advertises", advertises);
        mav.addObject("products", pageProducts);
        mav.addObject("pageTotal", pageTotal);
        mav.addObject("supplyId", supplyId);
        mav.setViewName("shop_supply");
        return mav;
    }

    @GetMapping("/shop/supply/{id}/{page}")
    public ModelAndView viewProductSupplyPage(@PathVariable("id") Long supplyId, @PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        List<Product> products = productService.findProductsBySupplyId(supplyId, Constants.DELETE.FALSE);
        List<Product> pageProducts;
        int pageTotal = 0;
        int size = 6;
        if (products.size() > 0) {
            if (products.size() % size != 0) {
                pageTotal = products.size() / size + 1;
            } else {
                pageTotal = products.size() / size;
            }
        }
        int startItem = (page - 1) * size;
        return getModelAndViewSupply(supplyId, mav, user, categories, supplies, advertises, products, pageTotal, size, startItem);
    }


    @GetMapping("/product/{id}")
    public ModelAndView viewProductDetails(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        List<Product> products = productService.findProductsByDeleted(Constants.DELETE.FALSE);
        Product productDetail = productService.findProductById(id);
        mav.addObject("user", user);
        mav.addObject("categories", categories);
        mav.addObject("supplies", supplies);
        mav.addObject("advertises", advertises);
        mav.addObject("products", products);
        mav.addObject("productDetail", productDetail);
        mav.setViewName("product_detail");
        return mav;
    }
    @GetMapping("/search")
    public ModelAndView searchProduct(@RequestParam("keyword") String keyword) {
        ModelAndView mav = new ModelAndView();
        int pageNumber = 1;
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        List<Product> products = productService.searchProduct(keyword);
        int pageTotal = 0;
        int size = 12;
        List<Product> pageProducts;
        if (products.size() > 0) {
            if (products.size() % size != 0) {
                pageTotal = products.size() / size + 1;
            } else {
                pageTotal = products.size() / size;
            }
        }
        int startItem = (pageNumber - 1) * size;
        int toIndex = Math.min(startItem + size, products.size());
        pageProducts = products.subList(startItem, toIndex);
        mav.addObject("user", user);
        mav.addObject("categories", categories);
        mav.addObject("supplies", supplies);
        mav.addObject("advertises", advertises);
        mav.addObject("products", pageProducts);
        mav.addObject("pageTotal", pageTotal);
        mav.addObject("keyword", keyword);
        mav.setViewName("shop-search");
        return mav;
    }

    @GetMapping("/search/{page}")
    public ModelAndView searchProduct(@RequestParam("keyword") String keyword, @PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
        List<Supply> supplies = supplyService.findSuppliesByDeletedAndActive(Constants.DELETE.FALSE, Constants.ACTIVE.TRUE);
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        List<Product> products = productService.searchProduct(keyword);
        int pageTotal = 0;
        int size = 12;
        List<Product> pageProducts;
        if (products.size() > 0) {
            if (products.size() % size != 0) {
                pageTotal = products.size() / size + 1;
            } else {
                pageTotal = products.size() / size;
            }
        }
        int startItem = (page - 1) * size;
        int toIndex = Math.min(startItem + size, products.size());
        pageProducts = products.subList(startItem, toIndex);
        mav.addObject("user", user);
        mav.addObject("categories", categories);
        mav.addObject("supplies", supplies);
        mav.addObject("advertises", advertises);
        mav.addObject("products", pageProducts);
        mav.addObject("pageTotal", pageTotal);
        mav.addObject("keyword", keyword);
        mav.setViewName("shop-search");
        return mav;
    }
}
