package ok.practice.timer;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class MyTimer implements InitializingBean{

	final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void setTimer() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				log.debug("time is on");
				

			}
		};
		timer.schedule(task, 0, 5000);
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		this.setTimer();
	}
}
