package controller;

import view.Gui;

/**
 * Controller of the application
 * @author Marek Michali
 * @version 3.0
 */
public class Main {
    /**
     * Method to show the gui and pass program parameter
     * @param args number to convert from roman to arabic or arabic to roman
     */
    public static void main(String args[]){
        String toConvert;
        if (args.length == 0 || args[0].isEmpty()) {
            toConvert = "";
        } else {
            toConvert = args[0];
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui(toConvert).setVisible(true);
            }
        });  
    }
}


