package com.controller;

import com.config.ProjConfig;
import com.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.model.Employee;
import java.util.Scanner;
import java.time.LocalDate;
public class EmployeeController {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        var context=new AnnotationConfigApplicationContext(ProjConfig.class);
        EmployeeService employeeService=context.getBean(EmployeeService.class);
        while(true){
            System.out.println("1. Add Employee");
            System.out.println("2. Exit");
            int choice=sc.nextInt();
            if(choice==0)
                break;
            switch(choice){
                case 1:
                    Employee employee = new Employee();

                    System.out.println("Enter First Name:");
                    employee.setFirstName(sc.nextLine());

                    System.out.println("Enter Last Name:");
                    employee.setLastName(sc.nextLine());

                    System.out.println("Enter Gender:");
                    employee.setGender(sc.nextLine());

                    System.out.println("Enter Email:");
                    employee.setEmail(sc.nextLine());

                    System.out.println("Enter Password:");
                    employee.setPassword(sc.nextLine());

                    System.out.println("Enter Contact Number:");
                    employee.setContactNumber(sc.nextLine());

                    System.out.println("Enter Joined Date (yyyy-mm-dd):");
                    employee.setJoinedDate(LocalDate.parse(sc.nextLine()));

                    System.out.println("Enter Role:");
                    employee.setRole(sc.nextLine());

                    System.out.println("Enter Designation:");
                    employee.setDesignation(sc.nextLine());

                    System.out.println("Enter Status:");
                    employee.setStatus(sc.nextLine());

                    System.out.println("Enter Manager Id:");
                    employee.setManagerId(Integer.parseInt(sc.nextLine()));

                    System.out.println("Enter HR Id:");
                    employee.setHrId(Integer.parseInt(sc.nextLine()));
                    employeeService.insert(employee);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
