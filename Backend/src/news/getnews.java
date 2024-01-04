package news;

import java.io.*;
import java.util.Objects;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "getNews", value = "/getNews")
public class getnews extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String nid = request.getParameter("nid");
        newsDao dao = new newsDao();
        news gotNews = new news();
        gotNews = dao.getNews(nid);
        response.setContentType("application/json");
        if (gotNews != null && Objects.equals(gotNews.getType(), "5")) {
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"获取成功\", "+ gotNews + "}");
        } else {
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"获取失败\"}");
        }
    }
}