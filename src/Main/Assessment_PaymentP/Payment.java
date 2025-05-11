package Main.Assessment_PaymentP;

import Main.AccountP.Account;
import Main.CourseP.CourseUI;
import Others.System_Texts.Texts;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Payment extends Assessment{
  List<String> paymentInformations = new ArrayList<>();

  boolean isPaymentExits;

  public void paymentSelection(double totalAssessmentFee){
    isPaymentExits = false;

    paymentInformations.add("Payment Type");

    OUTER:
    do{
      LINELENGTH.setValueInt(34);
      String[] payment_menu = {"FULL PAYMENT", "PARTIAL PAYMENT", "CANCELL PAYMENT"};

      clear();
      newLinesHorizontalCenteredTextln(16,"=".repeat(LINELENGTH.getValueInt()));
      horizontalCenteredTextln("PAYMENT OPTIONS");
      horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

      for(int i = 0; i < payment_menu.length; i++) lnprintlnWithTabs_spaces(7, 10, (i + 1) + ". " + payment_menu[i]);

      lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

      try {
        lnprint_alignText(146, "ENTER CHOICE : ");
        int choice = Integer.parseInt(scan.nextLine());

        if (choice > 0 && choice <= 2){
          setDirectory("data\\Payments"); //create a folder for the transaction data file.
          paymentInformations.add(payment_menu[choice - 1]); //set the first index of the array for file writing. This will become the payment type.
        }

        switch (choice) {
          case 1 -> fullPaymentSelection_menu(totalAssessmentFee);
          case 2 -> partialPayment(totalAssessmentFee);
          case 3 -> {
            paymentInformations.clear();
            STUDENT_INFO_FILE.delete();
            ASSESSMENT_FILE.delete();
            lnHorizontalCenteredText_animation(30, BACKTOMAINMENU.getMessage());
            threading(DEFAULT_DELAY.getValueInt());
            break OUTER;
          }

          default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
        }
      } catch (NumberFormatException e) {
        lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
      }

      threading(DEFAULT_DELAY.getValueInt());
    } while(!isPaymentExits);
  }

  private void fullPaymentSelection_menu(double totalAssessmentFee){
    String[] fullPayment_menu = {"CASH ON REGISTRAR", "ACCOUNT BALANCE", "RETURN PAYMENT OPTION"};
    String str_totalPayment = "TOTAL PAYMENT : " + BigDecimal.valueOf(totalAssessmentFee);

    OUTER:
    do {
      LINELENGTH.setValueInt(50);

      clear();
      newLinesHorizontalCenteredTextln(16, "-".repeat(LINELENGTH.getValueInt()));
      horizontalCenteredTextln("FULL PAYMENT OPTION");
      horizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

      for(int i = 0; i < fullPayment_menu.length; i++) lnprintlnWithTabs_spaces(7, 6, (i + 1) + ". " + fullPayment_menu[i]);
      
      lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

      try{
        enterChoice();
        int choice = Integer.parseInt(scan.nextLine());

        if (choice > 0 && choice <= 2) paymentInformations.add(fullPayment_menu[choice - 1]); //add the type of fullpayment in the linked list.
        
        switch (choice) {
          case 1 -> fullPayment_cash(totalAssessmentFee, str_totalPayment);
          case 2 -> fullPayment_account(totalAssessmentFee, str_totalPayment);
          case 3 -> {
            paymentInformations.removeLast();
            returnTo("paymentOption");
            break OUTER;
          }
        }

      } catch(NumberFormatException e) {
        lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
      }

      threading(DEFAULT_DELAY.getValueInt());
    } while (!isPaymentExits);
  }

  private void fullPayment_cash(double totalAssessmentFee, String str_totalPayment){
    LINELENGTH.setValueInt(40);

    do{
      clear();
      fullPaymentHeader("CASH"); //type of the fullPayment (Cash)
      lnHorizontalCenteredTextln(str_totalPayment);
      lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

      try{
        lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getValueInt() - 20, DEFAULT_NO_COLUMNS.getValueInt() - 6, "ENTER AMOUNT (0 == Back) : ");
        double cash_ammount = Double.parseDouble(scan.nextLine());
        
        if (cash_ammount == 0) {
          returnTo("fullPaymentOption");
          break;
        }

        if (cash_ammount < totalAssessmentFee) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "THE AMOUNT THAT YOU ENTER IS TOO LOW!");
        else{
          clear();
          newLinesHorizontalCenteredTextln(18, "ONCE YOU HAVE PAID THE REQUIRED FEE TO THE REGISTRAR, YOU WILL RECEIVE A PIN TO ENTER FOR TRANSACTION CONFIRMATION.");
          typeAnyCharacter("TO CONTINUE");

          pinGenerate_toFile(totalAssessmentFee, cash_ammount, str_totalPayment);
          break;
        }

      } catch(NumberFormatException e){
        lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), MESSAGE.invalidInputModifier("AMOUNT OF CASH"));
      }

      threading(DEFAULT_DELAY.getValueInt());
    } while(true);
  }

  private void pinGenerate_toFile(double totalAssessmentFee, double cash_ammount, String str_totalPayment){
    int pin = new Random().nextInt(90000) + 10000;

    String[] detailsPayment = {String.valueOf(accountNo), String.valueOf(totalAssessmentFee), String.valueOf(cash_ammount), String.valueOf(calculateReceipt(totalAssessmentFee, cash_ammount)), String.valueOf(pin)};

    try {
      try(BufferedWriter writer = new BufferedWriter(new FileWriter(FULLPAYMENT_TRANSACTION_FILE, true))){
        for(String details : detailsPayment) writer.append(details + ":");
        writer.newLine();
      }
    } catch (IOException e) {
    }

    fullPayment_cashConfirmation(totalAssessmentFee, cash_ammount, str_totalPayment, pin);
  }
  
  private void fullPayment_cashConfirmation(double totalAssessmentFee, double cash_ammount, String str_totalPayment, int pin){
    String[] cash_details = {str_totalPayment, "AMOUNT : " + cash_ammount, "CHANGE : " + calculateReceipt(totalAssessmentFee, cash_ammount)};

    do {
        clear();
        fullPaymentHeader("CASH");
        for (String details : cash_details) lnprintlnWithTabs_spaces(7, 10, details);
        lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

        try{
          lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 6, "ENTER THE PIN (0 == Cancel) : ");
          int inputPin = Integer.parseInt(scan.nextLine());

          if (inputPin == 0) {
            returnTo("pinConfirmationCancel");
            break;
          }

          if (inputPin == pin) {
            new Account().showTransactionReciept("FULL PAYMENT (CASH)", cash_details, null, false);
            enrolled();
            break;
          }else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "WRONG PIN!");
          
        } catch(NumberFormatException e){
          lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
        }

        threading(DEFAULT_DELAY.getValueInt());
    } while (true);
  }

  private double calculateReceipt(double totalAssessmentFee, double cash_ammount){
    return cash_ammount - totalAssessmentFee;
  }

  private void fullPayment_account(double totalAssessmentFee, String str_totalPayment){
    LINELENGTH.setValueInt(40);

    account = new Account();
    
    OUTER:
    do{
      clear();

      fullPaymentHeader("ACCOUNT BALANCE");

      lnHorizontalCenteredTextln(str_totalPayment);
      lnHorizontalCenteredTextln("ACCOUNT BALANCE : " + account.getAccountBalance());
      lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

      String paymentDecide;
      if (account.getAccountBalance().doubleValue() < totalAssessmentFee) {
        lnHorizontalCenteredText_animation(SHORT_ANIMATION_SPEED.getValueInt(), "INSUFFICIENT BALANCE! PLEASE ADD FUNDS IN YOUR ACCOUNT FIRST TO PROCEED.");
        paymentDecide = """
              "ADD" to Add Funds (Top Up) to the Account. """;
      } else paymentDecide = """
              "CONFIRM" to Fully Pay the Fee. """;

      newLinesHorizontalCenteredTextln(2, "Enter " + paymentDecide + " " + """
          "RETURN" to go back to Full Payment Option.""");
      lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 6, ARROW.getValueString());
      
      String paymentChoice = scan.nextLine();

      switch(paymentDecide.charAt(2) + paymentChoice.toUpperCase()){
        case "DADD" -> account.addFunds_menu("FULL PAYMENT OPTION");
        case "OCONFIRM" -> {
          account.setAccountBalance(account.getAccountBalance().subtract(BigDecimal.valueOf(totalAssessmentFee)));
          account.fileWrite();
          enrolled();
          break OUTER;
        }

        case "DRETURN", "ORETURN" -> {
          returnTo("fullPaymentOption");
          break OUTER;
        }

        default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
      }

      threading(DEFAULT_DELAY.getValueInt());

    } while(true);
  }

  private void fullPaymentHeader(String type){
    newLinesHorizontalCenteredTextln(16,"=".repeat(LINELENGTH.getValueInt()));
    horizontalCenteredTextln("FULL PAYMENT " + type);
    horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));
  }

  private void partialPayment(double totalAssessment){
    String[] countWordValuesOfNumbers = {"First", "Second", "Third", "Fourth", "Fifth"};
    String[] partialPayment_menu = {"TWO PAYMENT", "THREE PAYMENT", "FOUR PAYMENT", "FIVE PAYMENT", "RETURN TO PAYMENT OPTION"};

    do {
      LINELENGTH.setValueInt(40);

      clear();
      newLinesHorizontalCenteredTextln(15,"=".repeat(LINELENGTH.getValueInt()));
      horizontalCenteredTextln("PARTIAL PAYMENT");
      horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

      //this loop will print the String partialPaymentMenu with the integer choiceOfNumber in specific location.
      for(int i = 0; i < partialPayment_menu.length; i++) lnprintlnWithTabs_spaces(7, 6, (i + 1) + ". " + partialPayment_menu[i]);

      lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

      try{
        lnprint_alignText(138, "ENTER CHOICE : ");
        int choice = Integer.parseInt(scan.nextLine());

        /*this condition will determine if the user wants to proceed to seeing the partial payment by count of payment.
        if the choice is greater than zero and the choice is also greater than 4, which is the choiceOfNumber.length - 1, then it will proceed in setting up variable.
        Also the choice will become the payment number. This variable sets how many times the user pays.
        Values will use to get the partial payments by using the givesCalculation() that was used to the function below.
        the choice will multiply to 0.01 (1%) for the interest. */

        if(choice > 0 && choice <= (partialPayment_menu.length - 1)) userPartialPaymentShow(partialPayment_menu, countWordValuesOfNumbers, choice + 1, 0.01 * choice, totalAssessment, new double[choice + 1]);
        else if (choice == 5) { //by calling the paymentSelection(), it will make the system return to payment option.
          paymentInformations.removeLast();
          returnTo("paymentOption");
          break;
        } else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
        
      } catch(NumberFormatException e){
        lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
      }

      threading(DEFAULT_DELAY.getValueInt());

    } while(!isPaymentExits);
  }

  private void userPartialPaymentShow(String[] partialPaymentMenu, String[] countWordValuesOfNumbers, int payment_count, double interest, double totalAssessmentFee, double[] partialPayment){
    LINELENGTH.setValueInt(40);

    OUTER:
    do{
      clear();
      newLinesHorizontalCenteredTextln(14,"=".repeat(LINELENGTH.getValueInt()));
      horizontalCenteredTextln("PARTIAL PAYMENT - " + partialPaymentMenu[payment_count - 2]);
      horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

      for(int i = 0; i < partialPayment.length; i++){
        partialPayment[i] = partialPaymentCalculation(payment_count, interest, totalAssessmentFee, i + 1);
        lnprintlnWithTabs_spaces(7, 10, countWordValuesOfNumbers[i] + " Payment : " + partialPayment[i]);
      }

      lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));
      lnHorizontalCenteredTextln("""
          Type "READ" to show the Payment Procedure. "RETURN" to go Back to Partial Payment.""");
      lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getValueInt() - 20, DEFAULT_NO_COLUMNS.getValueInt() - 6, ARROW.getValueString());

      String choice = scan.nextLine();

      if(choice.isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
      else{
        switch (choice.toUpperCase()) {
          case "READ" -> {
            paymentInformations.add(partialPaymentMenu[payment_count - 2]);
            partialPaymentConfirmation(partialPaymentMenu[payment_count - 2], partialPayment);
            break OUTER;
          }
          case "RETURN" -> {
            returnTo("partialPaymentOption");
            break OUTER;
          }

          default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
        }
      }

      threading(DEFAULT_DELAY.getValueInt());
    } while(true);
  }

  private double partialPaymentCalculation(int payment_count, double interest, double totalAssessmentFee, int paymentCount){ //calculates partial payment choice by the user
    return (totalAssessmentFee / payment_count) + (totalAssessmentFee * interest / paymentCount);
  }

  private void partialPaymentConfirmation(String title, double[] partialPayment){
    OUTER:
    do{
      clear();
      Texts.showTexts("partialPaymentProcedure");

      newLinesHorizontalCenteredTextln(2, """
          Type "CONFIRM" to be Enrolled. "CANCEL" to go back to Partial Payment Options.""");
          
      lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 6, ARROW.getValueString());
      String choice = scan.nextLine();

      if (choice.isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
      else{
        switch (choice.toUpperCase()) {
          case "CONFIRM" -> {
            fileWrite_partialPayment(title, partialPayment);
            enrolled();
            break OUTER;
          }
          
          case "CANCEL" -> {
            paymentInformations.removeLast();
            returnTo("partialPaymentOption");
            break OUTER;
          }

          default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
        }
      }
        
      threading(DEFAULT_DELAY.getValueInt());
    } while(true);
  }

  public void fileWrite_partialPayment(String title, double[] partialPayment){
    try{
      try(BufferedWriter writer = new BufferedWriter(new FileWriter(PARTIALPAYMENT_TRANSACTION_FILE, true))){
        writer.append(accountNo + ":" + title);
        for(double payment : partialPayment) writer.append(":" + payment);
        writer.newLine();
      }
    }catch(IOException e){}
  }

  private void enrolled(){
    isPaymentExits = true;

    clear();
    
    fileWrite();
    spinLoading(21, DEFAULT_NO_COLUMNS.getValueInt(), DEFAULTSTEP.getValueInt(), "SAVING THE INFORMATIONS", DEFAULT_ANIMATION_SPEED.getValueInt());
    clear();

    newLinesHorizontalCenteredText_animation(19, DEFAULT_ANIMATION_SPEED.getValueInt() + 40, "YOU SUCCESSFULLY ENROLLED!");
    newLinesHorizontalCenteredText_animation(3, DEFAULT_ANIMATION_SPEED.getValueInt() - 10, """
    Enter "VIEW" to Open your Subject Schedule and Assessment. Enter any Character to Skip Viewing it and Return to Main Menu. """);

    printWithNewLines_alignText_animation(2, DEFAULT_ANIMATION_SPEED.getValueInt() - 20, DEFAULT_NO_COLUMNS.getValueInt() - 6, ARROW.getValueString());

    if (scan.nextLine().equalsIgnoreCase("VIEW")) assessmentFile_open();
    else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), BACKTOMAINMENU.getMessage());

    threading(DEFAULT_DELAY.getValueInt());
  }

  private void returnTo(String returnTo){
    returnTo = switch (returnTo){
      case "pinConfirmationCancel" -> "CONFIRMATION PROCESS CANCELLED...";
      case "paymentOption" -> "RETURN TO PAYMENT OPTION...";
      case "partialPaymentOption" -> "RETURN TO PARTIAL PAYMENT OPTION...";
      case "fullPaymentOption" -> "RETURN TO FULL PAYMENT OPTION...";
      default -> "RETURN TO PREVIOUS SECTION...";
    };
    
    lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), returnTo);
    threading(SHORT_DELAY.getValueInt());
  }

  @Override
  public void fileWrite(){
    try{
      try(BufferedWriter writer = new BufferedWriter(new FileWriter(ASSESSMENT_FILE, true))){
        writer.append("\n\n" + document_stringAlign(67, ARROW.getValueString()));
        for(String information : paymentInformations) writer.append(": " + information);
        writer.append("\n\n" + document_center_StringAlign(ARROW.getValueString() + "THIS ASSESSEMENT WILL BE THE EVIDENCE THAT YOU ARE ENROLLED."));
      }

      try(BufferedWriter writer = new BufferedWriter(new FileWriter(COURSE_INFO_FILE, true))){
        int[] choices = {CourseUI.getCourseChoice(), CourseUI.getYearChoice(), CourseUI.getSemesterChoice(), CourseUI.getTrackChoice()}; //array that will be write to the file.
        
        writer.append(String.valueOf(accountNo));
        for(int choice : choices) writer.append(":" + choice);
        writer.newLine();
      }
    } catch(IOException e){}
  }

  public void assessmentFile_open(){
    try {
      new ProcessBuilder("notepad", String.valueOf(ASSESSMENT_FILE)).start();
    } catch (IOException e) {
      lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "FAILED TO OPEN THE FILE");
      threading(DEFAULT_DELAY.getValueInt());
    }
  }
}
