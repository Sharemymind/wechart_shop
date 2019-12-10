package cn.zhangkai.goods.handler;

import cn.zhangkai.goods.controller.BasicController;
import cn.zhangkai.goods.utils.ApiJSONResult;
import cn.zhangkai.goods.utils.component.RedisOperator;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;


public class MyHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    public RedisOperator redisOperator;
    /**
     * 请求前处理
     *
     * @param request request
     * @param response response
     * @param handler handler
     * @return true/false true: 放行
     * @throws Exception Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userId = request.getHeader("userId");
        String userToken = request.getHeader("userToken");
        if(StringUtils.isNoneBlank(userId) && StringUtils.isNoneBlank(userToken)){
            String token = redisOperator.get(BasicController.UserInfo_REDIS_SESSION + ":" + userId);
            if(StringUtils.isBlank(token) && StringUtils.isBlank(token)){
                returnErrorResponse(response, ApiJSONResult.errorMsg("登录超时，请重新登录"));
                return false;
            } else{
                if(!token.equals(userToken)){
                    returnErrorResponse(response,ApiJSONResult.errorMsg("账号被挤出，"));
                    return false;
                } else {
                    return true;
                }
            }

        } else {
            returnErrorResponse(response,ApiJSONResult.errorMsg("请登录"));
            return false;
        }

    }

    public void returnErrorResponse(HttpServletResponse response, ApiJSONResult result)
            throws IOException, UnsupportedEncodingException {
        OutputStream out=null;
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JSONObject.toJSONString(result).getBytes());
            out.flush();
        } finally{
            if(out!=null){
                out.close();
            }
        }
    }

    /**
     * 请求 Controller 之后 渲染视图之前
     * @param request request
     * @param response response
     * @param handler handler
     * @param modelAndView modelAndView
     * @throws Exception Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求 Controller 之结束后 渲染视图之后
     * @param request request
     * @param response response
     * @param handler handler
     * @param ex ex
     * @throws Exception Exception Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
