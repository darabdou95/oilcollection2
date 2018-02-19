package android.cs.aui.oilcollection.classes;

import java.util.Calendar;

/**
 * Created by Mao on 12/26/2017.
 */

public class collection {

    private int year;
    private String month;
    private String day;
    private String us;
    private String name;

    public collection(Calendar date, String us,String name) {
        this.year = date.get(Calendar.YEAR);
        this.month = date.get(Calendar.YEAR)+"-"+date.get(Calendar.MONTH);
        this.month = this.month + 1;
        this.day=date.get(Calendar.YEAR)+"-"+this.month+"-"+date.get(Calendar.DAY_OF_MONTH);
        this.name = name;
        this.us = us;
    }
    public collection(){
        this.year = 0;
        this.month = null;
        this.day=null;
        this.name = null;
        this.us = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUs() {
        return us;
    }

    public void setUs(String us) {
        this.us = us;
    }
}
