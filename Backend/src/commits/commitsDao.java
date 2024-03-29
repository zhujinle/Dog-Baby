package commits;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.time.DayOfWeek;

public class commitsDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public String listCommits(String tonewsid){
        try {
            if(tonewsid == null)
                return null;
            if(tonewsid.equals("0")) {
                String sql = "select * from commits order by time desc";
                return template.query(sql, new BeanPropertyRowMapper<commits>(commits.class)).toString();
            }
            String sql = "select * from commits where tonewsid = ? order by time desc";
            return template.query(sql, new BeanPropertyRowMapper<commits>(commits.class), tonewsid).toString();
        } catch (DataAccessException e) {
            return null;
        }
    }

    public Integer addCommit(commits doCommit){
        try {
            String sql = "insert into commits (fromuser, tonewsid, commit, time) values (?, ?, ?, ?)";
            return template.update(sql, doCommit.getFromuser(), doCommit.getTonewsid(), doCommit.getCommit(), doCommit.getTime());
        } catch (DataAccessException e) {
            return 0;
        }
    }

    public Integer deleteCommit(String cid){
        try {
            String sql = "delete from commits where cid = ?";
            return template.update(sql, cid);
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
