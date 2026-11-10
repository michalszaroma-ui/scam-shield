
package ca.scamshield;

/*
 *  ScamShield.java
 *
 *  Rev 2
 *
 *  07/11/2026
 *
 *  @author Michal Szaroma, 
 */

public class ScamShield {

    public static void main(String[] args) {
        //stop Java's default Metal style showwing text in bold
        javax.swing.UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //open the main menu
        new MainMenuFrame().setVisible(true);
    }
}
