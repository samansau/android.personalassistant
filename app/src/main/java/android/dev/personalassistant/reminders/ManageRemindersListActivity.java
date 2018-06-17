package android.dev.personalassistant.reminders;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.reminder.Reminder;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.helpers.reminder.ReminderHelper;
import android.dev.personalassistant.vo.kym.CardVO;
import android.dev.personalassistant.vo.reminder.ReminderVO;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static android.dev.personalassistant.utils.Keys.reminderFromDate;
import static android.dev.personalassistant.utils.Keys.reminderFromTime;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeFriday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeMonday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeSaturday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeSunday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeThursday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeTuesday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeWednesday;
import static android.dev.personalassistant.utils.Keys.reminderIdKey;
import static android.dev.personalassistant.utils.Keys.reminderName;
import static android.dev.personalassistant.utils.Keys.reminderInterval;
import static android.dev.personalassistant.utils.Keys.reminderRepeatHH;
import static android.dev.personalassistant.utils.Keys.reminderRepeatHHMMSS;
import static android.dev.personalassistant.utils.Keys.reminderRepeatMM;
import static android.dev.personalassistant.utils.Keys.reminderRepeatSS;
import static android.dev.personalassistant.utils.Keys.reminderToDate;
import static android.dev.personalassistant.utils.Keys.reminderToTime;
import static android.dev.personalassistant.utils.Keys.reminderToTimeFriday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeMonday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeSaturday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeSunday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeThursday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeTuesday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeWednesday;
import static java.security.AccessController.getContext;

public class ManageRemindersListActivity extends AppCompatActivity {

    static final ArrayList<Map<String,String>> reminderList =
            new ArrayList<Map<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_reminders_list);

        ListView reminderListView = (ListView) findViewById(R.id.list);
        ListAdapter reminderAdapter = new SimpleAdapter(
                getBaseContext(),
                reminderList,
                R.layout.three_line_list_item,
                new String[] {reminderName,reminderRepeatHHMMSS,reminderInterval},
                new int[] {R.id.text1,R.id.text2,R.id.text3}
        );
        populateReminderList(getBaseContext());
        reminderListView.setAdapter(reminderAdapter);

        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent=new Intent(getBaseContext(),ManageRemindersDetailsActivity.class);
                Bundle extras=new Bundle();

                ReminderHelper reminderHelper=new ReminderHelper();
                HashMap<String,String> reminderData=(HashMap)adapterView.getItemAtPosition(pos);
                final String reminderNameStr=reminderData.get(reminderName);
                PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(view.getContext());
                try {
                    ReminderVO reminderVO = reminderHelper.fetchReminderVOByName(personalAssistantDatabase,reminderNameStr);
                    extras.putInt(reminderIdKey, reminderVO.getReminderId());
                    extras.putString(reminderName, reminderVO.getReminderName());
                    extras.putString(reminderFromDate ,reminderVO.getFromDate());
                    extras.putString(reminderToDate ,reminderVO.getToDate());
                    extras.putString(reminderFromTime,reminderVO.getFromTime());
                    extras.putString(reminderToTime,reminderVO.getToTime());
                    extras.putString(reminderFromTimeSunday,reminderVO.getFromTimeSunday());
                    extras.putString(reminderToTimeSunday,reminderVO.getToTimeSunday());
                    extras.putString(reminderFromTimeMonday,reminderVO.getFromTimeMonday());
                    extras.putString(reminderToTimeMonday,reminderVO.getToTimeMonday());
                    extras.putString(reminderFromTimeTuesday,reminderVO.getFromTimeTuesday());
                    extras.putString(reminderToTimeTuesday,reminderVO.getToTimeTuesday());
                    extras.putString(reminderFromTimeWednesday,reminderVO.getFromTimeWednesday());
                    extras.putString(reminderToTimeWednesday,reminderVO.getToTimeWednesday());
                    extras.putString(reminderFromTimeThursday,reminderVO.getFromTimeThursday());
                    extras.putString(reminderToTimeThursday,reminderVO.getToTimeThursday());
                    extras.putString(reminderFromTimeFriday,reminderVO.getFromTimeFriday());
                    extras.putString(reminderToTimeFriday,reminderVO.getToTimeFriday());
                    extras.putString(reminderFromTimeSaturday,reminderVO.getFromTimeSaturday());
                    extras.putString(reminderToTimeSaturday,reminderVO.getToTimeSaturday());

                    extras.putInt(reminderRepeatHH,reminderVO.getRepeatEveryHH());
                    extras.putInt(reminderRepeatMM,reminderVO.getRepeatEveryMM());
                    extras.putInt(reminderRepeatSS,reminderVO.getRepeatEverySS());
                    extras.putInt(reminderInterval,reminderVO.getInterval());



                    //TODO

                }catch (InterruptedException ie){
                    Log.e("InterruptedException",ie.getStackTrace().toString());
                }
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }

    private void populateReminderList(Context context){
        reminderList.clear();
        try {
            ReminderHelper reminderHelper = new ReminderHelper();
            List<ReminderVO> reminderVOs=reminderHelper.fetchAllReminderVOs(DatabaseHelper.getDatabase(context));
            for(ReminderVO reminderVO : reminderVOs){
                Map<String, String> map = new HashMap();
                map.put(reminderName, reminderVO.getReminderName());
                map.put(reminderRepeatHHMMSS, "Repeat every(hh:mm:ss) : "+reminderVO.getRepeatEveryHH() +":" +reminderVO.getRepeatEveryMM()+":"+reminderVO.getRepeatEverySS());
                map.put(reminderInterval, "Buzz for period : "+reminderVO.getInterval()+" secs");
                reminderList.add(map);
            }

        }catch (InterruptedException ie){
            Log.e(ManageRemindersListActivity.class.getName() , ie.getStackTrace().toString());
        }
    }

    public void addManageReminders(View view){
        Log.d("Called","addManageReminders");
        Intent intent=new Intent(this,ManageRemindersDetailsActivity.class);
        startActivity(intent);

    }
}
