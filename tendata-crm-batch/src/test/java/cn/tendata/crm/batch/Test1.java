package cn.tendata.crm.batch;

import cn.tendata.crm.batch.config.JobConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.Date;

/**
 * Created by Luo Min on 2016/12/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JobConfig.class)
public class Test1 {

    @Autowired
    protected JobLauncher jobLauncher;

    @Autowired
    @Qualifier("billJob")
    protected Job job;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void finish() throws Exception {

    }

    @Test
    public void test1() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobExecution result = jobLauncher.run(job, new JobParametersBuilder().addString("pa", new Date().toString()).toJobParameters());
        System.out.println(result.toString());
    }
}
