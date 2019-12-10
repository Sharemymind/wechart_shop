package cn.zhangkai.goods.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
public class GoodsType {
    /**
     * 类目编号
     */
    @Id
    private String id;

    /**
     * 类目名字
     */
    private String name;

    /**
     * 类目图片地址
     */
  //  private String icon;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;


}