package jp.co.future.uroborosql.springboot.demo.config;


import jp.co.future.uroborosql.springboot.demo.common.interceptors.AuthInterceptor;
import jp.co.future.uroborosql.springboot.demo.common.interceptors.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${petclinic.enabled-auth}")
    private boolean enabledAuth;

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
            .addPathPatterns("/**");
        if (enabledAuth) {
            registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");

        }
    }

}
