package cn.tendata.crm.webapp.config;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by minjay on 2017/2/17.
 */
@Configuration
@EnableMongoRepositories(basePackages = "cn.tendata.crm.data.mongodb.repository")
public class MongoDbConfig extends AbstractMongoConfiguration{

    @Override
    protected String getMappingBasePackage() {
        return "cn.tendata.crm.data.mongodb.domain";
    }

    @Override
    protected String getDatabaseName() {
        return "local";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new Mongo();
    }
}
