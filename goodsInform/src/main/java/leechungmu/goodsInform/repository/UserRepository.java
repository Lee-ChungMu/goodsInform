package leechungmu.goodsInform.repository;

import leechungmu.goodsInform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
