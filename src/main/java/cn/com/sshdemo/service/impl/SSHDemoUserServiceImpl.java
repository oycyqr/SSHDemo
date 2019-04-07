package cn.com.sshdemo.service.impl;

import cn.com.sshdemo.dao.SSHDemoUserDao;
import cn.com.sshdemo.domain.User;
import cn.com.sshdemo.exception.SSHDemoServiceException;
import cn.com.sshdemo.service.SSHDemoUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: oyc
 * @Date: 2019-04-07 16:31
 * @Description:
 */
@Service("SSHDemoUserService")
public class SSHDemoUserServiceImpl implements SSHDemoUserService {
    @Resource
    private SSHDemoUserDao userDao;

    @Override
    public List<User> getUser() throws SSHDemoServiceException {
        return userDao.findAll("User");
    }
}
