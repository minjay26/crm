package cn.tendata.crm.data.repository;

import cn.tendata.crm.data.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by minjay on 2016/11/13.
 */
public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUsername(String username);
}
