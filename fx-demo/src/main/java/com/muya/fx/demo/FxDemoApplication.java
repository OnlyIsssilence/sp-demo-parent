package com.muya.fx.demo;

import com.muya.fx.demo.view.ChoiceBoxDemoView;
import com.muya.fx.demo.view.RadioButtonDemoView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-28
 * @Time: 22:55
 * @Description:
 */
@SpringBootApplication
public class FxDemoApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(FxDemoApplication.class, ChoiceBoxDemoView.class, args);
    }
}
