package np.com.debid.restroapigatewayservice.filter;


import np.com.debid.restroapigatewayservice.exception.GatewayCustomException;
import np.com.debid.restroapigatewayservice.util.JwtUtil;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new GatewayCustomException("Authorization header is missing. Please include a valid Authorization header to access this resource", 401);
                }
                String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
                if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                jwtUtil.validateJwtToken(authHeader);
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}