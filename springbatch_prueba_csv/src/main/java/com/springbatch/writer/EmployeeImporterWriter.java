package com.springbatch.writer;

import com.springbatch.listener.CustomSkipListener;
import com.springbatch.model.Employee;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import org.slf4j.Logger;

public class EmployeeImporterWriter implements ItemWriter<Employee> {
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeImporterWriter.class);



    @Override
    public void write(List<? extends Employee> items) throws Exception {
        for (Employee employee : items) {
            logger.info("{}", employee);
        }
    }

}
