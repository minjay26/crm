package cn.tendata.crm.data.repository;

import cn.tendata.crm.data.domain.GoOutRecord;
import cn.tendata.crm.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Luo Min on 2017/1/16.
 */
public interface GoOutRecordRepository extends PagingAndSortingRepository<GoOutRecord, Long> {

    Page<GoOutRecord> findByMatterRecord_User(User user, Pageable pageable);
}
