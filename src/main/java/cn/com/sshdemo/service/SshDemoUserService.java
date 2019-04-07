package cn.com.sshdemo.service;

import cn.com.sshdemo.domain.User;
import cn.com.sshdemo.exception.SshDemoServiceException;

import java.util.List;

/**
 * @Author: oyc
 * @Date: 2019-04-07 16:30
 * @Description:
 */
public interface SshDemoUserService {
    List<User> getUser() throws SshDemoServiceException;
}
