package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.Department;
import cn.tendata.crm.data.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by minjay on 2017/2/24.
 */
@Service
public class DepartmentServiceImpl extends EntityServiceSupport<Department, Integer, DepartmentRepository> implements DepartmentService {

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository) {
        super(repository);
    }
}
