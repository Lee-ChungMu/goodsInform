package leechungmu.goodsInform.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item extends TimeBase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private int price;

    private String content;

    private boolean EnterpriseItem;

    private int stockQuantity;
}
