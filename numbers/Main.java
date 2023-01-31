package numbers;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MagicNumberAnalyzer analyzer;

        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);

        while (true) {
            System.out.print("Enter a request: ");
            String userInput = scan.nextLine();
            boolean isInputValid = validateUserInput(userInput);
            while (!isInputValid) {
                System.out.print("\nEnter a request: ");
                userInput = scan.nextLine();
                isInputValid = validateUserInput(userInput);
            }
            String[] inputFields = userInput.split(" ");
            long firstParameter = Long.parseLong(inputFields[0]);
            if (firstParameter == 0) {
                break;
            }
            if (inputFields.length > 1) {
                Long secondParameter = Long.parseLong(inputFields[1]);
                if (inputFields.length > 2) {
                    String[] properties = Arrays.copyOfRange(inputFields, 2, inputFields.length);
                    for (int i = 0; i < properties.length; i++) {
                        properties[i] = properties[i].toLowerCase();
                    }
                    analyzer = new MagicNumberAnalyzer(firstParameter, secondParameter, properties);
                } else {
                    analyzer = new MagicNumberAnalyzer(firstParameter, secondParameter);
                }
            } else {
                analyzer = new MagicNumberAnalyzer(firstParameter);
            }
            System.out.println();
            System.out.println(analyzer);
            System.out.println();
        }
        System.out.println("\nGoodbye!");
    }

    private static boolean validateUserInput(String userInput) {
        if (userInput == null || userInput.isBlank()) {
            return false;
        }
        String[] inputFields = userInput.split(" ");
        if (inputFields.length < 1) {
            return false;
        }
        if (!inputFields[0].matches("\\d+")) {
            System.out.println("\nThe first parameter should be a natural number or zero.");
            return false;
        }
        long firstParameter = Long.parseLong(inputFields[0]);
        if (firstParameter == 0) {
            return true;
        }
        if (firstParameter < 1) {
            System.out.println("\nThe first parameter should be a natural number or zero.");
            return false;
        }
        if (inputFields.length > 1) {
            long secondParameter = Long.parseLong(inputFields[1]);
            if (secondParameter < 1) {
                System.out.println("\nThe second parameter should be a natural number.");
                return false;
            }
            if (inputFields.length > 2) {
                Set<String> validProperties = new HashSet<>(
                        List.of("even", "odd", "buzz", "duck", "palindromic", "gapful",
                                "spy", "square", "sunny", "jumping", "happy", "sad",
                                "-even", "-odd", "-buzz", "-duck", "-palindromic",
                                "-gapful", "-spy", "-square", "-sunny", "-jumping", "-happy", "-sad"));

                Set<String> userProperties = new HashSet<>();
                Set<String> userPropertiesToCheckMutual = new HashSet<>();
                for (int i = 2; i < inputFields.length; i++) {
                    userProperties.add(inputFields[i].toLowerCase());
                    userPropertiesToCheckMutual.add(inputFields[i].toLowerCase());
                }
                userProperties.removeAll(validProperties);
                if (userProperties.size() > 0) {
                    List<String> upperValues = userProperties.stream().map(String::toUpperCase).toList();
                    StringBuilder sb = new StringBuilder("The ");
                    if (upperValues.size() > 1) {
                        sb.append("properties ").append(upperValues).append(" are wrong.");

                    } else {
                        sb.append("property ").append(upperValues).append(" is wrong.");
                    }
                    sb.append("\n").append("Available properties: ");
                    sb.append(validProperties.stream().map(String::toUpperCase).toList());
                    System.out.println(sb);
                    return false;
                }

                String[][] mutualExclusives = new String[][] {
                        {"square", "sunny"}, {"odd", "even"}, {"duck", "spy"}, {"happy", "sad"},
                        {"-odd", "-even"}, {"-duck", "-spy"}, {"-happy", "-sad"},
                        {"even", "-even"}, {"odd", "-odd"}, {"buzz", "-buzz"}, {"duck", "-duck"},
                        {"palindromic", "-palindromic"}, {"gapful", "-gapful"}, {"spy", "-spy"},
                        {"square", "-square"}, {"sunny", "-sunny"}, {"jumping", "-jumping"},
                        {"happy", "-happy"}, {"sad", "-sad"}
                };
                for (String[] array: mutualExclusives) {
                    List<String> list = Arrays.stream(array).toList();
                    if (userPropertiesToCheckMutual.containsAll(list)) {
                        System.out.println("The request contains mutually exclusive properties: "
                                + list.stream().map(String::toUpperCase).toList());
                        System.out.println("There are no numbers with these properties.");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
