package com.app.framework.auth.filter;

import com.app.framework.core.utils.Log;
import com.app.framework.core.utils.LoggerFactory;
import com.app.framework.core.utils.Response;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Log logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    //shiro异常处理
    @ExceptionHandler({AuthenticationException.class, AuthorizationException.class})
    public Response handleException(HttpServletRequest request, HttpServletResponse response, ShiroException exception) throws Exception {
        Integer responseCode = HttpStatus.UNAUTHORIZED.value();
        //如下的异常顺序还需要根据父子关系调整
        /**
         * UnknownAccountException extends AccountException extends AuthenticationException extends ShiroException
         * IncorrectCredentialsException extends CredentialsException  extends AuthenticationException extends ShiroException
         * UnauthenticatedException extends AuthorizationException extends ShiroException
         * UnauthorizedException extends  AuthorizationException extends ShiroException
         * ExcessiveAttemptsException extends AccountException extends AuthenticationException extends ShiroException
         * ExpiredCredentialsException extends CredentialsException extends AuthenticationException extends ShiroException
         * DisabledAccountException extends AccountException extends AuthenticationException extends ShiroException
         * LockedAccountException extends DisabledAccountException extends AccountException extends AuthenticationException extends ShiroException
         */
        /**
         * （IncorrectCredentialsException，ExpiredCredentialsException）-> CredentialsException
         * 最后的顺序(zi-parent)：LockedAccountException->DisabledAccountException
         */
        DisabledAccountException disabledAccountException = null;
        if (exception instanceof UnknownAccountException) {
            return new Response(responseCode, "账号不存在");
        } else if (exception instanceof IncorrectCredentialsException) {
            return new Response(responseCode, "密码错误");
        } else if (exception instanceof UnauthenticatedException) {
            return new Response(responseCode, "用户未通过认证");
        } else if (exception instanceof UnauthorizedException) {
            return new Response(responseCode, "用户授权错误");
        } else if (exception instanceof ExcessiveAttemptsException) {
            return new Response(responseCode, "登录失败次数过多");
        } else if (exception instanceof ExpiredCredentialsException) {
            return new Response(responseCode, "凭证过期");
        } else if (exception instanceof LockedAccountException) {
            return new Response(responseCode, "帐号被锁定");
        } else if (exception instanceof DisabledAccountException) {
            return new Response(responseCode, "帐号被禁用");
        } else {
            return new Response(responseCode, "用户未通过认证");
        }
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleException(HttpServletRequest request, IllegalStateException exception) throws Exception {
        logger.error(exception.getMessage());
        return new Response(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleException(HttpServletRequest request, IllegalArgumentException exception) throws Exception {
        logger.error(exception.getMessage());
        return new Response(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    //参数绑定异常处理
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleException(HttpServletRequest request, BindException exception) throws Exception {
        return buildResponse(exception.getFieldErrors());
    }


    //@Valid异常处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleException(HttpServletRequest request, MethodArgumentNotValidException exception) throws Exception {
        return buildResponse(exception.getBindingResult().getFieldErrors());
    }

    //@RequestParam异常处理
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleException(HttpServletRequest request, MissingServletRequestParameterException exception) throws Exception {
        StringBuffer sb = new StringBuffer();
        buildLog(sb, exception.getParameterName(), exception.getMessage());
        return new Response(HttpStatus.BAD_REQUEST.value(), sb.toString());
    }

    private void buildLog(StringBuffer sb, String field, String msg) {
        sb.append("field:[");
        sb.append(field);
        sb.append("] message:[");
        sb.append(msg);
        sb.append("]");
        logger.error(sb.toString());
    }

    private Response buildResponse(List<FieldError> fieldErrors) throws IOException {
        Response response = new Response();
        StringBuffer sb = new StringBuffer();
        for (FieldError fieldError : fieldErrors) {
            buildLog(sb, fieldError.getField(), fieldError.getDefaultMessage());
        }
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMsg(sb.toString());
        return response;
    }

}
