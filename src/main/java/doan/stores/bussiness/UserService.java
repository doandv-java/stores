package doan.stores.bussiness;

import doan.stores.domain.User;
import doan.stores.dto.request.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void saveUser(UserRequest request);

    User findUserByUserName(String userName);

    void changePassword(String userName, String passwordNew);

    boolean checkPassword(String userName, String password);

    boolean existUser(String userName,String userNameOld);
}
