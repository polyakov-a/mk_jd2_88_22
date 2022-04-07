package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.servlets;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IUserAuditService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    private final IUserAuditService userAuditService;

    public LogoutController(IUserAuditService userAuditService) {
        this.userAuditService = userAuditService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String logOut(@SessionAttribute(value = "user", required = false) User user,
                         HttpSession session) {

        if (user == null) {
            throw new SecurityException("Security exception");
        }
        UserAudit logOutUserAudit = UserAudit.Builder.build()
                .setDt_create(LocalDateTime.now())
                .setText("User logged out")
                .setUser(user)
                .setAuthor(user)
                .createUserAudit();

        userAuditService.create(logOutUserAudit);

        session.invalidate();

        return "signIn";
    }
}
