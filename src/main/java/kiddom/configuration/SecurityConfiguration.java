package kiddom.configuration;
import javax.servlet.SessionTrackingMode;
import javax.sql.DataSource;


import kiddom.authentication.MyDBAythenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.EnumSet;


//@Configuration
//@EnableWebSecurity
@Component
@Configuration
//@EnableRedisHttpSession
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyDBAythenticationService myDBAauthenticationService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Qualifier("dataSource")
    @Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
        auth.userDetailsService(myDBAauthenticationService);
    }
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

    @Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/").permitAll()
				//.antMatchers("/login").permitAll()
				.antMatchers("/index").permitAll()
				.antMatchers("/register").permitAll()
                .antMatchers("/register_prov").permitAll()
                .antMatchers("/about").permitAll()
                .antMatchers("/error_page").permitAll()
                .antMatchers("/activity").permitAll()
                .antMatchers("/google_map").permitAll()
                .antMatchers("/search").permitAll()
                .antMatchers("/faq").permitAll()
                .antMatchers("/activity_reg").permitAll()//will be authorizied
                .antMatchers("/categories_form").permitAll()//will be authorizied
                .antMatchers("/category_submit").permitAll()//will be authorizied
                .antMatchers("/profile").hasRole("ROLE_1")//hasAuthority("1")//be a parent
                .antMatchers("/profileProvider").permitAll()//will be authorizied
				.antMatchers("/admin").hasAuthority("0").anyRequest()//be admin
				.authenticated().and().csrf().disable()
                .formLogin()
				.loginPage("/index")
                .loginProcessingUrl("/login")
                .failureUrl("/error_page")
                .defaultSuccessUrl("/index")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/error_page");
        http.sessionManagement()//now added
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(2)
                .expiredUrl("/index");
        http.sessionManagement()
                .invalidSessionUrl("/index")
                .sessionFixation().migrateSession();
      //  servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));*/
	}

    @Bean//lately added
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

  /*  @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }*/
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/fragments/**","/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/scripts/**");
		web.ignoring().antMatchers("/the_js_path/**");
	}


   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("password").roles("USER","ADMIN")
//                .and()
//                .withUser("jaiprak").password("password").roles("USER","ADMIN");
        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
        userDetailsService.setDataSource(datasource);
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
        auth.jdbcAuthentication().dataSource(datasource);


    }*/

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().fullyAuthenticated()
                .and()
                .httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .permitAll();
    }*/
}