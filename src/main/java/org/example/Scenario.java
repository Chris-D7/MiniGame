package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scenario {
    protected static int scenarioAddress = 0;
    protected String prompt;
    protected Integer options;
    protected String[] optionText = new String[3];
    protected Integer[][] successfulEffects = new Integer[3][7];
    protected Integer[][] conditions = new Integer[3][7];
    protected Integer[][] failedEffects = new Integer[3][7];
    protected String[] successfulPrompts = new String[3];
    protected String[] failedPrompts = new String[3];
    protected void parse(){
        try {
            File file = new File("Scenarios.txt");
            Scanner sc = new Scanner(file);
            for(int i=0; i<scenarioAddress; i++){
                sc.nextLine();
            }
            {
                options= sc.nextInt(); sc.nextLine();
                //System.out.println(options);
                prompt=sc.nextLine();
                //System.out.println(prompt);
                for(int i=0; i<options; i++){
                    optionText[i]=sc.nextLine();
                    //System.out.println(optionText[i]);
                }
                for(int i=0; i<options; i++){
                    for(int j=0; j<7; j++) {
                        successfulEffects[i][j] = sc.nextInt();
                        //System.out.println(successfulEffects[i][j]);
                    }
                    sc.nextLine();
                }
                for(int i=0; i<options; i++){
                    for(int j=0; j<7; j++) {
                        conditions[i][j] = sc.nextInt();
                        //System.out.println(conditions[i][j]);
                    }
                    sc.nextLine();
                }
                for(int i=0; i<options; i++){
                    for(int j=0; j<7; j++) {
                        failedEffects[i][j] = sc.nextInt();
                        //System.out.println(failedEffects[i][j]);
                    }
                    sc.nextLine();
                }
                successfulPrompts[0]=sc.nextLine();
                //System.out.println(successfulPrompts[0]);
                if(successfulPrompts[0].trim().equals("*")){
                    if(options==3){scenarioAddress+=15;}
                    if(options==2){scenarioAddress+=11;}
                }
                else {
                    for (int i = 1; i < options; i++) {
                        successfulPrompts[i] = sc.nextLine();
                        //System.out.println(successfulPrompts[i]);
                    }
                    for (int i = 0; i < options; i++) {
                        failedPrompts[i] = sc.nextLine();
                        //System.out.println(failedPrompts[i]);
                    }
                    if(options==3){scenarioAddress+=21;}
                    if(options==2){scenarioAddress+=15;}
                }
            }
            sc.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Scenario() {
        parse();
    }

    public String getPrompt() {
        return prompt;
    }

    public int getOptions() {
        return options;
    }

    public String[] getOptionText() {
        return optionText;
    }

    public Integer[][] getSuccessfulEffects() {
        return successfulEffects;
    }

    public Integer[][] getConditions() {
        return conditions;
    }

    public static int getScenarioAddress() {
        return scenarioAddress;
    }

    public Integer[][] getFailedEffects() {
        return failedEffects;
    }

    public String[] getSuccessfulPrompts() {
        return successfulPrompts;
    }

    public String[] getFailedPrompts() {
        return failedPrompts;
    }
}
