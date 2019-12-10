package cn.zhangkai.goods.controller;

import cn.zhangkai.goods.service.*;
import cn.zhangkai.goods.utils.component.FastDFSClient;
import cn.zhangkai.goods.utils.component.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.ApiIgnore;


@ApiIgnore
@Component
public class BasicController {
    public final AdminService adminService;
    public final RedisOperator redisService;
    public final FastDFSClient fastDFSClient;
    public final UserInfoService userInfoService;
    public final GoodsInfoService goodsInfoServiceService;
    public final GoodsTypService categoryService;
    public final OrderService orderService;
    @Autowired
    public BasicController(AdminService adminService, RedisOperator redisService, FastDFSClient fastDFSClient, UserInfoService userInfoService,GoodsInfoService goodsInfoServiceService,GoodsTypService categoryService, OrderService orderService) {
        this.adminService = adminService;
        this.redisService = redisService;
        this.fastDFSClient = fastDFSClient;
        this.userInfoService = userInfoService;
        this.goodsInfoServiceService = goodsInfoServiceService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    /**
     * 用户登录回话状态钥匙
     */
    public static final String UserInfo_REDIS_SESSION = "UserInfo_redis_session";
    /**
     * 文件保存的命名空间
     */
    public static final String FILE_SPACE = "D:/zxh_wenjian";
    /**
     * ffmpeg所在目录
     */
   // public static final String FFMPEG_EXE = "C:\\ffmpeg\\bin\\ffmpeg.exe";
    /**
     * 每页分页的记录数
     */
    public static final Integer PAGE_SIZE = 5;

}
