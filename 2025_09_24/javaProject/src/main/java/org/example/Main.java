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


        // reading the file length
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


        // reading a whole file to the array
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


        // doing the things
        ArrayList<String> valid_numbers = new ArrayList<String>();
        int number_with_the_most_primes = 0;
        int number_with_the_most_different_primes = 0;

        for(int i = 0; i < numbers.length; i++)
        {
            // checking condition for 4.1
            if(numbers[i].charAt(0) == numbers[i].charAt(numbers[i].length()-1))
            {
                valid_numbers.add(numbers[i]);
            }

            // calculations for 4.2
            if(czynniki_pierwsze(number_with_the_most_primes) < czynniki_pierwsze(Integer.parseInt(numbers[i])))
            {
                number_with_the_most_primes = Integer.parseInt(numbers[i]);
            }
            if(rozne_czynniki_pierwsze(number_with_the_most_different_primes) < rozne_czynniki_pierwsze(Integer.parseInt(numbers[i])))
            {
                number_with_the_most_different_primes = Integer.parseInt(numbers[i]);
            }
        }

        try { // saving 4.1 to a file
            FileWriter writer = new FileWriter("wyniki4.txt");
            String text = valid_numbers.getFirst() + " " + valid_numbers.toArray().length + "\n";
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try { // saving 4.2 to a file
            FileWriter writer = new FileWriter("wyniki4.txt", true);
            String text = number_with_the_most_primes + " " + czynniki_pierwsze(number_with_the_most_primes) + "\n" +
                    number_with_the_most_different_primes + " " + rozne_czynniki_pierwsze(number_with_the_most_different_primes);
            writer.append(text);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


            // testing the methods
//        System.out.println(czynniki_pierwsze(420));
//        System.out.println(rozne_czynniki_pierwsze(420));

    }
}