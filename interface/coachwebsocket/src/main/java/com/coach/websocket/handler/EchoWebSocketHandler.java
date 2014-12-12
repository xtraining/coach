package com.coach.websocket.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Echo messages by implementing a Spring {@link WebSocketHandler} abstraction.
 */
public class EchoWebSocketHandler extends TextWebSocketHandler {

	public static Map<String, WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>();

	private final EchoService echoService;
	

	@Autowired
	public EchoWebSocketHandler(EchoService echoService) {
		this.echoService = echoService;
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		this.echoService.getMessage(session.getId());
		String token = message.getPayload();
		sessionMap.put(token, session);
	}
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		Set <String> tokenSet = sessionMap.keySet();
		for(String token : tokenSet){
			WebSocketSession s = sessionMap.get(token);
			if(StringUtils.equals(session.getId(), s.getId())){
				sessionMap.remove(token);
				break;
			}
		}
		super.afterConnectionClosed(session, status);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		super.afterConnectionEstablished(session);
	}

	public void pushToLogin(String userToken, String coachId, String coachSessionId){
		Set <String> tokenSet = sessionMap.keySet();
		for(String token : tokenSet){
			if(StringUtils.equals(userToken, token)){
				WebSocketSession s = sessionMap.get(token);
				try {
					s.sendMessage(new TextMessage(coachId + "," + coachSessionId));
					sessionMap.remove(token);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
