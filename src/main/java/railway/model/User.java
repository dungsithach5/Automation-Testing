package railway.model;

public class User {
    private String email;
    private String password;
    private String newPassword;
    private String confirmPassword;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String password, String newPassword, String confirmPassword) {
        this.password = password;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
