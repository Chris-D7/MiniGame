package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    protected List<Scenario> scenarios = new ArrayList<>();
    Player player = new Player();
    protected void initialize(){
        while(Scenario.getScenarioAddress()<255){
            scenarios.add(new Scenario());
        }
    }
    public Game(){
        initialize();
    }
    public void play(){
        String potionPrompt;
        Random rng= new Random();
        int scContor = 0;
        for(Scenario x : scenarios){
            Scanner sc = new Scanner(System.in);
            int options = x.getOptions();
            System.out.println(x.getPrompt());
            System.out.println("Options: ");
            for(int i=0; i<options; i++){
                System.out.println((i+1)+"."+x.getOptionText()[i]+" ");
            }
            int chosen = sc.nextInt();
            while(chosen>options || chosen<=0){
                chosen=sc.nextInt();
            }
            Integer[] stats = player.getStats();
            Integer[] conditions = x.getConditions()[chosen-1];
            boolean successful = true;
            for(int i=0; i<7; i++){
                if(conditions[i]>stats[i]){
                    successful = false;
                    break;
                }
            }
            Integer[] effects;
            if(successful){
                effects = x.getSuccessfulEffects()[chosen-1];
            }
            else{
                effects = x.getFailedEffects()[chosen-1];
            }
            boolean state = player.applyStats(effects);
            if(!x.getSuccessfulPrompts()[0].trim().equals("*")){
                if(successful){
                    System.out.println(x.getSuccessfulPrompts()[chosen - 1]);
                }
                else{
                    System.out.println(x.getFailedPrompts()[chosen - 1]);
                }
            }
            if(!state){
                System.out.println("");
                System.out.println("You cannot continue anymore. \nGAME OVER");
                player.getReport("Game Report.xlsx");
                return;
            }
            else {
                if ((rng.nextInt(100)%6==0) && (scContor >= 1)) {
                    int potion = rng.nextInt(3) + 1;
                    if (potion == 1) {
                        player.pushItem(new HealthPotion(rng.nextInt(5) + 1));
                        System.out.println("You found a Health Potion!");
                    }
                    if (potion == 2) {
                        player.pushItem(new ManaPotion(rng.nextInt(5) + 1));
                        System.out.println("You found a Mana Potion!");
                    }
                    if (potion == 3) {
                        player.pushItem(new StaminaPotion(rng.nextInt(5) + 1));
                        System.out.println("You found a Stamina Potion!");
                    }
                }
                String items = player.getItems();
                if (!items.equals("")) {
                    System.out.println(items);
                    System.out.print("Choose which potion to use (press 0 if you do not wish to use any potion): ");
                    int chosenPotion = sc.nextInt();
                    int itemsNr = player.getItemContor();
                    while (chosenPotion > itemsNr || chosenPotion < 0) {
                        chosenPotion = sc.nextInt();
                    }
                    player.useItem(chosenPotion);
                }
                scContor++;
            }
        }
    }
}
