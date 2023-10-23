package com.linhv.scheduling.ga;

import com.linhv.scheduling.model.*;
import com.linhv.scheduling.service.CourseScheduleService;
import com.linhv.scheduling.service.StudentJoinCourseService;
import com.linhv.scheduling.service.TimeSlotService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    @Autowired
    private TimeSlotService timeSlotService;
    @Autowired
    private CourseScheduleService scheduleService;
    @Autowired
    private StudentJoinCourseService joinService;

    @Getter
    private ArrayList<DNA> population;
    private int totalFitness;
    private List<TimeSlot> timeslots;
    private List<CourseSchedule> schedules;


    public void initAlgorithm(int yearCode, Date startD, int days, int populationSize) {
        this.schedules = this.scheduleService.getAllByYearCode(yearCode);
        this.timeslots = this.timeSlotService.getAll();
        this.population = new ArrayList<>(populationSize);

        for (int i = 0; i < populationSize; i++) {
            DNA newDNA = new DNA(startD, days, timeslots, schedules);
            this.population.add(newDNA);
        }

        this.evaluatePopulation();
    }

    public DNA getBestResult() {
        return this.population.get(0);
    }

    public DNA getWorstResult() {
        return this.population.get(population.size() - 1);
    }

    public void evaluatePopulation() {
        this.totalFitness = 0;
        for (DNA dna : this.population) {
            this.totalFitness += this.calcFitness(dna);
        }

        this.sortPopulationFromFitness();
    }

    public void sortPopulationFromFitness() {
        population.sort(DNAComparator.obj);
    }

    public int calcFitness(DNA dna) {
        if (dna.getFitness() != 1)
            return 1;

        int point = 1;

        for (int i = 0; i < this.schedules.size() - 1; i++) {
            ScheduledExam exam1 = dna.getExamSchedules().get(this.schedules.get(i).getScheduleId());

            for (int j = i + 1; j < this.schedules.size(); j++) {
                ScheduledExam exam2 = dna.getExamSchedules().get(this.schedules.get(j).getScheduleId());

//                Check sv có 2 môn thi cùng 1 ngày
                if (exam1.getExamDate().equals(exam2.getExamDate())) {
//                    Check sv nào thi 2 môn cùng lúc
                    if (exam1.getTimeSlot().equals(exam2.getTimeSlot())) {
                        for (User student1 : this.joinService.getAllUserByCourse(exam1.getCourseSchedule().getScheduleId())) {
                            if (this.joinService.getAllUserByCourse(exam2.getCourseSchedule().getScheduleId()).contains(student1))
                                point += 4;
                        }
                    }
//                    Check sv nào thi 2 môn liên tiếp
                    else if (exam1.getTimeSlot().isRightAfter(exam2.getTimeSlot()))
                        point += 1;
                }
            }
        }

        dna.setFitness(point);
        return point;
    }

    public void calcProb(int worstFitness) {
        for (int i = 0; i < this.population.size(); i++) {
            DNA dna = this.population.get(i);
            dna.setProb(1.0 - ((double) dna.getFitness())/worstFitness);
        }
    }

    public DNA randomParent() {
        int worstFitness = this.getWorstResult().getFitness();
        calcProb(worstFitness);

        int i = 0;
        Random random = new Random();
        double r = random.nextDouble();

        while (r > 0) {
            r = r - population.get(i).getProb();
            if (r > 0) i++;

            if (i == population.size())
                return population.get(0);
        }

        return population.get(i);
    }

    public void doCrossOver(double multateRate) {
        int initSize = population.size();
        int keepSize = initSize / 2;

        population.subList(keepSize, population.size()).clear();

        while (population.size() < initSize) {
            DNA p1 = randomParent();
            DNA p2 = randomParent();

            DNA child = new DNA(p1, p2, schedules);
            this.doMutation(child, multateRate);
            population.add(child);
        }

        this.evaluatePopulation();
    }

    public DNA doMutation(DNA dna, Double multationRate) {
        dna.mutate(multationRate, timeslots, schedules);
        return dna;
    }

}
