package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.servlets;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.UserAuditService;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IUserAuditService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private final IUserAuditService userAuditService;

    public LogoutServlet() {
        this.userAuditService = UserAuditService.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        UserAudit logOutUserAudit = UserAudit.Builder.build()
                .setDt_create(LocalDateTime.now())
                .setText("User logged out")
                .setUser(user)
                .setAuthor(user)
                .createUserAudit();

        userAuditService.create(logOutUserAudit);

        session.invalidate();

        resp.sendRedirect(req.getContextPath() + "/signIn");
    }
}
