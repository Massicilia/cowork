package esgi.com.exposition.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(1000)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @org.springframework.beans.factory.annotation.Autowired
    private javax.sql.DataSource dataSource;

    /*  @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration ();
        configuration.setAllowedOrigins(java.util.Arrays.asList("*"));//http://localhost:4200/"));
        configuration.setAllowedMethods(java.util.Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(java.util.Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(java.util.Arrays.asList("x-auth-token"));
        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource ();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
*/
    @Override
    public void configure (HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }


    @org.springframework.beans.factory.annotation.Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("root").password("{noop}"+System.getenv("SPRING_SECURITY_USER_PASSWORD")).roles("USER");

    }

}