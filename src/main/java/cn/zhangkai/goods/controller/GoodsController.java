package cn.zhangkai.goods.controller;

import cn.zhangkai.goods.pojo.GoodsInfo;
import cn.zhangkai.goods.utils.ApiJSONResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: GoodsController
 * @description:  货物操作类
 * @author: 
 * @date: 
 */
@Api(value = "商品管理接口", tags = "商品管理")
@RestController
@RequestMapping("/Goods")
public class GoodsController {

    private final BasicController basicController;

    @Autowired
    public GoodsController(BasicController basicController) {
        this.basicController = basicController;
    }

    @ApiOperation(value = "根据商品ID查询商品详情")
    @ApiImplicitParam(paramType = "path", name = "id", value = "商品", required = true, dataType = "String")
    @GetMapping("{id}")
    public ApiJSONResult getBookById(@PathVariable("goodsInfo") GoodsInfo goodsInfo) {
        GoodsInfo book = basicController.goodsInfoServiceService.getGoodsInfoById(goodsInfo);
        return ApiJSONResult.ok(book);
    }

    @ApiOperation(value = "根据类别id获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "显示记录数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "categoryId", value = "商品类别id", required = true, dataType = "Integer")
    })
    @GetMapping("/category")
    public ApiJSONResult getPageBookByCategoryId(Integer pageNum, Integer pageSize, Integer categoryId){
        PageInfo<GoodsInfo> list = basicController.goodsInfoServiceService.getGoodsInfoByCategoryId(pageNum,pageSize,categoryId);
        return ApiJSONResult.ok(list);
    }
    @ApiOperation(value = "根据类别id获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "显示记录数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "商品名字", required = true, dataType = "String")
    })
    @GetMapping("/search")
    public ApiJSONResult getAllBookByName(Integer pageNum, Integer pageSize, String name){
        PageInfo<GoodsInfo> bookList = basicController.goodsInfoServiceService.getGoodsInfoByName(pageNum, pageSize, name);
        return ApiJSONResult.ok(bookList);
    }
}
