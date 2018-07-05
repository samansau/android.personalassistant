package android.dev.personalassistant.converters;

import android.arch.persistence.room.TypeConverter;
import android.dev.personalassistant.entities.expense.ExpenseTag;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ExpenseTypeConverter {

    @TypeConverter
    public static String convertExpenseTagToString(List<ExpenseTag> expenseTags){
        Gson gson=new Gson();
        String json=gson.toJson(expenseTags);
        Log.d(ExpenseTypeConverter.class.getName(),json);
        return json;
    }

    @TypeConverter
    public static List<ExpenseTag> convertStringToExpenseTag(String expenseTagAsString){
        Gson gson=new Gson();
        if(expenseTagAsString ==null)
            return Collections.EMPTY_LIST;

        Type listType=new TypeToken<List<ExpenseTag>>(){}.getType();
        List<ExpenseTag> list=gson.fromJson(expenseTagAsString,listType);
        Log.d(ExpenseTypeConverter.class.getName(),list.toString());
        return list;
    }
}
