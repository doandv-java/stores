package doan.stores.controller.api;

import doan.stores.bussiness.UserService;
import doan.stores.dto.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteEmployee(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        UserRequest flag = userService.findUserById(id);
        if (flag == null) {
            map.put("status", 101);
        } else {
            map.put("status", 200);
            userService.deleteEmployee(id);
        }
        return map;
    }




}
