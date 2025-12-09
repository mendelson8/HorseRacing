package horses.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggedUserLog implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoggedUserLog.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        logger.info("User {} has successfully authenticated via {}",
                   authentication.getName(),
                   request.getRequestURI());

        String userAgent = request.getHeader("User-Agent");
        String clientIp = getClientIP(request);

        logger.debug("Authentication details - User: {}, IP: {}, User-Agent: {}",
                    authentication.getName(), clientIp, userAgent);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}