package com.dataHandle.controller;


import com.dataHandle.bean.ElectricProject;
import com.dataHandle.service.EPservice;
import com.dataHandle.view.SelectUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FXMLMainController {
    @FXML public TableView<ElectricProject> epTableView;
    @FXML private ComboBox<String> selectComboBox;
    private static List<ElectricProject> epList=new ArrayList<>();
    private static Map choiceMapList= new HashMap<String, List<String>>(){{
        for(String str:ElectricProject.selectAttrNames) {
            List<String> list =new EPservice().getOneCol(str);
            put(str, list);
        }
    }};



    public static void setEPList(List<ElectricProject> epList) {
        FXMLMainController.epList = epList;
    }

    public void tableShow(ActionEvent actionEvent){
        tableShow();
    }

    public void tableShow(){
        ObservableList<ElectricProject> observableList = FXCollections.observableArrayList();
        observableList.addAll(epList);
        epTableView.setItems(observableList);
    }


    public void addExcel(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose excel");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("excel Files(.xlsx)", "*.xlsx"));
        String excelPath = fileChooser.showOpenDialog(new Stage()).getAbsolutePath();
        System.out.println(excelPath);
        ExcelHandle eh = new ExcelHandle();
        try {
            eh.excel2db(excelPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void popExcel(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("save excel");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("excel Files(.xlsx)", "*.xlsx"));
        String excelPath = fileChooser.showSaveDialog(new Stage()).getAbsolutePath();
        ExcelHandle eh = new ExcelHandle();
        try {
            eh.db2excel(excelPath,epList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void select(ActionEvent actionEvent)  {
        EPservice eps = new EPservice();
        String selectStr =  selectComboBox.getSelectionModel().getSelectedItem();
        if (selectStr.equals("all")) {
            epList = eps.getAll();
            fillAllChoice();
            tableShow();
        }else{
            String attrName = ExcelHandle.col2attr.get(selectStr);
            clearChoice(attrName);
            SelectUI selectUI  = new SelectUI(selectStr, attrName ,eps.getOneCol(attrName));
            selectUI.start(new Stage());
        }




    }

    public static List<String> getChoice(String attr) {
        return ((List)choiceMapList.get(attr));
    }

    public static void fillAllChoice(){
        for(String str:ElectricProject.selectAttrNames) {
            choiceMapList.put(str, new EPservice().getOneCol(str));
        }
    }

    public static void clearChoice(String attr){
        ((List)choiceMapList.get(attr)).clear();
    }

    public static void chooseChoice(String attr, String val){
        ((List) choiceMapList.get(attr)).add(val);
    }

    public static void cancelChoice(String attr, String val){
        ((List) choiceMapList.get(attr)).remove(val);
    }



}
