/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progs2;

/**
 *
 * @author FL UBay
 */
import java.util.*;

public class Simulation1 {
    
    public static void main(String[] args){
        
        int TIME = 0;
        
        Scanner s = new Scanner(System.in);
        
        ArrayList<Process> priority_1 = new ArrayList<>();
        ArrayList<Process> priority_2 = new ArrayList<>();
        
    
        System.out.print("Enter the expected Process = ");
        int N = s.nextInt();
        
        for (int i = 0; i < N; i++) {
            System.out.println("\nPROCESS["+(i+1)+"]");
            System.out.print("Enter the burst time = ");
            int B_T = s.nextInt();
            System.out.print("Enter the arrival time = ");
            int A_T = s.nextInt();
            System.out.print("Enter the priority = ");
            int PRIORITY = s.nextInt();
            
            Process p = new Process((i + 1), B_T, A_T, PRIORITY);
            
            switch (PRIORITY) {
                case 1:
                    priority_1.add(p);
                    break;
                case 2:
                    priority_2.add(p);
                    break;
                default:
                    System.out.println("Invalid Priority");
                    System.exit(0);
            }
        }
        
        
        ArrayList<Process> completed = new ArrayList<>();
        
        while (!priority_1.isEmpty() || !priority_2.isEmpty()){
            // sorting based in the arrival time
            // of each priorities
            sort(priority_1);
            sort(priority_2);
            
            String temp = "";
            
            // in priority_1 , is there a process arrive?
            for (int i = 0; i < priority_1.size(); i++) {
                if (TIME >= priority_1.get(i).getA_T()){
                    // execute with 4 time quantum
                    for (int j = 0; j < 4 && priority_1.get(i).getB_T() != 0; j++) {
                        // determine the first execution
                        if (priority_1.get(i).getFirst_execution() == -1){
                            priority_1.get(i).setFirst_execution(TIME);
                        }
                        // increment the time
                        TIME = TIME + 1;
                        // decrement the burst time
                        priority_1.get(i).setB_T( priority_1.get(i).getB_T() - 1);
                        // set the arrival time into the current time
                        priority_1.get(i).setA_T( TIME );
                        
                    }
                    
                    temp = ("P" + priority_1.get(i).getProcess_id() + ", B_T = " + priority_1.get(i).getB_T() + ", A_T = " + priority_1.get(i).getA_T() + ", TIME = " + TIME);
                    
                    // check if there is a process
                    // that has zero burst time
                    if (priority_1.get(i).getB_T() == 0){
                        priority_1.get(i).setC_T( TIME );
                        completed.add(priority_1.remove(i));
                    }
                    
                } // end of if
            } // end of for loop
            
            System.out.println(temp);
            temp = "";
            // in priority_2 , is there a process arrive?
            for (int i = 0; i < priority_2.size(); i++) {
                if ( TIME >= priority_2.get(i).getA_T() && !arriveWithOnePriority( TIME, priority_1 )){
                    // execute it, 3 quantum time
                    // [condition explaination]
                    // if the increment is lesser than 3, or the increment is not completed
                    // and
                    // the burst time is not equal to zero, it means it is not complete
                    // and
                    // is there no process that arrived in the current time with one priority?
                    for (int j = 0; j < 3 && priority_2.get(i).getB_T() != 0 && !arriveWithOnePriority( TIME, priority_1 ); j++) {
                        
                        // determine the first execution
                        if (priority_2.get(i).getFirst_execution() == -1){
                            priority_2.get(i).setFirst_execution(TIME);
                        }
                        // increment the time
                        TIME = TIME + 1;
                        // decrement the burst time
                        priority_2.get(i).setB_T( priority_2.get(i).getB_T() - 1);
                        // set the arrival time into the current time
                        priority_2.get(i).setA_T( TIME );
                    }
                    
                    System.out.print("\nP" + priority_2.get(i).getProcess_id() + ", B_T = " + priority_2.get(i).getB_T() + ", A_T = " + priority_2.get(i).getA_T() + ", TIME = " + TIME);
                    
                    // check if there is a process
                    // that has zero burst time
                    if (priority_2.get(i).getB_T() == 0){
                        priority_2.get(i).setC_T( TIME );
                        completed.add(priority_2.remove(i));
                    }
                    
                } // end of if
            } // end of foor loop
            
            System.out.println(temp);
            
            
        } // end of while 
        
       
        
        System.out.println("\nCompleted Sequence");
        for (Process p : completed){
            System.out.print(p.getProcess_id() + " ");
        }
        
        
        // the table
        // [/] Process 
        // [/] Burst Time
        // [/] Arrival Time
        // [/] Priority
        // [/] Completion Time
        // [/] Turnaround Time = Completion Time - Arrival Time
        // [/] Waiting Time = Turnaround Time - Burst Time
        // [/] Response Time = First Execution - Arrival Time
        int total_tat = 0;
        int total_wt = 0;
        int total_rt = 0;
        
        System.out.println("\n");
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s", "PROCESS","BT", "AT", "PRIORITY", "CT", "TAT", "WT", "RT");
        System.out.println("");
        for (Process p : completed){
            System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s", 
                    "P" + p.getProcess_id(),
                    p.getO_B_T(),
                    p.getO_A_T(),
                    p.getPRIORITY(),
                    p.getC_T(),
                    (p.getC_T() - p.getO_A_T()),
                    ((p.getC_T() - p.getO_A_T()) - p.getO_B_T()),
                    (p.getFirst_execution() - p.getO_A_T()) 
            );
            
            total_tat += (p.getC_T() - p.getO_A_T());
            
            total_wt += ((p.getC_T() - p.getO_A_T()) - p.getO_B_T());
            
            total_rt += (p.getFirst_execution() - p.getO_A_T());
            
            System.out.println("");
        }
    
        System.out.printf("%-50s%-10.2f%-10.2f%-10.2f", "                     Average",((double)total_tat / N), ((double)total_wt / N), ((double)total_rt / N) );
        System.out.println("");
    
    
    }
    
    public static boolean arriveWithOnePriority(int TIME, ArrayList<Process> list){
        
        for (Process p : list){
            if (p.getA_T() <= TIME){
                return true;
            }
        }
        
        return false;
    }
    
    public static void sort(ArrayList<Process> list){
        
        for (int i = 1; i < list.size(); i++) {
            
            Process current = list.get(i);
            
            int j;
            
            for (j = i - 1; j >= 0 && ( current.getA_T() < list.get(j).getA_T() ); j--) {
                list.set(j + 1, list.get(j));
            }
            list.set(j + 1, current);
            
        }
        
    }
    
}
