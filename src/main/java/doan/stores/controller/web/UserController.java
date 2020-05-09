package doan.stores.controller.web;

import doan.stores.bussiness.*;
import doan.stores.bussiness.implement.CommonService;
import doan.stores.domain.*;
import doan.stores.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    private OrderService orderService;

    @GetMapping("/home")
    public ModelAndView viewHome() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        List<Category> categories = categoryService.findCategoriesByActive(1);
        List<Supply> supplies = supplyService.findSuppliesByDeleted(Constants.DELETE.FALSE);
        List<Advertise> advertises = advertiseService.findAllAdvertise();
        List<Product> products = productService.findProductsByDeleted(Constants.DELETE.FALSE);
        mav.addObject("user", user);
        mav.addObject("categories", categories);
        mav.addObject("supplies", supplies);
        mav.addObject("advertises", advertises);
        mav.addObject("products", products);

        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/cart")
    public ModelAndView viewCart() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.getPrincipal();
        if (user == null) {
            mav.setViewName("redirect:/login");
        } else {
            List<Category> categories = categoryService.findCategoriesByActive(1);
            List<Supply> supplies = supplyService.findSuppliesByDeleted(Constants.DELETE.FALSE);
            Order order = orderService.viewCart();
            List<OrderDetail> orderDetails = orderService.viewDetailCart();
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
        orderService.deleteItemCart(orderDetailId);
        mav.setViewName("redirect:/cart");
        return mav;
    }

    @GetMapping("/cart/{id}/down")
    public ModelAndView downItemCart(@PathVariable("id") Long orderDetailId) {
        ModelAndView mav = new ModelAndView();
        orderService.changeQuantityCart(orderDetailId, false);
        mav.setViewName("redirect:/cart");
        return mav;
    }

    @GetMapping("/cart/{id}/up")
    public ModelAndView upItemCart(@PathVariable("id") Long orderDetailId) {
        ModelAndView mav = new ModelAndView();
        orderService.changeQuantityCart(orderDetailId, true);
        mav.setViewName("redirect:/cart");
        return mav;
    }

    @PostMapping("/cart/pay")
    @ResponseBody
    public Map<String, Object> payCart() {
        Map<String, Object> map = new HashMap<>();
        orderService.payCart();
        map.put("status",200);
        return map;
    }

    @GetMapping("/order/history")
    public ModelAndView viewOrderHistory() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("order_history");
        return mav;
    }

    @GetMapping("/product")
    public ModelAndView viewProduct() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("products");
        return mav;
    }

    @GetMapping("/detail")
    public ModelAndView viewDetails() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("details");
        return mav;
    }
}
