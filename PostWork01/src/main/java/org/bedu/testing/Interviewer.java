package org.bedu.testing;

import java.io.*;
import java.util.ArrayList;

public class Interviewer  implements Serializable {
    static ArrayList<Interviewer> data;

    static String  fileStorage;

    int id;
    String name;
    String lastName;
    String email;
    Boolean isActive;


    public Interviewer( String name,  String lastName,
                        String email, Boolean isActive) {

        this.id       = data.size() + 1;
        this.name     = name;
        this.lastName = lastName;
        this.email    = email;
        this.isActive = isActive;
    }

    public Interviewer add() {
        data.add(this);
        Interviewer.saveDataToFile();
        return this;
    }

    public void delete() throws Exception{
        Interviewer interviewer = Interviewer.getByEmail(this.email);

        if ( interviewer == null )
            throw new Exception("Interviewer not found");
        else{
            data.remove(this);
            Interviewer.saveDataToFile();
        }
    }

    public void save( String name,  String lastName,
                      String email, Boolean isActive) {

        try {
            this.delete();

            if (!name.equals(""))     this.name     = name;

            if (!lastName.equals("")) this.lastName = lastName;

            if (!email.equals(""))    this.email    = email;

            this.isActive = isActive;

            data.add(this);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static Interviewer getByEmail(String email) throws Exception{

        if (data== null)
            throw new Exception("data is null, did you forget initialized or call loadDataFromFile");
        else
            for (Interviewer interviewer : data) {
                if (interviewer.email.equals(email))
                    return interviewer;
            }

        return null;
    }

    public static void saveDataToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileStorage);
            ObjectOutputStream outputStream     = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(Interviewer.data);

            outputStream.close();
            fileOutputStream.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void loadDataFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileStorage);
            ObjectInputStream inputStream   = new ObjectInputStream(fileInputStream);

            ArrayList<Interviewer> fileData = (ArrayList<Interviewer>) inputStream.readObject();

            Interviewer.data.clear();
            Interviewer.data.addAll(fileData);

            inputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            if (!e.getMessage().contains("No such file or directory"))
                e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "\n"+
                "ID:        \t" + this.id +"\n"+
                "Name:      \t" + this.name +"\n"+
                "Last Name: \t" + this.lastName +"\n"+
                "Email:     \t" + this.email +"\n"+
                "Is Active: \t" + this.isActive + "\n";
    }
}