package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.Staff;
import cn.tendata.crm.data.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by minjay on 2017/2/24.
 */
@Service
public class StaffServiceImpl extends EntityServiceSupport<Staff, Integer, StaffRepository> implements StaffService {

    @Autowired
    public StaffServiceImpl(StaffRepository repository) {
        super(repository);
    }
}
