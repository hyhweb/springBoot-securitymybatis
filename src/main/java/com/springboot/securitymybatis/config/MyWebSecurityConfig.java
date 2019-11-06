package com.springboot.securitymybatis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.securitymybatis.dao.TSysUserDao;
import com.springboot.securitymybatis.service.TSysUserService;
import com.springboot.securitymybatis.service.impl.TSysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  TSysUserServiceImpl tSysUserService;

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
    //return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  RoleHierarchy roleHierarchy(){
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    String hierarchy = "ROLE_dba> ROLE_admin \n ROLE_admin > ROLE_user";
    roleHierarchy.setHierarchy(hierarchy);
    return roleHierarchy;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(tSysUserService);
    /*auth.inMemoryAuthentication()
        .withUser("admin")
            .password("$2a$10$xTL9QJHCaslb2hVDTuub7ucseMR7P37gB3DeGqCGo9F3WJSrXhHye")
        //.password("123")
        .roles("ADMIN")
        .and()
            .withUser("dba")
            .password("$2a$10$ZD8ODNNEwIPBbvV2AVaQh.HR1a7cNdH6SEI4roXTqeLW1Np.lf40S")
            .roles("DBA")
            .and()
        .withUser("hong")
            .password("$2a$10$dIfjnKcRNFNl.vzC6UV9AOiJwS5uJ0Z9oH8IABkrkv.nT92MdwBue")
        //.password("123")
        .roles("USER");*/
    //super.configure(auth);
  }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/admin/**")
            .hasRole("admin")
            .antMatchers("/dba/**")
            .hasRole("dba")
            .antMatchers("/user/**")
            .hasRole("user")
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login_page")
            .loginProcessingUrl("/login")
            .successHandler(
                    new AuthenticationSuccessHandler() {
                      @Override
                      public void onAuthenticationSuccess(
                              HttpServletRequest req, HttpServletResponse resp, Authentication auth)
                              throws IOException, ServletException {
                        Object principal = auth.getPrincipal();
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(200);
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", principal);
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                      }
                    })
            .failureHandler(
                    new AuthenticationFailureHandler() {
                      @Override
                      public void onAuthenticationFailure(
                              HttpServletRequest req, HttpServletResponse resp, AuthenticationException e)
                              throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(401);
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 401);
                        if (e instanceof LockedException) {
                          map.put("msg", "账号被锁定，登录失败");
                        } else if (e instanceof BadCredentialsException) {
                          map.put("msg", "账号或密码输入错误，请重新登录");
                        } else if (e instanceof DisabledException) {
                          map.put("msg", "账号被禁用，登录失败");
                        } else if (e instanceof AccountExpiredException) {
                          map.put("msg", "账号过期，登录失败");
                        } else if (e instanceof CredentialsExpiredException) {
                          map.put("msg", "密码过期，登录失败");
                        } else {
                          map.put("msg", "登录失败");
                        }
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                      }
                    })
            .and()
            .logout()
            .logoutUrl("/logout")
            .clearAuthentication(true)
            .invalidateHttpSession(true)
            .addLogoutHandler(new LogoutHandler() {
              @Override
              public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication auth) {

              }
            })
            .logoutSuccessHandler(new LogoutSuccessHandler() {
              @Override
              public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.sendRedirect("/logout_page");
              }
            })
            .permitAll()
            .and()
            .csrf()
            .disable();
   /* http.authorizeRequests()
        .antMatchers("/admin/**")
        .hasRole("ADMIN")
        .antMatchers("/user/**")
        .access("hasAnyRole('ADMIN','USER')")
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login_page")
        .loginProcessingUrl("/login")
        .usernameParameter("name")
        .passwordParameter("password")
        .successHandler(
            new AuthenticationSuccessHandler() {
              @Override
              public void onAuthenticationSuccess(
                  HttpServletRequest req, HttpServletResponse resp, Authentication auth)
                  throws IOException, ServletException {
                Object principal = auth.getPrincipal();
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                resp.setStatus(200);
                Map<String, Object> map = new HashMap<>();
                map.put("status", 200);
                map.put("msg", principal);
                ObjectMapper om = new ObjectMapper();
                out.write(om.writeValueAsString(map));
                out.flush();
                out.close();
              }
            })
        .failureHandler(
            new AuthenticationFailureHandler() {
              @Override
              public void onAuthenticationFailure(
                  HttpServletRequest req, HttpServletResponse resp, AuthenticationException e)
                  throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                resp.setStatus(401);
                Map<String, Object> map = new HashMap<>();
                map.put("status", 401);
                if (e instanceof LockedException) {
                  map.put("msg", "账号被锁定，登录失败");
                } else if (e instanceof BadCredentialsException) {
                  map.put("msg", "账号或密码输入错误，请重新登录");
                } else if (e instanceof DisabledException) {
                  map.put("msg", "账号被禁用，登录失败");
                } else if (e instanceof AccountExpiredException) {
                  map.put("msg", "账号过期，登录失败");
                } else if (e instanceof CredentialsExpiredException) {
                  map.put("msg", "密码过期，登录失败");
                } else {
                  map.put("msg", "登录失败");
                }
                ObjectMapper om = new ObjectMapper();
                out.write(om.writeValueAsString(map));
                out.flush();
                out.close();
              }
            })
        .and()
        .logout()
        .logoutUrl("/logout")
            .clearAuthentication(true)
            .invalidateHttpSession(true)
            .addLogoutHandler(new LogoutHandler() {
              @Override
              public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication auth) {

              }
            })
            .logoutSuccessHandler(new LogoutSuccessHandler() {
              @Override
              public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.sendRedirect("/logout_page");
              }
            })
        .permitAll()
        .and()
        .csrf()
        .disable();*/

        //super.configure(http);
    }
}
