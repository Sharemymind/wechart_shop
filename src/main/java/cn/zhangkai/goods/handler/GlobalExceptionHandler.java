package cn.zhangkai.goods.handler;

import cn.zhangkai.goods.utils.ApiJSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ApiJSONResult errorHandler(Exception ex) {

        return ApiJSONResult.errorMsg(ex.getMessage());
    }

}
