package news;

import java.io.*;
import java.util.Objects;

import User.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "reviewNews", value = "/reviewNews")
public class reviewNews extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String nid = request.getParameter("nid");
        String token = request.getParameter("token");
        String status = request.getParameter("status");
        newsDao newsdao = new newsDao();
        UserDao userdao = new UserDao();
        User reviewUser = userdao.fidsuser(token);
        news reviewNews = newsdao.getNews(nid);
        String reviewer = reviewUser.getUsername();
        response.setContentType("application/json");
        if (!(Objects.equals(status, "0") || Objects.equals(status, "1"))|| reviewNews == null || reviewUser == null) {
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"±£¥Ê…Û∫À ß∞‹\"}");
            return;
        }
        if(Objects.equals(status, "0"))
            reviewNews.setType("4");
        if(Objects.equals(status, "1"))
            reviewNews.setType("3");
        Integer result = newsdao.reviewNews(nid, reviewer , reviewNews.getType());
        if (result == 1) {
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"±£¥Ê…Û∫À≥…π¶\"}");
        } else {
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"±£¥Ê…Û∫À ß∞‹\"}");
        }
    }
}