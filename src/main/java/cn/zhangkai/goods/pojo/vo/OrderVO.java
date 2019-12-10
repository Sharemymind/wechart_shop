package cn.zhangkai.goods.pojo.vo;


import cn.zhangkai.goods.pojo.OrderGoods;
import cn.zhangkai.goods.pojo.OrderMaster;
import lombok.Data;

import java.util.List;

@Data
public class OrderVO {

    /**
     * 订单主表信息
     */
    private OrderMaster orderMaster;
    /**
     * 订单详情表信息
     */
    private List<OrderGoods> orderGoodsList;

    public OrderMaster getOrderMaster() {
        return orderMaster;
    }

    public void setOrderMaster(OrderMaster orderMaster) {
        this.orderMaster = orderMaster;
    }

    public List<OrderGoods> getorderGoodsList() {
        return orderGoodsList;
    }

    public void setorderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }


    @Override
    public String toString() {
        return "OrderVO{" +
                "orderMaster=" + orderMaster +
                ", orderGoodsList=" + orderGoodsList +
                '}';
    }
}
