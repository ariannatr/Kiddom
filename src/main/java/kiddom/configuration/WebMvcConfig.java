package kiddom.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	/**Define Url Pages **/
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/activity_reg").setViewName("activity_reg");
		registry.addViewController("/about").setViewName("about");
		registry.addViewController("/error_page").setViewName("error_page");
		registry.addViewController("/faq").setViewName("faq");
		registry.addViewController("/profile").setViewName("profile");
		registry.addViewController("/profileProvider").setViewName("profileProvider");
		registry.addViewController("/register_prov").setViewName("register_prov");
		registry.addViewController("/register").setViewName("register");
		registry.addViewController("/search").setViewName("search");
		registry.addViewController("/activity").setViewName("activity");
		registry.addViewController("/google_map").setViewName("google_map");
		registry.addViewController("/category_submit").setViewName("category_submit");
		registry.addViewController("/buy_points").setViewName("buy_points");
	}

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setPageParameterName("page.page");
        resolver.setSizeParameterName("page.size");
        resolver.setFallbackPageable(new PageRequest(1, 3));
        resolver.setOneIndexedParameters(true);
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }

	/*@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
	{
		PageableArgumentResolver resolver = new PageableArgumentResolver();
		resolver.setFallbackPagable(new PageRequest(1, 5));
		argumentResolvers.add(new ServletWebArgumentResolverAdapter(resolver));
	}*/

}