package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Main
{
    static ArrayList<Integer> getPrimeFactors(int n)
    {
        ArrayList<Integer> primeFactors = new ArrayList<>();

        int factor = 2;

        while (n > 1)
        {
            if (n % factor == 0)
            {
                n /= factor;
                primeFactors.add(n);
            }
            else
            {
                factor++;
            }
        }

        return primeFactors;
    }

    static ArrayList<Integer> removeDuplicates(ArrayList<Integer> numbers)
    {
        ArrayList<Integer> resultArray = new ArrayList<>();
        for (Integer number : numbers) {
            if (!resultArray.contains(number)) {
                resultArray.add(number);
            }
        }
        return resultArray;
    }

    /**
     * Calculates the total number of prime factors of a given number.
     *
     * @param n The number to calculate prime factors for.
     * @return The total count of prime factors.
     */
    static int getNumberOfPrimeFactors(int n)
    {
        return getPrimeFactors(n).size();
    }

    /**
     * Calculates the number of distinct prime factors of a given number.
     *
     * @param n The number to calculate distinct prime factors for.
     * @return The count of distinct prime factors.
     */
    static int getNumberOfDifferentPrimeFactors(int n)
    {
        return removeDuplicates(getPrimeFactors(n)).size();
    }

    /**
     * Counts the number of lines in a given file.
     *
     * @param file The file to count lines in.
     * @return The number of lines in the file.
     */
    static int getFileLength(File file)
    {
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
        return file_length;
    }

    static void writeToFile(String filename, String text, boolean shouldAppend)
    {
        try {
            FileWriter writer = new FileWriter(filename, shouldAppend);

            if (shouldAppend)
            {
                writer.append(text);
            }
            else
            {
                writer.write(text);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static void main(String[] args)
    {
        File file = new File("./Dane_2205/liczby.txt");


        // reading the file length
//        int file_length = getFileLength(file);


        // reading a whole file to the list
        ArrayList<Integer> numbers = new ArrayList<>();

        try (Scanner reader = new Scanner(file))
        {
            while(reader.hasNextLine())
            {
                numbers.add(Integer.parseInt(reader.nextLine()));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        // doing the things
        ArrayList<Integer> valid_numbers = new ArrayList<>();
        int number_with_the_most_primes = 0;
        int number_with_the_most_different_primes = 0;

        for (int number : numbers) {
            // checking condition for 4.1
            String numberString = Integer.toString(number);
            if (numberString.charAt(0) == numberString.charAt(numberString.length() - 1))
            {
                valid_numbers.add(number);
            }

            // calculations for 4.2
            if (getNumberOfPrimeFactors(number_with_the_most_primes) < getNumberOfPrimeFactors(number))
            {
                number_with_the_most_primes = number;
            }
            if (getNumberOfDifferentPrimeFactors(number_with_the_most_different_primes) < getNumberOfDifferentPrimeFactors(number))
            {
                number_with_the_most_different_primes = number;
            }
        }

        // saving 4.1 to a file
        writeToFile("wyniki4.txt", valid_numbers.getFirst() + " " + valid_numbers.toArray().length + "\n", false);
        // saving 4.2 to a file
        writeToFile("wyniki4.txt", number_with_the_most_primes + " " + getNumberOfPrimeFactors(number_with_the_most_primes) + "\n" +
                number_with_the_most_different_primes + " " + getNumberOfDifferentPrimeFactors(number_with_the_most_different_primes), true);



            // testing the methods
//        System.out.println(czynniki_pierwsze(420));
//        System.out.println(rozne_czynniki_pierwsze(420));

    }
}