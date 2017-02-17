package cn.tendata.crm.batch.config;


import cn.tendata.crm.batch.model.CreditBill;
import cn.tendata.crm.batch.processor.CreditBillProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Luo Min on 2016/12/28.
 */
@EnableBatchProcessing
@Configuration
public class JobConfig {

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

//    @Autowired
//    private DataSource dataSource;

//    @Bean
//    public JobRepository jobRepository() throws Exception {
//        return new MapJobRepositoryFactoryBean().getObject();
//    }

    @Bean
    public JobRepository jobRepository(DataSource dataSource,PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactory = new JobRepositoryFactoryBean();
        jobRepositoryFactory.setDataSource(dataSource);
        jobRepositoryFactory.setDatabaseType("MYSQL");
        jobRepositoryFactory.setTransactionManager(transactionManager);
        return jobRepositoryFactory.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource,PlatformTransactionManager transactionManager) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource,transactionManager));
        return jobLauncher;
    }

    @Bean
    @StepScope
    public ItemProcessor<CreditBill, CreditBill> creditBillProcessor() {
        return new CreditBillProcessor();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<CreditBill> csvItemReader() {
        FlatFileItemReader<CreditBill> csvItemReader = new FlatFileItemReader();
        csvItemReader.setResource(new ClassPathResource("credit-card-bill-201303.csv"));
        csvItemReader.setLineMapper(new DefaultLineMapper<CreditBill>() {{
            setLineTokenizer(lineTokenizer());
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CreditBill>() {{
                setTargetType(CreditBill.class);

            }});
        }});

        return csvItemReader;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<CreditBill> csvItemWriter() {
        FlatFileItemWriter<CreditBill> csvItemWriter = new FlatFileItemWriter<>();
        csvItemWriter.setResource(new ClassPathResource("outPut.csv"));
        csvItemWriter.setLineAggregator(new DelimitedLineAggregator<CreditBill>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<CreditBill>() {{
                setNames(new String[]{"accountID", "name", "amount", "date", "address"});
            }});
        }});

        return csvItemWriter;

    }

    @Bean
    public DelimitedLineTokenizer lineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames(new String[]{"accountId", "name", "amount", "date", "address"});
        return lineTokenizer;
    }


    @Bean
    public Job billJob() {
        return jobBuilders.get("billJob").start(billStep()).build();
    }

    @Bean
    public Step billStep() {
        return stepBuilders.get("billStep")
                .<CreditBill, CreditBill>chunk(100)
                .reader(csvItemReader())
                .processor(creditBillProcessor())
                .writer(csvItemWriter())
                .build();
    }
}
