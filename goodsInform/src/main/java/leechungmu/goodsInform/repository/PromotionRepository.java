package leechungmu.goodsInform.repository;

import leechungmu.goodsInform.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findAllByItem_ItemId(Long ItemId);

}
