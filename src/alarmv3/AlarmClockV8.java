package alarmv3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * Owen Hilyard
 * 10/1/2018
 * An Alarm Clock
 */


//Written in notepad, pasted here for your convience

/* 
        Class Diagram

AlarmClockV8
+Calendar calendar
-boolean hasGoneOff = false;
_____________________________

Constructor
+AlarmClockV8()

Methods
+Calendar setAlarm()
+void alarm()
+boolean hasGoneOff()

*/




public class AlarmClockV8 {
    private boolean hasGoneOff = false;
    private Calendar calendar; 
    public AlarmClockV8(String time) {
        Scanner scnr = new Scanner(System.in);
        int[] tempDateInfo = new int[3];
        
        String[] tempDateInfoString = time.split(":");
        
                
        for (int i = 0; i < tempDateInfoString.length; i++) {
            tempDateInfo[i] = Integer.parseInt(tempDateInfoString[i]);
        }
        
        Calendar alarmAt = Calendar.getInstance();
        
        alarmAt.add(Calendar.HOUR, tempDateInfo[0]);
        alarmAt.add(Calendar.MINUTE, tempDateInfo[1]);
        alarmAt.add(Calendar.SECOND, tempDateInfo[2]);
        calendar = alarmAt;
    }   
    
    public boolean getHasGoneOff(){
        return hasGoneOff;
    }
    
    public void setHasGoneOff(boolean bool){
        hasGoneOff = bool;
    }
    
    public Calendar getCalendar(){
        return calendar;
    }
    
    public void setCalendar(Calendar cal){
        calendar = cal;
    }
    
    public boolean isAfter(Calendar cal){
        return this.calendar.after(cal);
    }
    
    public boolean isBefore(Calendar cal){
        return this.calendar.before(cal);
    }
    
    @Override
    public String toString(){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(calendar.getTimeInMillis() - new Date().getTime());
        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        
        //All useful data can be infered from this, it's a time until alarm
        return df.format(cal.getTime());
        
    }
    
    public void alarm(){
        if (!hasGoneOff) {
            hasGoneOff = true;
            System.out.println( "\n\n\n"
                            + "#     #    #    #    # #######    #     # ######  \n" +
                            "#  #  #   # #   #   #  #          #     # #     # \n" +
                            "#  #  #  #   #  #  #   #          #     # #     # \n" +
                            "#  #  # #     # ###    #####      #     # ######  \n" +
                            "#  #  # ####### #  #   #          #     # #       \n" +
                            "#  #  # #     # #   #  #          #     # #       \n" +
                            " ## ##  #     # #    # #######     #####  #       \n\n");   
        }
        
        
        
        //I debated having it play a sound but I don't know if you grade when people might be sleeping or if you have headphones turned up, so this is the alarm
    }
}
