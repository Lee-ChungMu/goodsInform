package leechungmu.goodsInform.entity;


import leechungmu.goodsInform.entity.Dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private int price;

    private String content;

    private boolean enterpriseItem;

    private Date startDate;
    private Date endDate;

    private int stockQuantity;

    @Builder
    public Item(ItemDto dto){
        this.content = dto.getContent();
        this.price = dto.getPrice();
        this.enterpriseItem = dto.isEnterpriseItem();
        this.startDate = dto.getStartDate();
        this.endDate =dto.getEndDate();
        this.stockQuantity=dto.getStockQuantity();
    }
}
