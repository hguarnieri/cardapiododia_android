package com.hguarnieri.cardapiododia.utils;

public enum Schedule {
    LEAVES_CENTRAL_BUILDING(0), MAIN_GATE(1), ARIVES_CENTRAL_BUILDING(2),
    LEAVES_CENTRAL_BUILDING_HOLIDAYS(3), MAIN_GATE_HOLIDAYS(4), ARIVES_CENTRAL_BUILDING_HOLIDAYS(5);

    private final int value;

    Schedule(int s) {
        value = s;
    }

    public int toInt() {
        return this.value;
    }

    public boolean compare(int i){
        return value == i;
    }

    public static Schedule fromInteger(int x) {
        Schedule[] schedules = Schedule.values();

        for(int i = 0; i < schedules.length; i++) {
            if(schedules[i].compare(x))
                return schedules[i];
        }

        return Schedule.LEAVES_CENTRAL_BUILDING;
    }
}