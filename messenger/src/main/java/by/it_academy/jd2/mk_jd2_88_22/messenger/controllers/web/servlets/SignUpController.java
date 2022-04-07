package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.servlets;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final IUserService userService;

    public SignUpController(IUserService userService) {
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "signUp";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String signUp(@SessionAttribute(value = "user", required = false) User user,
                         @RequestAttribute("firstName") String firstName,
                         @RequestAttribute("lastName") String lastName,
                         @RequestAttribute("middleName") String middleName,
                         @RequestAttribute("login") String login,
                         @RequestAttribute("password") String password,
                         @RequestAttribute("birthday") String birthday,
                         Model model, HttpSession session) {

        if (user != null) {
            return "mainMenu";
        }

        if (firstName.isEmpty() || lastName.isEmpty() || login.isEmpty() || password.isEmpty() || birthday.isEmpty()) {
            return "signUp";
        }

        User user1 = User.Builder.build()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setMiddleName(middleName)
                    .setLogin(login)
                    .setPassword(password)
                    .setBirthday(LocalDate.parse(birthday))
                    .setRegistration(LocalDateTime.now())
                    .createUser();

        if (userService.ifUserExists(login)) {
            model.addAttribute("wrongUser", "This user already exists, try once again");
            return "signUp";
        } else {
            userService.signUp(user1);

            session.setAttribute("user", user1);
            return "mainMenu";
        }
    }
}
