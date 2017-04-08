package ohtu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Submission {
    private String student_number;
    private int week;
    private boolean a1;
    private boolean a2;
    private boolean a3;
    private boolean a4;
    private boolean a5;
    private boolean a6;
    private boolean a7;
    private boolean a8;
    private boolean a9;
    private boolean a10;
    private boolean a11;
    private boolean a12;
    private boolean a13;
    private boolean a14;
    private boolean a15;
    

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }
    
    public int doneExercises() {
        int done = 0;
        List<Boolean> exercises = new ArrayList();
        exercises.addAll(Arrays.asList(a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15));
        for (Boolean bool : exercises) {
            if(bool) {
                done++;
            }
        }
        return done;
    }

    
    @Override
    public String toString() {
        return "opiskelijanumero: " + student_number + " viikko: " + week + " tehdyt tehtävät yhteensä: " + doneExercises();
    }
    
}