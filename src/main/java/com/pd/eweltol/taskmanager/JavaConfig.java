package com.pd.eweltol.taskmanager;

import com.pd.eweltol.taskmanager.configuration.MvcConfig;
import com.pd.eweltol.taskmanager.configuration.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({SecurityConfig.class, MvcConfig.class})
public class JavaConfig {





}
