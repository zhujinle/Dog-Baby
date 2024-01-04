package User;

import java.io.*;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.druid.util.Base64;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        response.setContentType("application/json");
        Map<String, Object> data = new HashMap<>();
        if (user == null) {
            data.put("statusCode", 403);
            data.put("msg", "用户名或密码错误");
            data.put("token", "");
        } else {
            data.put("statusCode", 200);
            data.put("msg", "登陆成功");
//            SecureRandom random = new SecureRandom();
//            byte[] bytes = new byte[16];
//            random.nextBytes(bytes);
//            String token = Base64.byteArrayToBase64(bytes);
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            dao.setToken(user);
            System.out.println(dao.setToken(user));
            data.put("token", token);
        }
        Gson gson = new Gson();
        String jsonData = gson.toJson(data);
        response.getWriter().write(jsonData);
    }
}