package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.servlets;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.UserAuditService;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.UserService;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IUserAuditService;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IUserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "SignInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

    private final IUserService userService;
    private final IUserAuditService userAuditService;
    private final String SIGN_IN_URL_PAGE = "views/signIn.jsp";

    public SignInServlet() {
        this.userService = UserService.getInstance();
        this.userAuditService = UserAuditService.getInstance();
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

        if (!userService.ifUserExists(login)) {
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

        UserAudit signInUserAudit = UserAudit.Builder.build()
                .setDt_create(LocalDateTime.now())
                .setText("User signed in")
                .setUserLogin(user.getLogin())
                .setAuthorLogin(user.getLogin())
                .createUserAudit();

        userAuditService.create(signInUserAudit);

        session.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/mainMenu.jsp");
        dispatcher.forward(req, resp);
    }
}
