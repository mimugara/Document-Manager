import java.util.*;


import domainlayer.controllers.CtrlDomini;
import domainlayer.model.*;

public class DriverCtrlDomini {
    
    public static void main(String[] args) throws Exception {
        CtrlDomini ctrl = CtrlDomini.getInstance();

        Scanner scan = new Scanner(System.in);
        boolean finish = false;
        String titol;
        String autor;
        String cont;
        String format;
        int nombreDocuments;
        String prefix;
        ArrayList<String> llistaString;
        ArrayList<Document> llistaDocuments;
        String expressio;
        String query;

        String options = "      OPTIONS        \n" +
                            "1.  Alta Document\n" +
                            "2.  Modificar Document\n" +
                            "3.  Baixa Document\n" +
                            "4.  Llistar Títols Autor\n" +
                            "5.  Llistar Autors per Prefix\n" +
                            "6.  Mostrar Contingut\n" +
                            "7.  Llistar Documents Semblants\n" +
                            "8.  Llistar Documents per Expressió\n" +
                            "9.  Alta Expressió Booleana\n" +
                            "10. Modificar Expressió Booleana\n" +
                            "11. Baixa Expressió Booleana\n" +
                            "12. Sortir\n";

        System.out.println(options);
        int option = -1;
        while (!finish) {        

            try {
                option = Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                option = -1;
            }
            switch (option) {
                case 1:
                    try {
                        System.out.println("Introduir títol:");
                        titol = scan.nextLine();
                        System.out.println("Introduir autor:");
                        autor = scan.nextLine();
                        System.out.println("Introduir contingut:");
                        cont = scan.nextLine();
                        System.out.println("Introduir format:");
                        format = scan.nextLine();
                        ctrl.alta_document(format, autor, titol, cont);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Introduir títol:");
                        titol = scan.nextLine();
                        System.out.println("Introduir autor:");
                        autor = scan.nextLine();
                        System.out.println("Introduir contingut:");
                        cont = scan.nextLine();
                        ctrl.modificar_document(autor, titol, cont);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Introduir títol:");
                        titol = scan.nextLine();
                        System.out.println("Introduir autor:");
                        autor = scan.nextLine();
                        ctrl.baixa_document(autor, titol);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Introduir autor:");
                        autor = scan.nextLine();
                        llistaString = ctrl.llistar_titols_unautor(autor);
                        System.out.println(llistaString);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break; 
                case 5:
                    try {
                        System.out.println("Introduir prefix:");
                        prefix = scan.nextLine();
                        llistaString = ctrl.llistarPrefix(prefix);
                        System.out.println(llistaString);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        System.out.println("Introduir títol:");
                        titol = scan.nextLine();
                        System.out.println("Introduir autor:");
                        autor = scan.nextLine();
                        cont = ctrl.mostrar_contingut(autor, titol);
                        System.out.println(cont);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        System.out.println("Introduir títol:");
                        titol = scan.nextLine();
                        System.out.println("Introduir autor:");
                        autor = scan.nextLine();
                        System.out.println("Introduir nombre de documents");
                        nombreDocuments = scan.nextInt();
                        llistaDocuments = ctrl.ordenar_per_semblanca(titol, autor, nombreDocuments);
                        System.out.println(llistaDocuments);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        System.out.println("Introduir Identificador:");
                        expressio = scan.nextLine();
                        llistaDocuments = ctrl.bool_search(expressio);
                        System.out.println(llistaDocuments);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    try {
                        System.out.println("Introduir Identificador");
                        expressio = scan.nextLine();
                        System.out.println("Introduir Query");
                        query = scan.nextLine();
                        ctrl.alta_BoolExpression(expressio, query);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    try {
                        System.out.println("Introduir Identificador");
                        expressio = scan.nextLine();
                        System.out.println("Introduir Query");
                        query = scan.nextLine();
                        ctrl.mod_BoolExpression(expressio, query);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    try {
                        System.out.println("Introduir Identificador");
                        expressio = scan.nextLine();
                        ctrl.baixa_BoolExpression(expressio);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 12:
                    finish = true;
                    scan.close();
                    break;

                default:
                    System.out.println(options);
                    break;
            }
            }


    }
    
}

