package User;

import java.io.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "modifyUser", value = "/modifyUser")
public class modifyUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
        String uid = request.getParameter("uid");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String membertype = request.getParameter("membertype");
        UserDao dao = new UserDao();
        User doUser = dao.fidsuser(token);
        if(!doUser.getMembertype().equals("1")){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"权限不足\"}");
            return;
        }
        if(uid==null){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"参数错误\"}");
            return;
        }
        doUser = dao.fidsuserbyuid(uid);
        if(doUser==null){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"找不到该用户\"}");
            return;
        }
        if(membertype!=null){
            doUser.setMembertype(membertype);
        }
        if(username!=null){
            doUser.setUsername(username);
        }
        if(password!=null){
            doUser.setPassword(password);
        }
        Integer result = dao.modifyUser(doUser);
        response.setContentType("application/json");
        if (result == 1) {
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"修改成功\"}");
        } else {
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"修改失败\"}");
        }
    }
}