package site.metacoding.blogv3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티 활성화
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    // 인증을 설정하는 메서드
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        http.csrf().disable(); // 이게 없으면 postman으로 테스트 불가능
        // /s로 들어오는 주소는 인증필요, 나머진 필요없음
        http.authorizeRequests()
                .antMatchers("/s/**").authenticated()
                .anyRequest().permitAll()
                .and()
                // 로그인페이지를 loginForm으로 보내고
                // 성공하면 디폴트페이지를 /로
                .formLogin()
                // .usernameParameter("user") 변경가능
                // .passwordParameter("pwd") 변경가능
                .loginPage("/login-form")
                .loginProcessingUrl("/login") // login 프로세스를 탄다
                // .failureHandler(null) 실패했을 때
                // .successHandler(null) 성공했을 때
                .defaultSuccessUrl("/");
    }
}
