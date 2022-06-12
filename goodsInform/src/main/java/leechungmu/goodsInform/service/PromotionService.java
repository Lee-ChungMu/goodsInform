package leechungmu.goodsInform.service;

import leechungmu.goodsInform.entity.Dto.PromotionDto;
import leechungmu.goodsInform.entity.Dto.PromotionResponseDto;
import leechungmu.goodsInform.entity.Item;
import leechungmu.goodsInform.entity.Promotion;

public interface PromotionService {
    Long register(PromotionDto dto);
    boolean delete(Long promotionId);
    PromotionResponseDto optimalPromotion(Item item);

    default Promotion dtoToEntity(PromotionDto dto){
        Item item = Item.builder().itemId(dto.getItemId()).build();
        Promotion promotion = Promotion.builder()
                .ratio(dto.isRatio())
                .sale(dto.getSale())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .item(item)
                .build();

        return promotion;
    }


}
