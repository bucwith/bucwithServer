package com.bucwith.repository.chat;


import com.bucwith.domain.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer>, JpaSpecificationExecutor<ChatRoom> {
}

