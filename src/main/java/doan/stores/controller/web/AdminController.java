package doan.stores.controller.web;

import doan.stores.bussiness.CategoryService;
import doan.stores.bussiness.SupplyService;
import doan.stores.bussiness.UserService;
import doan.stores.bussiness.implement.CommonService;
import doan.stores.domain.Category;
import doan.stores.domain.Supply;
import doan.stores.domain.User;
import doan.stores.dto.request.UserRequest;
import doan.stores.dto.response.ErrorResponse;
import doan.stores.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
        mav.setViewName("admin/employee/list");
        return mav;
    }

    @GetMapping("/product")
    public ModelAndView viewProduct() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/products/list");
        return mav;
    }

    @GetMapping("/category")
    public ModelAndView viewCategory() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(Constants.DELETE.FALSE);
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
        mav.setViewName("admin/warehouse/list");
        return mav;
    }

}
