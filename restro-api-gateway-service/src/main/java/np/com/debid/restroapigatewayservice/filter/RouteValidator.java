package np.com.debid.restroapigatewayservice.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    private static final List<String> AUTH_WHITELIST = List.of(
            "/api/v1/auth/register", "/api/v1/auth/login"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> AUTH_WHITELIST.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
