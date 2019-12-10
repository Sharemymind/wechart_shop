package cn.zhangkai.goods.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "order_info")
public class OrderMaster {
    @Id
    private String id;

    /**
     * 买家ID
     */
    @Column(name = "uid")
    private String uid;

    /**
     * 商品信息
     */
    @Column(name = "goods_id")
    private String goodsId;

    /**
     * 数量
     */
    @Column(name = "num")
    private int num;

    /**
     * 商品编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 1-已支付 0-未支付
     */
    private Byte is_pay;

    /**
     * 付款时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 支付单号
     */
    @Column(name = "pay_order")
    private Byte payOrder;

    /**
     * 1-已收货 0 未收货
     */
    @Column(name = "is_ship")
    private Byte isShip;

    /**
     * 收货时间
     */
    @Column(name = "ship_time")
    private Date shipTime;

    /**
     * is_recepit
     * 0-未发货 1-已发货
     */
    @Column(name = "is_recepit")
    private Byte isRecepit;

    /**
     * recepit_time
     * 0-未发货 1-已发货
     */
    @Column(name = "recepit_time")
    private Date recepitTime;

    /**
     * ship_code
     * 快递单号
     */
    @Column(name = "ship_code")
    private Date shipCode;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * address_id
     * 地址
     */
    @Column(name = "address_id")
    private Date addressId;

}