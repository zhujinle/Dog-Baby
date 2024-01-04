package User;

import java.io.*;
import java.util.Objects;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "deleteUser", value = "/deleteUser")
public class deleteUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
        String uid = request.getParameter("uid");
        UserDao dao = new UserDao();
        User doUser = dao.fidsuser(token);
        if(!doUser.getMembertype().equals("1")){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"权限不足\"}");
            return;
        }
        if(Objects.equals(uid, doUser.getUid())){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"不能删除自己\"}");
            return;
        }
        doUser = dao.fidsuserbyuid(uid);
        if(doUser == null){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"用户不存在\"}");
            return;
        }
        Integer result = dao.deleteUser(doUser);
        response.setContentType("application/json");
        if (result == 1) {
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"删除成功\"}");
        } else {
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"删除失败\"}");
        }
    }
}