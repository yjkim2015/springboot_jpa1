package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 가격

    private int count; //주문 수량


    //== 비즈니스 로직 == //
    /**
     * 주문 취소
     */
    public void cancle() {
        getItem().addStock(count);
    }

    /**
     * 전체 주문 가격 조회
     * @return int
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
