package cn.zhangkai.goods.controller;

import cn.zhangkai.goods.pojo.Admin;
import cn.zhangkai.goods.pojo.vo.AdminVO;
import cn.zhangkai.goods.utils.ApiJSONResult;
import cn.zhangkai.goods.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Api(value = "管理员接口", tags = "管理员操作")
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    /**
     * 基本控制器
     */
    private final BasicController basicController;

    @Autowired
    public AdminController(BasicController basicController) {
        this.basicController = basicController;
    }

    @ApiOperation(value = "管理员登录", notes = "用户登录接口")
    @PostMapping(value = "/login")
    public ApiJSONResult login(@ApiParam(value = "管理员对象", required = true) @RequestBody Admin admin) throws Exception {

        if (StringUtils.isBlank(admin.getName()) || StringUtils.isBlank(admin.getPassword())) {
            return ApiJSONResult.errorMsg("账号和密码不能为空");
        }
        System.out.println();
        Admin adminResult = basicController.adminService.login(admin.getName(), MD5Util.getMD5Str(admin.getPassword()));

        if (null == adminResult) {
            return ApiJSONResult.errorMsg("账号或密码错误");
        }

        adminResult.setPassword("");
        String uniqueToken = UUID.randomUUID().toString();
        basicController.redisService.set(BasicController.UserInfo_REDIS_SESSION + ":" + adminResult.getId(), uniqueToken, 1000 * 60 * 30);

        AdminVO adminVO = new AdminVO();
        // 拷贝值
        BeanUtils.copyProperties(adminResult, adminVO);
        adminVO.setUserToken(uniqueToken);

        return ApiJSONResult.ok(adminVO);

    }

}
