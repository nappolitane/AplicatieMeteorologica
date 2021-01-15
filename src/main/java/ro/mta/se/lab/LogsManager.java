package ro.mta.se.lab;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Aceasta clasa este implementarea managerului de loging din program
 */
public class LogsManager
{
    private static String logFilePath = "src/main/resources/log_file.txt";

    // Aceasta functie scrie in fisierul de log variabila line primita ca parametru
    public static void logThis(String line)
    {
        try {
            File myObj = new File(logFilePath);
            if (myObj.createNewFile()) // Se creaza fisierul de log daca nu exista
                System.out.println("File created: " + myObj.getName());
            FileWriter myWriter = new FileWriter(logFilePath,true);
            String timeStamp = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss").format(Calendar.getInstance().getTime());
            myWriter.write("[" + timeStamp + "] " + line + "\n"); // Se scrie in fisier log-ul
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
