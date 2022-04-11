package site.metacoding.blogv3.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.handler.ex.CustomHtmlException;
import site.metacoding.blogv3.service.UserService;
import site.metacoding.blogv3.web.dto.user.JoinReqDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "/user/joinForm";
    }

    @PostMapping("/join")
    // BindingResult는 무조건 @Valid 뒤에 있어야한다.
    public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fe : bindingResult.getFieldErrors()) {
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            throw new CustomHtmlException(errorMap.toString());
        }

        // 핵심 로직
        userService.회원가입(joinReqDto.toEntity());

        return "redirect:/login-form";
    }
}
