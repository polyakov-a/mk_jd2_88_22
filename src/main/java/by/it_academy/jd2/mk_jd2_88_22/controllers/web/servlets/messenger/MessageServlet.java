package by.it_academy.jd2.mk_jd2_88_22.controllers.web.servlets.messenger;

import by.it_academy.jd2.mk_jd2_88_22.service.MessengerChatService;
import by.it_academy.jd2.mk_jd2_88_22.service.api.IChatService;
import by.it_academy.jd2.mk_jd2_88_22.dto.Message;
import by.it_academy.jd2.mk_jd2_88_22.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {

    private final IChatService<Message> chatService;

    public MessageServlet() {
        this.chatService = MessengerChatService.getInstance();
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
        String sender = ((User) session.getAttribute("user")).getLogin();
        String message = req.getParameter("message");
        Message messageToSave = new Message(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), sender, message);
        chatService.saveMessageToStorage(recipient, messageToSave);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/sendMessage.jsp");
        dispatcher.forward(req, resp);

    }
}
