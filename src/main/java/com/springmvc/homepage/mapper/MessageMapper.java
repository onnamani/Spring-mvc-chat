package com.springmvc.homepage.mapper;

import com.springmvc.homepage.POJO.ChatForm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM MESSAGES")
    List<ChatForm> getAllChatMessages();

    @Insert("INSERT INTO MESSAGES (username, messageText)" +
            "VALUES(#{username}, #{messageText})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    Integer insertMessage(ChatForm chatForm);
}
