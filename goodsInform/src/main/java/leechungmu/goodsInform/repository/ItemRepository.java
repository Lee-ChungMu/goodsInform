package leechungmu.goodsInform.repository;

import leechungmu.goodsInform.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
