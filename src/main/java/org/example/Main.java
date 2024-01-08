package org.example;

import org.example.dto.TraineeDTO;
import org.example.dto.TrainerDTO;
import org.example.dto.TrainingDTO;
import org.example.entity.Trainee;
import org.example.entity.Training;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

@ComponentScan(basePackages = "org.example")
public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TraineeService traineeService = context.getBean(TraineeService.class);

        traineeService.create(new TraineeDTO("aaa","bbb","Tashkent",new Date()));
        traineeService.delete(8,"aaa.bbb1","Q5ApiguyOh");
    }
}