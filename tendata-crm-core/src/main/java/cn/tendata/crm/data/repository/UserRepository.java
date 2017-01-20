package cn.tendata.crm.data.repository;

import cn.tendata.crm.data.domain.User;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by minjay on 2016/11/13.
 */
public interface UserRepository extends CrudRepository<User,Integer>,QueryDslPredicateExecutor<User> {

    User findByUsername(String username);

    List<User> findAllByStringAuthorities(String stringAuthorities);
}
