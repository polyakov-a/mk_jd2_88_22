package by.it_academy.jd2.mk_jd2_88_22.controllers.web.servlets.messenger;

import by.it_academy.jd2.mk_jd2_88_22.service.MessengerUserService;
import by.it_academy.jd2.mk_jd2_88_22.service.api.IUserService;
import by.it_academy.jd2.mk_jd2_88_22.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SignInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

    private final IUserService<User> userService;
    private final String SIGN_IN_URL_PAGE = "views/signIn.jsp";

    public SignInServlet() {
        this.userService = MessengerUserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(SIGN_IN_URL_PAGE);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login.isEmpty() || password.isEmpty()) {
            req.setAttribute("wrongData", "You entered incorrect data, try once again!");
            RequestDispatcher dispatcher = req.getRequestDispatcher(SIGN_IN_URL_PAGE);
            dispatcher.forward(req, resp);
        }

        if (!userService.isUserExists(login)) {
            req.setAttribute("wrongUser", "This user doesn't exist, try once again!");
            RequestDispatcher dispatcher = req.getRequestDispatcher(SIGN_IN_URL_PAGE);
            dispatcher.forward(req, resp);
        }

        if (!userService.isPasswordCorrect(login, password)) {
            req.setAttribute("wrongPassword", "You entered wrong password, try once again!");
            RequestDispatcher dispatcher = req.getRequestDispatcher(SIGN_IN_URL_PAGE);
            dispatcher.forward(req, resp);

        }

        User user = userService.getUserByLogin(login);
        session.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/mainMenu.jsp");
        dispatcher.forward(req, resp);
    }
}
