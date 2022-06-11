package leechungmu.goodsInform.service;

import leechungmu.goodsInform.entity.Dto.ItemDto;
import leechungmu.goodsInform.entity.Item;

import java.util.List;


public interface ItemService {
    Long register(ItemDto dto);
    boolean delete(Long itemId);
    List<ItemDto> read(String userType);
    default Item dtoToEntity(ItemDto dto) {
        Item item = Item.builder().price(dto.getPrice())
                .itemId(dto.getItemId())
                .content(dto.getContent())
                .enterpriseItem(dto.isEnterpriseItem())
                .stockQuantity(dto.getStockQuantity())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
        return item;
    }

    default ItemDto entityToDto(Item item){
        ItemDto dto = ItemDto.builder()
                .price(item.getPrice())
                .itemId(item.getItemId())
                .content(item.getContent())
                .enterpriseItem(item.isEnterpriseItem())
                .stockQuantity(item.getStockQuantity())
                .startDate(item.getStartDate())
                .endDate(item.getEndDate())
                .build();
        return dto;
    }
}
