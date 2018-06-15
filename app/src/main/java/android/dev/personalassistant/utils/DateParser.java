package android.dev.personalassistant.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by saurabh on 6/10/18.
 */

public class DateParser {

    SimpleDateFormat simpleDateFormat;
    private String dateFormat;

    public DateParser(String dateFormat){
        this.dateFormat=dateFormat;
        simpleDateFormat=new SimpleDateFormat(dateFormat);
    }

    public Date getDate(String date) throws ParseException{
        Date parsedDate=simpleDateFormat.parse(date);
        return parsedDate;
    }

    public String getString(Date date){
        return simpleDateFormat.format(date);
    }

}
