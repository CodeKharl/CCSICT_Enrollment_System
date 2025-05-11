package Main.AccountP;
import Main.Assessment_PaymentP.Assessment;
import Main.Assessment_PaymentP.Payment;
import Main.CourseP.Course;
import Main.CourseP.CourseUI;
import Main.MainUI;
import Main.StudentP.Student;
import Others.System_IN.System_in;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Account extends MainUI{ //Simple Account System that can handle payments
    private static BigDecimal accountBalance_BigDecimal = new BigDecimal(BigInteger.ZERO);

    String finishView = "TO FINISH VIEWING"; //used in typeAnyCharacter function.

    public void accountUI(){
        String[] accountMenu = {"BALANCE", "UNFINISHED PAYMENT", "ENROLLMENT STATUS", "RETURN TO MAIN MENU"};
        
        OUTER:
        do {
            clear();

            LINELENGTH.setValueInt(40);
            newLinesHorizontalCenteredTextln(15,"=".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln(System_in.getUsername() + "'s Account");
            horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

            for(int i = 0; i < accountMenu.length; i++) lnprintlnWithTabs_spaces(7, 6, (i + 1) + ". " + accountMenu[i]);

            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));
            
            try{
                enterChoice();

                switch(Integer.parseInt(scan.nextLine())){
                    case 1 -> balanceMenu();
                    case 2 -> unfinishedPayment();
                    case 3 -> enrollmentStatus();
                    case 4 -> {
                        lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), BACKTOMAINMENU.getMessage());
                        threading(DEFAULT_DELAY.getValueInt());
                        break OUTER;
                    }
                    default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                }
            } catch(NumberFormatException e){
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getValueInt());

        } while (true);
    }

    private void balanceMenu(){
        OUTER:
        do {
            String[] menu = {"ADD FUNDS (TOP-UP) TO THE ACCOUNT", "RETURN TO ACCOUNT MENU"};
            LINELENGTH.setValueInt(50);

            clear();
            newLinesHorizontalCenteredTextln(16, "-".repeat(LINELENGTH.getValueInt()));
            lnHorizontalCenteredTextln("ACCOUNT BALANCE : " + getAccountBalance());
            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            for (int i = 0; i < menu.length; i++) lnprintlnWithTabs_spaces(7, 6, (i + 1) + ". " + menu[i]);

            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            try{
                enterChoice();

                switch (Integer.parseInt(scan.nextLine())) {
                    case 1 -> addFunds_menu("ACCOUNT'S MENU");
                    case 2 -> {
                        backToAccount_menu();
                        break OUTER;
                    }
                    default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                }

            } catch(NumberFormatException e){
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getValueInt());
            
        } while (true);
    }

    public void addFunds_menu(String returnTO_type){
        String[] funds_Menu = {"BANK", "GCASH", "RETURN TO " + returnTO_type};
        String[] details = new String[3];

        OUTER:
        do {
            LINELENGTH.setValueInt(40);

            clear();
            newLinesHorizontalCenteredTextln(16, "-".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("ADD FUNDS (TOP UP)");
            horizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            for(int i = 0; i < funds_Menu.length; i++) lnprintlnWithTabs_spaces(7, 6, (i + 1) + ". " + funds_Menu[i]);
            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            try{
                enterChoice();
                switch(Integer.parseInt(scan.nextLine())){
                    case 1 -> {
                        bankNames(details);
                        break OUTER;
                    }
                    case 2 -> {
                        addFunds_gcash(details);
                        break OUTER;
                    }
                    case 3 -> {
                        lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), funds_Menu[funds_Menu.length - 1] + "...");
                        break OUTER;
                    }
                    default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                }
            } catch(NumberFormatException e){
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getValueInt());
        } while (true);
    }

    private void addFunds_gcash(String[] details){
        details[0] = "GCASH NAME";
        details[1] = "GCASH NUMBER";
        
        addFunds_gcashBank_input("GCASH", details, 11, "09");
    }

    private void bankNames(String[] details){
        String[] bankNames = {"PHILIPPINE NATIONAL BANK", "LAND BANK", "BDO BANK"};
        
        LINELENGTH.setValueInt(60);
        do {
            clear();
            newLinesHorizontalCenteredTextln(16, "-".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("BANK ACCOUNT NAMES");
            horizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            for(int i = 0; i < bankNames.length; i++) lnprintlnWithTabs_spaces(7, 6, (i + 1) + ". " + bankNames[i]);
            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            try{
                lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 6, "ENTER THE BANK : ");
                int choice = Integer.parseInt(scan.nextLine());

                if (choice > 0 && choice <= bankNames.length){
                    details[0] = "BANK ACCOUNT NAME";
                    details[1] = "BANK ACCOUNT NUMBER";

                    int[] bankNumber_lengths = {12, 10, 12};
                    String[] banks_startNum = {"01", "1", "01"};
                    addFunds_gcashBank_input(bankNames[choice - 1], details, bankNumber_lengths[choice - 1], banks_startNum[choice - 1]);
                    break;
                } else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
            } catch(NumberFormatException e){
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(SHORT_DELAY.getValueInt());
        } while (true);
    }

    private void addFunds_contents(String type, String[] details, String[] userInputs){
        LINELENGTH.setValueInt(60);
        clear();
        newLinesHorizontalCenteredTextln(16, "-".repeat(LINELENGTH.getValueInt()));
        horizontalCenteredTextln(type);
        horizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

        for (int i = 0; i < details.length; i++) lnprintlnWithTabs_spaces(7, 6, details[i] + " : " + userInputs[i]);
        lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));
    }

    private void addFunds_gcashBank_input(String type, String[] details, int account_numberLength, String account_startNum){
        int count = 0; //set to one to pass the 0 index which is the account name since theres no condition for it, just put any name.

        String[] userInputs = {"", "", ""};
        details[details.length - 1] = "AMOUNT";
        
        String[] addDetails = {"Valid Account Name;", "Account No. Length : " + account_numberLength + "; Start At : " + account_startNum + ";", "Min : 1"};

        do{
            addFunds_contents(type, details, userInputs); //contents
            
            //for scanning the user input
            lnHorizontalCenteredTextln("ENTER " + details[count] + " (*" + addDetails[count] + " X == Cancel)");
            lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 6, ARROW.getValueString());
            String input = scan.nextLine();

            if(input.equalsIgnoreCase("X")) break; //for breaking the loop and go back to balance menu.
            
            if(input.isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage()); //if user input is blank
            else input = addFunds_gcashChecker(details, input, count, account_numberLength, account_startNum);
                
            if (!input.equals("")){
                userInputs[count] = input;
                count++;
            }

            if (count == details.length) {
                addFunds_confirmation(type, details, userInputs);
                break;
            }

            threading(SHORT_DELAY.getValueInt());
        } while(true);
    }

    private String addFunds_gcashChecker(String[] details, String input, int count, int account_numberLength, String account_startNum){
        try{
            if(count == 0) return input;
            else if (count == 1 && input.length() == account_numberLength && input.startsWith(account_startNum) && Long.parseLong(input) > 0) return input;
            else if (count == 2 && Double.parseDouble(input) > 0) return input;
        } catch(NumberFormatException e){}

        lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), MESSAGE.invalidInputModifier(details[count] + "."));
        
        return "";
    }


    private void addFunds_confirmation(String type, String[] details, String[] userInputs){
        do{
            addFunds_contents(type, details, userInputs);
            lnHorizontalCenteredTextln("""
                    TYPE YOUR ACCOUNT PASSWORD TO PROCESS THE TRANSACTION. "BACK" TO CANCEL THE PROCESS.""");
            lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 6, ARROW.getValueString());

            String input = scan.nextLine();

            if(input.toUpperCase().equals("BACK")){
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "CANCELLING THE TRANSACTION PROCESS...");
                break;
            }
            
            if(input.equals(password)) {
                addAccountBalance(BigDecimal.valueOf(Long.parseLong(userInputs[userInputs.length - 1])));
                fileWrite();

                showTransactionReciept(type, details, userInputs, true);
                break;
            } else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), MESSAGE.invalidInputModifier("YOUR VALID PASSWORD."));

            threading(DEFAULT_ANIMATION_SPEED.getValueInt());

        } while(true);
    }

    public void showTransactionReciept(String title, String[] details, String[] userInputs, boolean isForAddFunds){
        String transactionNo = "TRANSACTION NO. " + generate_transactionNo();

        LINELENGTH.setValueInt(60);

        clear();
        newLinesHorizontalCenteredTextln(16, "-".repeat(LINELENGTH.getValueInt()));
        horizontalCenteredTextln(title + " - " + transactionNo);
        horizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

        String message = null;
        for(int i = 0; i < details.length; i++) message = isForAddFunds ? details[i] + " : " + userInputs[i] : details[i];

        lnprintlnWithTabs_spaces(7, 6, message);

        lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));
        typeAnyCharacter("TO RETURN");
    }

    private int generate_transactionNo(){
        return new Random().nextInt(1, 999) + 1000;
    }

    private void unfinishedPayment(){
        LINELENGTH.setValueInt(50);

        OUTER:
        do {
            //0 == accountNo, 1 == payment type, Rest == amounts.
            List<String> paymentDetails = new ArrayList<>();

            paymentDetails.addAll(Arrays.asList(getUserPayment_data()));

            clear();
            if (paymentDetails.isEmpty() || paymentDetails.size() <= 2){
                newLinesHorizontalCenteredText_animation(21, DEFAULT_ANIMATION_SPEED.getValueInt(), "THERE'S NO EXISTING UNFINISHED PAYMENTS IN YOUR ACCOUNT.");
                break;
            }

            newLinesHorizontalCenteredTextln(14, "-".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("UNFINISHED PAYMENT (PARTIAL PAYMENT)");
            horizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            println_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 20, paymentDetails.get(1)); //print the partialPayment count
            lnHorizontalCenteredTextln(paymentDetails.get(2).concat(" - Ammount Due"));
            for (int i = 3; i < paymentDetails.size(); i++) lnprintlnWithTabs_spaces(8, 4, paymentDetails.get(i) + " -");
            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            lnHorizontalCenteredTextln("""
                    Enter "Pay" to pay the ammount due. "Return" to return Account menu.""");
            
            lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 6, ARROW.getValueString());
            String choice = scan.nextLine();
            
            if (choice.isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
            else{
                switch (choice.toUpperCase()){
                    case "PAY" -> {
                        setUnfinished_payments_pay(paymentDetails); //get the next payment
                        break OUTER;
                    }

                    case "RETURN" -> {
                        backToAccount_menu();
                        break OUTER;
                    }

                    default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                }
            }

            threading(SHORT_DELAY.getValueInt());
        } while (true);
    }

    private void setUnfinished_payments_pay(List<String> paymentDetails){
        double next_payment = Double.parseDouble(paymentDetails.get(2));

        if(accountBalance_BigDecimal.doubleValue() < next_payment) lnHorizontalCenteredText_animation(SHORT_ANIMATION_SPEED.getValueInt(), "UNSUFFICIENT FUNDS! PLEASE ADD FUNDS TO YOUR ACCOUNT FIRST BEFORE PAYING.");
        else{
            accountBalance_BigDecimal = accountBalance_BigDecimal.subtract(BigDecimal.valueOf(next_payment));

            double[] payment = new double[paymentDetails.size() - 3];
            
            for(int i = 0; i < payment.length; i++) payment[i] = Double.parseDouble(paymentDetails.get(i + 3));
            new Payment().fileWrite_partialPayment(paymentDetails.get(1), payment);

            paymentDetails.clear();
            fileWrite();

            String message = payment.length == 0 ? "YOU SUCCESSFULLY SETTLED THE UNFINISHED PAYMENT." : "YOU SUCCESSFULLY PAY THE AMMOUNT DUE PAYMENT.";
            lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), message);
        }

        threading(DEFAULT_DELAY.getValueInt());
    }

    private String[] getUserPayment_data(){
        String payment_data = "";

        try {
            try(BufferedReader reader = new BufferedReader(new FileReader(PARTIALPAYMENT_TRANSACTION_FILE))){
                System.out.println();
                while((line_Data = reader.readLine()) != null) {
                    if (line_Data.startsWith(accountNo + ":")) payment_data = line_Data; //set the variable to if the user has existing partial payment.
                    spinLoading(0, DEFAULT_NO_COLUMNS.getValueInt(), SHORTSTEP.getValueInt() - 2, "PROCCESSING YOUR PAYMENTS", SHORT_ANIMATION_SPEED.getValueInt());
                }
            }
        } catch (IOException e) {}

        return !payment_data.isBlank() ? payment_data.split(":") : null;
    }

    private void enrollmentStatus(){
        String[] enrollmentStatus_menu = {"PERSONAL INFORMATION", "SHOW COURSE CURRICULUM", "SHOW ASSESSMENT FEE'S", "SHOW THE ASSESSMENT FILE", "RETURN TO ACCOUNT MENU"};
        
        OUTER:
        do {
            LINELENGTH.setValueInt(50);
            clear();
            newLinesHorizontalCenteredTextln(14,"=".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("ENROLLMENT STATUS");
            horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

            if (!ASSESSMENT_FILE.exists()) {
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "YOU ARE NOT CURRENTLY ENROLLED TO ANY CCSICT COURSE");
                threading(DEFAULT_DELAY.getValueInt());
                break;
            } else for(int i = 0; i < enrollmentStatus_menu.length; i++) lnprintlnWithTabs_spaces(7, 8, (i + 1) + ". " + enrollmentStatus_menu[i]);

            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            try {
                enterChoice();
                switch(Integer.parseInt(scan.nextLine())){
                    case 1 -> studentInfoShow();
                    case 2 -> courseCurriculumShow();
                    case 3 -> assessmentFeesShow();
                    case 4 -> new Payment().assessmentFile_open();
                    case 5 -> {
                        backToAccount_menu();
                        break OUTER;
                    }
                }
            } catch (NumberFormatException e) {
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getValueInt());
        } while (true);
    }

    private void courseCurriculumShow(){
        //0 == account number, 1 == course, 2 == year, 3 == semester, 4 track.
        String[] courseData = getUserCourse_data();

        if(courseData != null){ //set choices to locate the variables for the curriculum
            CourseUI.setCourseChoice(Integer.parseInt(courseData[1]));
            CourseUI.setYearChoice(Integer.parseInt(courseData[2]));
            CourseUI.setSemesterChoice(Integer.parseInt(courseData[3]));
            CourseUI.setTrackChoice(Integer.parseInt(courseData[4]));

            new Course().curriculumSetupVariables(true);
            typeAnyCharacter(finishView);
        } else{
            System.out.print("\r");
            print_alignText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), DEFAULT_NO_COLUMNS.getValueInt(), "THERE'S NO COURSE DATA FOR THIS ACCOUNT.");
        }
    }

    private String[] getUserCourse_data(){ //use to locate the course file and find the user course data
        try {
            try(BufferedReader reader = new BufferedReader(new FileReader(COURSE_INFO_FILE))){
                System.out.println();
                while((line_Data = reader.readLine()) != null){
                    spinLoading(0, DEFAULT_NO_COLUMNS.getValueInt(), SHORTSTEP.getValueInt() - 2, "FINDING THE DATA", SHORT_ANIMATION_SPEED.getValueInt());
                    
                    if (line_Data.contains(accountNo + ":")) return line_Data.split(":");
                }
            }
        } catch (IOException e) {}

        return null;
    }

    private void studentInfoShow(){
        Student student = new Student();

        try{
            try(BufferedReader reader = new BufferedReader(new FileReader(STUDENT_INFO_FILE))){
                String[] student_details = new String[student.array_StudentInfo.length]; //set the size to handle the personal information of the user.

                int count = 0;
                while((line_Data = reader.readLine()) != null){
                    student_details[count] = line_Data;
                    count++;
                }

                student.studentInfoShow("PERSONAL INFORMATION", student_details);
                typeAnyCharacter(finishView);
            }
        } catch(IOException e){
            lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "THERE'S NO EXISTING FILE DATA");
            threading(DEFAULT_DELAY.getValueInt());
        }
    }

    private void assessmentFeesShow(){
        if (COURSE_INFO_FILE.exists()) {
            new Assessment().assessmentFees(true);
            typeAnyCharacter(finishView);
        } else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "THE SYSTEM CANNOT PROVIDE THIS REQUEST");
    }

    @Override
    public void fileWrite(){ //function that writes the present account balance of an account to retain it even the account is sign-out.
        try {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE, true))){
                writer.write(getAccountNumber() + ":" + getAccountBalance());  //write the account number with balance of 0
                writer.newLine(); //to make other input to the file go to nextline.
            }
        } catch (IOException e) {}
    }

    private void backToAccount_menu(){
        lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "RETURN TO ACCOUNT'S MENU...");
    }

    public void setAccountBalance(BigDecimal accountBalance_BigDecimal){
        Account.accountBalance_BigDecimal = accountBalance_BigDecimal;
    }

    public void addAccountBalance(BigDecimal accountBalance_BigDecimal){
        Account.accountBalance_BigDecimal = Account.accountBalance_BigDecimal.add(accountBalance_BigDecimal);
    }

    public BigDecimal getAccountBalance(){
        return Account.accountBalance_BigDecimal;
    }
    
}

