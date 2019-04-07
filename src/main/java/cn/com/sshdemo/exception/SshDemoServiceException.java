package cn.com.sshdemo.exception;

/**
 * @Author: oyc
 * @Date: 2019-04-07 16:34
 * @Description:Service自定义异常类
 */
public class SshDemoServiceException extends Exception{

    private static final long serialVersionUID = -3247597564472350006L;

    public SshDemoServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SshDemoServiceException(String message) {
        super(message);
    }

    public SshDemoServiceException(Throwable cause) {
        super(cause);
    }

}