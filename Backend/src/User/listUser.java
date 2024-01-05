package User;

import java.io.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "listUser", value = "/listUser")
public class listUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
        String uid = request.getParameter("uid");
        UserDao dao = new UserDao();
        User doUser = dao.fidsuser(token);
        if(doUser == null || !doUser.getMembertype().equals("1") ){
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"msg\": \"权限不足\"}");
            return;
        }
        if(uid!=null){
            doUser = dao.fidsuserbyuid(uid);
            if(doUser == null){
                response.setContentType("application/json");
                response.getWriter().write("{\"statusCode\": 403, \"msg\": \"用户不存在\"}");
                return;
            }
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 200, \"msg\": \"读取成功\","+"\"data\":["+doUser.toString()+"]}");
            return;
        }
        String result = dao.listUser();
        response.setContentType("application/json");
        response.getWriter().write("{\"statusCode\": 200, \"msg\": \"读取成功\","+"\"data\":"+result+"}");
    }
}