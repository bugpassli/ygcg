package NightAudit;
import java.util.Timer;
import java.util.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import NightAudit.TimeThreadNight.TimeThreadNight;

public class TimerNightAudit implements ServletContextListener {
	private Timer timer = null;
	public void contextInitialized(ServletContextEvent event){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY , 23);//控制时
		calendar.set(Calendar.MINUTE , 45);  //控制分
		calendar.set(Calendar.SECOND, 0 ); //控制秒
		Date time = calendar.getTime(); //得出任务的时间为12:00:00
		//如果第一天执行定时任务的时间，小于当前的时间，判断第一次执行加一条
		if (time.before(new Date())) {  
            time = this.addDay(time, 1);  
        } 
		timer = new Timer();
		event.getServletContext().log("夜审整合定时器已启动");
		timer.schedule(new TimeThreadNight(), time , 1000*60*60*24);
	}
	public void contextDestroyed(ServletContextEvent event) 
	{ 
		  //在这里关闭监听器，所以在这里销毁定时器。 
		  timer.cancel(); 
		  event.getServletContext().log("定时器销毁"); 

	 }
	 Date addDay(Date date , int num){
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH,num);
		return startDT.getTime();
	 }
}