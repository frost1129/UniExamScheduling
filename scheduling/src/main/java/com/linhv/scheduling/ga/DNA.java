package com.linhv.scheduling.ga;

import com.linhv.scheduling.model.CourseSchedule;
import com.linhv.scheduling.model.ScheduledExam;
import com.linhv.scheduling.model.TimeSlot;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class DNA {
    private HashMap<String, ScheduledExam> examSchedules;
    private int fitness;
    private Double prob;
    private Date startDate;
    private int totalDays;
    private int totalCourses;

    private Random random = new Random();

//    new random DNA
    public DNA(Date startD, int days, List<TimeSlot> slots, List<CourseSchedule> scheduleList ) {
        this.startDate = startD;
        this.totalDays = days;
        this.totalCourses = scheduleList.size();
        this.fitness = 1;
        this.examSchedules = new HashMap<String, ScheduledExam>();

        for (int i = 0; i < this.totalCourses; i++) {
            String scheduleId = scheduleList.get(i).getScheduleId();
            ScheduledExam exam = new ScheduledExam();

            exam.setCourseSchedule(scheduleList.get(i));

            exam.setTimeSlot(slots.get(random.nextInt(slots.size() - 1) + 1));

            Calendar c = Calendar.getInstance();
            c.setTime(this.startDate);
            c.add(Calendar.DATE, random.nextInt(days - 1) + 1);
            exam.setExamDate(c.getTime());

            this.examSchedules.put(scheduleId, exam);
        }
    }

//    cross-over DNA
    public DNA(DNA p1, DNA p2, List<CourseSchedule> scheduleList) {
        this.startDate = p1.startDate;
        this.totalDays = p1.getTotalDays();
        this.totalCourses = p1.getTotalCourses();
        this.fitness = 1;
        this.examSchedules = new HashMap<String, ScheduledExam>();

        int crossOverPoint = random.nextInt(this.totalCourses);

        for (int i = 0; i < crossOverPoint; i++) {
            String nextScheduleId = scheduleList.get(i).getScheduleId();
            ScheduledExam nextSchedule = new ScheduledExam(p1.examSchedules.get(nextScheduleId));
            this.examSchedules.put(nextScheduleId, nextSchedule);
        }

        for (int i = crossOverPoint; i < this.totalCourses; i++) {
            String nextScheduleId = scheduleList.get(i).getScheduleId();
            ScheduledExam nextSchedule = new ScheduledExam(p2.examSchedules.get(nextScheduleId));
            this.examSchedules.put(nextScheduleId, nextSchedule);
        }
    }

    public void mutate(Double mutationRate, List<TimeSlot> slots, List<CourseSchedule> scheduleList) {
        int mutateProb = (int) (mutationRate * 100);
        if (mutateProb == 0)
            return;

        for (int i = 0; i < this.totalCourses; i++) {
            if (random.nextInt(100) + 1 <= mutateProb) {
                String scheduleId = scheduleList.get(i).getScheduleId();

                Calendar c = Calendar.getInstance();
                c.setTime(this.startDate);
                c.add(Calendar.DATE, random.nextInt(this.totalDays - 1) + 1);
                Date randomDate = c.getTime();
                TimeSlot randomTimeSlot = slots.get(random.nextInt(slots.size()) + 1);

                this.examSchedules.get(scheduleId).setTimeSlot(randomTimeSlot);
                this.examSchedules.get(scheduleId).setExamDate(randomDate);
            }
        }
    }
}
