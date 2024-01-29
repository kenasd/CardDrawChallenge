The code contains logic to compare two 5-card poker hands using a rank of the two hands by their strengths. 

The list of poker hands and their order can be found here:
https://en.wikipedia.org/wiki/List_of_poker_hands

Used: 
- Java 11
- Maven
- Git
- Jupiter 5.10.1

Ignored:
- jokers/wild cards

Run CLI:
1. `mvn clean package`
2. `java -cp target/CardDrawChallenge-1.0-SNAPSHOT.jar com/kenasd/poker/CardDrawApplication`

Run in IntelliJ IDEA:
1. chose a **src/main/java/com/kenasd/poker/CardDrawApplication.java** class
2. run it

Future improvements:
1. CardPattern: move logic by finding combinations to rules class
2. CardRank: simplify enum by extracting score logic
3. HandRankCalculator: use some DI to skip manual adding new combinations
4. HandParser: add error handling to validate input data
5. All classes: add proper documentations
6. All classes in model package: use Lombok or Record in Java 14+
7. Tests: cover all edge cases
8. Modify main class to have more interactive input process