package ru.dvvar.topjava.desktop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dvvar.topjava.desktop.model.Model;
import ru.dvvar.topjava.desktop.view.View;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
@org.springframework.stereotype.Controller
public class ControllerImpl implements Controller {

    @Autowired
    private View view;

    @Autowired
    private Model model;

    @Override
    public void getAll() {
        view.refresh(model.getAll());
    }
}
