package site.metacoding.blogv3.web.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.blogv3.domain.user.User;

@NoArgsConstructor
@AllArgsConstructor
@Data // Getter, Setter, toString
public class JoinReqDto {

    @Size(min = 4, max = 20)
    @NotBlank
    @Pattern(regexp = "[a-zA-Z1-9]{4,20}", message = "ID엔 한글 사용 불가")
    private String username;

    @Size(min = 4, max = 20)
    @NotBlank
    private String password;

    @Email
    @Size(min = 8, max = 60)
    @NotBlank // @NotNull, @NotEmpty 2개 기능을 같이 가지고있음. null, "", " "
    private String email;

    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }
}
