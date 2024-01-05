package news;

import java.io.*;
import java.util.Objects;
import User.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "pushToReview", value = "/pushToReview")
public class pushToReview extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String nid = request.getParameter("nid");
        String token = request.getParameter("token");
        newsDao dao = new newsDao();
        news doNews = dao.getNews(nid);
        if (Objects.equals(doNews.getType(), "2")){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"该新闻已经在审核中\"}");
            return;
        }
        if (Objects.equals(doNews.getType(), "4")){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"该新闻已经通过审核\"}");
            return;
        }
        if (Objects.equals(doNews.getType(), "5")){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"该新闻已经发布\"}");
            return;
        }
        UserDao userdao = new UserDao();
        User douser = userdao.fidsuser(token);
        if (!(Objects.equals(doNews.getAuthorUID(), douser.getUid())||Objects.equals(douser.getMembertype(), "1"))){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"权限不足\"}");
            return;
        }
        doNews.setType("2");
        Integer result = dao.modifyNews(doNews);
        response.setContentType("application/json");
        if (result == 1) {
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"提交成功\"}");
        } else {
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"提交失败\"}");
        }
    }
}