package android.dev.personalassistant.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.dev.personalassistant.main.MainActivity;
import android.support.v7.app.AlertDialog;

import java.util.HashMap;
import java.util.List;

/**
 * Created by saurabh on 5/6/18.
 */

public class Utils {
    public static void populateList(List list) {
        HashMap map = new HashMap();
        map.put("rank", "1");
        map.put("model", "Samsung Galaxy Nexus");
        list.add(map);

        map = new HashMap();
        map.put("rank", "2");
        map.put("model", "Samsung Epic Touch 4G");
        list.add(map);

        map = new HashMap();
        map.put("rank", "3");
        map.put("model", "Samsung Epic Touch 5G");
        list.add(map);
        map = new HashMap();
        map.put("rank", "4");
        map.put("model", "Samsung Epic Touch 6G");
        list.add(map);

    }

    public static void showDialog(String text,Activity activity){
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setCancelable(false);
        dialog.setTitle("Dialog on Android");
        dialog.setMessage(text);
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();

    }

}
