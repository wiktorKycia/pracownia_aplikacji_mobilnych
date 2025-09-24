package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Main
{
    static int czynniki_pierwsze(int n)
    {
        int result = 0;

        int dzielnik = 2;

        while(n > 1)
        {
            if(n % dzielnik == 0)
            {
                n /= dzielnik;
                result++;
            }
            else
            {
                dzielnik++;
            }
        }

        return result;
    }

    static int rozne_czynniki_pierwsze(int n)
    {
        int result = 0;

        int dzielnik = 2;
        ArrayList<Integer> czynniki = new ArrayList<Integer>();

        while(n > 1)
        {
            if(n % dzielnik == 0)
            {
                n /= dzielnik;
                if(!czynniki.contains(dzielnik))
                {
                    czynniki.add(dzielnik);
                    result++;
                }
            }
            else
            {
                dzielnik++;
            }
        }

        return result;
    }

    public static void main(String[] args)
    {
        File file = new File("./Dane_2205/liczby.txt");

        int file_length = 0;
        try (Scanner lineCounter = new Scanner(file)) {
            while (lineCounter.hasNextLine()) {
                lineCounter.nextLine();
                file_length++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String[] numbers = new String[file_length];

        try (Scanner reader = new Scanner(file))
        {
            for(int i = 0;reader.hasNextLine(); i++)
            {
                String data = reader.nextLine();
                numbers[i] = data;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ArrayList<String> valid_numbers = new ArrayList<String>();

        for(int i = 0; i < numbers.length; i++)
        {
            if(numbers[i].charAt(0) == numbers[i].charAt(numbers[i].length()-1))
            {
                valid_numbers.add(numbers[i]);
            }
        }

        try {
            FileWriter writer = new FileWriter("wyniki4.txt");
            String text = valid_numbers.getFirst() + " " + valid_numbers.toArray().length;
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(czynniki_pierwsze(420));
        System.out.println(rozne_czynniki_pierwsze(420));
    }
}