package Main.Assessment_PaymentP;

import Main.AccountP.Account;
import Others.System_Texts.Texts;

public class Payment extends Assessment{
  boolean paymentExits = false;

  public void paymentSelection(double totalAssessment){
    LINELENGTH.setChoiceInt(34);

    OUTER:
    do{
      String[] payment_menu = {"1. FULL PAYMENT", "2. PARTIAL PAYMENT", "3. CANCELL PAYMENT"};

      clear();
      newLinesHorizontalCustomCenteredTextln(16, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
      customHorizontalCenteredTextln("PAYMENT OPTIONS");
      customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

      for(String types : payment_menu) lnprintlnWithTabs_spaces(7, 10, types);

      lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

      try {
        lnprint_alignText(146, "ENTER CHOICE : ");
        int choice = Integer.parseInt(scan.nextLine());
        
          switch (choice) {
            case 1 -> paymentExits = fullPayment(totalAssessment, Account.getAccountBalance());
            case 2 -> paymentExits = partialPayment(totalAssessment);
            case 3 -> {
              lnCustomHorizontalCenteredText_animation(30, BACKTOMAINMENU.getMessage());
              threading(DEFAULT_DELAY.getChoiceInt());
              break OUTER;
            }

            default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
          }
      } catch (NumberFormatException e) {
        lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NUMBERFORMATEXCEPTION.getMessage());
      }

      threading(DEFAULT_DELAY.getChoiceInt());

    } while(!paymentExits);
  }

  private boolean fullPayment(double totalAssessment, float accountBalance){
    LINELENGTH.setChoiceInt(40);

    OUTER:
    do{
      clear();
      newLinesHorizontalCustomCenteredTextln(16, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
      customHorizontalCenteredTextln("FULL PAYMENT");
      customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

      lnHorizontalCustomCenteredTextln("TOTAL PAYMENT : " + totalAssessment);
      lnHorizontalCustomCenteredTextln("ACCOUNT BALANCE : " + accountBalance);
      lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

      String paymentDecide;
      if (accountBalance < totalAssessment) {
        lnCustomHorizontalCenteredText_animation(10, "INSUFFICIENT CASH! PLEASE ADD CASH IN YOUR ACCOUNT FIRST TO PROCEED.");
        paymentDecide = """
              "CHECK" for Checking the Account. """;
      } else paymentDecide = """
              "CONFIRM" to Fully Pay the Fee's. """;

      newLinesHorizontalCustomCenteredTextln(2, "Enter " + paymentDecide + " " + """
          "RETURN" to go back to Payment Options.""");
      lnprint_alignText(150, ARROW.getChoiceString());
      String paymentChoice = scan.nextLine();

      switch(paymentDecide.charAt(2) + paymentChoice){
        case "HCHECK" -> {

        }
        
        case "OCONFIRM" -> {
          enrolled();
          return true;
        }

        case "HRETURN", "ORETURN" -> {
          lnCustomHorizontalCenteredText_animation(30, "RETURN TO PAYMENT OPTION...");
          threading(DEFAULT_DELAY.getChoiceInt());
          break OUTER;
        }

        default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
      }

      threading(DEFAULT_DELAY.getChoiceInt());

    } while(true);

    return false;
  }

  private boolean partialPayment(double totalAssessment){
    double partialPayment = 0; //partialPayment was the only initialized since it was used on other function below.
    double interest;
    int payment_count;
    
    String[] countWordValuesOfNumbers = {"First", "Second", "Third", "Fourth", "Fifth"};
    String[] partialPayment_menu = {"TWO PAYMENT", "THREE PAYMENT", "FOUR PAYMENT", "FIVE PAYMENT", "RETURN TO PAYMENT OPTION"};

    LINELENGTH.setChoiceInt(40);

    do {
      clear();
      newLinesHorizontalCustomCenteredTextln(15, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
      customHorizontalCenteredTextln("PARTIAL PAYMENT");
      customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

      //this loop will print the String partialPaymentMenu with the integer choiceOfNumber in specific location.
      for(int i = 0; i < partialPayment_menu.length; i++) lnprintlnWithTabs_spaces(7, 6, (i + 1) + ". " + partialPayment_menu[i]);

      lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

      try{
        lnprint_alignText(138, "ENTER CHOICE : ");
        int choice = Integer.parseInt(scan.nextLine());

        //this condition will determine if the user wants to proceed to seeing the partial payment by gives.
        /*if the choice is greater than zero and the choice is also greater than 4, which is the choiceOfNumber.length - 1,
          then it will proceed in setting up variable.*/
        if(choice > 0 && choice <= (partialPayment_menu.length - 1)){
          interest = 0.01 * choice; //the choice will multiply to 0.01 (1%) for the interest.
          payment_count = 1 + choice; //also the choice will become the payment number. This variable sets how many times the user pays.
          // this values will use to get the partial payments by using the givesCalculation() that was used to the function below.
          
          paymentExits = userPartialPaymentShow(partialPayment_menu, countWordValuesOfNumbers, payment_count, interest, totalAssessment, partialPayment);
        } else if (choice == 5) { //by calling the paymentSelection(), it will make the system return to payment option.
          lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), "RETURN TO PAYMENT OPTION...");
          threading(DEFAULT_DELAY.getChoiceInt());
          return false;

        } else lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
        
      } catch(NumberFormatException e){
        lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NUMBERFORMATEXCEPTION.getMessage());
      }

      threading(DEFAULT_DELAY.getChoiceInt());

    } while (!paymentExits);

