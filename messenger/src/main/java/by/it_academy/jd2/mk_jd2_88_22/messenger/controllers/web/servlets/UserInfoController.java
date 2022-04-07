package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.servlets;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @RequestMapping(method = RequestMethod.GET)
    public String getUserInfo(@SessionAttribute(value = "user", required = false) User user, Model model) {

        model.addAttribute("user", user);

        return "userInfo";
    }
}
