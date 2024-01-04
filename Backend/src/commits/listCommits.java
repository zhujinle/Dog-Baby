package commits;

import java.io.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "listCommits", value = "/listCommits")
public class listCommits extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String tonewsid = request.getParameter("tonewsid");
        commitsDao dao = new commitsDao();
        String result = dao.listCommits(tonewsid);
        response.setContentType("text/json; charset=utf-8");
        response.getWriter().write("{\"statusCode\": 200, \"msg\": \"∂¡»°≥…π¶\","+"\"data\":"+result+"}");
    }
}