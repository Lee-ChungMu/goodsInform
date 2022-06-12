package leechungmu.goodsInform.repository;

import leechungmu.goodsInform.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    List<Promotion> findAllByItem_ItemId(Long ItemId);

    @Query("select p from Promotion p where p.startDate < :now and p.endDate > :now and p.item.itemId = :ItemId")
    List<Promotion> findAllByItem_ItemIdAndDate(Long ItemId, Date now);

}
