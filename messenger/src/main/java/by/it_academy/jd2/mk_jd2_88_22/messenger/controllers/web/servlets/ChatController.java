package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.servlets;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/chats")
public class ChatController {

    private final IChatService chatService;

    public ChatController(IChatService chatService) {
        this.chatService = chatService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(@SessionAttribute(name = "user") User user,
                        Model model) {

        if (user == null) {
            throw new SecurityException("Security exception");
        }
        List<Message> messages = chatService.getAllByRecipient(user.getLogin());
        model.addAttribute("messages", messages);

        return "chats";
    }
}
