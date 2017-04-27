package edu.almabridge.restcontroller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import edu.almabridge.model.Message;
import edu.almabridge.model.OutputMessage;

@Controller
public class ChatController {

	private static Logger log = LoggerFactory.getLogger(ChatController.class);

	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message) {

		log.debug("Sening Message " + message);
		System.out.println("in backend chat controller " + message.getFid());
		log.debug("Message Sent Sucessfully");
		return new OutputMessage(message, new Date());
	}
}
