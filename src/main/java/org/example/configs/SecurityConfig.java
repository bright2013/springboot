/**
 * Copyright (C), 2019-2019, 软件卓越人才班
 * FileName: SecurityConfig
 * Author:   hzg
 * Date:     2019-12-27 08:10
 * Description: 登陆拦截等配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.example.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;
import javax.sql.DataSource;
/**
 * 〈一句话功能简述〉<br> 
 * 〈登陆拦截等配置〉
 *
 * @author hzg
 * @create 2019-12-27
 * @since 1.0.0
 */
@Configuration
@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    DataSource dataSource;

    @Resource
    AuthSuccessHandler successHandler;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password, groupname, enabled from t_users where username=?")
                .authoritiesByUsernameQuery("select username, rolename from t_user_roles where username=?");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2_console", "/assets/**", "/webjars/**",
                "/css/**", "/icons/**", "/js/**", "favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/example/**").permitAll() //不拦截示例的html文件
                .antMatchers("/h2_console/**").permitAll() //开发时使用，生产环境需要去掉
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/student/**").hasRole("STUDENT")
                .antMatchers("/teacher/**").hasRole("TEACHER")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .successHandler(successHandler)  //登陆成功后的处理
                .and().logout().permitAll();

        //开发时使用，生产环境需要去掉，参见https://grokonez.com/spring-framework/spring-security/configure-spring-security-access-h2-database-console-spring-boot-project
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
