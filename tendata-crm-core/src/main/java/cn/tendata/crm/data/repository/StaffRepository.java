package cn.tendata.crm.data.repository;

import cn.tendata.crm.data.domain.Staff;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by minjay on 2017/2/24.
 */
public interface StaffRepository extends PagingAndSortingRepository<Staff,Integer> {
}
