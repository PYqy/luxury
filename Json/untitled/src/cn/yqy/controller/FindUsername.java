package cn.yqy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.jws.WebService;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findUsername")
public class FindUsername extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String username = request.getParameter("username");
        Map userInfo = new HashMap();
        if ("tom".equals(username)) {
            userInfo.put("userExsit", true);
            userInfo.put("msg", "用户名已存在");

        } else {
            userInfo.put("userExsit", false);
            userInfo.put("msg", "成功");
        }
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), userInfo);
    }
}
