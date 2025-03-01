# Calculating digits of π - A Java implementation of the Chudnovsky algorithm
### ( [Proof 1](https://arxiv.org/pdf/1809.00533) | [Proof 2](https://arxiv.org/pdf/2003.06668) | [Wikipedia](https://arxiv.org/pdf/1809.00533) )

## Building
This is a Java/Gradle based application. Build using the following command
```
gradlew build     # Build on a Windows machine

./gradlew build   # Build on a MacOS/Linux machine
```
now navigate...
```
cd build/libs     # Output directory of built jar, should contain pi-calc-chudnovsky-{version}.jar
```
## Running
This application accepts a single command-line parameter in the form of a positive integer that represents the desired number of digits of pi to calculate. The default value (because it should be quite quick on most modern machines) is set to 1,000 digits
```
java -jar pi-calc-chudnovsky-REPLACE_WITH_VERSION.jar 1000000   # Command for finding one million digits
```
## Output
The result of running the application should be a file named pi_[NUMBER_OF_DIGITS_CALCULATED].txt at the same path of where the jar was executed.

| Digits of π                                                                                                                        | File size (be careful) | Time to calculate<br/>(Ryzen 7 2700X) | First 10 ... Last 10 Digits |
|------------------------------------------------------------------------------------------------------------------------------------|------------------------|---------------------------------------|-----------------------------|
| [1,000](https://raw.githubusercontent.com/the-special-8421/pi-calc-chudnovsky/refs/heads/main/pi_generated/pi_1000.txt)            | 1 KB                   | < 1s                                  | 3.1415926535...2164201989   |
| [10,000](https://raw.githubusercontent.com/the-special-8421/pi-calc-chudnovsky/refs/heads/main/pi_generated/pi_10000.txt)          | 10 KB                  | ~ 1s                                  | 3.1415926535...5256375678   |
| [100,000](https://raw.githubusercontent.com/the-special-8421/pi-calc-chudnovsky/refs/heads/main/pi_generated/pi_100000.txt)        | 98 KB                  | 3s                                    | 3.1415926535...5493624646   |
| [1,000,000](https://raw.githubusercontent.com/the-special-8421/pi-calc-chudnovsky/refs/heads/main/pi_generated/pi_1000000.txt)     | 977 KB                 | 61s                                   | 3.1415926535...5779458151   |
| [10,000,000](https://raw.githubusercontent.com/the-special-8421/pi-calc-chudnovsky/refs/heads/main/pi_generated/pi_10000000.txt)   | 9.53 MB                | 42m 54s (2574s)                       | 3.1415926535...5348955897   |
| [100,000,000](https://raw.githubusercontent.com/the-special-8421/pi-calc-chudnovsky/refs/heads/main/pi_generated/pi_100000000.txt) | 95.3 MB                | 21hr 44m 15s (78255s)                 | 3.1415926535...0187751592   |

