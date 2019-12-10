package cn.zhangkai.goods.service;

import cn.zhangkai.goods.pojo.UserInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public interface UserInfoService {



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean saveUserInfo(UserInfo user);

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    UserInfo queryUserInfoByOpenid(String openId);

    /**
     * 微信登录
     *
     * @param encryptedData 明文,加密数据
     * @param iv            加密算法的初始向量
     * @param code          code值五分钟限制
     * @return 返回用户信息
     * @throws Exception 登录失败
     */
    UserInfo weChatLogin(String encryptedData, String iv, String code) throws Exception;





    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean updateUserInfo(UserInfo user);
}
