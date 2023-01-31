# amazing-numbers

With this project you can input some numbers and check if they are special, based on 12 different properties. You can even get a range of numbers with a speficic or a set of specific properties by specifying the initial value. For example, if the user would like to get 5 numbers starting from 1 that are even, sunny, happy, are not duck and are not gapful, the program will show:

Enter a request: 1 5 even sunny happy -duck -gapful

             3,968 is even, sunny, happy
            34,224 is even, sunny, happy
            75,624 is even, sunny, happy
           134,688 is even, sunny, happy
           178,928 is even, sunny, happy
           
Property definition:
  - Spy number: a number is said to be Spy if the sum of all digits is equal to the product of all digits.
  - Duck number: a Duck number is a positive number that contains zeroes. For example, 3210, 8050896, 70709 are Duck numbers. Note that a number with a leading 0 is not a Duck number. So, numbers like 035 or 0212 are not Duck numbers.
  - Happy number: is a number that reaches 1 after a sequence during which the number is replaced by the sum of each digit squares. For example, 13 is a happy number, as 12 + 32 = 10 which leads to 12 + 02 = 1. On the other hand, 4 is not a happy number because the sequence starts with 42 = 16, 12 + 62 = 37, and finally reaches 22 + 02 = 4.
  - Sad number: as the name explains, a number that is not happy.
  - Jumping number: is a number whose the adjacent digits inside it differ by 1. The difference between 9 and 0 is not considered as 1. Single-digit numbers are considered Jumping numbers. For example, 78987, and 4343456 are Jumping numbers, but 796 and 89098 are not.
  - Buzz numbers: are numbers that are either divisible by 7 or end with 7.
  - Palindromic number: is a symmetrical number; in other words, it stays the same regardless of whether we read it from left or right. For example, 17371 is a palindromic number. 5 is also a palindromic number. 1234 is not. If read it from right, it becomes 4321.
  - Gapful numbers: is a number that contains at least 3 digits and is divisible by the concatenation of its first and last digit without a remainder. 12 is not a Gapful number, as it has only two digits. 132 is a Gapful number, as 132 % 12 == 0. Another good example of a Gapful number is 7881, as 7881 % 71 == 0.
  - Sunny and square: N is a sunny number if N+1 is a perfect square number. In mathematics, a square number or a perfect square is an integer that is the square of an integer; in other words, it is the product of an integer with itself. For example, 9 is a square number, since it equals 32 and can be written as 3×3.
