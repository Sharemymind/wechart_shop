package cn.zhangkai.goods.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
public class Admin {
    @Id
    private Integer id;

    /**
     * 名字
     */
    private String name;

    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 常用地址
     */
    private String address;

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