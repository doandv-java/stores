package doan.stores.controller.web;

import doan.stores.bussiness.OrderService;
import doan.stores.bussiness.implement.CommonService;
import doan.stores.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/cart")
public class CartController {


    @Autowired
    private CommonService commonService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/{id}")
    @ResponseBody
    public Map<String, Object> sendCart(@PathVariable("id") Long productId) {
        Map<String, Object> map = new HashMap<>();
        User user = commonService.getPrincipal();
        if (user == null) {
            map.put("status", 101);
        } else {
            map.put("status", 200);
            orderService.addCart(productId);
        }
        return map;
    }

}