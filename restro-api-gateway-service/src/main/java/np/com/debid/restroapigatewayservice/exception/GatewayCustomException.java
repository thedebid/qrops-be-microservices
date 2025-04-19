package np.com.debid.restroapigatewayservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GatewayCustomException extends RuntimeException {

    private String requestId;
    private String message;
    private int statusCode;
    private int errorCode;


    public GatewayCustomException() {
        super();
    }

    public GatewayCustomException(String message) {
        super(message);
        this.message = message;
    }

    public GatewayCustomException(String message, int statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

    public GatewayCustomException(String message, int statusCode, int errorCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }
}