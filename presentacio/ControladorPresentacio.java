/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio;

import domini.ControladorDomini;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author carlosaires
 */
public class ControladorPresentacio {
    
    private ControladorDomini cd;
    private loginsign ls;
    private login l;
    private register r;
    private menu m;
    private menuplay mp;
    

    /**
     * @param args the command line arguments
     */
    
    //Creadora
    public ControladorPresentacio() {
        cd = new ControladorDomini();
        if(ls == null) {
            ls = new loginsign(this);
        }
    }
    
    public void iniciaPresentacio() {
        ls.setVisible(true);
    }
    public void canviEscena(String escena) {
        switch(escena) {
            case "menulogin" :
                ls.setVisible(true);
                break;
            
            case "login" :
                l = new login(this);
                l.setVisible(true);
                break;
            
            case "register" :
                r = new register(this);
                r.setVisible(true);
                break;
            
            case "menuplay" :
                m = new menu(this);
                m.setVisible(true);
                break;
                
            case "menuloginback" :
                ls = new loginsign(this);
                ls.setVisible(true);
                break;
                
            case "menutypeplay" :
                mp = new menuplay(this);
                mp.setVisible(true);
                break;
            
            default:
                break;
        }
    }
    
    public void loginUsr(String usr, String pwd) throws IOException, FileNotFoundException, ClassNotFoundException {
        boolean b1;
        Boolean b2 = false;
        b1 = cd.validarLogin(usr, pwd, b2);
        if(!b1) {
            JOptionPane.showMessageDialog(null,"L'usuari no existex", "Display Message", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            if(!b2) {
                JOptionPane.showMessageDialog(null, "La contrasenya és incorrecta!", "Display Message", JOptionPane.INFORMATION_MESSAGE);
                
            }
            else {
               canviEscena("menuplay");
               l.setVisible(false);
            }
        }
        
    }
    
    public void registerUsr(String usr1, String pwd1, String pwd2) throws IOException, FileNotFoundException, ClassNotFoundException {
        boolean b;
        Boolean b2;
        b2 = pwd1.equals(pwd2);
        b = cd.validarRegisterCd(usr1, pwd1, b2);
        
        
        if (b && b2) {
        //Creacio d'usuari exitosa
        JOptionPane.showMessageDialog(null,"L'usuari s'ha creat satisfactòriament","Display Message", JOptionPane.INFORMATION_MESSAGE);
        canviEscena("login");
        r.setVisible(false);
        }
        
        //Usuari ja registrat
        else if(!b) {
            JOptionPane.showMessageDialog(null,"L'usuari ja existeix", "Display Message", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
        else {
         // Contrasenyes diferents   
         JOptionPane.showMessageDialog(null, "Les contrasenyes no són iguals!", "Display Message", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
