package leechungmu.goodsInform.controller;

import leechungmu.goodsInform.entity.Dto.PromotionDto;
import leechungmu.goodsInform.repository.PromotionRepository;
import leechungmu.goodsInform.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/promotion")
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;
    private final PromotionRepository promotionRepository;


    @PostMapping("")
    public ResponseEntity registerPromotion(@RequestBody PromotionDto dto){
        Long promotionId = promotionService.register(dto);
        if(promotionId == null) return new ResponseEntity<String>("적용할 상품이 없습니다.", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<Long>(promotionId, HttpStatus.OK);

    }



    @DeleteMapping("/{promotionId}")
    public ResponseEntity deletePromotion(@PathVariable("promotionId") Long promotionId){
        if(!promotionService.delete(promotionId)){
            return new ResponseEntity<String>("삭제할 프로모션이 없습니다.", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<String>("삭제 완료", HttpStatus.OK);
        }
    }
}

