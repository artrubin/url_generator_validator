# url_generator_validator
This Java application encompasses two primary functionalities: 1) It generates URLs based on user-entered company names, and 2) It assesses the validity of user-provided URLs against predefined criteria.


In a college project focused on Java, I was tasked with developing an application that dynamically generates a website URL based on a user-inputted company name. Additional functionality of this project was to allow user to input several URLs and validate them based on the pre-defined criteria.

PART 1

The core objective was to construct an application that manipulates three primary components of a URL: the "Protocol", "Hostname", and "Path", adhering to specific rules for each segment:

1. **Protocol Determination**:
   - Utilize "coap" if the company name includes "Meta" (case-insensitive), otherwise default to "ftp".

2. **Hostname Construction**:
   - Abbreviate "incorporated", "limited", and "limited liability company" to "Inc", "Ltd", and "LLC", respectively.
   - Replace spaces with underscores.
   - Append ".org" for hostnames with an odd count of consonants, and ".edu" for those with an even count.

3. **Path Generation**:
   - The path varies based on the count of adjacent vowel pairs in the company name, leading to "bio", "FAQ", or "Glossary".

For instance, inputting "Deloitte Touche Tohmatsu Limited" would result in the URL "ftp://Deloitte_Touche_Tohmatsu_Ltd.edu/FAQ".

The development was segmented into two main parts:

**Part 1 (URLGenerator Class):**
- This instantiable class was responsible for the URL generation logic, equipped with methods to set the company name, compute the URL based on the outlined rules, and retrieve the generated URL.

**Part 2 (URLGeneratorApp Application):**
- This application utilized the URLGenerator class, allowing users to input multiple company names and view the generated URLs. The loop continued until the user opted out by not entering "yes" when prompted for more entries.

PART2

In the second core part of the app, I had to Introduce a new method within the URLGenerator class, designed to accept a one-dimensional array of string URLs as an input parameter. This method evaluates the validity of each URL based on predefined criteria, categorizing them as either valid or invalid. The outcome will be articulated through an array of Boolean values, where 'true' denotes a valid URL and 'false' signifies an invalid one.

The validation criteria for determining a URL's validity include:
- The mandatory inclusion of "Google" within the hostname.
- Hostname extensions are limited to either ".ie" or ".com".
- A minimum character length of six.
- Acceptable characters confined to the letters a-z (case-insensitive), digits 0-9, hyphens ('-'), forward slashes ('/'), and periods ('.').

For instance, given an array of URLs such as ["https://Google.com/bin", "www.amazon.ie", "google", "google.com/12b", "goo"], the method will assess each URL against the validation criteria and return an array of Boolean values corresponding to the validity of each URL, in this case, [true, false, false, true, false].

It's imperative to note that the validation process is case-insensitive, allowing the method to convert all URLs to lowercase for consistent evaluation. This approach ensures that the validation remains unaffected by the case of the letters used in the URLs.

The implementation guidelines prohibited the use of abstract classes, threading, and exception handling. Additionally, comprehensive commenting was essential throughout the source code to illuminate critical functionalities and the rationale behind specific implementations.

This project demanded meticulous attention to detail and extensive commenting to elucidate the logic and functionality of the code, ensuring clarity and maintainability.
