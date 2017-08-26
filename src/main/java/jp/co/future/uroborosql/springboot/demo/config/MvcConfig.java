package jp.co.future.uroborosql.springboot.demo.config;


import jp.co.future.uroborosql.springboot.demo.interceptors.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * MVC configuration
 *
 * @author Kenichi Hoshi
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    private final LoggingInterceptor loggingInterceptor;

    @Autowired
    public MvcConfig(LoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/**");
    }

}
