package User;

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
            System.out.println(e);
            return null;
        }
    }
}
