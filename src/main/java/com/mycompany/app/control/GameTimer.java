package com.mycompany.app.control;

public class GameTimer {
    private long STtime;
    String minText = "";
    String secText = "";
    int mins;
    int secs = 0;
    boolean ended = false;

    public GameTimer() {
        STtime = System.currentTimeMillis();
    }

    public void updateTime() {
        if (!ended) {
            //Setting up timer
            long elapsedTime = System.currentTimeMillis() - STtime;
            long elapsedSeconds = elapsedTime / 1000;
            long secondsDisplay = elapsedSeconds % 60;
            long elapsedMinutes = elapsedSeconds / 60;

            minText = "Time: " + elapsedMinutes + ": " + secondsDisplay ;
            secText = "Time: " + secondsDisplay ;
            mins = (int) elapsedMinutes;
            secs = (int) secondsDisplay;  
        }

    }

    public void setEnd() {
        ended = true;
    }

    public int getSecs() {
        return secs;
    }

    public int getMins() {
        return mins;
    }

    public String getMinText() {
        return minText;
    }

    public String getSecText() {
        return secText;
    }
    
}
