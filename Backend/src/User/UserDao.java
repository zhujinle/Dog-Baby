package User;

import news.news;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public User login(User loginUser) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }
    public Integer setToken(User loginUser) {
        try {
            String sql = "UPDATE java.user t " +
                    "SET t.Token = ? " +
                    "WHERE t.uid = ?;";
            template.update(sql, loginUser.getToken(), loginUser.getUid());
            return 1;
        } catch (DataAccessException e) {
            System.out.println(e);
            return 0;
        }
    }

    public User fidsuser(String token) {
        try {
            String sql = "select * from user where Token = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), token);
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public Integer addUser(String username, String password, String membertype) {
        try {
            String sql = "INSERT INTO java.user (username, password, membertype) VALUES (?, ?, ?);";
            template.update(sql, username, password, membertype);
            return 1;
        } catch (DataAccessException e) {
            return 0;
        }
    }

    public String listUser() {
        try {
            String sql = "select * from user";
            return template.query(sql,new BeanPropertyRowMapper<User>(User.class)).toString();
        } catch (DataAccessException e) {
            return null;
        }
    }

    public Integer modifyUser(User inUser){
        try {
            String sql = "UPDATE java.user t " +
                    "SET t.username = ?, t.password = ?, t.membertype = ? " +
                    "WHERE t.uid = ?;";
            return template.update(sql, inUser.getUsername(), inUser.getPassword(), inUser.getMembertype(), inUser.getUid());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public User fidsuserbyuid(String uid) {
        try {
            String sql = "select * from user where uid = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), uid);
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }
    public Integer deleteUser(User inUser){
        try {
            String sql = "DELETE FROM java.user WHERE uid = ?;";
            return template.update(sql, inUser.getUid());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
