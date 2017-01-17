package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.RegistrationRegulation;
import cn.tendata.crm.data.repository.RegistrationRegulationRepository;
import cn.tendata.crm.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Luo Min on 2017/1/13.
 */
@Service
public class RegistrationRegulationServiceImpl extends EntityServiceSupport<RegistrationRegulation,Integer,RegistrationRegulationRepository> implements RegistrationRegulationService{

    @Autowired
    public RegistrationRegulationServiceImpl(RegistrationRegulationRepository repository) {
        super(repository);
    }

    @Override
    public List<RegistrationRegulation> getAll(Sort sort) {
        return CollectionUtils.toList(getRepository().findAll(sort));
    }
}
