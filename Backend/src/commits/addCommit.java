package commits;

import java.io.*;
import news.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "addCommit", value = "/addCommit")
public class addCommit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String fromuser = request.getParameter("fromuser");
        String tonewid = request.getParameter("tonewid");
        String commit = request.getParameter("commit");
        String time = request.getParameter("time");
        newsDao dao = new newsDao();
        news doNews = dao.getNews(tonewid);
        if(doNews==null){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"找不到该新闻\"}");
            return;
        }
        commitsDao cdao = new commitsDao();
        commits doCommit = new commits();
        doCommit.setFromuser(fromuser);
        doCommit.setTonewsid(tonewid);
        doCommit.setCommit(commit);
        doCommit.setTime(time);
        Integer result = cdao.addCommit(doCommit);
        response.setContentType("application/json");
        if (result == 1) {
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"添加成功\"}");
        } else {
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"添加失败\"}");
        }
    }
}