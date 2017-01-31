package com.example.krutikovap.comeleave.data;

import java.util.ArrayList;

/**
 * Created by krutikovap on 31.05.2016.
 */
public class DayWork {
     ArrayList<CiclCL> ciclCLs;
    private boolean _set_come;

    public void add_come(int come) {
        CiclCL ciclCL = new CiclCL();

        if (is_set_come()) {
            ciclCL.set_come(come);
        }
    }

    //TODO: Добавление ухода

    public boolean is_set_come() {
        //TODO:Проверить, если уход в предыдущем цикле
        return _set_come;
    }
}
