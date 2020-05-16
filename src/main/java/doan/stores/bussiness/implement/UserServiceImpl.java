package doan.stores.bussiness.implement;

import doan.stores.bussiness.ImageService;
import doan.stores.bussiness.UserService;
import doan.stores.domain.User;
import doan.stores.domain.UserPrincipal;
import doan.stores.dto.dxo.RegisterDxo;
import doan.stores.dto.request.UserRequest;
import doan.stores.enums.RoleEnum;
import doan.stores.persistenct.UserRepository;
import doan.stores.utils.Constants;
import doan.stores.utils.Dates;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ImageService imageService;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserRequest request) {
        User user;
        if (request.getId() == null) {
            user = new User();
            user.setPassword(passwordEncoder.encode(Constants.PASS_RANDOM));
        } else {
            user = userRepository.getOne(request.getId());
        }
        user.setUserName(request.getUserName());
        user.setName(request.getName());
        user.setRole(request.getRole());
        if (!StringUtils.isEmpty(request.getBirthDay())) {
            user.setBirthDay(Dates.parseExact(request.getBirthDay(), Constants.DATE_FORMAT.YYYY_MM_DD));
        }

        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        String imageLink = imageService.saveImage(request.getImage(), request.getImageLink());
        user.setImageLink(imageLink);
        user.setState(request.getState());
        user.setState(request.getDeleted());
        userRepository.save(user);

    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserNameIs(userName);
    }

    @Override
    public UserRequest findUserById(Long id) {
        if (id == null) {
            return new UserRequest();
        } else {
            User user = userRepository.getOne(id);
            UserRequest request = new UserRequest();
            request.setId(user.getId());
            request.setAddress(user.getAddress());
            if (user.getBirthDay() != null) {
                request.setBirthDay(Dates.format(user.getBirthDay(), Constants.DATE_FORMAT.YYYY_MM_DD));
            }
            request.setGender(user.getGender());
            request.setImageLink(user.getImageLink());
            request.setName(user.getName());
            request.setUserName(user.getUserName());
            request.setRole(user.getRole());
            request.setUserNameOld(user.getUserName());
            request.setPhone(user.getPhone());
            request.setState(user.getState());
            request.setDeleted(user.getDeleted());
            return request;
        }
    }

    @Override
    public List<User> findUsersByRole(RoleEnum role) {
        return userRepository.findUsersByRoleEqualsAndDeleted(role.getText(), Constants.DELETE.FALSE);
    }

    @Override
    public boolean deleteEmployee(Long id) {
        User flag = userRepository.getOne(id);
        if (flag == null) {
            return false;
        } else {
            flag.setDeleted(Constants.DELETE.TRUE);
            userRepository.save(flag);
            return true;
        }
    }

    @Override
    public void changePassword(String userName, String passwordNew) {
        User user = userRepository.findUserByUserNameIs(userName);
        user.setPassword(passwordEncoder.encode(passwordNew));
        userRepository.save(user);
    }

    @Override
    public boolean checkPassword(String userName, String password) {
        User user = userRepository.findUserByUserNameIs(userName);
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public boolean existUser(String userName, String userNameOld) {
        if (!StringUtils.isEmpty(userName)) {
            User user = userRepository.findUserByUserNameIsAndDeleted(userName, Constants.DELETE.FALSE);
            if (StringUtils.isEmpty(userNameOld)) {
                return user != null;
            } else {
                boolean check = userName.equals(userNameOld) || user == null;
                return !check;
            }
        }
        return false;
    }

    @Override
    public UserPrincipal loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserNameIs(userName);
        if (user == null) {
            log.error("Unknown User");
            throw new UsernameNotFoundException("Unknown User");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new UserPrincipal(
                user, true, true, true, true,
                grantedAuthorities);
    }

    @Override
    public boolean register(RegisterDxo dxo) {
        User user = userRepository.findUserByUserNameIsAndDeleted(dxo.getUsername(), Constants.DELETE.FALSE);
        if (user != null) {
            return false;
        } else {
            user = new User();
            user.setUserName(dxo.getUsername());
            user.setName(dxo.getName());
            user.setPassword(passwordEncoder.encode(dxo.getPassword()));
            user.setGender(dxo.getGender().getValue());
            user.setRole(dxo.getRole().getText());
            user.setImageLink(Constants.IMAGE_DEFAULT);
            user.setDeleted(Constants.DELETE.FALSE);
            userRepository.save(user);
            return true;
        }
    }
}
