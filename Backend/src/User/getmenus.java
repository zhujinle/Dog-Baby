package User;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@WebServlet(name = "getmenus", value = "/getmenus")
public class getmenus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
        UserDao dao = new UserDao();
        User user = dao.fidsuser(token);
        response.setContentType("application/json");
        Map<String, Object> data = new HashMap<>();
        if(user == null) {
            data.put("statusCode", 403);
            data.put("msg", "token´íÎó");
            data.put("data", "");
        }
        else if (Objects.equals(user.getMembertype(), "1")) {
            data.put("statusCode", 200);
            data.put("msg", "1");
            data.put("data", "");
        } else if (Objects.equals(user.getMembertype(), "2")) {
            data.put("statusCode", 200);
            data.put("msg", "2");
            data.put("data", "");
        } else if (Objects.equals(user.getMembertype(), "3")) {
            data.put("statusCode", 200);
            data.put("msg", "3");
            data.put("data", "");
        } else {
            data.put("statusCode", 400);
            data.put("msg", "ÍøÂç´íÎó");
            data.put("data", "");
        }
        Gson gson = new Gson();
        String jsonData = gson.toJson(data);
        response.getWriter().write(jsonData);
    }
}
