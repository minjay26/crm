package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.RegistrationRecord;
import cn.tendata.crm.data.domain.RegistrationRegulation;
import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.data.repository.RegistrationRecordPredicates;
import cn.tendata.crm.data.repository.RegistrationRecordRepository;
import cn.tendata.crm.model.RegistrationRecordDto;
import cn.tendata.crm.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Luo Min on 2017/1/13.
 */
@Service
public class RegistrationRecordServiceImpl extends EntityServiceSupport<RegistrationRecord, Long, RegistrationRecordRepository> implements RegistrationRecordService {

    @Autowired
    public RegistrationRecordServiceImpl(RegistrationRecordRepository repository) {
        super(repository);
    }

    public List<RegistrationRecordDto> getTodayRegistration(List<RegistrationRegulation> regulations,User user) {
        List<RegistrationRecordDto> dtos = new ArrayList<>();
        for (RegistrationRegulation rg : regulations) {
            RegistrationRecordDto dto = new RegistrationRecordDto();
            RegistrationRecord record = getRepository().findOne(RegistrationRecordPredicates.checkRegistred(rg,user));
            dto.setRegistered(record == null?false:true);
            dto.setRegistrationDate(record == null?null:record.getRegistrationDate());
            dto.setRegulation(rg);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public Page<RegistrationRecord> getByUser(User user, Pageable pageable) {
        return getRepository().findByUser(user,pageable);
    }
}
