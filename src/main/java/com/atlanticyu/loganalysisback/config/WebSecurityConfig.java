package com.atlanticyu.loganalysisback.config;

import com.atlanticyu.loganalysisback.filter.JwtTokenFilter;
import com.atlanticyu.loganalysisback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration      //指明当前类为一个配置类，可替代Spring配置文件
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    //@Bean 将方法的返回值添加到组件中，容器中这个组件默认的id就是方法名
    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
        System.out.println("JwtTokenFilter组件已添加入容器");
        return new JwtTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        System.out.println("AuthenticationManager组件已添加入容器");
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( userService ).passwordEncoder( new BCryptPasswordEncoder() );
    }

    @Override
    protected void configure( HttpSecurity httpSecurity ) throws Exception {

        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()  //跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.POST, "/admin/**").permitAll()
                .antMatchers(HttpMethod.POST, "/authentication/**").permitAll()
           //     .antMatchers(HttpMethod.POST).authenticated()
                .antMatchers(HttpMethod.PUT).authenticated()
                .antMatchers(HttpMethod.DELETE).authenticated();
             //   .antMatchers(HttpMethod.GET).authenticated();

        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行swagger
        web.ignoring().antMatchers(HttpMethod.GET,
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**");
    }
}
