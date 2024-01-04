package news;

import User.User;
import User.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "addNews", value = "/addNews")
public class addNews extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("utf-8");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String token = req.getParameter("token");
        String date = req.getParameter("date");
        String img = req.getParameter("img");
        newsDao dao = new newsDao();
        UserDao userdao = new UserDao();
        User authorUser = userdao.fidsuser(token);
        String author = authorUser.getUsername();
        String authoruid = authorUser.getUid();
        news inputNews = new news();
        inputNews.setTitle(title);
        inputNews.setContent(content);
        inputNews.setDate(date);
        inputNews.setAuthor(author);
        inputNews.setAuthorUID(authoruid);
        inputNews.setImg(img);
        Integer result = dao.newNews(inputNews);
        resp.setContentType("application/json");
        if (result == 1) {
            resp.getWriter().write("{\"statusCode\": 200, \"msg\": \"添加成功\"}");
        } else {
            resp.getWriter().write("{\"statusCode\": 403, \"msg\": \"添加失败\"}");
        }
    }
}
