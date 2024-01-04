package news;

import java.io.*;
import java.util.Objects;

import User.User;
import User.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "publicNews", value = "/publicNews")
public class publicNews extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            request.setCharacterEncoding("utf-8");
            String nid = request.getParameter("nid");
            String token = request.getParameter("token");
            UserDao userdao = new UserDao();
            User douser = userdao.fidsuser(token);
            newsDao dao = new newsDao();
            news doNews = dao.getNews(nid);
            if(!(Objects.equals(douser.getMembertype(), "1")|| Objects.equals(doNews.getAuthor(), douser.getUsername())||Objects.equals(douser.getMembertype(), "2"))){
                response.setContentType("application/json");
                response.getWriter().write("{\"statusCode\": 403, \"msg\": \"权限不足\"}");
                return;
            }
            if (!Objects.equals(doNews.getType(), "4")){
                response.setContentType("application/json");
                response.getWriter().write("{\"statusCode\": 403, \"msg\": \"该新闻还未通过审核\"}");
                return;
            }
            doNews.setType("5");
            Integer result = dao.modifyNews(doNews);
            response.setContentType("application/json");
            if (result == 1) {
                response.getWriter().write("{\"statusCode\": 200, \"msg\": \"发布成功\"}");
            } else {
                response.getWriter().write("{\"statusCode\": 403, \"msg\": \"发布失败\"}");
            }
        } catch (IOException e) {
            response.getWriter().write("{\"statusCode\": 400, \"msg\": \"请求参数有误\"}");
        }
    }
}