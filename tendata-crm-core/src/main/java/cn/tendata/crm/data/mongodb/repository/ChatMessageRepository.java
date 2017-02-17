package cn.tendata.crm.data.mongodb.repository;

import cn.tendata.crm.data.mongodb.domain.ChatMessage;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by minjay on 2017/2/17.
 */
public interface ChatMessageRepository extends PagingAndSortingRepository<ChatMessage,String> {
}
