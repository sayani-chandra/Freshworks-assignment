package jsonproject;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.TimerTask;

import org.json.simple.JSONObject;

public class ScheduleTime extends TimerTask{
	String file=null;
	public ScheduleTime(String file) {
		this.file=file;
	}
		// TODO Auto-generated constructor stub

	public void run() {
		Operations op = new Operations();
		try {
			JSONObject data = op.get(file);
			for(Object key : data.keySet()) {
				JSONObject k = (JSONObject)data.get(key);
				if(k.get("Time To Live") != null) {
					JSONObject ob = (JSONObject)k.get("Time To Live");
					Long sec = (Long) ob.get("Time in seconds");
					LocalDateTime st = LocalDateTime.parse((CharSequence) ob.get("Saving time"));
					LocalDateTime instant = LocalDateTime.now();
					Duration d = Duration.between(st, instant);
					if(d.getSeconds()>=sec) {
						if(data.get(key)!=null) {
							data.remove(key);
							op.save(file, data);
							System.out.println("Key Timeout: "+key);
						}
					}
				}
			}
		}
	 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
			

