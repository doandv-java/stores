package doan.stores.persistenct;

import doan.stores.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserNameIs(String userName);

    User findUserByUserNameIsAndDeleted(String userName, int deleted);

    List<User> findUsersByRoleEqualsAndDeleted(String role, int deleted);
}
