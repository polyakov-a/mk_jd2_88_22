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
import java.time.LocalDate;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    private final IUserService<User> userService;
    private final String SIGN_UP_URL_PAGE = "views/signUp.jsp";

    public SignUpServlet() {
        this.userService = MessengerUserService.getInstance();
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


        if (userService.isUserExists(login)) {
            req.setAttribute("wrongUser", "This user already exists, try once again!");
            RequestDispatcher dispatcher = req.getRequestDispatcher(SIGN_UP_URL_PAGE);
            dispatcher.forward(req, resp);
        }

        User user;
        if (firstName.isEmpty() || lastName.isEmpty() || login.isEmpty() || password.isEmpty() || birthday.isEmpty()) {
            req.setAttribute("wrongData", "Your entered incorrect data, try once again!");
            RequestDispatcher dispatcher = req.getRequestDispatcher(SIGN_UP_URL_PAGE);
            dispatcher.forward(req, resp);
        }
        if (middleName.isEmpty()) {
            user = new User(firstName, lastName, login, password, LocalDate.parse(birthday));
        } else {
            user = new User(firstName, lastName, middleName, login, password, LocalDate.parse(birthday));
        }
        userService.createUser(user);
        session.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/mainMenu.jsp");
        dispatcher.forward(req, resp);
    }
}
