/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.svb.imc.web;

import java.security.Principal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.svb.imc.models.Incident;
import org.svb.imc.models.Message;
import org.svb.imc.service.IncidentService;
import org.svb.imc.service.MessageService;


@Controller
public class IncidentController {

	private static final Log logger = LogFactory.getLog(IncidentController.class);

	private final IncidentService incidentService;

	private final MessageService messageService;


	@Autowired
	public IncidentController(IncidentService incidentService, MessageService messageService) {
		this.incidentService = incidentService;
		this.messageService = messageService;
	}

	@SubscribeMapping("/incidents")
	public List<Incident> getIncidents() throws Exception {
		List<Incident> incidents = this.incidentService.getIncidents();
        logger.debug("Incidents: " + incidents);
		return incidents;
	}

	   @SubscribeMapping("/messages")
	    public List<Message> getMessages() throws Exception {
	        List<Message> messages = this.messageService.getAllMessages();
	        logger.debug("Incidents: " + messages);
	        return messages;
	    }
	   
	@MessageMapping("/message")
	public void receiveMessage(Message message, Principal principal) {
		message.setUser(principal.getName());
		logger.debug("Message from: " + message.getUser());
		this.messageService.addMessage(message);
	}

	   @MessageMapping("/incident")
	    public void receiveIncident(Incident incident, Principal principal) {
	        incident.setUser(principal.getName());
	        logger.debug("Incident from: " + incident.getUser());
	        this.incidentService.addIncident(incident);
	    }
	   
	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}

}
