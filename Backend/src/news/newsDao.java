package news;

import User.User;
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
    public Integer modifyNews(news inputNews) {
        try {

//            String sql = "UPDATE news t " +
//                    "set t.title = ?, t.author = ?, t.content = ?, t.updateDate = ?, t.img = ?, t.authorUID = ?, t.type = ? " +
//                    "WHERE t.nid = ?;";
//            template.update(sql, inputNews.getTitle(), inputNews.getAuthor(), inputNews.getContent(), inputNews.getUpdateDate(),inputNews.getImg(), inputNews.getAuthorUID(), inputNews.getType());
            String sql = "UPDATE news " +
                    "set news.title = ?, news.author = ?, news.content = ?, news.updateDate = ?, news.img = ? ,news.authorUID = ?, news.type = ?" +
                    "WHERE news.nid = ?;";
            template.update(sql, inputNews.getTitle(), inputNews.getAuthor(), inputNews.getContent(), inputNews.getUpdateDate(),inputNews.getImg(),inputNews.getAuthorUID(),inputNews.getType(),inputNews.getNid());
            return 1;
        } catch (DataAccessException e) {
            System.out.println(e);
            return 0;
        }
    }

    public news getNews(String nid){
        try {
            String sql = "select * from news where nid = ?";
            news returnnew= template.queryForObject(sql, new BeanPropertyRowMapper<news>(news.class), nid);
            return returnnew;
        } catch (DataAccessException e) {
            return null;
        }
    }
    public Integer reviewNews(String nid,String reviewer,String status){
        try {
            String sql = "UPDATE java.news t set t.type = ?, t.reviewer = ?" +
                    "WHERE t.nid = ?;";
            template.update(sql, status, reviewer,nid);
            return 1;
        } catch (DataAccessException e) {
            return 0;
        }
    }
    public String getNewsList(String uid) {
        try {
            if(uid.equals("0")) {
                String sql = "select * from news order by date desc";
                return template.query(sql, new BeanPropertyRowMapper<news>(news.class)).toString();
            }
            String sql = "select * from news where authorUID = ? order by date desc";
            return template.query(sql, new BeanPropertyRowMapper<news>(news.class), uid).toString();
        } catch (DataAccessException e) {
            return null;
        }
    }

    public Integer deleteNews(String nid) {
        try {
            String sql = "DELETE FROM news WHERE nid = ?";
            template.update(sql, nid);
            return 1;
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
