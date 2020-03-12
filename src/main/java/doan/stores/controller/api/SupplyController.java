package doan.stores.controller.api;

import doan.stores.bussiness.SupplyService;
import doan.stores.bussiness.implement.CommonService;
import doan.stores.domain.Supply;
import doan.stores.dto.request.SupplyRequest;
import doan.stores.dto.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supply")
public class SupplyController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private SupplyService supplyService;

    @PostMapping("")
    public Map<String, Object> saveSupply(@Valid @RequestBody SupplyRequest request, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        List<ErrorResponse> errors = commonService.bindingResult(result);
        if (errors.isEmpty()) {
            if (supplyService.existsSupply(request.getName(), request.getNameOld())) {
                errors.add(new ErrorResponse("name", "Đã tồn tại nhà cung cấp"));
                map.put("status", 101);
                map.put("errors", errors);
            } else {
                map.put("status", 200);
                supplyService.saveSupply(request);
            }
        } else {
            map.put("status", 101);
            map.put("errors", errors);
        }
        return map;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteSupply(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        Supply flag = supplyService.findSupplyById(id);
        if (flag == null) {
            map.put("status", 101);
        } else {
            map.put("status", 200);
            supplyService.deleteSupply(id);
        }
        return map;
    }

    @GetMapping("/{id}")
    public Supply getSupplyById(@PathVariable("id") Long id) {
        return supplyService.findSupplyById(id);
    }

    @PutMapping("/{id}/{active}")
    public void changeActive(@PathVariable("id") Long id, @PathVariable("active") int active) {
        supplyService.changeActive(id, active);
    }
}
