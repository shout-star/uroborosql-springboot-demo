package jp.co.future.uroborosql.springboot.demo.common.interceptors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jp.co.future.uroborosql.springboot.demo.common.context.AuthContext;
import jp.co.future.uroborosql.springboot.demo.common.exceptions.JwtAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AuthInterceptor
 *
 * @author Kenichi Hoshi
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthInterceptor.class);

    @Value("${petclinic.jwt-secret-key}")
    private String secretKey;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws Exception {
        String authHeader = request.getHeader("authorization");

        if (!"OPTIONS".equals(request.getMethod())) {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new JwtAuthException();
            }

            String token = authHeader.substring(7);

            try {
                Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
                AuthContext.addClaims(claims);
            } catch (Exception e) {
                LOG.error("JWT parse error.", e);
                throw new JwtAuthException(e);
            }
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object object, Exception ex) throws Exception {
        AuthContext.removeClaims();
    }
}
