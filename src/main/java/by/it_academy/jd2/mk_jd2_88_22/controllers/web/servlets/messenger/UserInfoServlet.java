package by.it_academy.jd2.mk_jd2_88_22.controllers.web.servlets.messenger;

import by.it_academy.jd2.mk_jd2_88_22.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserInfoServlet", urlPatterns = "/userInfo")
public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/userInfo.jsp");
        dispatcher.forward(req, resp);
    }
}
