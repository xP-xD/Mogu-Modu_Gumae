package com.bunsaned3thinking.mogu.chat.service.module;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bunsaned3thinking.mogu.chat.controller.dto.response.ChatMessageResponse;
import com.bunsaned3thinking.mogu.chat.controller.dto.response.ChatResponse;

public interface ChatService {
	ResponseEntity<ChatResponse> createChat(Long postId);

	ResponseEntity<ChatResponse> findChat(Long id);

	ResponseEntity<List<ChatResponse>> findChatByUser(String userId);

	ResponseEntity<Void> exitChatUser(String userId, Long chatId);

	void enterChatUser(String userId, Long chatId);

	boolean checkChatUser(Long chatId, String userId);

	ChatMessageResponse createChatMessage(Long chatId, String userId, String message, List<String> readUserIds);

	void readMessage(Long chatId, String userId);

	ResponseEntity<List<ChatMessageResponse>> findAllChatMessages(Long chatId, String userId);
}
