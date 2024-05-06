package common;

public class User {
    private String username;
    private String qqNumber;
    private String password;
    private String phoneNumber;

    public User() {
    }

    public User(String username, String qqNumber, String password, String phoneNumber) {
        this.username = username;
        this.qqNumber = qqNumber;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "common.User{" +
                "username='" + username + '\'' +
                ", qqNumber='" + qqNumber + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
