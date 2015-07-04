package ru.orbot90;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.orbot90.message.Message;

import java.util.Date;

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
    public String processMessages(ModelMap model, @RequestParam(value = "pagenum", defaultValue = "0")
        String pageNum, @RequestParam(value = "username", required = false)String userName,
                                  @RequestParam(value = "mess", required = false)String mess) {
        if(null != userName && null != mess) {
            Message m = new Message(userName, new Date(), mess);
        }
        return "guestbook";
    }

}
