package news;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class newsDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public Integer newNews(news inputNews) {
        try {
            String sql = "INSERT INTO java.news (title, content, date, type, updateDate, img, authorUID)" +
                    "VALUES ('?', '?', '?', 1, null, '?', '?')";
            template.update(sql, inputNews.getTitle(), inputNews.getContent(),inputNews.getDate(),inputNews.getImg(),inputNews.getAuthorUID());
            return 1;
        } catch (DataAccessException e) {
            return 0;
        }
    }


}
