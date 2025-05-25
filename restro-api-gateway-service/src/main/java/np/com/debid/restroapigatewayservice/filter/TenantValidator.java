package np.com.debid.restroapigatewayservice.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class TenantValidator {
    private static final List<String> TENANT_WHITELIST = List.of(
            "/api/v1/restaurant/create", "/api/v1/restaurant/my-restaurants"
    );

    public Predicate<ServerHttpRequest> isTenantIdRequired =
            request -> TENANT_WHITELIST.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
