package cn.tendata.crm.data.repository;

import cn.tendata.crm.data.domain.Email;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Luo Min on 2016/12/6.
 */
public interface EmailRepository extends PagingAndSortingRepository<Email,Long> {
}
