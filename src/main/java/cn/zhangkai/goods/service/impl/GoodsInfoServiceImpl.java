package cn.zhangkai.goods.service.impl;

import cn.zhangkai.goods.pojo.GoodsInfo;
import cn.zhangkai.goods.mapper.GoodsInfoMapper;
import cn.zhangkai.goods.service.GoodsInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * @program: GoodsInfo_small
 * @className: GoodsInfoServiceImpl
 * @description: 商品服务
 * @author: zxh
 * @date: 2019-03-29 17:02
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    private GoodsInfoMapper goodsInfoMapper;

    @Autowired
    public GoodsInfoServiceImpl(GoodsInfoMapper goodsInfoMapper) {
        this.goodsInfoMapper = goodsInfoMapper;
    }

    /**
     * 根据商品id查询
     *
     * @param id 商品id
     * @return 商品信息
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public GoodsInfo getGoodsInfoById(GoodsInfo goodsInfo) {
        return  goodsInfoMapper.selectOne(goodsInfo);
    }

    /**
     * 分页类别查询
     *
     * @param categoryId 类别id
     * @return 分页信息
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public PageInfo<GoodsInfo> getGoodsInfoByCategoryId(Integer page, Integer pageSize, Integer categoryId){
        if(categoryId == 0){
          categoryId = null;
        }
        PageHelper.startPage(page, pageSize);
        Example example = new Example(GoodsInfo.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",categoryId);
        List<GoodsInfo> GoodsInfoList =  goodsInfoMapper.selectByExample(example);
        return new PageInfo<>(GoodsInfoList);
    }

    /**
     * 根据商品名称查询商品信息
     *
     * @param pageNum 当前页
     * @param pageSize 个数
     * @param name 商品名字
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public PageInfo<GoodsInfo> getGoodsInfoByName(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(GoodsInfo.class);
        Criteria criteria = example.createCriteria();
        criteria.andLike("name", "%"+name+"%");
        List<GoodsInfo> GoodsInfoList =  goodsInfoMapper.selectByExample(example);
        return new PageInfo<>(GoodsInfoList);
    }

}
