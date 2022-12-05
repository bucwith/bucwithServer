package com.bucwith.service.chat;

import com.bucwith.domain.chat.ChatRoom;
import com.bucwith.repository.chat.ChatRoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private final ChatRoomRepository chatRoomRepository;
    private Map<Integer, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findRoomById(int roomId) {
        ChatRoom chatRoom = chatRooms.get(roomId);
        if(chatRoom == null) {
            chatRoom = chatRoomRepository.findById(roomId)
                    .orElseThrow(() -> new NullPointerException("NOT FOUND ROOM"));
            chatRooms.put(chatRoom.getRoomId(), chatRoom);
        }
        return chatRoom;
    }

    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .build();

        return chatRoomRepository.save(chatRoom);
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}