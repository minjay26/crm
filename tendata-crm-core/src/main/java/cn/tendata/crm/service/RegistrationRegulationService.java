package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.RegistrationRegulation;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by Luo Min on 2017/1/13.
 */
public interface RegistrationRegulationService extends EntityService<RegistrationRegulation,Integer> {

    List<RegistrationRegulation> getAll(Sort sort);
}
