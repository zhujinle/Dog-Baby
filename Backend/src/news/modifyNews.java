package news;

import java.io.*;

import User.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "modifyNews", value = "/modifyNews")
public class modifyNews extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String token = request.getParameter("token");
        String date = request.getParameter("date");
        String img = request.getParameter("img");
        String nid = request.getParameter("nid");
        String summary = request.getParameter("summary");
        newsDao dao = new newsDao();
        UserDao userdao = new UserDao();
        User authorUser = userdao.fidsuser(token);
        String author = authorUser.getUsername();
        String authoruid = authorUser.getUid();
        news inputNews = dao.getNews(nid);
        inputNews.setTitle(title);
        inputNews.setContent(content);
        inputNews.setUpdateDate(date);
        inputNews.setAuthor(author);
        inputNews.setAuthorUID(authoruid);
        inputNews.setImg(img);
        inputNews.setSummary(summary);
        Integer result = dao.modifyNews(inputNews);
        response.setContentType("application/json");
        if (result == 1) {
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"�޸ĳɹ�\"}");
        } else {
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"�޸�ʧ��\"}");
        }
    }
}