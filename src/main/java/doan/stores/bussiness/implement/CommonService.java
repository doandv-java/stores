package doan.stores.bussiness.implement;

import doan.stores.bussiness.BaseService;
import doan.stores.domain.Product;
import doan.stores.domain.User;
import doan.stores.dto.response.ErrorResponse;
import doan.stores.persistenct.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommonService implements BaseService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getPrincipal() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (o.equals("anonymousUser")) {
            return null;
        } else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findUserByUserNameIs(userDetails.getUsername());
            return user;
        }

    }

    @Override
    public List<ErrorResponse> bindingResult(BindingResult result) {
        List<ErrorResponse> errors = new ArrayList<>();
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(fieldError -> errors.add(new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage())));
        }
        return errors;
    }

    @Override
    public List<Product> pageNavigation(List<Product> products, int page, int size) {
        if (products.isEmpty()) {
            return Collections.emptyList();
        }
        int startItem = (page - 1) * size;
        int toIndex = Math.min(startItem + size, products.size());
        if(startItem<=toIndex) {
            return products.subList(startItem, toIndex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public int getPageTotal(List<Product> products, int size) {
        int pageTotal = 0;
        if (products.size() > 0) {
            if (products.size() % size != 0) {
                pageTotal = products.size() / size + 1;
            } else {
                pageTotal = products.size() / size;
            }
        }
        return pageTotal;
    }
}
