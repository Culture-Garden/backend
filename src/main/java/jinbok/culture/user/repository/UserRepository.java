package jinbok.culture.user.repository;

import jinbok.culture.user.domain.User;
import jinbok.culture.user.dto.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginId(String loginId);
}
