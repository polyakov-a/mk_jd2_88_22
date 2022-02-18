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
import java.util.List;

@WebServlet(name = "ChatServlet", urlPatterns = "/chats")
public class ChatServlet extends HttpServlet {

    private final IChatService<Message> chatService;

    public ChatServlet() {
        this.chatService = MessengerChatService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        List<Message> messages = chatService.getMessagesFromStorage(user.getLogin());
        req.setAttribute("messages", messages);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/chats.jsp");
        dispatcher.forward(req, resp);
    }
}
