package com.example.Patas.controller.form;

import lombok.Data;

@Data
public class ChoiceForm {
    private int key;
    private String choice;

    public void setChoice(int i){
        if(i==1){
            this.choice = "未着手";
        }
        else if(i==2){
            this.choice = "実行中";
        }
        else if(i==3){
            this.choice = "ステイ中";
        }
        else if(i==4){
            this.choice = "完了";
        }
    }
}
