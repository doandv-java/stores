package doan.stores.controller.api;

import doan.stores.bussiness.AdvertiseService;
import doan.stores.bussiness.implement.CommonService;
import doan.stores.domain.Advertise;
import doan.stores.dto.request.AdvertiseRequest;
import doan.stores.dto.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/advertise")
public class AdvertisesController {
    @Autowired
    private CommonService commonService;

    @Autowired
    private AdvertiseService advertiseService;

    @PostMapping("")
    public Map<String, Object> getListAdvertise(@Valid AdvertiseRequest request, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        List<ErrorResponse> errors = commonService.bindingResult(result);
        if (errors.isEmpty()) {
            map.put("status", 200);
            advertiseService.saveAdvertise(request);
        } else {
            map.put("status", 101);
            map.put("errors", errors);
        }
        return map;
    }

    @GetMapping("/{id}")
    public Advertise getAdvertise(@PathVariable("id") Long id) {
        return advertiseService.findAdvertiseById(id);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteAdvertise(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        Advertise advertise = advertiseService.findAdvertiseById(id);
        if (advertise == null) {
            map.put("status", 101);
        } else {
            map.put("status", 200);
            advertiseService.deleteAdvertise(id);
        }
        return map;
    }

    @PutMapping("/{id}/{active}")
    public void changeActive(@PathVariable("id") Long id, @PathVariable("active") int active) {
        advertiseService.changeActive(id, active);
    }

}
