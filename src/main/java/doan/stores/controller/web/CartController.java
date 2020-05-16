package doan.stores.controller.web;

import doan.stores.bussiness.CartService;
import doan.stores.bussiness.implement.CommonService;
import doan.stores.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/cart")
public class CartController {


    @Autowired
    private CommonService commonService;

    @Autowired
    private CartService cartService;

    @PostMapping("/{id}")
    @ResponseBody
    public Map<String, Object> sendCart(@PathVariable("id") Long productId) {
        Map<String, Object> map = new HashMap<>();
        User user = commonService.getPrincipal();
        if (user == null) {
            map.put("status", 101);
        } else {
            map.put("status", 200);
            cartService.addCart(productId);
        }
        return map;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> changeQuantityCart(@PathVariable("id") Long itemId,
                                                  @RequestParam("quantity") int quantity) {
        Map<String, Object> map = new HashMap<>();
        if (cartService.updateQuantity(itemId, quantity)) {
            map.put("status", 200);
        } else {
            map.put("status", 101);
        }
        return map;
    }

}
