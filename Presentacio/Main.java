/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacio;

/**
 *
 * @author carlosaires
 */
public class Main {
    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            
            public void run() {
            ControladorPresentacio cp = new ControladorPresentacio();
            cp.iniciaPresentacio();
            }
        });
    }
    
}
