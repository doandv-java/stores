package doan.stores.bussiness.implement;

import doan.stores.bussiness.ImageService;
import doan.stores.bussiness.UserService;
import doan.stores.domain.User;
import doan.stores.domain.UserPrincipal;
import doan.stores.dto.request.UserRequest;
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
        User user = userRepository.getOne(request.getId());
        if (user == null) {
            user = new User();
            user.setPassword(passwordEncoder.encode(Constants.PASS_RANDOM));
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

}
