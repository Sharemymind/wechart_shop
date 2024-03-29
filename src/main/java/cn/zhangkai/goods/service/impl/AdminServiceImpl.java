package cn.zhangkai.goods.service.impl;

import cn.zhangkai.goods.pojo.Admin;
import cn.zhangkai.goods.mapper.AdminMapper;
import cn.zhangkai.goods.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @className: AdminServiceImpl
 * @description:
 * @author: zxh
 * @date: 2019-1-29 10:04:23
 */
@Service
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Admin login(String adminName, String adminPassword) {

        Example adminExample = new Example(Admin.class);
        Example.Criteria criteria = adminExample.createCriteria();
        criteria.andEqualTo("name", adminName)
                .andEqualTo("password", adminPassword);
        return adminMapper.selectOneByExample(adminExample);
    }

}
