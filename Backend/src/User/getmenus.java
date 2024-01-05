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
            data.put("msg", "token����");
            data.put("data", "");
            Gson gson = new Gson();
            String jsonData = gson.toJson(data);
            response.getWriter().write(jsonData);
            return;
        }
        else if (Objects.equals(user.getMembertype(), "1")) {
            response.getWriter().write("{\"statusCode\":200,\"msg\":\"\",\"data\":[{\"id\":100,\"authName\":\"����\",\"path\":null,\"children\":[{\"id\":101,\"authName\":\"ȫ������\",\"path\":\"view\",\"children\":[]},{\"id\":102,\"authName\":\"д����\",\"path\":\"write\",\"children\":[]},{\"id\":103,\"authName\":\"���Ź���\",\"path\":\"newsManage\",\"children\":[]},{\"id\":104,\"authName\":\"������������뷢��\",\"path\":\"push\",\"children\":[]}]},{\"id\":200,\"authName\":\"���\",\"path\":null,\"children\":[{\"id\":201,\"authName\":\"�����\",\"path\":\"done\",\"children\":[]},{\"id\":202,\"authName\":\"�����\",\"path\":\"pending\",\"children\":[]}]},{\"id\":300,\"authName\":\"����\",\"path\":null,\"children\":[{\"id\":301,\"authName\":\"�����б�\",\"path\":\"commentList\",\"children\":[]},{\"id\":302,\"authName\":\"���۹���\",\"path\":\"commentManage\",\"children\":[]}]},{\"id\":400,\"authName\":\"�û�����\",\"path\":null,\"children\":[{\"id\":401,\"authName\":\"�û��б�\",\"path\":\"userList\",\"children\":[]},{\"id\":402,\"authName\":\"�޸��û���Ϣ\",\"path\":\"modifyUser\",\"children\":[]},{\"id\":403,\"authName\":\"����û�\",\"path\":\"addUser\",\"children\":[]}]}]}");
        } else if (Objects.equals(user.getMembertype(), "2")) {
            response.getWriter().write("{\"statusCode\":200,\"msg\":\"\",\"data\":[{\"id\":200,\"authName\":\"���\",\"path\":null,\"children\":[{\"id\":201,\"authName\":\"�����\",\"path\":\"done\",\"children\":[]},{\"id\":202,\"authName\":\"�����\",\"path\":\"pending\",\"children\":[]}]},{\"id\":300,\"authName\":\"����\",\"path\":null,\"children\":[{\"id\":301,\"authName\":\"�����б�\",\"path\":\"commentList\",\"children\":[]},{\"id\":302,\"authName\":\"���۹���\",\"path\":\"commentManage\",\"children\":[]}]}]}");
        } else if (Objects.equals(user.getMembertype(), "3")) {
            response.getWriter().write("{\"statusCode\":200,\"msg\":\"\",\"data\":[{\"id\":100,\"authName\":\"����\",\"path\":null,\"children\":[{\"id\":101,\"authName\":\"ȫ������\",\"path\":\"view\",\"children\":[]},{\"id\":102,\"authName\":\"д����\",\"path\":\"write\",\"children\":[]},{\"id\":104,\"authName\":\"������������뷢��\",\"path\":\"push\",\"children\":[]}]},{\"id\":300,\"authName\":\"����\",\"path\":null,\"children\":[{\"id\":301,\"authName\":\"�����б�\",\"path\":\"commentList\",\"children\":[]}]}]}");
        } else {
            data.put("statusCode", 400);
            data.put("msg", "�������");
            data.put("data", "");
            Gson gson = new Gson();
            String jsonData = gson.toJson(data);
            response.getWriter().write(jsonData);
            return;
        }

    }
}
