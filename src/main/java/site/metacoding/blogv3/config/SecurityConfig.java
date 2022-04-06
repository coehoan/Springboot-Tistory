package site.metacoding.blogv3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // 해당 파일로 시큐리티 활성화
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 인증을 설정하는 메서드
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        http.csrf().disable();
        // /s로 들어오는 주소는 인증필요, 나머진 필요없음
        http.authorizeRequests()
                .antMatchers("/s/**").authenticated()
                .anyRequest().permitAll()
                .and()
                // 로그인페이지를 loginForm으로 보내고
                // 성공하면 디폴트페이지를 /로
                .formLogin()
                .loginPage("/loginForm")
                .defaultSuccessUrl("/");
    }
}
