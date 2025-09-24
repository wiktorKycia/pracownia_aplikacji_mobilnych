package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        File file = new File("./Dane_2205/liczby.txt");

        try (Scanner reader = new Scanner(file))
        {
            while(reader.hasNextLine())
            {
                String data = reader.nextLine();
                System.out.println(data);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}