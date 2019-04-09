package cn.com.sshdemo.dao;

import cn.com.sshdemo.common.impl.BaseDaoImpl;
import cn.com.sshdemo.domain.User;
import org.springframework.stereotype.Component;

/**
 * @Author: oyc
 * @Date: 2019-04-07 20:32
 * @Description:
 */
@Component
public class SshDemoUserDao<T extends User> extends BaseDaoImpl<User> {
}
