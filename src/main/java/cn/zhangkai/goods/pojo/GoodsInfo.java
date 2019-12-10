package cn.zhangkai.goods.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
public class GoodsInfo {

    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     *
     */
    private String notes;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 图片、小图地址
     */
    private String imageUrl;

    /**
     * 库存
     */
    private Integer num;

    /**
     * 类别
     */

    private String goodsTypeId;

    private String goodsTypeCode;

    /**
     * 创建时间
     */

    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;


    /**
     * 大图片地址(轮播图未实现）
     */
    private List goodsImage;

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", num=" + num +
                ", goodsTypeId='" + goodsTypeId + '\'' +
                ", goodsTypeCode='" + goodsTypeCode + '\'' +
                ", createTime=" + createDate+
                ", updateTime=" + updateDate +
                ", goodsImage=" + goodsImage +
                '}';
    }
}