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
            data.put("msg", "token错误");
            data.put("data", "");
            Gson gson = new Gson();
            String jsonData = gson.toJson(data);
            response.getWriter().write(jsonData);
            return;
        }
        else if (Objects.equals(user.getMembertype(), "1")) {
            response.getWriter().write("{\"statusCode\":200,\"msg\":\"\",\"data\":[{\"id\":100,\"authName\":\"新闻\",\"path\":null,\"children\":[{\"id\":101,\"authName\":\"全部新闻\",\"path\":\"view\",\"children\":[]},{\"id\":102,\"authName\":\"写新闻\",\"path\":\"write\",\"children\":[]},{\"id\":103,\"authName\":\"新闻管理\",\"path\":\"newsManage\",\"children\":[]},{\"id\":104,\"authName\":\"新闻推送审核与发表\",\"path\":\"push\",\"children\":[]}]},{\"id\":200,\"authName\":\"审核\",\"path\":null,\"children\":[{\"id\":201,\"authName\":\"已审核\",\"path\":\"done\",\"children\":[]},{\"id\":202,\"authName\":\"待审核\",\"path\":\"pending\",\"children\":[]}]},{\"id\":300,\"authName\":\"评论\",\"path\":null,\"children\":[{\"id\":301,\"authName\":\"评论列表\",\"path\":\"commentList\",\"children\":[]},{\"id\":302,\"authName\":\"评论管理\",\"path\":\"commentManage\",\"children\":[]}]},{\"id\":400,\"authName\":\"用户管理\",\"path\":null,\"children\":[{\"id\":401,\"authName\":\"用户列表\",\"path\":\"userList\",\"children\":[]},{\"id\":402,\"authName\":\"修改用户信息\",\"path\":\"modifyUser\",\"children\":[]},{\"id\":403,\"authName\":\"添加用户\",\"path\":\"addUser\",\"children\":[]}]}]}");
        } else if (Objects.equals(user.getMembertype(), "2")) {
            response.getWriter().write("{\"statusCode\":200,\"msg\":\"\",\"data\":[{\"id\":200,\"authName\":\"审核\",\"path\":null,\"children\":[{\"id\":201,\"authName\":\"已审核\",\"path\":\"done\",\"children\":[]},{\"id\":202,\"authName\":\"待审核\",\"path\":\"pending\",\"children\":[]}]},{\"id\":300,\"authName\":\"评论\",\"path\":null,\"children\":[{\"id\":301,\"authName\":\"评论列表\",\"path\":\"commentList\",\"children\":[]},{\"id\":302,\"authName\":\"评论管理\",\"path\":\"commentManage\",\"children\":[]}]}]}");
        } else if (Objects.equals(user.getMembertype(), "3")) {
            response.getWriter().write("{\"statusCode\":200,\"msg\":\"\",\"data\":[{\"id\":100,\"authName\":\"新闻\",\"path\":null,\"children\":[{\"id\":101,\"authName\":\"全部新闻\",\"path\":\"view\",\"children\":[]},{\"id\":102,\"authName\":\"写新闻\",\"path\":\"write\",\"children\":[]},{\"id\":104,\"authName\":\"新闻推送审核与发表\",\"path\":\"push\",\"children\":[]}]},{\"id\":300,\"authName\":\"评论\",\"path\":null,\"children\":[{\"id\":301,\"authName\":\"评论列表\",\"path\":\"commentList\",\"children\":[]}]}]}");
        } else {
            data.put("statusCode", 400);
            data.put("msg", "网络错误");
            data.put("data", "");
            Gson gson = new Gson();
            String jsonData = gson.toJson(data);
            response.getWriter().write(jsonData);
            return;
        }

    }
}
