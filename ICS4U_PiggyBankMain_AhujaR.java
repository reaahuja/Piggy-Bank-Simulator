import java.util.*;
class PiggyBank2 {
    int quarters;
    int nickels;
    int dimes;
    int cents;
    public PiggyBank2(){
        quarters = 0;
        nickels = 0;
        dimes = 0;
        cents = 0;
    }


    public void addMoney(int coin25, int coin10, int coin5, int coin1){
        quarters += coin25;
        nickels += coin10;
        dimes += coin5;
        cents += coin1;
        System.out.println("Total number of quarters: " + quarters);
        System.out.println("Total number of nickels: " + nickels);
        System.out.println("Total number of dimes: " + dimes);
        System.out.println("Total number of cents: " + cents);
    }

    public int totalMoney(){
        int total = (quarters*25) + (nickels*10) + (dimes*5) + (cents*1);
        System.out.println("Total amount of money in piggy bank is: " + total + " cents");
        return total;
    }

    public String withDrawMoney(int withDraw){
        int total = totalMoney();

        int newQuarters = quarters;
        int newNickels = nickels;
        int newDimes = dimes;
        int newCents = cents;

        while (withDraw != 0){
            if (quarters > 0 && (withDraw - 25 >= 0)){
                total = total - 25;
                quarters = quarters - 1;
                withDraw = withDraw - 25;
            }else if (nickels > 0 && (withDraw - 10 >= 0)){
                total = total - 10;
                nickels = nickels - 1;
                withDraw = withDraw - 10;
            }else if (dimes > 0 && (withDraw - 5 >= 0)){
                total = total - 5;
                dimes = dimes - 1;
                withDraw = withDraw - 5;
            }else if(cents > 0 && (withDraw - 1 >= 0)){
                total = total - 1;
                cents = cents - 1;
                withDraw = withDraw - 1;
            }else{
                newQuarters = newQuarters - quarters;
                newNickels = newNickels - nickels;
                newDimes = newDimes - dimes;
                newCents = newCents - cents;

                System.out.println(newQuarters + " quarters, " + newNickels + " nickels, " + newDimes + " dimes, and " + newCents + " cents have been withdrawn");
                return "More money cannot be withdrawn " + "\nNew total: " + total;
            }
        }

        newQuarters = newQuarters - quarters;
        newNickels = newNickels - nickels;
        newDimes = newDimes - dimes;
        newCents = newCents - cents;


        System.out.println(newQuarters + " quarters, " + newNickels + " nickels, " + newDimes + " dimes, and " + newCents + " cents have been withdrawn");
        return "Money has been withdrawn " + "\nNew total: " + total;

    }

    public static void withDrawCoin(String coinType, int coinNumber){
        //ADD CODE
    }


}

/* Author: Rea Ahuja */
/* Date: October 23rd, 2021 */
/* Description: This program acts as a virtual bank that contains a verification process, allows the user to create multiple
piggy banks, and it allows the user to deposit money and withdraw money from any of the piggy banks.
 */
