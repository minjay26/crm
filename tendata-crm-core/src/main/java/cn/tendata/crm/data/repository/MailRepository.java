package cn.tendata.crm.data.repository;

import cn.tendata.crm.data.domain.Mail;
import cn.tendata.crm.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Luo Min on 2016/12/6.
 */
public interface MailRepository extends PagingAndSortingRepository<Mail,Long>{

    Page<Mail> findAllByToUserAndReaded(User toUser, Boolean readed, Pageable pageable);
}
