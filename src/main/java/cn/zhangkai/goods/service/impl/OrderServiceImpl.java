package cn.zhangkai.goods.service.impl;

import cn.zhangkai.goods.pojo.OrderGoods;
import cn.zhangkai.goods.pojo.OrderMaster;
import cn.zhangkai.goods.pojo.vo.OrderVO;
import cn.zhangkai.goods.utils.MyPageInfo;
import cn.zhangkai.goods.mapper.OrderGoodsMapper;
import cn.zhangkai.goods.mapper.OrderMasterMapper;
import cn.zhangkai.goods.service.OrderService;
import cn.zhangkai.goods.utils.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMasterMapper orderMasterMapper;
    private final OrderGoodsMapper orderGoodsMapper;

    @Autowired
    public OrderServiceImpl(OrderMasterMapper orderMasterMapper, OrderGoodsMapper orderGoodsMapper) {
        this.orderMasterMapper = orderMasterMapper;
        this.orderGoodsMapper = orderGoodsMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveOrder(OrderMaster orderMaster, List<OrderGoods> OrderGoodsList) {
        String itemId = IDUtil.getMasterId();
        orderMaster.setId(itemId);


        for(OrderGoods OrderGoods : OrderGoodsList){
            OrderGoods.setOrderId(itemId);
            OrderGoods.setId(IDUtil.getDetailId());
        }
        orderMasterMapper.insert(orderMaster);
        orderGoodsMapper.inserDetailList(OrderGoodsList);

        /* 不知道为啥就是差不如 id 列， 打印的sql中少了id列 */
        /* 记录一个通用mapper的一个小坑，MySQLMapper的insertList方法中传入list时，这个Entity的主键必须为自增主键，否则他在执行sql是不会去插入主键，自然就会报一些奇怪的错误了，比如DB2的-407*/
//        OrderGoodsMapper.insertList(OrderGoodsList);
    }

    @Override
    public MyPageInfo<OrderVO> getAllOrder(Integer pageNum, Integer pageSize, Byte payStatus, String openid) {
        PageHelper.startPage(pageNum,pageSize);
        Example exampleMaster = new Example(OrderMaster.class);
        exampleMaster.setOrderByClause("create_time DESC");
        Example.Criteria criteria = exampleMaster.createCriteria();
        criteria.andEqualTo("payStatus", payStatus).andEqualTo("userOpenid",openid);
        List<OrderMaster> orderMasters = orderMasterMapper.selectByExample(exampleMaster);
        PageInfo<OrderMaster> pageInfo = new PageInfo<>(orderMasters);

        List<OrderVO> orderVOList = new ArrayList<>();
        for (OrderMaster orderMaster: orderMasters){
            Example exampleDetail = new Example(OrderGoods.class);

            Example.Criteria exampleDetailCriteria = exampleDetail.createCriteria();
            exampleDetailCriteria.andEqualTo("orderId", orderMaster.getId());
            List<OrderGoods> OrderGoodss = orderGoodsMapper.selectByExample(exampleDetail);
            OrderVO orderVO = new OrderVO();
            orderVO.setOrderMaster(orderMaster);
            orderVO.setorderGoodsList(OrderGoodss);
            orderVOList.add(orderVO);
        }

        MyPageInfo<OrderVO> myPageInfo = new MyPageInfo<>();
        myPageInfo.setList(orderVOList);
        myPageInfo.setPageNum(pageInfo.getPageNum());
        myPageInfo.setPageSize(pageInfo.getPageSize());
        myPageInfo.setTotal(pageInfo.getTotal());
        myPageInfo.setPages(pageInfo.getPages());
        return myPageInfo;
    }
}
