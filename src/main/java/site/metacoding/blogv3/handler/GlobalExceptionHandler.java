package site.metacoding.blogv3.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import site.metacoding.blogv3.handler.ex.CustomApiException;
import site.metacoding.blogv3.handler.ex.CustomHtmlException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(Exception e) { // fetch 요청 시 발동
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomHtmlException.class)
    public String htmlException(Exception e) { // 일반적인 Get, Post 요청 시 발동
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + e.getMessage() + "');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

}
