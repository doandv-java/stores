package doan.stores.controller.api;

import doan.stores.bussiness.CategoryService;
import doan.stores.bussiness.implement.CommonService;
import doan.stores.domain.Category;
import doan.stores.dto.request.CategoryRequest;
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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CommonService commonService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public Map<String, Object> saveCategory(@Valid @RequestBody CategoryRequest request, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        List<ErrorResponse> errors = commonService.bindingResult(result);
        if (errors.isEmpty()) {
            if (categoryService.existCategory(request.getName(), request.getNameOld())) {
                errors.add(new ErrorResponse("name", "Đã tồn tại danh mục"));
                map.put("status", 101);
                map.put("errors", errors);
            } else {
                map.put("status", 200);
                categoryService.saveCategory(request);
            }
        } else {
            map.put("status", 101);
            map.put("errors", errors);
        }
        return map;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteCategory(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        Category flag = categoryService.findCategoryById(id);
        if (flag == null) {
            map.put("status", 101);
        } else {
            map.put("status", 200);
            categoryService.deleteCategory(id);
        }
        return map;
    }
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) {
        return categoryService.findCategoryById(id);
    }

}
