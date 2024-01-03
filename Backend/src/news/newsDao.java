package news;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class newsDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public Integer newNews(news inputNews) {
        try {

            String sql = "INSERT INTO java.news (title, author, content, date, type, updateDate, img, authorUID)" +
                    "VALUES (?, ?,?, ?, 1, DEFAULT, ?, ?)";
            if(inputNews.getImg() == null) {
                sql = "INSERT INTO java.news (title, author, content, date, type, updateDate, img, authorUID)" +
                        "VALUES (?, ?,?, ?, 1, DEFAULT, DEFAULT, ?)";
                template.update(sql, inputNews.getTitle(), inputNews.getAuthor(), inputNews.getContent(), inputNews.getDate(), inputNews.getAuthorUID());
            }
            else {
                template.update(sql, inputNews.getTitle(), inputNews.getAuthor(), inputNews.getContent(), inputNews.getDate(), inputNews.getImg(), inputNews.getAuthorUID());
            }
            return 1;
        } catch (DataAccessException e) {
            System.out.println(e);
            return 0;
        }
    }


}
