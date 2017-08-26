package jp.co.future.uroborosql.springboot.demo.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoggingInterceptor
 *
 * @author Kenichi Hoshi
 */
@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        request.setAttribute("request-stopwatch", stopWatch);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object object, Exception ex)
        throws Exception {
        StopWatch stopWatch = (StopWatch) request.getAttribute("request-stopwatch");
        stopWatch.stop();

        LOG.info("{} - {} {}ms", response.getStatus(), request.getRequestURI(), stopWatch.getTotalTimeMillis());
        if (ex != null) {
            LOG.error(ex.getMessage(), ex);
        }
    }
}
