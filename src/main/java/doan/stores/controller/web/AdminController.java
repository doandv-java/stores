package doan.stores.controller.web;

import doan.stores.bussiness.*;
import doan.stores.bussiness.implement.CommonService;
import doan.stores.domain.*;
import doan.stores.dto.request.ProductRequest;
import doan.stores.dto.request.UserRequest;
import doan.stores.dto.response.ErrorResponse;
import doan.stores.enums.RoleEnum;
import doan.stores.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is controller to view page of admin
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AdvertiseService advertiseService;

    @Autowired
    private ProductService productService;

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/home")
    public ModelAndView viewHome() {
        ModelAndView mav = new ModelAndView();
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByUserName(principal.getUsername());
        mav.addObject("user", user);
        mav.setViewName("admin/home");
        return mav;
    }

    @GetMapping("/profile")
    public ModelAndView viewProfile() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        mav.addObject("user", user);
        mav.setViewName("admin/profile");
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

    @GetMapping("/employee")
    public ModelAndView viewEmployee() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<User> employees = userService.findUsersByRole(RoleEnum.ROLE_EMP);
        mav.addObject("user", user);
        mav.addObject("employees", employees);
        mav.setViewName("admin/employee/list");
        return mav;
    }

    @GetMapping(path = "/employee/{id}")
    public ModelAndView viewEmployeeForm(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        UserRequest employee = userService.findUserById(id);
        mav.addObject("user", user);
        mav.addObject("employee", employee);
        mav.setViewName("admin/employee/form");
        return mav;
    }


    @GetMapping(path = "/employee/")
    public ModelAndView viewEmployeeCreateForm() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        UserRequest employee = new UserRequest();
        employee.setRole(RoleEnum.ROLE_EMP.getText());
        mav.addObject("user", user);
        mav.addObject("employee", employee);
        mav.setViewName("admin/employee/form");
        return mav;
    }

    @GetMapping("/product")
    public ModelAndView viewProduct() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Product> products = productService.findProductsByDeleted(Constants.DELETE.FALSE);
        mav.addObject("user", user);
        mav.addObject("products", products);
        mav.setViewName("admin/products/list");
        return mav;
    }

    @GetMapping(path = "/product/{id}")
    public ModelAndView viewProductForm(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        ProductRequest product = productService.getProductById(id);
        List<Supply> supplies = supplyService.findSuppliesByDeleted(Constants.DELETE.FALSE);
        List<Category> categories = categoryService.findCategoriesByActive(1);
        mav.addObject("supplies", supplies);
        mav.addObject("categories", categories);
        mav.addObject("user", user);
        mav.addObject("product", product);
        mav.setViewName("admin/products/form");
        return mav;
    }


    @GetMapping(path = "/product/")
    public ModelAndView viewProductCreateForm() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        ProductRequest product = new ProductRequest();
        List<Supply> supplies = supplyService.findSuppliesByDeleted(Constants.DELETE.FALSE);
        List<Category> categories = categoryService.findCategoriesByActive(1);
        mav.addObject("supplies", supplies);
        mav.addObject("categories", categories);
        mav.addObject("user", user);
        mav.addObject("product", product);
        mav.setViewName("admin/products/form");
        return mav;
    }

    @PostMapping("/product")
    @ResponseBody
    public Map<String, Object> updateProduct(@Valid ProductRequest request, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        List<ErrorResponse> errors = commonService.bindingResult(result);
        if (errors.isEmpty()) {
            boolean exist = productService.existProduct(request.getName(), request.getNameOld());
            if (exist) {
                errors.add(new ErrorResponse("name", "Sản phẩm đã tồn tại"));
                map.put("status", 101);
                map.put("errors", errors);
            } else {
                productService.saveProduct(request);
                map.put("status", 200);
            }
        } else {
            map.put("status", 101);
            map.put("errors", errors);
        }
        return map;
    }

    @GetMapping("/category")
    public ModelAndView viewCategory() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(1);
        mav.addObject("user", user);
        mav.addObject("categories", categories);
        mav.setViewName("admin/category/list");
        return mav;
    }

    @GetMapping("/order")
    public ModelAndView viewOrder() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/orders/list");
        return mav;
    }

    @GetMapping("/advertise")
    public ModelAndView viewAdvertise() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        mav.addObject("user", user);
        mav.addObject("advertises", advertises);
        mav.setViewName("admin/advertise/list");
        return mav;
    }

    @GetMapping("/supply")
    public ModelAndView viewSupply() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Supply> supplies = supplyService.findSuppliesByDeleted(Constants.DELETE.FALSE);
        mav.addObject("user", user);
        mav.addObject("supplies", supplies);
        mav.setViewName("admin/supply/list");
        return mav;
    }

    @GetMapping("/warehouse")
    public ModelAndView viewWarehouse() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Warehouse> warehouses = warehouseService.getWarehouses();
        mav.addObject("user", user);
        mav.addObject("warehouses", warehouses);
        mav.setViewName("admin/warehouse/list");
        return mav;
    }

}
