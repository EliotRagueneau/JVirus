package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("ALL")
public class IO {
    public static void print(String message) {
        System.out.print(message);
    }

    public static void print(int nombre) {
        System.out.print(nombre);
    }

    public static String input() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            print("Erreur");
            return "";
        }
    }

    public static String input(String message) {
        print(message);
        return input();
    }

    public static int intInput() {
        try {
            return Integer.parseInt(input());
        } catch (Error e) {
            print("Ceci n'est pas un entier, veuillez entrez un entier");
            return intInput();
        }

    }

    public static int intInput(String message) {
        try {
            return Integer.parseInt(input(message));
        } catch (Exception e) {
            print("Ceci n'est pas un entier, veuillez entrez un entier");
            return intInput(message);
        }
    }

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }

}
