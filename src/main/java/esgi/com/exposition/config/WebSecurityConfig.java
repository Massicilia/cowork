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


  /*  public void configureGlobal (AuthenticationManagerBuilder authenticationMgr) throws Exception {

        authenticationMgr.jdbcAuthentication ().dataSource (dataSource ())
                .usersByUsernameQuery (
                        "select email,password from user where email=? and statusenable=true")
                .authoritiesByUsernameQuery (
                        "select email,role from user where email=? and statusenable=true");

        System.out.println (authenticationMgr.jdbcAuthentication ().dataSource (dataSource ())
                .usersByUsernameQuery (
                        "select email,password from user where email=? and statusenable=true")
                .authoritiesByUsernameQuery (
                        "select email,role from user where email=? and statusenable=true"));
    }

    @Bean (name = "dataSource")
    public DriverManagerDataSource dataSource () {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource ();
        driverManagerDataSource.setDriverClassName ("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl ("jdbc:mysql://localhost:3306/pal");
        driverManagerDataSource.setUsername ("root");
        driverManagerDataSource.setPassword ("");
        return driverManagerDataSource;
    }
*/
    @Override
    public void configure (HttpSecurity http) throws Exception {
        http.csrf ().disable ().authorizeRequests ()
                .antMatchers ("/").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.GET, "/user/users").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.GET, "/user/{uuid}").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.POST, "/user/insert").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.GET, "/user/auth").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.GET, "/user/username").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.GET, "/ticket/tickets").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.GET, "/ticket/{uuid}").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.GET, "/ticket/assigned/{uuid}").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.GET, "/ticket/creator/{uuid}").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.POST, "/ticket/insertTicket").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.POST, "/ticket/statuschange").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.GET, "/equipment/available/{type}").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.GET, "/equipment/{uuid}").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.POST, "/mealtrayorder/new").permitAll ()
                .antMatchers (org.springframework.http.HttpMethod.GET, "/mealtrayorder/{date}").permitAll ()
                .anyRequest ().authenticated ();
    }


}