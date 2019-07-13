package com.dataHandle.view;

import com.dataHandle.bean.ElectricProject;
import com.dataHandle.controller.ExcelHandle;
import com.dataHandle.controller.FXMLMainController;
import com.dataHandle.service.EPservice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainUI extends Application {
    private static final String MAIN_UI_RES = "MainUI.fxml";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(MAIN_UI_RES));
        primaryStage.setTitle("table");
        primaryStage.setScene(new Scene(root));
        FXMLMainController.fillAllChoice();
        primaryStage.show();
    }

//    public static void select(){
//        ExcelHandle eh = new ExcelHandle();
//        System.out.println("输入筛选项");
//        String[][] selects =new String[7][];
//        Scanner in = new Scanner(System.in);
//        int i =0;
//        for (String attr: ElectricProject.selectAttrNames){
//            printCol(attr);
//            System.out.println("请选择（全选 输入all）");
//            String choice = in.nextLine();
//            if (choice.equals("all")){
//                List<String> list= new EPservice().getOneCol(attr);
//                selects[i]=new String[list.size()];
//                list.toArray(selects[i]);
//            }else{
//                selects[i] = choice.split(" ");
//            }
//            i++;
//        }
//        System.out.println("请输入结果保存文件名：");
//        String excelName = in.next();
//        eh.db2excel(excelName, selects);
//    }
//
//
//    public static void printCol(String attr){
//        ArrayList<String> col_list = new ArrayList<> (new EPservice().getOneCol(attr));
//        System.out.println(ExcelHandle.attr2col.get(attr)+":");
//        int i = 0;
//        for (String cell:col_list){
//            if (i>3){
//                System.out.println();
//                i=0;
//            }
//            System.out.print(cell+"  ");
//            i++;
//        }
//        System.out.println();
//
//    }

}
