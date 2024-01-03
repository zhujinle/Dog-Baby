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
        req.setCharacterEncoding("utf-8");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String token = req.getParameter("token");
        String date = req.getParameter("date");
        newsDao dao = new newsDao();
        UserDao userdao = new UserDao();
        User authorUser = userdao.fidsuser(token);
        String author = authorUser.getUsername();
        String authoruid = authorUser.getUid();
        super.doPost(req, resp);
    }
}
