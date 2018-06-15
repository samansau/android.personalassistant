package android.dev.personalassistant.helpers.kym;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;

import static android.dev.personalassistant.utils.Constants.DATABASE_NAME;

/**
 * Created by saurabh on 5/30/18.
 */

public class DatabaseHelper {

    public  static PersonalAssistantDatabase getDatabase(Context context){
        PersonalAssistantDatabase personalAssistantDatabase = Room.databaseBuilder(context,
                PersonalAssistantDatabase.class,DATABASE_NAME).build();
        return personalAssistantDatabase;

    }

}
