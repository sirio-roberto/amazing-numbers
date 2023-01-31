package numbers;

import java.util.*;

public class MagicNumberAnalyzer {

    private Long number;
    private Long number2;
    List<String> properties = new ArrayList<>();


    public MagicNumberAnalyzer(Long number) {
        this.number = number;
    }

    public MagicNumberAnalyzer(Long number, Long number2) {
        this(number);
        this.number2 = number2;
    }

    public MagicNumberAnalyzer(long firstParameter, Long secondParameter, String... passedProperties) {
        this(firstParameter, secondParameter);
        Collections.addAll(this.properties, passedProperties);
    }

    boolean isDivisibleBy7() {
        return this.number % 7 == 0;
    }

    boolean endsWith7() {
        String stringNumber = String.valueOf(this.number);
        return stringNumber.charAt(stringNumber.length() - 1) == '7';
    }

    private boolean isBuzz() {
        return isDivisibleBy7() || endsWith7();
    }

    boolean isEven() {
        return this.number % 2 == 0;
    }

    private boolean isDuck() {
        String stringNumber = String.valueOf(this.number);
        return stringNumber.contains("0");
    }

    private boolean isPalindromic() {
        String stringNumber = String.valueOf(this.number);
        String reversedString = new StringBuilder(stringNumber).reverse().toString();
        return stringNumber.equals(reversedString);
    }

    private boolean isGapful() {
        String stringNumber = String.valueOf(this.number);
        if (stringNumber.length() < 3) {
            return false;
        }
        String firstConcatLastStr = stringNumber.charAt(0) + ""
                + stringNumber.charAt(stringNumber.length() - 1);
        long firstConcatLast = Long.parseLong(firstConcatLastStr);
        return this.number % firstConcatLast == 0;
    }

    private boolean isSpy() {
        String stringNumber = String.valueOf(this.number);
        int allSum = 0;
        int allProduct = 1;
        for (char c: stringNumber.toCharArray()) {
            int intChar = Integer.parseInt(c + "");
            allSum += intChar;
            allProduct *= intChar;
        }
        return allSum == allProduct;
    }

    private boolean isPerfectSquare() {
        double sqrtNumber = Math.sqrt(this.number);
        double truncatedSqrtNumber = (double) ((long) sqrtNumber);
        return sqrtNumber == truncatedSqrtNumber;
    }

    private boolean isSunny() {
        this.number++;
        boolean isPerfect = isPerfectSquare();
        this.number--;
        return isPerfect;
    }

    private boolean isJumping() {
        String stringNumber = String.valueOf(this.number);
        for (int i = 0; i < stringNumber.toCharArray().length - 1; i++) {
            if (Math.abs(stringNumber.charAt(i) - stringNumber.charAt(i + 1)) != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isHappy(long number) {
        String stringNumber = String.valueOf(number);
        if (stringNumber.length() < 2) {
            return stringNumber.charAt(0) == '1'
                    || stringNumber.charAt(0) == '7';
        }
        number = 0;
        for (char c : stringNumber.toCharArray()) {
            number += Math.pow(Character.getNumericValue(c), 2.0);
        }
        return isHappy(number);
    }

    private String generateLongToString() {
        Set<Long> resultSet = getResultSet();
        StringBuilder sb = new StringBuilder();
        for (long i: resultSet) {
            this.number = i;
            sb.append(i).append(" is ");
            if (isBuzz()) {
                sb.append("buzz, ");
            }
            if (isDuck()) {
                sb.append("duck, ");
            }
            if (isPalindromic()) {
                sb.append("palindromic, ");
            }
            if (isGapful()) {
                sb.append("gapful, ");
            }
            if (isSpy()) {
                sb.append("spy, ");
            }
            if (isPerfectSquare()) {
                sb.append("square, ");
            }
            if (isSunny()) {
                sb.append("sunny, ");
            }
            if (isJumping()) {
                sb.append("jumping, ");
            }
            if (isHappy(this.number)) {
                sb.append("happy, ");
            }
            if (!isHappy(this.number)) {
                sb.append("sad, ");
            }
            if (isEven()) {
                sb.append("even, ");
            } else {
                sb.append("odd, ");
            }
            sb.delete(sb.length() - 2, sb.length() - 1);
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    private Set<Long> getResultSet() {
        Set<Long> set = new LinkedHashSet<>();
        if (!this.properties.isEmpty()) {
            while (this.number2 > 0) {
                if (chosenFunction(this.properties)) {
                    set.add(this.number);
                    this.number2--;
                }
                this.number++;
            }
        } else  {
            this.number2 += this.number;
            while (this.number < this.number2) {
                set.add(this.number);
                this.number++;
            }
        }
        return set;
    }

    private boolean chosenFunction(List<String> properties) {
        boolean isChosenFunction = true;
        for (String property: properties) {
            isChosenFunction = switch (property) {
                case "buzz" -> isChosenFunction && isBuzz();
                case "-buzz" -> isChosenFunction && !isBuzz();
                case "duck" -> isChosenFunction && isDuck();
                case "-duck" -> isChosenFunction && !isDuck();
                case "palindromic" -> isChosenFunction && isPalindromic();
                case "-palindromic" -> isChosenFunction && !isPalindromic();
                case "gapful" -> isChosenFunction && isGapful();
                case "-gapful" -> isChosenFunction && !isGapful();
                case "spy" -> isChosenFunction && isSpy();
                case "-spy" -> isChosenFunction && !isSpy();
                case "square" -> isChosenFunction && isPerfectSquare();
                case "-square" -> isChosenFunction && !isPerfectSquare();
                case "sunny" -> isChosenFunction && isSunny();
                case "-sunny" -> isChosenFunction && !isSunny();
                case "jumping" -> isChosenFunction && isJumping();
                case "-jumping" -> isChosenFunction && !isJumping();
                case "happy" -> isChosenFunction && isHappy(this.number);
                case "-happy" -> isChosenFunction && !isHappy(this.number);
                case "sad" -> isChosenFunction && !isHappy(this.number);
                case "-sad" -> isChosenFunction && isHappy(this.number);
                case "even" -> isChosenFunction && isEven();
                case "-even" -> isChosenFunction && !isEven();
                case "odd" -> isChosenFunction && !isEven();
                case "-odd" -> isChosenFunction && isEven();
                default -> true;
            };
        }
        return isChosenFunction;
    }

    @Override
    public String toString() {
        if (number2 != null) {
            return generateLongToString();
        }
        return String.format("""
                Properties of %d
                        buzz: %b
                        duck: %b
                 palindromic: %b
                      gapful: %b
                         spy: %b
                      square: %b
                       sunny: %b
                     jumping: %b
                       happy: %b
                         sad: %b
                        even: %b
                         odd: %b""",
                this.number, isBuzz(), isDuck(), isPalindromic(), isGapful(), isSpy(),
                isPerfectSquare(), isSunny(), isJumping(), isHappy(this.number),
                !isHappy(this.number), isEven(), !isEven());
    }
}
