package np.com.debid.restroapigatewayservice.exception;

import np.com.debid.restrocommons.util.ResponseUtil;
import np.com.debid.restrocommons.util.ResponseWrapper;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.ServerWebExchange;

@ControllerAdvice
public class GatewayServiceExceptionHandler {

    int httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    String apiPath = null;
    String httpMethod = null;

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ResponseWrapper<ResponseUtil>> handleRuntimeException(RuntimeException exception, ServerWebExchange exchange) {
        String message = exception.getMessage();
        if (exception instanceof NoResourceFoundException) {
            httpStatusCode = ((NoResourceFoundException) exception).getStatusCode().value();
            message = ((NoResourceFoundException) exception).getReason();
        }
        if (exception instanceof NotFoundException) {
            httpStatusCode = ((NotFoundException) exception).getStatusCode().value();
            message = ((NotFoundException) exception).getReason();
        }
        httpMethod = exchange.getRequest().getMethod().toString();
        return ResponseUtil.errorResponse(httpStatusCode, message, null, apiPath, httpMethod);
    }

    @ExceptionHandler(value = {GatewayCustomException.class})
    public ResponseEntity<ResponseWrapper<ResponseUtil>> handleAPIGatewayCustomException(GatewayCustomException exception, ServerWebExchange exchange) {
        httpStatusCode = exception.getStatusCode();
        apiPath = exchange.getRequest().getURI().getPath();
        httpMethod = exchange.getRequest().getMethod().toString();
        return ResponseUtil.errorResponse(httpStatusCode, exception.getMessage(), null, apiPath, httpMethod);
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<ResponseWrapper<ResponseUtil>> handleIllegalState(IllegalStateException exception, ServerWebExchange exchange) {
//        httpStatusCode = exception.
        apiPath = exchange.getRequest().getURI().getPath();
        httpMethod = exchange.getRequest().getMethod().toString();
        return ResponseUtil.errorResponse(httpStatusCode, exception.getMessage(), null, apiPath, httpMethod);
    }
}