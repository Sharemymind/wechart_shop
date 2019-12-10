package cn.zhangkai.goods.mapper;

import cn.zhangkai.goods.pojo.OrderDetail;
import cn.zhangkai.goods.utils.mapper.MyMapper;

import java.util.List;

/**
 * @className: OrderDetailMapper
 * @description:
 * @author: zxh
 * @date: 2019-3-29 10:01:01
 */
public interface OrderDetailMapper extends MyMapper<OrderDetail> {

    Integer inserDetailList(List<OrderDetail> list);

}