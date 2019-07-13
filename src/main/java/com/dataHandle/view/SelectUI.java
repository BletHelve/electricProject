package com.dataHandle.view;

import com.dataHandle.bean.ElectricProject;
import com.dataHandle.controller.ExcelHandle;
import com.dataHandle.controller.FXMLMainController;
import com.dataHandle.service.EPservice;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.List;

public class SelectUI extends Application {
    private static final String SELECT_UI_RES = "/SelectUI.fxml";
    private String title;
    private String attrName;
    private List<String> selectList;

    public SelectUI(String title, String attrName, List<String> selectList) {
        this.title = title;
        this.attrName = attrName;
        this.selectList =  selectList;
    }

    @Override
    public void start(Stage primaryStage)  {
        ListView<String> listView = new ListView<>();
        for (String str:selectList) {
            listView.getItems().add(str);
        }

        listView.setCellFactory(CheckBoxListCell.forListView(item -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                EPservice eps = new EPservice();
                if (isNowSelected){
                    FXMLMainController.chooseChoice(attrName,item);
                }else {
                    FXMLMainController.cancelChoice(attrName,item);
                }
                String[][] attrNameArray=new String[ExcelHandle.SELECT_LEN][];
                for (int i=0;i<ExcelHandle.SELECT_LEN;i++) {
                    List<String> attrNames = FXMLMainController.getChoice(
                                        ElectricProject.selectAttrNames[i]);
                    attrNameArray[i] = new String[attrNames.size()];
                    for (int j=0; j<attrNames.size();j++) {
                        attrNameArray[i][j] = attrNames.get(j);
                    }
                }
                List<ElectricProject> epList = eps.getFilter(
                        attrNameArray[0],attrNameArray[1],attrNameArray[2],attrNameArray[3],
                        attrNameArray[4],attrNameArray[5],attrNameArray[6]);
                FXMLMainController.setEPList(epList);
            });
            return observable;
        }));

        BorderPane root = new BorderPane(listView);
        Scene scene = new Scene(root, 250, 400);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}



