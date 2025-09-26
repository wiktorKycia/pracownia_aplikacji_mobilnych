package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
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
        System.out.println("number: "+n);
        System.out.println("primes: "+ primeFactors);

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
        System.out.println("original: "+numbers);
        System.out.println("reduced:  "+resultArray);
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
        System.out.println("===============");
        System.out.println("number: "+n);
        int result = removeDuplicates(getPrimeFactors(n)).size();
        System.out.println("returning: "+result);
        return result;
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
//        File file = new File("./Dane_2205/liczby.txt");
        File file = new File("./Dane_2205/przyklad.txt");


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
        int most_primes = 0;
        int number_with_the_most_different_primes = 0;
        int most_different_primes = 0;

        for (int number : numbers) {
            // checking condition for 4.1
            String numberString = Integer.toString(number);
            if (numberString.charAt(0) == numberString.charAt(numberString.length() - 1))
            {
                valid_numbers.add(number);
            }

            // calculations for 4.2
            int number_of_primes_for_n = getNumberOfPrimeFactors(number);
            if (most_primes < number_of_primes_for_n)
            {
                number_with_the_most_primes = number;
                most_primes = number_of_primes_for_n;
            }
            int number_of_different_primes_for_n = getNumberOfDifferentPrimeFactors(number);
            if (most_different_primes < number_of_different_primes_for_n)
            {
                number_with_the_most_different_primes = number;
                most_different_primes = number_of_different_primes_for_n;
            }
        }

        // saving 4.1 to a file
        writeToFile("wyniki4.txt", valid_numbers.size() + " " + valid_numbers.getFirst() + "\n", false);
        // saving 4.2 to a file
        writeToFile("wyniki4.txt", number_with_the_most_primes + " " + most_primes + "\n" +
                number_with_the_most_different_primes + " " + most_different_primes, true);



            // testing the methods
//        System.out.println(getNumberOfPrimeFactors(420)); // to działa
        System.out.println(getNumberOfDifferentPrimeFactors(420));

//        System.out.println(removeDuplicates(new ArrayList<>(Arrays.asList(1, 2, 2, 3)))); // to działa

    }
}