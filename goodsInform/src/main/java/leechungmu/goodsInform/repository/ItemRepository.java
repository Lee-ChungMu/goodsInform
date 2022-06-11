package leechungmu.goodsInform.repository;

import leechungmu.goodsInform.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    @Query("select i from Item i where i.startDate < :now and i.endDate > :now and i.enterpriseItem=false and i.stockQuantity>0")
    List<Item> findAllNormal(@Param("now") Date now);

    @Query("select i from Item i where i.startDate < :now and i.endDate > :now and i.stockQuantity>0")
    List<Item> findAllEnterprise(@Param("now") Date now);
}
