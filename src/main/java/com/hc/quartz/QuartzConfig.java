package com.hc.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger myJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")) // Executes every 5 seconds
                .build();
    }

    @Bean
    public Trigger myJobTriggerUtc() {
        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myJobTriggerUtc")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 13 * * ?") // Executes at 1:00 PM UTC
                        .inTimeZone(TimeZone.getTimeZone("UTC")))
                .build();
    }

    @Bean
    public Trigger myJobTriggerPst() {
        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myJobTriggerPst")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 13 * * ?") // Executes at 1:00 PM PST
                        .inTimeZone(TimeZone.getTimeZone("America/Los_Angeles")))
                .build();
    }

    @Bean
    public Trigger myJobTriggerIst() {
        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myJobTriggerIst")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 13 * * ?") // Executes at 1:00 PM IST
                        .inTimeZone(TimeZone.getTimeZone("Asia/Kolkata")))
                .build();
    }
}
