package Sheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution

public class SchedulerApp {

    public static void main(String[] args) {

        try {
            JobDetail job = JobBuilder.newJob(SchedulerJob.class).withIdentity("SchedulerJob").build();

            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("TriggerRelatorio")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 13-14 ? * MON,TUE,WED,THU,FRI *")).build();

            Scheduler agendador = new StdSchedulerFactory().getScheduler();
            agendador.start();
            agendador.scheduleJob(job, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
