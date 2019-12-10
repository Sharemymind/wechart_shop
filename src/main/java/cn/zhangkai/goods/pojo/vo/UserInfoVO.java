package cn.zhangkai.goods.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVO {

    private String id;

    private String userToken;
    /**
     * 名字
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 常用地址
     */
    private String address;

    /**
     * 微信openid
     */
    private String openid;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private  String UserInfoToken;

}