package news;

import java.io.*;
import java.util.Objects;

import User.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "deleteNews", value = "/deleteNews")
public class deleteNews extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String nid = request.getParameter("nid");
        String token = request.getParameter("token");
        newsDao dao = new newsDao();
        news doNews = dao.getNews(nid);
        UserDao userdao = new UserDao();
        User douser = userdao.fidsuser(token);
        if(!(Objects.equals(douser.getMembertype(), "1")|| Objects.equals(doNews.getAuthorUID(), douser.getUid()))){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"权限不足\"}");
            return;
        }
        Integer result = dao.deleteNews(nid);
        response.setContentType("application/json");
        if (result == 1) {
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"删除成功\"}");
        } else {
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"删除失败\"}");
        }
    }
}