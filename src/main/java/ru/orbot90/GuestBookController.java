package ru.orbot90;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.orbot90.datasource.GuestBookDAO;
import ru.orbot90.message.Message;

import java.util.Date;
import java.util.List;

/**
 * Created by orbot on 04.07.15.
 */
@Controller
@RequestMapping("/")
public class GuestBookController {
    @RequestMapping("/")
    public String showMain() {
        return "main";
    }

    @RequestMapping("/gb")
    public String processMessages(ModelMap model, @RequestParam(value = "page", defaultValue = "0")
        String pageNum, @RequestParam(value = "username", required = false)String userName,
                                  @RequestParam(value = "mess", required = false)String mess,
                                  @RequestParam(value = "new", defaultValue = "0")String newMess) {
        if(null != userName && null != mess) {
            Message m = new Message(userName, new Date(), mess);
            GuestBookDAO.getInstance().saveMessage(m);
        }

        List<Message> messages = GuestBookDAO.getInstance().getMessages(5 * Integer.parseInt(pageNum));
        long rows = GuestBookDAO.getInstance().getRowNum();
        long pages = rows / 5;
        if(rows%5 != 0) {
            pages++;
        }
        if(Integer.parseInt(newMess) == 1) {
            model.addAttribute("new", "");
        }
        model.addAttribute("messages", messages);
        model.addAttribute("pages", pages);
        model.addAttribute("pageNum", pageNum);
        return "guestbook";
    }

}
