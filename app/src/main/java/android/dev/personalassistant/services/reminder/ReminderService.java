package android.dev.personalassistant.services.reminder;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.helpers.reminder.ReminderHelper;
import android.dev.personalassistant.vo.reminder.ReminderVO;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderService extends Service {

    Timer mainTimer=new Timer();
    private final int MAIN_INERVAL=1*60*1000;
    private final Map<String,List<Integer>> reminderMap=new HashMap();
    private final Map<String,Timer> timerMap=new HashMap();
    public ReminderService() {
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onCreate(){

        super.onCreate();
    }

    public int onStartCommand(Intent intent,int flags,int startId){
        mainTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.d("Running.." ,"xyz");
                ReminderHelper reminderHelper = new ReminderHelper();
                try {
                    List<ReminderVO> reminderVOs = reminderHelper.fetchAllReminderVOs(DatabaseHelper.getDatabase(getBaseContext()));
                    Log.d("Reminder VOs>> " ,reminderVOs.toString());
                    for(ReminderVO reminderVO:reminderVOs){
                        int everyHH=reminderVO.getRepeatEveryHH();
                        int everyMM=reminderVO.getRepeatEveryMM();
                        int everySS=reminderVO.getRepeatEverySS();
                        int intervalInMillies=reminderVO.getInterval()*1000;
                        int periodInMillies=(everyHH*60*60+everyMM*60+everySS)*1000;

                        String reminderName=reminderVO.getReminderName();
                        List<Integer> reminderInterval=reminderMap.get(reminderName);
                        if(reminderInterval==null){
                            List<Integer> reminderIntervalList=new ArrayList<>();
                            reminderIntervalList.add(periodInMillies);
                            reminderIntervalList.add(intervalInMillies);
                            reminderMap.put(reminderName,reminderIntervalList);
                            scheduleTask(reminderName,periodInMillies,intervalInMillies,0);
                        }else{
                            if(!reminderInterval.get(0).equals(periodInMillies) || !reminderInterval.get(1).equals(intervalInMillies)){
                                reminderInterval.clear();
                                reminderInterval.add(periodInMillies);
                                reminderInterval.add(intervalInMillies);
                                reminderMap.put(reminderName,reminderInterval);
                                scheduleTask(reminderName,periodInMillies,intervalInMillies,periodInMillies);
                            }
                        }
                    }
                }catch (InterruptedException ie){
                    Log.e(this.getClass().getName(),ie.getMessage());
                }
            }},0,MAIN_INERVAL);


        return super.onStartCommand(intent,flags,startId);
    }

    public void onDestroy() {
        mainTimer.cancel();
        Collection<Timer> timers=timerMap.values();
        for(Timer timer:timers){
            timer.cancel();
        }
    }


    private void scheduleTask(String reminderName,int reminderInterval,int interval,int delay){
        Timer timer=timerMap.get(reminderName);
        if(timer!=null) {
            timer.cancel();
            timer.purge();
        }
        timer=new Timer();
        timerMap.put(reminderName,timer);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.d("Timer running.. every",reminderInterval+" ms"+" for a period of : " +interval);
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(interval);

            }
        },delay,reminderInterval);


    }


}
