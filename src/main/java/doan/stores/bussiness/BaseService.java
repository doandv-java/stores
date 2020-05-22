package doan.stores.bussiness;

import doan.stores.domain.Product;
import doan.stores.domain.User;
import doan.stores.dto.response.ErrorResponse;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface BaseService {

    User getPrincipal();

    List<ErrorResponse> bindingResult(BindingResult result);

    List<Product> pageNavigation(List<Product> products, int page, int size);

    int getPageTotal(List<Product> products, int size);
}
