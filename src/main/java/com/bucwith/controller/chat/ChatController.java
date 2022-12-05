package com.bucwith.controller.chat;


import com.bucwith.common.CommController;
import com.bucwith.domain.bucket.Bucket;
import com.bucwith.domain.chat.ChatRoom;
import com.bucwith.dto.bucket.BucketReqDto;
import com.bucwith.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController extends CommController {

    private final ChatService chatService;
    /*
     * ChatRoom 등록
     * Request Data : BucketReqDto(userId, contents, type)
     * Response Data : 등록한 Bucket 반환
     */
    @PostMapping("/{roomName}")
    public ResponseEntity createRoom(@PathVariable String roomName) {
      ChatRoom chatRoom = chatService.createRoom(roomName);

        return SuccessReturn(chatRoom);
    }


    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}