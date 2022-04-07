package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.servlets;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IChatService;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final IChatService chatService;
    private final IUserService userService;

    public MessageController(IChatService chatService, IUserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        return "message";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@SessionAttribute(name = "user") User user,
                          @RequestParam(name = "recipient") String recipient,
                          @RequestParam(name = "message") String message) {


        if (userService.ifUserExists(recipient)) {
            Message messageToSave = Message.Builder.build()
                    .setRecipient(userService.getUserByLogin(recipient))
                    .setSender(user)

                    .setMessage(message)
                    .setDate(LocalDateTime.now())
                    .createMessage();

            chatService.save(messageToSave);

            return "sendMessage";
        } else {
            return "errorMessage";
        }
    }
}
