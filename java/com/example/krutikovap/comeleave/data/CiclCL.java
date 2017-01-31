package com.example.krutikovap.comeleave.data;

/**
 * Класс для учета рабочего времени в один день
 * Многоразовый вход/выход
 * Переработки
 */
public class CiclCL {
    private int come;
    private int leave;

    private static Boolean isCome;

    public CiclCL(){
        come = -13;
        leave = -13;

        isCome = false;
    }

    public static void Come(){
        isCome = true;
    }

    public static void Leave(){
        isCome = false;
    }

    public static Boolean getIsCome() {
        return isCome;
    }

    public int get_come() {
        return this.come;
    }

    public void set_come(int come) {
        this.come = come;
    }

    public int get_leave() {
        return leave;
    }

    public void set_leave(int leave) {
        this.leave = leave;
    }
}
