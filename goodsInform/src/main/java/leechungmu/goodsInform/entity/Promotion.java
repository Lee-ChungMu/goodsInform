package leechungmu.goodsInform.entity;

import javax.persistence.*;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;

    private int ratioDiscount;

    private int fixDiscount;

    @ManyToOne
    private Item item;

}
