package cn.zhangkai.goods.service.impl;

import cn.zhangkai.goods.mapper.GoodsTypeMapper;
import cn.zhangkai.goods.pojo.GoodsType;

import cn.zhangkai.goods.service.GoodsTypService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: book_small
 * @className: CategoryServiceImpl
 * @description: 类别接口服务
 * @author: zxh
 * @date: 2019-04-01 00:14
 */
@Service
public class GoodsTypServiceImpl implements GoodsTypService {

    private GoodsTypeMapper goodsTypeMapper;

    @Autowired
    public GoodsTypServiceImpl(GoodsTypeMapper goodsTypeMapper) {
        this.goodsTypeMapper = goodsTypeMapper;
    }

    /**
     * 查询类别信息
     *
     * @return List<Category>
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<GoodsType> getAllCategory() {
        return goodsTypeMapper.selectAll();
    }
}
