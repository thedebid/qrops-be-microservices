package np.com.debid.restrocommons.exception;

import io.jsonwebtoken.ExpiredJwtException;
import np.com.debid.restrocommons.util.ResponseUtil;
import np.com.debid.restrocommons.util.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionsHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionsHandler.class);
    int httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    String apiPath = null;
    String httpMethod = null;

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ResponseWrapper<Object>> handleCustomException(CustomException exception, WebRequest request) {
        httpStatusCode = exception.getStatusCode();
        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
        return ResponseUtil.errorResponse(httpStatusCode, exception.getMessage(), null, exception.getErrorCode(), apiPath, httpMethod);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ResponseWrapper<Object>> handleBadCredentialsException(Exception exception, WebRequest request) {
        httpStatusCode = HttpStatus.BAD_REQUEST.value();
        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
        return ResponseUtil.errorResponse(httpStatusCode, exception.getMessage(), null, apiPath, httpMethod);
    }

    @ExceptionHandler(value = {InsufficientAuthenticationException.class})
    public ResponseEntity<ResponseWrapper<ResponseUtil>> handleInsufficientAuthenticationException(Exception exception, WebRequest request) {
        httpStatusCode = HttpStatus.UNAUTHORIZED.value();
        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
        return ResponseUtil.errorResponse(httpStatusCode, exception.getMessage(), null, apiPath, httpMethod);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<ResponseWrapper<ResponseUtil>> handleExpiredJwtException(Exception exception, WebRequest request) {
        httpStatusCode = HttpStatus.UNAUTHORIZED.value();
        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
        return ResponseUtil.errorResponse(httpStatusCode, exception.getMessage(), null, apiPath, httpMethod);
    }

//
////    @ExceptionHandler(value = {DataIntegrityViolationException.class})
////    public ResponseEntity<Object> handleDataIntegrityViolationException(Exception exception, WebRequest request) {
////        httpStatusCode = HttpStatus.CONFLICT.value();
////        errorMessage = exception.getMessage();
////        String message = "could not execute statement";
////        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
////        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
////        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, message, errorMessage, httpMethod, apiPath, data);
////        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
////    }
////
////    @ExceptionHandler(value = {IllegalArgumentException.class})
////    public ResponseEntity<Object> handleIllegalArgumentException(Exception exception, WebRequest request) {
////        httpStatusCode = HttpStatus.BAD_REQUEST.value();
////        errorMessage = exception.getMessage();
////        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
////        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
////        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
////        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
////    }
////
////    @ExceptionHandler(value = {NullPointerException.class})
////    public ResponseEntity<Object> handleNullPointerException(Exception exception, WebRequest request) {
////        httpStatusCode = HttpStatus.NOT_FOUND.value();
////        errorMessage = exception.getMessage();
////        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
////        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
////        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
////        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
////    }
////
//
//
//
////

////
////    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
////    public ResponseEntity<Object> handleHttpMessageNotReadableException(Exception exception, WebRequest request) {
////        httpStatusCode = HttpStatus.BAD_REQUEST.value();
////        errorMessage = exception.getMessage();
////        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
////        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
////        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
////        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
////    }
////
////
////    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
////    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
////        httpStatusCode = HttpStatus.BAD_REQUEST.value();
////        Map<String, String> errorsMap = new HashMap<>();
////        exception.getBindingResult().getAllErrors().forEach((error) -> {
////            String fieldName = ((FieldError) error).getField();
////            String errorMessage = error.getDefaultMessage();
////            errorsMap.put(fieldName, errorMessage);
////        });
////        errorMessage = exception.getMessage().substring(0, 30);
////        errors = errorsMap;
////        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
////        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
////        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, errorMessage, httpMethod, apiPath, errors);
////        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
////    }
////
////    @ExceptionHandler(value = {ServerException.class})
////    public ResponseEntity<Object> handleServerException(Exception exception, WebRequest request) {
////        httpStatusCode = HttpStatus.BAD_REQUEST.value();
////        errorMessage = exception.getMessage();
////        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
////        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
////        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
////        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
////    }
////
////    @ExceptionHandler(value = {SignatureException.class})
////    public ResponseEntity<Object> handleSignatureException(Exception exception, WebRequest request) {
////        httpStatusCode = HttpStatus.FORBIDDEN.value();
////        errorMessage = exception.getMessage();
////        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
////        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
////        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
////        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
////    }
////
////    @ExceptionHandler(value = {ExpiredJwtException.class})
////    public ResponseEntity<Object> handleExpiredJwtException(Exception exception, WebRequest request) {
////        httpStatusCode = HttpStatus.FORBIDDEN.value();
////        errorMessage = exception.getMessage();
////        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
////        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
////        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
////        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
////    }
////
////    @ExceptionHandler(value = {MalformedJwtException.class})
////    public ResponseEntity<Object> handleMalformedJwtException(Exception exception, WebRequest request) {
////        httpStatusCode = HttpStatus.FORBIDDEN.value();
////        errorMessage = exception.getMessage();
////        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
////        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
////        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
////        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
////    }
////
////    @ExceptionHandler(value = {UnsupportedJwtException.class})
////    public ResponseEntity<Object> handleUnsupportedJwtException(Exception exception, WebRequest request) {
////        httpStatusCode = HttpStatus.FORBIDDEN.value();
////        errorMessage = exception.getMessage();
////        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
////        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
////        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
////        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
////    }
////
////    @ExceptionHandler(value = {UsernameNotFoundException.class})
////    public ResponseEntity<Object> handleUsernameNotFoundException(Exception exception, WebRequest request) {
////        httpStatusCode = HttpStatus.FORBIDDEN.value();
////        errorMessage = exception.getMessage();
////        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
////        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
////        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
////        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
////    }
//
//
//    /////////////////////////////////////////////////////////////////////
//    @ExceptionHandler(value = {np.com.debid.restrouserservice.exception.CustomException.class})
//    public ResponseEntity<Object> handleCustomException(np.com.debid.restrouserservice.exception.CustomException exception, WebRequest request) {
//        httpStatusCode = exception.getErrorCode();
//        errorMessage = exception.getMessage();
//        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
//        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
//        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
//        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
//    }
//
//    @ExceptionHandler(value = {AccessDeniedException.class})
//    public ResponseEntity<Object> handleAccessDeniedException(Exception exception, WebRequest request) {
//        httpStatusCode = HttpStatus.FORBIDDEN.value();
//        errorMessage = exception.getMessage();
//        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
//        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
//        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, "You do not have permission to access this resource. This may be due to: 'Insufficient Permissions', 'Authentication Required', 'Resource Restrictions'.", errorMessage, httpMethod, apiPath, data);
//        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
//    }
//        @ExceptionHandler(value = {BadCredentialsException.class})
//    public ResponseEntity<Object> handleBadCredentialsException(Exception exception, WebRequest request) {
//        httpStatusCode = HttpStatus.UNAUTHORIZED.value();
//        errorMessage = exception.getMessage();
//        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
//        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
//        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
//        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
//    }
//
//    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
//    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(Exception exception, WebRequest request) {
//        httpStatusCode = HttpStatus.METHOD_NOT_ALLOWED.value();
//        errorMessage = exception.getMessage();
//        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
//        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
//        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
//        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
//    }
//
//    @ExceptionHandler(value = {NoResourceFoundException.class})
//    public ResponseEntity<Object> handleNoResourceFoundException(Exception exception, WebRequest request) {
//        httpStatusCode = HttpStatus.NOT_FOUND.value();
//        errorMessage = exception.getMessage();
//        apiPath = ((ServletWebRequest) request).getRequest().getRequestURI();
//        httpMethod = ((ServletWebRequest) request).getRequest().getMethod();
//        ExceptionDTO exceptionResponseDTO = new ExceptionDTO(httpStatusCode, null, errorMessage, httpMethod, apiPath, data);
//        return new ResponseEntity<>(exceptionResponseDTO, HttpStatusCode.valueOf(httpStatusCode));
//    }
}
