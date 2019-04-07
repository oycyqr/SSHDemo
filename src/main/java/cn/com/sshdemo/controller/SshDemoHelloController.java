package cn.com.sshdemo.controller;

import cn.com.sshdemo.domain.User;
import cn.com.sshdemo.service.SshDemoUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: oyc
 * @Date: 2019-04-07 16:02
 * @Description: 框架测试hello Controller
 */
@Controller
public class SshDemoHelloController {
    @Resource
    private SshDemoUserService userService;

    @RequestMapping("/hello")
    public String test() throws Exception {
        try {
            List<User> userList = userService.getUser();
            System.out.println(userList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello";
    }

}
