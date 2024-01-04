package User;

public class User {
    private String uid;
    private String username;
    private String password;
    private String token;
    private String membertype;

    public String getMembertype() {
        return membertype;
    }

    public void setMembertype(String membertype) {
        this.membertype = membertype;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "\"User\":{" +
                "\"uid\":\"" + uid + '\"' +
                ", \"username\":\"" + username + '\"' +
//                ", \"password\":\"" + password + '\"' +
//                ", \"token\":\"" + token + '\"' +
                ", \"membertype\":\"" + membertype + '\"' + '}';
    }
}
