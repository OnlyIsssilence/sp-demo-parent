package com.muya.fx.demo.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-28
 * @Time: 22:55
 * @Description:
 */
@FXMLController
public class ChoiceBoxDemoController implements Initializable {


    public ChoiceBox nameChoice;
    public ChoiceBox companyChoice;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*普通的选项
        nameChoice.getItems().addAll("张三", new Separator(), "李四", new Separator(), "王五");
        nameChoice.setValue("张三");
        */


        /* 复杂选项 数据类型转换
        Student student1 = new Student("张三001", 18, 90);
        Student student2 = new Student("张三002", 12, 100);
        Student student3 = new Student("张三003", 14, 80);
        Student student4 = new Student("张三004", 15, 10);
        Student student5 = new Student("张三005", 28, 30);
        Student student6 = new Student("张三006", 18, 50);
        nameChoice.getItems().addAll(student1, student2, student3, student4, student5, student6);

        nameChoice.setConverter(new StringConverter<Student>() {

            @Override
            public String toString(Student object) {
                return JSON.toJSONString(object);
            }

            @Override
            public Student fromString(String string) {
                return null;
            }
        });

        nameChoice.getSelectionModel().select(2);
        nameChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
                System.out.println("oldValue:" + JSON.toJSONString(oldValue) + ", newValue:" + JSON.toJSONString(newValue));
            }

        });*/


        ObservableList<String> obList1 = FXCollections.observableArrayList();
        obList1.addAll("数字", "字母");

        ObservableList<String> obList2 = FXCollections.observableArrayList();
        obList2.addAll("1", "2", "3");

        ObservableList<String> obList3 = FXCollections.observableArrayList();
        obList3.addAll("A", "B", "C");


        nameChoice.setItems(obList1);

        nameChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {


            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ("数字".equals(newValue)) {
                    companyChoice.setItems(obList2);
                } else {
                    companyChoice.setItems(obList3);
                }
            }
        });

    }
}
