package User;

import java.io.*;
import java.util.Objects;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "addUser", value = "/addUser")
public class addUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String membertype = request.getParameter("membertype");
        UserDao dao = new UserDao();
        User doUser = dao.fidsuser(token);
        if(!Objects.equals(doUser.getMembertype(), "1")){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"权限不足\"}");
            return;
        }
        Integer result = dao.addUser(username, password, membertype);
        response.setContentType("application/json");
        if (result == 1) {
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"添加成功\"}");
        } else {
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"添加失败\"}");
        }

    }
}