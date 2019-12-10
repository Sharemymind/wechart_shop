package cn.zhangkai.goods.controller;

import cn.zhangkai.goods.pojo.GoodsType;
import cn.zhangkai.goods.utils.ApiJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "类别接口", tags = "类别操作")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final BasicController basicController;

    @Autowired
    public CategoryController(BasicController basicController) {
        this.basicController = basicController;
    }

    @ApiOperation(value = "获取全部类别信息")
    @GetMapping
    public ApiJSONResult showAllCategory(){
        List<GoodsType> allCategory = basicController.categoryService.getAllCategory();
        return ApiJSONResult.ok(allCategory);
    }

}
