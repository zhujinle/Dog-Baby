package commits;

import java.io.*;

import User.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "deleteCommit", value = "/deleteCommit")
public class deleteCommit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
        String cid = request.getParameter("commitid");
        UserDao dao = new UserDao();
        User doUser = dao.fidsuser(token);
        if(doUser == null || !doUser.getMembertype().equals("1")){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"权限不足\"}");
            return;
        }
        commitsDao cdao = new commitsDao();
        Integer result = cdao.deleteCommit(cid);
        response.setContentType("application/json");
        if (result == 1) {
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"删除成功\"}");
        } else {
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"删除失败\"}");
        }
    }
}