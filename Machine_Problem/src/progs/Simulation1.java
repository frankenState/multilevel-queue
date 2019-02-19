/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progs;

/**
 *
 * @author FL UBay
 */
import java.util.*;
public class Simulation1 {
    
    static int[][] matrix;
    
    static int T_Q_1 = 4, T_Q_2 = 3;
    
    static int TIME = 0;
    
    static int term = 0;
    
    public static void main(String[] args){
        
        Scanner s = new Scanner(System.in);
        
        System.out.print("Enter the desired process = ");
        int N = s.nextInt();
        
        matrix = new int[N][7];
        
        for (int i = 0; i < N; i++) {
            System.out.print("Burst Time of Process[" + (i + 1) + "] = ");
            matrix[i][0] = s.nextInt();
            System.out.print("Arrival Time of Process[" + (i + 1) + "] = ");
            matrix[i][1] = s.nextInt();
            System.out.print("Priority of Process[" + (i + 1) + "] = ");
            matrix[i][2] = s.nextInt();
        }
        
       // is there a process arrived in time 0 ? 
        while (N > 0){
            
            // find the arrived process in current time
            int process_row = 0;
            int priority = 0;
            
            
            
            for (int i = 0; i < N; i++){
                if (matrix[i][1] == TIME && isLowestPriority(i, N)){
                    process_row = i;
                    priority = matrix[i][2];
                    break;
                }
            }
            
            System.out.print(process_row + " ");
            
            // execute it
            // if the priority is equal to 1
            // increment using a loop and check everytime if there is a preemption
            // decrement the burst time, set the current time into an arrival time
            // of the current process_row
            int increment_len = 0;
            
            if (priority == 1){
                increment_len = T_Q_1;
            } else if (priority == 2){
                increment_len = T_Q_2;
            }
            
            for (int i = 1; i <= increment_len; i++) {
                
                if (matrix[process_row][0] == 0){
                    N = N - 1;
                    break;
                }
                
                matrix[process_row][0] = matrix[process_row][0] - 1;
                TIME = TIME + 1;
                matrix[process_row][1] = TIME;
                
                boolean willPreempt = false;
                // naa bay nag arrive na equal sa time na mas baba ug priority sa current process
                for (int j = 0; j < N; j++) {
                    if (process_row == j){
                        continue;
                    }
                    
                    if (matrix[j][1] == TIME && matrix[j][2] < priority){
                        willPreempt = true;
                    }
                    
                }
                
                if (willPreempt){
                    break;
                }
                
            }
           
        }
        System.out.println("");
        print("values");
    }
    
    public static boolean isLowestPriority(int i, int p_len){
    // inner loop for finding if the current "i" has the lowest priority    
        for (int j = i + 1; j < p_len; j++){
            // if there are many equal to current time and selects the
            // lesser priority, if true then it is not the lowest priority 
            // because there is other equal to time that has a lower priority, so 
            // it will return false
           if (TIME == matrix[i][1] && matrix[i][2] < matrix[j][2]){
               return false;
           }
        }
        // otherwise true, it is the lowest priority among all
        return true;

    }
    
    public static void print(String str){
        System.out.println(str);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            } System.out.println("");
        } System.out.println("");
    }
    
}
