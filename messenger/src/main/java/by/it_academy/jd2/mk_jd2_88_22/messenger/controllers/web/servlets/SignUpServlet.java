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
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    private final IUserService userService;
    private final IUserAuditService userAuditService;
    private final String SIGN_UP_URL_PAGE = "views/signUp.jsp";

    public SignUpServlet() {
        this.userService = UserService.getInstance();
        this.userAuditService = UserAuditService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(SIGN_UP_URL_PAGE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/mainMenu.jsp");
            dispatcher.forward(req, resp);
        }

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String middleName = req.getParameter("middleName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String birthday = req.getParameter("birthday");


        if (firstName.isEmpty() || lastName.isEmpty() || login.isEmpty() || password.isEmpty() || birthday.isEmpty()) {
            req.setAttribute("wrongData", "Your entered incorrect data, try once again!");
            RequestDispatcher dispatcher = req.getRequestDispatcher(SIGN_UP_URL_PAGE);
            dispatcher.forward(req, resp);
        }

        User user = User.Builder.build()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setMiddleName(middleName)
                    .setLogin(login)
                    .setPassword(password)
                    .setBirthday(LocalDate.parse(birthday))
                    .setRegistration(LocalDateTime.now())
                    .createUser();

        if (userService.ifUserExists(login)) {
            req.setAttribute("wrongUser", "This user already exists, try once again!");
            RequestDispatcher dispatcher = req.getRequestDispatcher(SIGN_UP_URL_PAGE);
            dispatcher.forward(req, resp);
        } else {
            userService.signUp(user);

            UserAudit signUpUserAudit =  UserAudit.Builder.build()
                    .setDt_create(LocalDateTime.now())
                    .setText("User registered")
                    .setUserLogin(user.getLogin())
                    .setAuthorLogin(null)
                    .createUserAudit();

            userAuditService.create(signUpUserAudit);

            session.setAttribute("user", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/mainMenu.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
