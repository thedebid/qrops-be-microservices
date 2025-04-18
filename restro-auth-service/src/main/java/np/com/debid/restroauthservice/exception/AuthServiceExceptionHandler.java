package np.com.debid.restroauthservice.exception;

import np.com.debid.restrocommons.util.ResponseUtil;
import np.com.debid.restrocommons.util.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AuthServiceExceptionHandler {
    int httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    String apiPath = null;
    String httpMethod = null;

    @ExceptionHandler(value = {InsufficientAuthenticationException.class})
    public ResponseEntity<ResponseWrapper<ResponseUtil>> handleInsufficientAuthenticationException(InsufficientAuthenticationException exception, WebRequest request) {
        httpStatusCode = HttpStatus.UNAUTHORIZED.value();
        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
        return ResponseUtil.errorResponse(httpStatusCode, exception.getMessage(), null, apiPath, httpMethod);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ResponseWrapper<ResponseUtil>> handleRuntimeException(RuntimeException exception, WebRequest request) {
        httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
        return ResponseUtil.errorResponse(httpStatusCode, exception.getMessage(), null, apiPath, httpMethod);
    }
}
