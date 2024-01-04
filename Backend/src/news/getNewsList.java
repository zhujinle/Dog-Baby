package news;

import java.io.*;
import java.util.Objects;

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
        newsDao dao = new newsDao();
        String result = dao.getNewsList(uid);
        response.setContentType("text/json; charset=utf-8");
        response.getWriter().write("{\"statusCode\": 200, \"msg\": \"∂¡»°≥…π¶\","+"\"data\":"+result+"}");
    }
}