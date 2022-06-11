package leechungmu.goodsInform.entity.Dto;

import lombok.Builder;

@Builder
public class PromotionDto {
    private Long promotionId;

    private int ratioDiscount;

    private int fixDiscount;

    private Long itemId;
}
