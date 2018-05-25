package com.nautilus.logging;

public enum PriorityLevel {
    LOW(1),
    MEDIUM(2),
    HIGH(3);






    private int value;
    private PriorityLevel(int num) {
        this.value = num;
    }
    public int getValue() { return value; }
}
