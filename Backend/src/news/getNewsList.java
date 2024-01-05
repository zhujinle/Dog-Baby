package news;

import java.io.*;
import java.util.Objects;

import User.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "getNewsList", value = "/getNewsList")
public class getNewsList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String uid = request.getParameter("uid");
        String token = request.getParameter("token");
        if(token==null){
            newsDao dao = new newsDao();
            String result = dao.getNewsList(uid);
            response.setContentType("text/json; charset=utf-8");
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"读取成功\","+"\"data\":"+result+"}");
            return;
        }
        UserDao dao1 = new UserDao();
        User doUser = dao1.fidsuser(token);
        if(doUser.getMembertype().equals("1")){
            newsDao dao = new newsDao();
            String result = dao.getNewsList("0");
            response.setContentType("text/json; charset=utf-8");
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"读取成功\","+"\"data\":"+result+"}");
            return;
        }
        newsDao dao = new newsDao();
        String result = dao.getNewsList(doUser.getUid());
        response.setContentType("text/json; charset=utf-8");
        response.getWriter().write("{\"statusCode\": 200, \"msg\": \"读取成功\","+"\"data\":"+result+"}");
    }
}