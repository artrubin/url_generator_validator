/*
*URLGeneratorApp.java
*@author Artem Chaykovskyy, Student ID x23224746
*6th Jan 2024
*/

//Importing Scanner
import java.util.Scanner;

//Defining a new class URLGeneratorApp
public class URLGeneratorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        URLGenerator urlGenerator = new URLGenerator();

        // Generating URLs based on company names, Using While loop untill user enters anything by "yes"
        while (true) {
            System.out.println("Enter a company name:");
            String userCompanyName = scanner.nextLine();
            urlGenerator.setUserCompanyName(userCompanyName);

            String url = urlGenerator.getURL();
            System.out.println("Generated URL: " + url);

            System.out.println("Would you like to generate another URL? (Enter 'yes' to continue):");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                break;
            }
        }

        // Validating URLs
        System.out.println("How many URLs would you like to validate?");
        int numURLs = Integer.parseInt(scanner.nextLine());
        String[] urls = new String[numURLs];

        for (int i = 0; i < numURLs; i++) {
            System.out.println("Enter URL " + (i + 1) + ":");
            urls[i] = scanner.nextLine();
        }

        boolean[] validities = urlGenerator.validateURLs(urls);

        System.out.println("URL Validities:");
        for (int i = 0; i < validities.length; i++) {
            System.out.println(urls[i] + " is " + (validities[i] ? "valid" : "invalid"));
        }

        scanner.close();
    }
}
