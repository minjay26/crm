package cn.tendata.crm.data.repository;

import cn.tendata.crm.data.domain.Department;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by minjay on 2017/2/24.
 */
public interface DepartmentRepository extends CrudRepository<Department,Integer> {
}
