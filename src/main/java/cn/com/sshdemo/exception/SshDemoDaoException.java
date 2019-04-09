package cn.com.sshdemo.exception;

import org.springframework.dao.DataAccessException;

/**
 * @Author: oyc
 * @Date: 2019-04-07 16:34
 * @Description: dao自定义异常类
 */
public class SshDemoDaoException extends DataAccessException {

    private static final long serialVersionUID = -3247597564472350006L;

    public SshDemoDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public SshDemoDaoException(String message) {
        super(message);
    }


}
