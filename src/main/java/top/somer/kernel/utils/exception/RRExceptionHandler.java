package top.somer.kernel.utils.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.somer.kernel.model.dto.AjaxResult;

/**
 * 异常处理
 *
 * @author Somer
 * @date 2018-03-07 20:00
 **/
@RestControllerAdvice
public class RRExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 权限异常
     *
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public AjaxResult handleAuthorizationException() {
        try {

        } catch (AuthorizationException e) {
            logger.error(e.getMessage(), e);
        }
        return new AjaxResult("500", "没有权限，请联系管理员授权");
    }

    /**
     * token失效异常
     *
     * @return
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    public AjaxResult handleIncorrectCredentialsException() {
        try {

        } catch (IncorrectCredentialsException e) {
            logger.error(e.getMessage(), e);
        }
        return new AjaxResult("401", "token失效");
    }

}
