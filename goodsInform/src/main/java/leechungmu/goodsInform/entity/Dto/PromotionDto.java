package leechungmu.goodsInform.entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
    private Long promotionId;

    private int ratioDiscount;

    private int fixDiscount;

    private Long itemId;
}
