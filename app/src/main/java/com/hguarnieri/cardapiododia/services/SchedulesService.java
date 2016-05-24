package com.hguarnieri.cardapiododia.services;

import com.hguarnieri.cardapiododia.utils.Functions;

public class SchedulesService {
    private static String[][] stringSchedules = {{"05:35", "06:20", "07:30", "08:30", "09:30", "11:05", "11:50", "12:25","13:15", "13:35",
            "14:30", "15:30", "16:05", "17:05", "17:35", "18:05", "19:50", "20:45", "21:50", "22:50"},

            {"05:45", "06:35", "07:45", "08:45", "09:45", "11:20", "12:05", "12:40", "13:30", "13:50",
                    "14:45", "15:45", "16:20", "17:20", "17:50", "18:20", "20:05", "21:00", "22:05", "23:05"},

            {"06:00", "06:50", "08:00", "09:00", "10:00", "11:35", "12:20", "12:55", "13:45",
                    "14:05", "15:00", "16:00", "16:35", "17:35", "18:05", "18:35", "20:20", "21:15", "22:20", "23:20"},

            {"05:35", "06:25", "07:30", "11:05", "12:25", "13:35", "15:00", "16:00", "17:05",
                    "17:45"},

            {"05:50", "06:35", "07:45", "11:20", "12:40", "13:50", "15:15", "16:15", "17:20",
                    "18:00"},

            {"06:05", "06:50", "08:00", "11:35", "12:55", "14:05", "15:30", "16:30", "17:35",
                    "18:15"}};


    public static String getSchedule(int type, int position) {
        return stringSchedules[type][position];
    }

    public static int getLength(int type) {
        return stringSchedules[type].length;
    }

    public static String getNextTime(int type) {
        int now = Functions.getTimeInMinutes();

        for(String s : stringSchedules[type]) {
            int min = Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3, 5));

            if (now <= min) {
                return s;
            }
        }

        return getSchedule(type, getLength(type) - 1);
    }
}
