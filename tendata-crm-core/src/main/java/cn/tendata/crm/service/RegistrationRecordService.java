package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.RegistrationRecord;
import cn.tendata.crm.data.domain.RegistrationRegulation;
import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.model.RegistrationRecordDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Luo Min on 2017/1/13.
 */
public interface RegistrationRecordService extends EntityService<RegistrationRecord,Long> {

    List<RegistrationRecordDto> getTodayRegistration(List<RegistrationRegulation> regulations,User user);

    Page<RegistrationRecord> getByUser(User user, Pageable pageable);
}
