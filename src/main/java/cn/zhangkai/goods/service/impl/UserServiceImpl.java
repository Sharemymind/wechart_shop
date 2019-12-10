package cn.zhangkai.goods.service.impl;

import cn.zhangkai.goods.pojo.UserInfo;
import cn.zhangkai.goods.service.UserInfoService;
import cn.zhangkai.goods.utils.AesCbcUtil;
import cn.zhangkai.goods.utils.HttpClientUtil;
import cn.zhangkai.goods.mapper.UserMapper;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.Map;

@Service
public  class UserServiceImpl implements UserInfoService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;
    @Value("${wx.grant_type}")
    private String grantType;
    @Value("${wx.url}")
    private String url;



    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean saveUserInfo(UserInfo user) {
        /* 插入的时候回调id列 */
        int i = userMapper.insertUseGeneratedKeys(user);
        return i == 1;
    }



    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public UserInfo queryUserInfoByOpenid(String openId) {
        Example userExample = new Example(UserInfo.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("openid", openId);
        return userMapper.selectOneByExample(userExample);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserInfo weChatLogin(String encryptedData, String iv, String code) throws Exception {
        Map<String, String> param = new HashMap<>(4);
        param.put("appid", appid);
        param.put("secret", secret);
        param.put("js_code", code);
        param.put("grant_type", grantType);

        //获取session_id
        String sr = HttpClientUtil.doGet(url, param);
        // 判断获取的是否有值
        if (StringUtils.isBlank(sr)) {
            throw new Exception("获取微信数据失败");
        }
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        String openid = json.getString("openid");
        UserInfo user = queryUserInfoByOpenid(openid);
        if (user != null) {
            return user;
        }
        //获取会话密钥（session_key）
        String sessionKey = json.get("session_key").toString();
        //2、对encryptedData加密数据进行AES解密
        String result = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");

        if (StringUtils.isBlank(result)) {
            throw new Exception("解析微信数据失败");
        }

        JSONObject userInfoJSON = JSONObject.parseObject(result);
        user = new UserInfo();
        user.setOpenid(userInfoJSON.getString("openId"));
        user.setName(userInfoJSON.getString("nickName"));

        /* user 使用的是回调 id */
        boolean isSave = saveUserInfo(user);
        System.out.println(user.getId());
        if (!isSave) {
            throw new Exception("服务器忙碌，请等下再试");
        }
//        user = queryUserByOpenid(openid);
        return user;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean updateUserInfo(UserInfo user) {
        Example userExample = new Example(UserInfo.class);
        Example.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andEqualTo("id", user.getId());

        int i = userMapper.updateByExampleSelective(user, userExample);
        return i == 1;
    }
}
