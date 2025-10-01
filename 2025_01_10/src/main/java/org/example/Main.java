package org.example;

import java.util.*;

public class Main
{
    static Random rand = new Random();
    static void main()
    {
        IO.println("Hello and welcome!");
        boolean running = true;

        do
        {
            int numberOfDices = 0;

            do
            {
                numberOfDices = getNumberOfDices();
            }
            while (!(numberOfDices >= 3 && numberOfDices <= 10));

            int[] dices = generateRandomDices(numberOfDices);
            printDices(dices);

            System.out.println("Liczba uzyskanych punktów: "+calculatePoints(dices));

            String again = getUserInput();

            if(Objects.equals(again, "n"))
            {
                running = false;
            }

        }
        while(running);

    }
    static String getUserInput()
    {
        Scanner scanner = new Scanner(System.in);
        final String[] expectedAnswers = new String[]{"t", "n"};
        while(true)
        {
            System.out.println("Jeszcze raz? (t/n)");
            String input = scanner.nextLine();

            if (Arrays.asList(expectedAnswers).contains(input))
            {
                return input;
            }
        }
    }
    static int getNumberOfDices()
    {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("Ile kostek chcesz rzucić(3-10)");
            String input = scanner.nextLine();
            try
            {
                return Integer.parseInt(input);
            }
            catch (java.lang.NumberFormatException _) {}
        }
    }
    static int getRandomNumber(int a, int b)
    {
        return rand.nextInt(a, b+1);
    }
    static int[] generateRandomDices(int numberOfDices)
    {
        int[] dices = new int[numberOfDices];
        for(int i = 0; i < numberOfDices; i++)
        {
            dices[i] = getRandomNumber(1, 6);
        }
        return dices;
    }
    static void printDices(int[] dices)
    {
        for (int i = 0; i < dices.length; i++) {
            System.out.println("Kostka "+i+1+": "+dices[i]);
        }
    }

    static int calculatePoints(int[] dices)
    {
        Map<Integer, Integer> map = new HashMap<>();
        for (int dice : dices)
        {
            if(map.containsKey(dice))
            {
                map.put(dice, map.get(dice)+1);
            }
            else
            {
                map.put(dice, 1);
            }
        }

        int points = 0;
        for(var entry: map.entrySet())
        {
            if(entry.getValue() > 1)
            {
                points += entry.getKey() * entry.getValue();
            }
        }
        return points;
    }
}
