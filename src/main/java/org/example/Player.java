package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Player {
    protected Integer[] stats = new Integer[7];

    protected List<Item> inventory=new ArrayList<>(10);
    protected int itemContor = 0;

    public Player() {
        for(int i=0; i<7; i++){
            stats[i]=0;
        }
    }

    public void pushItem(Item item){
        inventory.add(item);
        itemContor++;
    }

    public String getItems(){
        inventory.sort(Item::compareTo);
        String s = "";
        int contor = 1;
        for(Item o : inventory){
            s=s+contor+"."+o.name+"  ";
            contor++;
        }
        return s;
    }

    public void useItem(int itemIndex){
        int index=itemIndex-1;
        if(index!=-1) {
            int durability = inventory.get(index).useItem(this);
            if (durability == 0) {
                inventory.remove(index);
                itemContor--;
            }
            inventory.sort(Item::compareTo);
        }
    }

    public boolean applyStats(Integer[] newStats){
        for(int i=0; i<7; i++){
            stats[i]+=newStats[i];
        }
        if(stats[6]<0){
            stats[5]+=stats[6];
            stats[6]=0;
        }
        return stats[5] > 0;
    }

    public int getItemContor() {
        return itemContor;
    }

    public Integer[] getStats() {
        return stats;
    }

    public void getReport(String path){
        String[] statsNames = {"Strength", "Stamina", "Logic", "Mana", "Charisma", "Health", "Armour"};
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Game Report");
        for(int j=0; j<2; j++){
            Row row = sheet.createRow(j);
            for(int i=0; i<7; i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(String.valueOf(j==0? statsNames[i] : stats[i]));
            }
        }
        try
        {
            FileOutputStream out = new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
            System.out.println("Game Report written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
