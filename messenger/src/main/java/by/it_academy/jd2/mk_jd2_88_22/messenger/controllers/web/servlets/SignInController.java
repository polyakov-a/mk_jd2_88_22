package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.servlets;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("signIn")
public class SignInController {

    private final IUserService userService;

    public SignInController(IUserService userService) {
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "signIn";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String signIn(@RequestParam("login") String login,
                         @RequestParam("password") String password,
                         HttpSession session, Model model) {

        if (login.isEmpty() || password.isEmpty()) {
            model.addAttribute("wrongData", "You entered incorrect data, try once again!");
        } else if (!userService.ifUserExists(login)) {
            model.addAttribute("wrongData", "This user doesn't exist, try once again!");
        } else if (!userService.isPasswordCorrect(login, password)) {
            model.addAttribute("wrongData", "You entered incorrect data, try once again!");
        }
        if (model.getAttribute("wrongData") != null) {
            return "signIn";
        }

        User user = userService.getUserByLogin(login);

        session.setAttribute("user", user);
        return "mainMenu";
    }
}
