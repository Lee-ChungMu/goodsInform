package leechungmu.goodsInform.entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long itemId;
    private int price;
    private String content;
    private boolean enterpriseItem;
    private int stockQuantity;
}
