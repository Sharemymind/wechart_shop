package cn.zhangkai.goods.service;

import cn.zhangkai.goods.pojo.GoodsInfo;
import com.github.pagehelper.PageInfo;


public interface GoodsInfoService {

    /**
     * 根据id获取商品GoodsInfoServer
     *
     * @param id 商品id
     * @return GoodsInfo
     */
    GoodsInfo getGoodsInfoById(GoodsInfo goodsInfo);

    /**
     * 根据 类别id 分页查询商品列表
     * @param pageNum 当前页
     * @param pageSize 显示记录数
     * @param categoryId 类别id
     * @return PageInfo
     */
    PageInfo<GoodsInfo> getGoodsInfoByCategoryId(Integer pageNum, Integer pageSize, Integer categoryId);

    /**
     * 根据商品名称查询商品信息
     *
     * @param pageNum 当前页
     * @param pageSize 个数
     * @param name 商品名字
     * @return
     */
    PageInfo<GoodsInfo> getGoodsInfoByName(Integer pageNum, Integer pageSize, String name);
}
