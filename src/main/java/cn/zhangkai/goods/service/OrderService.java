package cn.zhangkai.goods.service;

import cn.zhangkai.goods.pojo.OrderGoods;
import cn.zhangkai.goods.pojo.OrderMaster;
import cn.zhangkai.goods.pojo.vo.OrderVO;
import cn.zhangkai.goods.utils.MyPageInfo;

import java.util.List;

/**
 * @program: book_small
 * @className: OrderService
 * @description: 订单服务
 * @author: zxh
 * @date: 2019-04-01 23:28
 */
public interface OrderService {

    /**
     * 添加对象信息
     * @param orderMaster 表体
     * @param OrderGoodsList 详情
     */
    void saveOrder(OrderMaster orderMaster, List<OrderGoods> OrderGoodsList);

    /**
     * 获取订单列表
     * @param pageNum 当前页
     * @param pageSize 个数
     * @param payStatus 购买状态
     * @param openid openid
     * @return 对象
     */
    MyPageInfo<OrderVO> getAllOrder(Integer pageNum, Integer pageSize, Byte payStatus, String openid);
}
