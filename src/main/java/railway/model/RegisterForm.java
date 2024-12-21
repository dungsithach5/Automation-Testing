package railway.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterForm {
    private String email;
    private String password;
    private String confirmPassword;
    private String pid;

    public User toUser() {
        return new User(email, password);
    }
}