    return true;
  }

  private boolean userPartialPaymentShow(String[] partialPaymentMenu, String[] countWordValuesOfNumbers, int payment_count, double interest, double totalAssessment, double partialPayment){
    LINELENGTH.setChoiceInt(40);

    OUTER:
    do{
      clear();
      newLinesHorizontalCustomCenteredTextln(15, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
      customHorizontalCenteredTextln("PARTIAL PAYMENT - " + partialPaymentMenu[payment_count - 2]);
      customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

      for(int i = 0; i < payment_count; i++){
        partialPayment = givesCalculation(payment_count, interest, totalAssessment, i + 1);
        lnprintlnWithTabs_spaces(7, 10, countWordValuesOfNumbers[i] + " Payment : " + partialPayment);
      }

      lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

      lnHorizontalCustomCenteredTextln("""
          Type "READ" to show the Payment Procedure. "RETURN" to go Back to Partial Payment.""");
      lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt() - 20, DEFAULT_NO_COLUMNS.getChoiceInt() - 6, ARROW.getChoiceString());
      String choice = scan.nextLine();

      switch (choice) {
        case "READ" -> {
          return partialPaymentConfirmation();
        }
        
        case "RETURN" -> {
          lnCustomHorizontalCenteredText_animation(30, "RETURN TO PARTIAL PAYMENT MENU...");
          threading(DEFAULT_DELAY.getChoiceInt());
          break OUTER;
        }

        default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
      }

      threading(DEFAULT_DELAY.getChoiceInt());

    } while (true);

    return false;
  }

  private boolean partialPaymentConfirmation(){
    OUTER:
    do{
      clear();
      Texts.showTexts("partialPaymentProcedure");

      newLinesHorizontalCustomCenteredTextln(2, """
          Type "CONFIRM" to be Enrolled. "CANCEL" to go back to Partial Payment Options.""");
          
      lnprint_alignText(DEFAULT_NO_COLUMNS.getChoiceInt() - 6, ARROW.getChoiceString());
      String choice = scan.nextLine();

      if (choice.isBlank() || choice.isEmpty()) lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NEEDTOFILLUP.getMessage());
      else{
        switch (choice) {
          case "CONFIRM" -> {
            enrolled();
            return true;
          }
          
          case "CANCEL" -> {
            lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), "RETURN TO PARTIAL PAYMENT OPTIONS...");
            threading(LONG_DELAY.getChoiceInt());
            break OUTER;
          }

          default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
        }
      }
        
      threading(DEFAULT_DELAY.getChoiceInt());
      
    } while(true);

    return false;
  }

  private void enrolled(){
    clear();
    newLinesCustomHorizontalCenteredText_animation(20, DEFAULT_ANIMATION_SPEED.getChoiceInt() + 40, "YOU SUCCESSFULLY ENROLLED!");
    newLinesCustomHorizontalCenteredText_animation(3, DEFAULT_ANIMATION_SPEED.getChoiceInt() + 10, """
    Enter Any Character to Return to Main Menu.""");
    scan.nextLine();
  }

  private double givesCalculation(int payment_count, double interest, double totalAssessment, int paymentCount){
    double partialAssessment;
    partialAssessment = (totalAssessment / payment_count) + (totalAssessment * interest / paymentCount);
    return partialAssessment;
  }
}
