package com.dsr.javaschool.langtrainer;


import com.dsr.javaschool.langtrainer.component.DbInitializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableAutoConfiguration
@ComponentScan(value = "com.dsr.javaschool.langtrainer", excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DbInitializer.class)})
public class TestConfiguration {

}
