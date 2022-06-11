package leechungmu.goodsInform.entity.Dto;

import lombok.Builder;

@Builder
public class ItemDto {
    private Long itemId;
    private int price;
    private String content;
    private boolean enterpriseItem;
    private int stockQuantity;
}