public class ICS4U_PiggyBankMain_AhujaR {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //user is greeted and told what the program is
        System.out.println("Hello and welcome to the bank! To enter, you must confirm that you are human and pass the verification process!");
        //user is given information about the verification process
        System.out.println("To pass, both of your answers for the verification questions must be correct! ");
        //user is asked first question and is given a hint
        System.out.println("First Question, what does the following text represent?: :) ");
        System.out.println("(Hint: Input 1 word that begins with the character s)");
        String smile = input.next();
        //user is asked second question and is given a hint
        System.out.println("Second Question, what does the following text represent?: :( ");
        System.out.println("(Hint: Input 1 word that begins with the character f) ");
        String frown = input.next();
        /*if the user's answers for the first verification question and second verification question are correct
        they are allowed to interact the rest of the banking program. Else their access is denied and the program ends.
         */
        if (smile.equals("smile") && frown.equals("frown")){
            //user's access has been granted as both of their answers for the verification questions are correct
            System.out.println("Access granted, you may enter the bank");
            int option, menuOption;
            int selectedPiggyBank = 0;
            //user is asked about how many piggy banks they would like to add
            System.out.println("How many piggy banks would you like to add?");
            int pigBankNumber = input.nextInt();
            //an object array is intialized with their input
            PiggyBank[] piggyBankArray = new PiggyBank[pigBankNumber];
            //the piggy bank objects are created
            for(int i = 0; i < pigBankNumber; i++) {
                piggyBankArray[i] = new PiggyBank();
            }
            //user is asked if they would like to fill each piggy bank right now or after
            System.out.println("Would you like to fill each piggy bank now, or leave them empty? (1 or 2 respectively)");
            option = input.nextInt();
            /*if the user enters 1, then the depositCoins method is called on and the user is asked how many of each
            coins they would like to deposit for each of the piggy bank objects*/
            if(option == 1){
                for(int i = 0; i < pigBankNumber; i++) {
                    System.out.println("Enter coins for piggy bank number " + (i+ 1) );
                    int[] depositedMoney = depositCoins();
                    //the values that the user entered are passed to the addMoney method as parameters
                    piggyBankArray[i].addMoney(depositedMoney[0], depositedMoney[1], depositedMoney[2], depositedMoney[3]);
                    //the total amount of money for the piggy banks is displayed to the user
                    piggyBankArray[i].totalMoney();
                }
            }
            /*while the user does not enter 0, which is the numeric option to exit the bank the while loop will keep running
            and the user will be able to deposit or withdraw money in their piggy banks
             */
            while (option != 0){
                //user is presented with the main menu
                System.out.println("Main Menu:");
                //user is given the options to deposit, withdraw or exit the program
                System.out.println("Deposit (1), Withdraw(2) or Exit (0) ");
                option = input.nextInt();
                menuOption = option;
                /*if the user does not enter 0, and their option is equal to either deposit or withdraw the program will ask
                them which piggy bank they would like to deposit money or withdraw money from
                 */
                if (menuOption != 0 && (menuOption == 1 || menuOption == 2)) {
                    //program asks the user which piggy bank they would like to interact with
                    System.out.println("Which piggybank would you like to use?" +
                            "\nInput from 1 to " + (piggyBankArray.length));
                    option = input.nextInt();
                    /*if the user does not enter an option between the given range, the program will enter the while loop
                    and the user will be asked again and again until the input is in the correct range given
                     */
                    while(option < 1 || option > piggyBankArray.length) {
                        System.out.println("Invalid option! Input a value between 1 to " + (piggyBankArray.length));
                        option = input.nextInt();
                    }
                    selectedPiggyBank = option;
                    //the variable selectedPiggyBank is equal to the piggy bank that the user want's to interact with
                }
                /* if the user chooses 1 for the menu, which is the numeric option of depositing money, the program will
                enter the following conditional statement
                 */
                if (menuOption == 1){
                    //program lets the user know that they have chosen the depositing money option
                    System.out.println("Depositing money option chosen");
                    //user is asked how many of each type of coins they would like to deposit through depositCoins() method
                    //array returned from depositCoins() method is equal to the depositedMoney array
                    int[] depositedMoney = depositCoins();
                    /*elements in the depositedMoney array are passed as parameters to the addMoney method for the given
                    piggy bank object (the piggy bank object that the user chose to interact with)
                     */
                    piggyBankArray[selectedPiggyBank-1].addMoney(depositedMoney[0], depositedMoney[1], depositedMoney[2], depositedMoney[3]);
                    /* totalMoney() method is called on to display the total amount of money in the bank after the money
                    has been deposited for the chosen piggy bank object
                     */
                    piggyBankArray[selectedPiggyBank-1].totalMoney();

                /* if the user chooses 2 for the menu, which is the numeric option of withdrawing money, the program will
                enter the following conditional statement.
                 */
                }else if (menuOption == 2){

                    //program lets the user know that they have chose to withdraw money
                    System.out.println("Withdrawing money option chosen");
                    //user is asked if they would like to withdraw a specific type of coin or a certain amount
                    System.out.println("Would you like to withdraw a specific coin or a certain amount?");
                    System.out.println("A specific coin (1) or Amount (2)");
                    int secondOption = input.nextInt();
                    /*if the user enters 1, which is the numeric option for withdrawing a specific coin, program enters the
                    following conditional statement
                     */
                    if (secondOption == 1){
                        //user is asked which coin and how many of those coins they would like to withdraw
                        System.out.println("Which coin would you like to withdraw?");
                        String coinType = input.next();
                        coinType = coinType.toLowerCase(Locale.ROOT);
                        System.out.println("How many of these coins would you like to withdraw?");
                        int coinNumber = input.nextInt();

                        /*the values entered by the user are passed to the withDrawCoin method as parameters for the chosen
                        piggy bank object
                         */
                        piggyBankArray[selectedPiggyBank-1].withDrawCoin(coinType, coinNumber);

                        /*if the user chose 2, which is the numeric option for withdrawing a specific amount the program
                        will enter the following conditional statement
                         */
                    }else if(secondOption == 2){
                        //user is asked about how much money they would like to withdraw
                        System.out.println("How much money would you like to withdraw: ");
                        int withdrawMoney = input.nextInt();
                        /*the value that the user entered is passed to the withDrawMoney method as a parameter for the chosen
                        piggy bank object
                         */
                        /*additionally, this line prints certain text (based on if the user asked for less or more money
                        than the total amount of money in the piggy bank) and what the new total is after withdrawing the
                        money
                         */
                        System.out.println(piggyBankArray[selectedPiggyBank-1].withDrawMoney(withdrawMoney));

                     /* if the user enters a number aside from 1 or 2, their choice is invalid and the program lets them know*/
                    }else{
                        System.out.println("Invalid choice! ");
                    }
                /* if the user enters the value 0, the program will enter the following conditional statement, let the user
                know that the program now ends, and the program will exit the while loop and end */
                }else if (menuOption == 0){
                    System.out.println("Program ends");
                /* if the user enters an option that is not 0, 1 or 2, their choice is invalid. The program will let the user
                know that their choice is invalid, and they will be asked for an input again.
                 */
                }else{
                    System.out.println("Invalid choice!");
                }


            }
        /* if the user gets both or even one of the verification questions wrong, they will not be able to interact with
        the rest of the program, and the program will come to an end.
         */
        }else{
            System.out.println("Access denied, you may not enter the bank");
        }

    }

    /* Pre and Post: This method does not recieve any parameters, but it does return the array coins */
    /* Function: this method allows the user to enter how many number of coins they would like to deposit for each
     of the type of coins */
    public static int[] depositCoins(){
        Scanner input = new Scanner(System.in);
        int[] coins = new int[4];
        /*The user is asked how many quarters they would like to deposit and their input is saved as the first element in the
        coins array */
        System.out.println("How many quarters would you like to input: ");
        int coin25 = input.nextInt();
        coins[0] = coin25;
        /*The user is asked how many nickels they would like to deposit and their input is saved as the second element in the
        coins array
         */
        System.out.println("How many nickels would you like to input: ");
        int coin10 = input.nextInt();
        coins[1] = coin10;
        /*The user is asked how many dimes they would like to deposit and their input is saved as the third element in the
        coins array
         */
        System.out.println("How many dimes would you like to input: ");
        int coin5 = input.nextInt();
        coins[2] = coin5;
        /*The user is asked how many cents they would like to deposit and their input is saved as the fourth element in the
        coins array
         */
        System.out.println("How many cents would you like to input: ");
        int coin1 = input.nextInt();
        coins[3] = coin1;
        /* the coins array is returned to the main method */
        return coins;
    }

}