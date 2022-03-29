package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.servlets;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.ChatService;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.UserService;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IChatService;
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

@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {

    private final IChatService chatService;
    private final IUserService userService;

    public MessageServlet() {
        this.chatService = ChatService.getInstance();
        this.userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/message.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        String recipient = req.getParameter("recipient");
        User user = (User) session.getAttribute("user");
        String message = req.getParameter("message");

        if (userService.ifUserExists(recipient)) {
            Message messageToSave = Message.Builder.build()
                    .setRecipient(this.userService.getUserByLogin(recipient))
                    .setSender(user)
                    .setMessage(message)
                    .setDate(LocalDateTime.now())
                    .createMessage();

            chatService.save(messageToSave);
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/sendMessage.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/errorMessage.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
