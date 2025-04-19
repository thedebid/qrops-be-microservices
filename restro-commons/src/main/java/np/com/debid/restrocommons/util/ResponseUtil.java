package np.com.debid.restrocommons.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<ResponseWrapper<T>> successResponse(int statusCode, String message, T data) {
        return new ResponseEntity<>(new ResponseWrapper<>(statusCode, message, data), HttpStatusCode.valueOf(statusCode));
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> successResponse(String message, T data) {
        return new ResponseEntity<>(new ResponseWrapper<>(HttpStatus.OK.value(), message, data), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> successResponse(String message) {
        return new ResponseEntity<>(new ResponseWrapper<>(HttpStatus.OK.value(), message, null), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> errorResponse(int statusCode, String message, T data, int errorCode, String apiPath, String httpMethod) {
        return new ResponseEntity<>(new ResponseWrapper<>(statusCode, message, data, errorCode, apiPath, httpMethod), HttpStatusCode.valueOf(statusCode));
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> errorResponse(int statusCode, String message, T data, String apiPath, String httpMethod) {
        return new ResponseEntity<>(new ResponseWrapper<>(statusCode, message, data, apiPath, httpMethod), HttpStatusCode.valueOf(statusCode));
    }

}
