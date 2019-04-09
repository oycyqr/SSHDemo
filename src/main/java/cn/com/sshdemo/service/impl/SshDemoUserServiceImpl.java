package cn.com.sshdemo.service.impl;

import cn.com.sshdemo.dao.SshDemoUserDao;
import cn.com.sshdemo.domain.User;
import cn.com.sshdemo.exception.SshDemoServiceException;
import cn.com.sshdemo.service.SshDemoUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: oyc
 * @Date: 2019-04-07 16:31
 * @Description:
 */
@Service("SshDemoUserService")
public class SshDemoUserServiceImpl implements SshDemoUserService {
    @Resource
    private SshDemoUserDao userDao;

    @Override
    public List<User> getUser() throws SshDemoServiceException {
        return userDao.loadAll();
    }

    @Override
    public User getUserById(Integer id) throws SshDemoServiceException {
        return (User)userDao.getById(id);
    }
}
