/*
*URLGenerator.java
*@author Artem Chaykovskyy, Student ID x23224746
*6th Jan 2024
*/


public class URLGenerator {
    // Instance variable to hold the company name
    private String userCompanyName;

    // Constructor - Initializes the useCompanyName instance variable.
    public URLGenerator() {
        this.userCompanyName = ""; // Setting the userCompanyName to an empty string
    }

    // Setter method for userCompanyName. It sets the instance variable to the provided value.
    public void setUserCompanyName(String userCompanyName) {
        this.userCompanyName = userCompanyName;
    }

    // Method to compute and return the generated URL as a String
    public String compute() {
         // Determines the URL's protocol based on whether the company name contains 'meta' as per UR2 requirement
        String protocol;
		if (userCompanyName.toLowerCase().contains("meta")) {
		    protocol = "coap://";
		} else {
		    protocol = "ftp://";
}

        // Replacing specific words in hostname and adding underscores
        String adjustedHostname = adjustHostname(userCompanyName);

        // Determining .org or .edu based on the number of consonants
        int consonantCount = countConsonants(userCompanyName);

		if (consonantCount % 2 == 0) {
            adjustedHostname += ".edu";
		} else {
		    adjustedHostname += ".org";
		}


        // Determining the path
        String path = determinePath(userCompanyName);

        // Combining all parts to form the URL
        return protocol + adjustedHostname + "/" + path;
    }

    // Helper method to modify the hostname
    private String adjustHostname(String name) {
        StringBuffer adjustedHostname = new StringBuffer();
        String lowerName = name.toLowerCase(); // Convert to lower case for case-insensitive comparison

        for (int i = 0; i < name.length(); i++) {
            if (i == 0 || name.charAt(i - 1) == ' ') { // Check if it's the start of a word
                if (lowerName.startsWith("incorporated", i)) {
                    adjustedHostname.append("Inc");
                    i += "incorporated".length() - 1; // Skip the rest of the word
                } else if (lowerName.startsWith("limited", i)) {
                    if (lowerName.startsWith("limited liability company", i)) {
                        adjustedHostname.append("LLC");
                        i += "limited liability company".length() - 1; // Skip the rest of the word
                    } else {
                        adjustedHostname.append("Ltd");
                        i += "limited".length() - 1; // Skip the rest of the word
                    }
                } else if (name.charAt(i) == ' ') {
                    adjustedHostname.append('_'); // Replace space with underscore
                } else {
                    adjustedHostname.append(name.charAt(i)); // Copy the character as is
                }
            } else {
                if (name.charAt(i) == ' ') {
                    adjustedHostname.append('_'); // Replace space with underscore
                } else {
                    adjustedHostname.append(name.charAt(i)); // Copy the character as is
                }
            }
        }

        return adjustedHostname.toString();
    }

    // Helper method to count consonants
    private int countConsonants(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.toLowerCase().charAt(i);
            if ("bcdfghjklmnpqrstvwxyz".indexOf(ch) != -1) {
                count++;
            }
        }
        return count;
    }

    // Helper method to determine the path
    private String determinePath(String str) {
        int pairs = countVowelPairs(str);
        if (pairs == 0) {
            return "bio";
        } else if (pairs <= 3) {
            return "FAQ";
        } else {
            return "Glossary";
        }
    }

    // Helper method to count pairs of vowels
    private int countVowelPairs(String str) {
        int count = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            char ch1 = str.toLowerCase().charAt(i);
            char ch2 = str.toLowerCase().charAt(i + 1);
            if ("aeiou".indexOf(ch1) != -1 && "aeiou".indexOf(ch2) != -1) {
                count++;
            }
        }
        return count;
    }

    // New method to validate an array of URLs
    public boolean[] validateURLs(String[] urls) {
        boolean[] validities = new boolean[urls.length];

        for (int i = 0; i < urls.length; i++) {
            String url = urls[i].toLowerCase(); // Convert to lower case
            validities[i] = isValidURL(url);
        }

        return validities;
    }

    // Helper method to check if a URL is valid
    private boolean isValidURL(String url) {
	    // Optional: Remove the protocol part of the URL before validation
	    url = url.replaceAll("^(http://|https://)", "");

	    // Check if URL length is at least 6
	    if (url.length() < 6) {
	        System.out.println("Invalid URL (Failed length check): " + url);
	        return false;
	    }

	    // Check if URL contains "google"
	    if (!url.contains("google")) {
	        System.out.println("Invalid URL (Does not contain 'Google'): " + url);
	        return false;
	    }

	    // Extracting the hostname by splitting the URL at the first slash "/"
	    String hostname = url.split("/")[0];

	    // Check for valid hostname extension (.ie or .com)
	    if (!(hostname.endsWith(".ie") || hostname.endsWith(".com"))) {
	        System.out.println("Invalid URL (Invalid extension): " + url);
	        return false;
	    }

	    // Check for valid characters (letters, digits, hyphens, forward slashes, periods)
	    if (!url.matches("[a-zA-Z0-9-/.]+")) {
	        System.out.println("Invalid URL (Contains invalid characters): " + url);
	        return false;
	    }

	    // If all checks pass, the URL is valid
	    return true;
	}


    // Getter method for the generated URL
    public String getURL() {
        return compute();
    }
}
