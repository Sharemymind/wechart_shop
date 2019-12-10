package cn.zhangkai.goods.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * @className: Category
 * @description:
 * @author: zxh
 * @date: 2019-3-29 10:03:35
 */
@Data
public class GoodsType {
    /**
     * 类目编号
     */
    @Id
    private Integer id;

    /**
     * 类目名字
     */
    private String name;

    /**
     * 类目图片地址
     */
    private String icon;

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


}