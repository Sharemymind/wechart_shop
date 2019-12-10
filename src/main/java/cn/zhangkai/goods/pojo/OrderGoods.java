package cn.zhangkai.goods.pojo;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "order_goods")
public class OrderGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private String id;

    /**
     * 主表id
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 书本id
     */
    @Column(name = "goods_id")
    private String goodsId;

    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 商品名称
     */
    @Column(name = "status")
    private String status;

    /**
     * 商品价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 商品数量
     */
    @Column(name = "num")
    private Integer num;


    /**
     * 创建时间
     */
    @Column(name = "create_data")
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "update_data")
    private Date updateDate;

}