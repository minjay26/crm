package cn.tendata.crm.data.repository;

import cn.tendata.crm.data.domain.RegistrationRecord;
import cn.tendata.crm.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Luo Min on 2017/1/13.
 */
public interface RegistrationRecordRepository extends PagingAndSortingRepository<RegistrationRecord, Long>,QueryDslPredicateExecutor<RegistrationRecord> {

    Page<RegistrationRecord> findByUser(User user, Pageable pageable);
}
