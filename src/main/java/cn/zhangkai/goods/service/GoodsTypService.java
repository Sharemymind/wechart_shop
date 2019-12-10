package cn.zhangkai.goods.service;

import cn.zhangkai.goods.pojo.GoodsType;

import java.util.List;

/**
 * @className: CategoryService
 * @description: 类别服务
 * @author: zxh
 * @date: 2019年4月1日00:11:42
 */
public interface GoodsTypService {

    /**
     * 获取全部分类信息
     *
     * @return List<Category>
     */
    List<GoodsType> getAllCategory();

}
