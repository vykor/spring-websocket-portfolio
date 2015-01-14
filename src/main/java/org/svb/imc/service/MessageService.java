package org.svb.imc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.svb.imc.models.Message;


@Service
public class MessageService implements ApplicationListener<BrokerAvailabilityEvent> {

	private static Log logger = LogFactory.getLog(MessageService.class);

	private final MessageSendingOperations<String> messagingTemplate;

	private AtomicBoolean brokerAvailable = new AtomicBoolean();

    private final List<Message> messages = new ArrayList<Message>();

	@Autowired
	public MessageService(MessageSendingOperations<String> messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@Override
	public void onApplicationEvent(BrokerAvailabilityEvent event) {
		this.brokerAvailable.set(event.isBrokerAvailable());
	}

	//@Scheduled(fixedDelay=2000)
	public void sendMessage(Message message) {
			if (logger.isTraceEnabled()) {
				logger.trace("Sending message " + message);
			}
			if (this.brokerAvailable.get()) {
				this.messagingTemplate.convertAndSend("/topic/" + message.getIncident(), message);
			}
	}

    public void recieveMessage(Message message) {
        messages.add(message);
    }
    
    public void addMessage(Message message) {
        messages.add(message);
    }
    
    public List<Message> getMessages(UUID topic) {
        return messages;
    }
    
    public List<Message> getAllMessages() {
        return messages;
    }
}
