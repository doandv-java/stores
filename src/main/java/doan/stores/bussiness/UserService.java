package doan.stores.bussiness;

import doan.stores.domain.User;
import doan.stores.dto.request.UserRequest;
import doan.stores.enums.RoleEnum;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(UserRequest request);

    User findUserByUserName(String userName);

    UserRequest findUserById(Long id);

    List<User> findUsersByRole(RoleEnum role);

    boolean deleteEmployee(Long id);

    void changePassword(String userName, String passwordNew);

    boolean checkPassword(String userName, String password);

    boolean existUser(String userName, String userNameOld);
}
