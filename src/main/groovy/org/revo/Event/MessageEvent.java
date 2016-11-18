package org.revo.Event;

import org.revo.Domain.Message;
import org.revo.Service.ActiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;

import java.util.Map;

/**
 * Created by ashraf on 06/09/16.
 */
@Component
public class MessageEvent {
    private final String path = "/topic/message";
    @Autowired
    SimpMessageSendingOperations operations;
    @Autowired
    private Map<String, Boolean> onlineUsers;
    @Autowired
    private ActiveUserService activeUserService;

    @EventListener
    public void AfterSaveMessage(AfterSaveEvent<Message> event) {
        if (event.getSource() != null && event.getSource() instanceof Message)
            operations.convertAndSendToUser(event.getSource().getTo().getUsername(), path, event.getSource());
    }

    @EventListener
    public void sessionConnectEvent(AbstractSubProtocolEvent abstractSubProtocolEvent) {
        SimpMessageHeaderAccessor wrap = SimpMessageHeaderAccessor.wrap(abstractSubProtocolEvent.getMessage());
        activeUserService.mark(wrap.getUser().getName());
    }

    @EventListener
    public void SessionConnectEvent(SessionConnectEvent sessionConnectEvent) {
        SimpMessageHeaderAccessor wrap = SimpMessageHeaderAccessor.wrap(sessionConnectEvent.getMessage());
    }

    @EventListener
    public void SessionConnectedEvent(SessionConnectedEvent sessionConnectedEvent) {
        SimpMessageHeaderAccessor wrap = SimpMessageHeaderAccessor.wrap(sessionConnectedEvent.getMessage());
        onlineUsers.put(wrap.getUser().getName(), true);
    }

    @EventListener
    public void SessionDisconnectEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        SimpMessageHeaderAccessor wrap = SimpMessageHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        onlineUsers.put(wrap.getUser().getName(), false);
    }

    @EventListener
    public void SessionSubscribeEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        SimpMessageHeaderAccessor wrap = SimpMessageHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
    }

    @EventListener
    public void SessionUnsubscribeEvent(SessionUnsubscribeEvent sessionUnsubscribeEvent) {
        SimpMessageHeaderAccessor wrap = SimpMessageHeaderAccessor.wrap(sessionUnsubscribeEvent.getMessage());
    }
}
