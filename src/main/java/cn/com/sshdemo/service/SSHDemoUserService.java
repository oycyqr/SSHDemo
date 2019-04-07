package cn.com.sshdemo.service;

import cn.com.sshdemo.domain.User;
import cn.com.sshdemo.exception.SSHDemoServiceException;

import java.util.List;

/**
 * @Author: oyc
 * @Date: 2019-04-07 16:30
 * @Description:
 */
public interface SSHDemoUserService {
    List<User> getUser() throws SSHDemoServiceException;
}
