package presentationlayer;
//import domainlayer.controllers.CtrlDomini;
//import domainlayer.model.*;

public class Main {

    
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater (
        new Runnable() {
            public void run() {
                CtrlPresentacio ctrlPresentacio = new CtrlPresentacio();
                ctrlPresentacio.inicialitzarPresentacio();
            }
        });
    }
}

