package news;

import java.io.*;
import java.util.Objects;

import User.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "getReviewedList", value = "/getReviewedList")
public class getReviewedList extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
        String uid = request.getParameter("uid");
        UserDao userdao = new UserDao();
        newsDao dao = new newsDao();
        if(userdao.fidsuser(token) == null || !(userdao.fidsuser(token).getMembertype().equals("1")||userdao.fidsuser(token).getMembertype().equals("2"))){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"权限不足\"}");
            return;
        }
        if(!Objects.equals(uid, "")){
            String result = dao.getNewsListByType2("4",uid);
            response.setContentType("text/json; charset=utf-8");
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"读取成功\","+"\"data\":"+result+"}");
            return;
        }
        String result = dao.getNewsListByType2("4",null);
        response.setContentType("text/json; charset=utf-8");
        response.getWriter().write("{\"statusCode\": 200, \"msg\": \"读取成功\","+"\"data\":"+result+"}");
    }
}