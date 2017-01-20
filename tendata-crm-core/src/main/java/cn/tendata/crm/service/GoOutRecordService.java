package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.GoOutRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Luo Min on 2017/1/16.
 */
public interface GoOutRecordService extends EntityService<GoOutRecord,Long> {

    Page<GoOutRecord> getAll(Pageable pageable);
}
