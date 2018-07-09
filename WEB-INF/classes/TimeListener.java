import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;
import javax.servlet.ServletContextEvent;    
import javax.servlet.ServletContextListener;
public class TimeListener implements ServletContextListener {  
    private Timer timer = null;  
  
    public void contextInitialized(ServletContextEvent event) {  
        // 在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能  
        timer = new Timer(true);  
        System.out.println("启动成功");  
        // 添加日志，可在tomcat日志中查看到  
        event.getServletContext().log("定时器已启动--------------"); 
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,12);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		Date date=cal.getTime();
        // 调用exportHistoryBean，0表示任务无延迟，4*60*60*1000表示一天执行一次。  
        timer.schedule(new exportHistoryBean(event.getServletContext()),date,   
                24 * 60 * 60 * 1000);  
        event.getServletContext().log("已经添加任务-------------");  
    }  
  
    // 在这里关闭监听器，所以在这里销毁定时器。  
    public void contextDestroyed(ServletContextEvent event) {  
        timer.cancel();  
        event.getServletContext().log("定时器销毁---------------");  
    }  
  
}  