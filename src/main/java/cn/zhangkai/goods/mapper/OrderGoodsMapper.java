package cn.zhangkai.goods.mapper;

import cn.zhangkai.goods.pojo.OrderGoods;
import cn.zhangkai.goods.utils.mapper.MyMapper;

import java.util.List;

/**
 * @className: OrderGoodsMapper
 * @description:
 * @author: zxh
 * @date: 2019-3-29 10:01:01
 */
public interface OrderGoodsMapper extends MyMapper<OrderGoods> {

    Integer inserDetailList(List<OrderGoods> list);

}