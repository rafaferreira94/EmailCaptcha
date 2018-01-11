package Sheduler;

import Saldo.App;
import Util.SendEmail;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.IOException;
import java.sql.SQLException;

public class SchedulerJob implements Job {

    @Override
    public void execute(JobExecutionContext jExeCtx){

        try {
            App.start(false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

