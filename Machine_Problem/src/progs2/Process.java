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
public class Process {
   
    
   
    public Process(int process_id, int B_T, int A_T, int PRIORITY){
        this.process_id = process_id;
        this.B_T = this.O_B_T = B_T;
        this.A_T = this.O_A_T = A_T;
        this.PRIORITY = PRIORITY;
    }
    // holds the variable of first execution
    private int first_execution = -1;
    // process identifier
    private int process_id;
    // handles the burst time
    private int B_T;
    // handles the original burst time for further computation
    private int O_B_T;
    // handles the arrival time
    private int A_T;
    // handles the orriginal arrival time
    private int O_A_T;
    // handles the priority num
    private int PRIORITY;
    // handles the completion time
    private int C_T;
    /**
     * @return the process_id
     */
    public int getProcess_id() {
        return process_id;
    }

    /**
     * @param process_id the process_id to set
     */
    public void setProcess_id(int process_id) {
        this.process_id = process_id;
    }

    /**
     * @return the B_T
     */
    public int getB_T() {
        return B_T;
    }

    /**
     * @param B_T the B_T to set
     */
    public void setB_T(int B_T) {
        this.B_T = B_T;
    }

    /**
     * @return the O_B_T
     */
    public int getO_B_T() {
        return O_B_T;
    }

    /**
     * @param O_B_T the O_B_T to set
     */
    public void setO_B_T(int O_B_T) {
        this.O_B_T = O_B_T;
    }

    /**
     * @return the A_T
     */
    public int getA_T() {
        return A_T;
    }

    /**
     * @param A_T the A_T to set
     */
    public void setA_T(int A_T) {
        this.A_T = A_T;
    }

    /**
     * @return the PRIORITY
     */
    public int getPRIORITY() {
        return PRIORITY;
    }
    
    /**
     * @return the O_A_T
     */
    public int getO_A_T() {
        return O_A_T;
    }

    /**
     * @param O_A_T the O_A_T to set
     */
    public void setO_A_T(int O_A_T) {
        this.O_A_T = O_A_T;
    }

    /**
     * @return the first_execution
     */
    public int getFirst_execution() {
        return first_execution;
    }

    /**
     * @param first_execution the first_execution to set
     */
    public void setFirst_execution(int first_execution) {
        this.first_execution = first_execution;
    }

    /**
     * @return the C_T
     */
    public int getC_T() {
        return C_T;
    }

    /**
     * @param C_T the C_T to set
     */
    public void setC_T(int C_T) {
        this.C_T = C_T;
    }

    
    
    
}
