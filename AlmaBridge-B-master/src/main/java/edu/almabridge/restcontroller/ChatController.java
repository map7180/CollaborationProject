package edu.almabridge.restcontroller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.almabridge.model.Message;
import edu.almabridge.model.OutputMessage;

@Controller
public class ChatController {
	 @MessageMapping("/chat")
	  @SendTo("/topic/message")
	  public OutputMessage sendMessage(Message message) {
		 System.out.println("in backend chat controller "+ message.getFid());
	    return new OutputMessage(message, new Date());
	  }
}
