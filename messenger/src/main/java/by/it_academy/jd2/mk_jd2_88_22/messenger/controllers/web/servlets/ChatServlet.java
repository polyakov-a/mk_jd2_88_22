package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.servlets;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.ChatService;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IChatService;

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

    private final IChatService chatService;

    public ChatServlet() {
        this.chatService = ChatService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        List<Message> messages = chatService.getAll(user.getLogin());
        req.setAttribute("messages", messages);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/chats.jsp");
        dispatcher.forward(req, resp);
    }
}
