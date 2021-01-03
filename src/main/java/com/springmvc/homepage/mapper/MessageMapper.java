package com.springmvc.homepage.mapper;

import com.springmvc.homepage.POJO.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM MESSAGES WHERE messageId = #{messageId}")
    ChatMessage getChatMessage(Integer messageId);

    @Insert("INSERT INTO MESSAGES (username, messageText)" +
            "VALUES( #{username}, #{messageText})")
    Integer insertMessage(ChatMessage chatMessage);
}
