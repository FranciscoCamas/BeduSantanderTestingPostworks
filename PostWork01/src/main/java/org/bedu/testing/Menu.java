package org.bedu.testing;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner sc;

    public Menu() {
        sc = new Scanner(System.in);

        Interviewer.fileStorage =  "./interviewers";
        Interviewer.data        = new ArrayList<Interviewer>();
        Interviewer.loadDataFromFile();

        showMainMenu();
    }

    public void showMainMenu() {
        int option = 0;

        while (option != 4) {
            System.out.println("Seleccione la operacion a realizar:");
            System.out.println("1. Dar de alta un entrevistador");
            System.out.println("2. Consultar un entrevistador");
            System.out.println("3. Modificar un entrevistador");
            System.out.println("4. Salir");

            //option = Integer.parseInt( sc.nextLine());
            option = sc.nextInt();sc.nextLine();

            switch (option) {
                case 1:
                    addInterviewer();
                    break;
                case 2:
                    searchInterviewer();
                    break;
                case 3:
                    modifyInterviewer();
                    break;
            }
        }
        ;
        sc.close();
        System.out.println("Programa terminado");
    }

    public void addInterviewer() {

        System.out.println("Ingrese el nombre del entrevistador: ");
        String name = sc.nextLine();
        System.out.println("Ingrese el apellido del entrevistador: ");
        String lastName = sc.nextLine();
        System.out.println("Ingrese el email del entrevistador: ");
        String email = sc.nextLine();
        System.out.println("El entrevistador se encuentra activo? (1=Si/2=No)");
        Boolean isActive = sc.nextInt() == 1;  sc.nextLine();

        Interviewer interviewer = new Interviewer(name, lastName, email, isActive);
        interviewer.add();

        System.out.println("Entrevistador agregado:");
        System.out.println(interviewer.toString());
    }

    public void searchInterviewer() {
        System.out.println("Ingrese el email del entrevistador a consultar:");
        String email = sc.nextLine();

        Interviewer interviewer = null;
        try {
            interviewer = Interviewer.getByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (interviewer != null) {
            System.out.println("Entrevistador encontrado:");
            System.out.println(interviewer.toString());
        } else {
            System.out.println("Entrevistador no encontrado");
        }
    }
    public void modifyInterviewer() {
        Interviewer interviewer = null;

        System.out.println("Ingrese el email del entrevistador a modificar:");
        String email = sc.nextLine();

        try {
            interviewer = Interviewer.getByEmail(email);

            if (interviewer == null)
                System.out.println("Entrevistador no encontrado");
                else{

                System.out.println("Entrevistador encontrado:");
                System.out.println(interviewer.toString());

                System.out.println("Ingrese el nuevo nombre del entrevistador: (Enter para mantener actual)");
                String name = sc.nextLine();
                System.out.println("Ingrese el nuevo apellido del entrevistador: (Enter para mantener actual)");
                String lastName = sc.nextLine();
                System.out.println("Ingrese el nuevo email del entrevistador: (Enter para mantener actual)");
                String newEmail = sc.nextLine();
                System.out.println("El entrevistador se encuentra activo? (1=Si/2=No)");
                Boolean isActive = sc.nextInt() == 1; sc.nextLine();

                interviewer.save(name, lastName, newEmail, isActive);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        new Menu();
    }

}
