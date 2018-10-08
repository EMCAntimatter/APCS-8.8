/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarmv3;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.util.stream.IntStream;


/**
 * Owen Hilyard
 * 10/3
 * 8.06
 * 
 */

/* 
        Class Diagram

AlarmClockFactory
_____________________________

Main
+Scanner scnr
+int numAlarms
+AlarmClockV8[] alarms
+String[] timeStrings
+SimpleDateFormat df
+

_____________________________________

Methods
+ boolean areAlarmsDone

*/

public class AlarmClockFactory {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Please enter how many alarms you would like to set:\t");    
        int numAlarms = scnr.nextInt();
        AlarmClockV8[] alarms = new AlarmClockV8[numAlarms];
        
        String[] timeStrings = new String[numAlarms];
        scnr = new Scanner(System.in); //for some reason scanner stops working unless I replace it with a new scanner
                                       // this is the fix for that.
        
        //get times for the alarms
        for (int i = 0; i < numAlarms; i++) {
            System.out.println("In how long (hh:mm:ss) do you want the alarm to go off?");
            timeStrings[i] = scnr.nextLine();
        }
        
        //init alarms to array
        for (int i = 0; i < numAlarms; i++) {
            alarms[i] = new AlarmClockV8(timeStrings[i]);
        }
        
        alarms = sortByTime(alarms);
        
        
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
        System.out.printf("%n%n%-12s\t\t%-8s%n", "Alarm Number", "Time");
        for (int i = 0; i < alarms.length; i++) {
            System.out.printf("%-12d\t\t%-8s%n", i + 1, df.format((alarms[i].getCalendar()).getTime()));
        }
        System.out.println("\n\nYour earliest alarm was at " + df.format((alarms[0].getCalendar()).getTime()));
        System.out.println("Your latest alarm was at " + df.format((alarms[alarms.length-1].getCalendar()).getTime()));
        
        //this loop checks to make sure all alarms have done off before the program exits
        while (!areAlarmsDone(alarms)){
            for (AlarmClockV8 clock : alarms) {
                //If the time in the calendar is before now
                if (clock.getCalendar().getTime().before(new Date())) {
                    clock.alarm();
                }
            }
        }
    }
    
    public static Date getAverageTime(){
        
    }
    
    public static AlarmClockV8[] sortByTime(AlarmClockV8[] clocks){
        AlarmClockV8[] sortedClocks = new AlarmClockV8[clocks.length];
        ArrayList<AlarmClockV8> clockList = new ArrayList();
        clockList.addAll(Arrays.asList(clocks));
        for (int i = 0; i < sortedClocks.length; i++) {
            AlarmClockV8 nextEarliest = nextEarliest(clockList);
            sortedClocks[i] = nextEarliest;
            clockList.remove(nextEarliest);
        }
        return sortedClocks;
    }
    public static AlarmClockV8 nextEarliest(ArrayList<AlarmClockV8> clockList){
        AlarmClockV8 earliestAlarm = clockList.get(0);
        for (AlarmClockV8 clock : clockList) {
            if (clock.isBefore(earliestAlarm.getCalendar())) {
                earliestAlarm = clock;
            }
        }
        return earliestAlarm;
    }
    
    
    //Checks if all of the alarms done, returning true if true, false if false
    public static boolean areAlarmsDone(AlarmClockV8[] clocks){
        boolean allDone = true;
        for (AlarmClockV8 clock : clocks) {
            if (!clock.getHasGoneOff()) {
                allDone = false;
                break;
            }
        }
        return allDone;
    }
}
