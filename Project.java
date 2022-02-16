
/**
 *
 * @author riwakaram
 */
import javax.swing.JOptionPane;

public class Project {

    public static boolean verify(String password) {

        //This method checks whether the input password complies to the conditions
        //This loop searches for at least 1 digit in the password
        boolean digit = true;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                digit = true;
                break;
            } else {
                digit = false;
            }
        }

        return ((password.length() == 8) && (Character.isUpperCase(password.charAt(0))) && (digit == true));

    }

    public static char displayMenu() {

        //This method displays the menu and accepts a choice from the user
        String choiceS = JOptionPane.showInputDialog(null,
                "Game Entertainment"
                + "\n------------------"
                + "\nP - Player Name"
                + "\nL - Lottery Game"
                + "\nE - Exit", "Game Options", JOptionPane.INFORMATION_MESSAGE);

        return Character.toUpperCase(choiceS.charAt(0));

    }

    public static int reorderDigits(int num) {

        //This method puts the digits of an integer in a descending order from left to right
        //Example: 569 becomes 965
        int c = num % 10;
        int b = num / 10 % 10;
        int a = num / 100;

        int largest = Math.max(c, Math.max(a, b));
        int smallest = Math.min(c, Math.min(a, b));
        int middle = c + b + a - largest - smallest;

        return largest * 100 + middle * 10 + smallest;

    }

    public static void playGame(String playerName) {

        //This method processes the game
        //This loop generates a 3 digit number and the digits are distinct
        int num = 0;
        do {
            num = (int) ((Math.random() * 900) + 100);
            if (((num % 10) != (num % 100 / 10)) && ((num % 10) != (num / 100)) && ((num % 100 / 10) != (num / 100))) {
                break;
            }
        } while (true);

        //This loop accepts a input guess from the player and checks whether or not it is a 3 digit number
        JOptionPane.showMessageDialog(null, "Welcome " + playerName + "!", "$$$ Lottery $$$", JOptionPane.PLAIN_MESSAGE);
        int guess = 0;
        do {
            String playerGuess = JOptionPane.showInputDialog(null, "Enter a 3 digit number.", "Guess", JOptionPane.PLAIN_MESSAGE);
            if (playerGuess.length() == 3) {
                guess = Integer.parseInt(playerGuess);
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Your guess is not a 3 digit number.\nPlease try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (true);

        //This loop compares the player's guessed number with the generated number
        if (guess == num) {
            JOptionPane.showMessageDialog(null, "Congratulations " + playerName + "!!!\nYou won 10 000$", "Winner: First Prize", JOptionPane.PLAIN_MESSAGE);
        } else {
            num = reorderDigits(num);
            guess = reorderDigits(guess);

            if (num == guess) {
                JOptionPane.showMessageDialog(null, "Congratulations " + playerName + "!!!\nYou won 5 000$", "Winner: Second Prize", JOptionPane.PLAIN_MESSAGE);
            } else {
                int count = 0; //Counting the number of identical digits
                if (num % 10 == guess % 10) {
                    count++;
                }
                if (num / 10 % 10 == guess / 10 % 10) {
                    count++;
                }
                if (num / 100 == guess / 100) {
                    count++;
                }
                switch (count) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Congratulations " + playerName + "!!!\nYou won 1 000$", "Winner: Fourth Prize", JOptionPane.PLAIN_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Congratulations " + playerName + "!!!\nYou won 3 000$", "Winner: Third Prize", JOptionPane.PLAIN_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Better Luck next time " + playerName + "...", "You lost", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {

        //Asking the user to enter a password to enter the game
        String password = JOptionPane.showInputDialog(null,
                "Enter a password: "
                + "\nYour password must conform to the following conditions:"
                + "\n1. Be of 8 characters."
                + "\n2. Have the first letter as an uppercase letter."
                + "\n3. Include 1 numeric value.", "Password", JOptionPane.PLAIN_MESSAGE);

        //Calling method verify to check whether the input password complies to the conditions
        boolean checking = verify(password);

        //If the password complies, the user enters the game; else the game exits
        if (checking == true) {

            String name = "Anonymous";

            do {
                char choice = displayMenu();
                switch (choice) {
                    case 'P':
                        name = JOptionPane.showInputDialog(null, "Enter your full name:", "Player Name", JOptionPane.PLAIN_MESSAGE);
                        break;
                    case 'L':
                        playGame(name);
                        break;
                    case 'E':
                        JOptionPane.showMessageDialog(null, "You just exited the game.\nThank you for playing!\nCome back soon!", "Exit", JOptionPane.PLAIN_MESSAGE);
                        System.exit(0);
                    default:
                        JOptionPane.showMessageDialog(null, "The letter you inserted is not in the menu. Please try again", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }

            } while (true);

        } else {
            JOptionPane.showMessageDialog(null, "The password is incompatible with the conditions.",
                    "Error --> Exiting Game", JOptionPane.WARNING_MESSAGE);
        }

    }

}
