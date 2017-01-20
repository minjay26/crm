package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.GoOutRecord;
import cn.tendata.crm.data.repository.GoOutRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Luo Min on 2017/1/16.
 */
@Service
public class GoOutRecordServiceImpl extends EntityServiceSupport<GoOutRecord,Long,GoOutRecordRepository> implements GoOutRecordService{

    @Autowired
    public GoOutRecordServiceImpl(GoOutRecordRepository repository) {
        super(repository);
    }

    @Override
    public Page<GoOutRecord> getAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }
}
