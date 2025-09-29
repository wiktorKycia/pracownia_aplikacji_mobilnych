package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Main
{
    static ArrayList<Integer> sieveOfEratosthenes(int max)
    {
        boolean[] isPrime = new boolean[max + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= max; i++)
        {
            if (isPrime[i])
            {
                for (int j = i * i; j <= max; j += i)
                {
                    isPrime[j] = false;
                }
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static HashMap<Integer, ArrayList<Integer>> PrimeFactors = new HashMap<Integer, ArrayList<Integer>>();
    static ArrayList<Integer> getPrimeFactors(int n, ArrayList<Integer> primes)
    {
        ArrayList<Integer> arr = PrimeFactors.get(n);
        if (arr == null)
        {
            ArrayList<Integer> primeFactors = new ArrayList<>();

            int k = n; // k jest modyfikowalne, n nie
            for(int factor: primes)
            {
                if(factor*factor > n || k == 1) break;
                while(k % factor == 0)
                {
                    primeFactors.add(factor);
                    k /= factor;
                }
            }

            PrimeFactors.put(n, primeFactors);
            return primeFactors;
        }
        else
        {
            return arr;
        }

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

    static int getNumberOfPrimeFactors(int n, ArrayList<Integer> primes)
    {
        return getPrimeFactors(n, primes).size();
    }

    static int getNumberOfDifferentPrimeFactors(int n, ArrayList<Integer> primes)
    {
        return removeDuplicates(getPrimeFactors(n, primes)).size();
    }


    static int getFileLength(File file)
    {
        try {
            return (int) Files.lines(file.toPath()).count();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return 0;
        }
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

        // predefined prime numbers up to 100_000
        ArrayList<Integer> primes = sieveOfEratosthenes(100000);


        // reading a whole file to the list
        int[] numbers = new int[getFileLength(file)];

        try (Scanner reader = new Scanner(file))
        {
            for(int i = 0; reader.hasNextLine(); i++)
            {
                numbers[i] = (Integer.parseInt(reader.nextLine()));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        int number_of_valid_numbers = 0;
        int first_valid_number = 0;
        boolean flag_valid_number = true;

        int number_with_the_most_primes = 0;
        int most_primes = 0;

        int number_with_the_most_different_primes = 0;
        int most_different_primes = 0;

        for (int number : numbers) {
            // checking condition for 4.1
            String numberString = Integer.toString(number);
            if (numberString.charAt(0) == numberString.charAt(numberString.length() - 1))
            {
                number_of_valid_numbers++;
                if(flag_valid_number)
                {
                    first_valid_number = number;
                    flag_valid_number = false;
                }
            }

            // calculations for 4.2
            int number_of_primes_for_n = getNumberOfPrimeFactors(number, primes);
            if (most_primes < number_of_primes_for_n)
            {
                number_with_the_most_primes = number;
                most_primes = number_of_primes_for_n;
            }
            int number_of_different_primes_for_n = getNumberOfDifferentPrimeFactors(number, primes);
            if (most_different_primes < number_of_different_primes_for_n)
            {
                number_with_the_most_different_primes = number;
                most_different_primes = number_of_different_primes_for_n;
            }
        }

        // saving 4.1 to a file
        writeToFile("wyniki4.txt", number_of_valid_numbers + " " + first_valid_number + "\n", false);
        // saving 4.2 to a file
        writeToFile("wyniki4.txt", number_with_the_most_primes + " " + most_primes + "\n" +
                number_with_the_most_different_primes + " " + most_different_primes, true);

        // doing task 4.3
        int valid3 = 0;
        int valid5 = 0;

        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length; i++)
        {
            for (int j = i+1; j < numbers.length; j++)
            {
                // jak j nie dzieli i, to nie tracimy czasu na dalsze sprawdzania
                if(numbers[j] % numbers[i] != 0) continue;

                for (int k = j+1; k < numbers.length; k++)
                {
                    // tu już wiemy, że na pewno j dzieli i, więc sprawdzamy tylko czy k dzieli j
                    if(numbers[k] % numbers[j] == 0)
                    {
                        valid3++;
                        writeToFile("trojki.txt", numbers[i] + " " + numbers[j] + " " + numbers[k], true);
                        for (int l = k+1; l < numbers.length; l++)
                        {
                            // sprawdzam, czy l dzieli k
                            if(numbers[l] % numbers[k] != 0) continue;

                            for (int m = l+1; m < numbers.length; m++)
                            {
                                // tutaj tak jak poprzednio, nie muszę sprawdzać innych warunków, bo jest to zrobione wcześniej
                                if(numbers[m] % numbers[k] == 0)
                                {
                                    valid5++;
                                }
                            }
                        }
                    }
                }
            }
        }
        // saving 4.3 to a file
        writeToFile("wyniki4.txt", "dobre trójki: "+valid3, true);
        writeToFile("wyniki4.txt", "dobre piątki: "+valid5, true);
    }
}