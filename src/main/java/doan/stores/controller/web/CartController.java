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
            if (cartService.addCart(productId)) {
                map.put("status", 200);
            } else {
                map.put("status", 500);
            }
        }
        return map;
    }

    @PostMapping("/{id}/{quantity}")
    @ResponseBody
    public Map<String, Object> addCart(@PathVariable("id") Long productId, @PathVariable("quantity") int quantity) {
        Map<String, Object> map = new HashMap<>();
        User user = commonService.getPrincipal();
        if (user == null) {
            map.put("status", 101);
        } else {

            if (cartService.addCart(productId, quantity)) {
                map.put("status", 200);
            } else {
                map.put("status", 500);
            }
        }
        return map;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> changeQuantityCart(@PathVariable("id") Long itemId,
                                                  @RequestParam("quantity") int quantity) {
        Map<String, Object> map = new HashMap<>();
        User user = commonService.getPrincipal();
        if (user == null) {
            map.put("status", 101);
        } else if(quantity>=10){
            map.put("status", 501);
        }else {
            if (cartService.updateQuantity(itemId, quantity)) {
                map.put("status", 200);
            } else {
                map.put("status", 500);
            }
        }
        return map;
    }

}
