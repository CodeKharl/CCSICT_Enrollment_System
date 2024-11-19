package Main.Login_Signup;

import Main.MainUI;
import Others.System_IN.System_in;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;


public class Login_Signup extends System_in{
    //this arrays used with the a loop method for to visualize the following.
    private final String[] arrayForUI_user_pass = {"Username : ", "Password : "};
    String[] arrayOf_username_password = new String[2];

    final String[] college_classification = {"Freshman", "Old Student", "Transferee", "Graduate Student"};
    
    private int count = 0; //variable that uses in loops and index of the arrays.

    public void signin_signup_Menu(){
        String[] login_signup_menu = {"LOGIN - ENTER EXISTING ACCOUNT", "SIGNUP - CREATE A ACCOUNT", "CLOSE - CLOSE THE PROGRAM"};
        
        OUTER:
        do {
            clear();
            LINELENGTH.setChoiceInt(25);
            newLinesHorizontalCustomCenteredTextln(15, HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            customHorizontalCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt())  + "> ISU-E CCSICT <" +
                                                    HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            customHorizontalCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            for(String menu_s : login_signup_menu) lnprintlnWithTabs_spaces(7, 7, menu_s);

            lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt() + 37));

            lnprint_alignText(DEFAULT_NO_COLUMNS.getChoiceInt() - 10, "TYPE " + ARROW.getChoiceString());
            String choice = scan.nextLine();

            if (choice.isBlank() || choice.isEmpty()) lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NEEDTOFILLUP.getMessage());
            else{
                
                switch (choice.toUpperCase()) {
                    case "LOGIN" -> login(); //go to login-section
                    case "SIGNUP" -> signup(); //go to signup-section
                    case "CLOSE" -> { //close the program
                        clear();
                        break OUTER;
                    }
                    default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
                }
            }

            threading(DEFAULT_DELAY.getChoiceInt());
            
        } while (true);
    }
    
    private void login(){
        do {
            arrayOf_username_password = setArray_username_password_value(count, "ACCOUNT LOG-IN"); //set the username and password of the user input

            if(arrayOf_username_password[0].toUpperCase().equals("X")){
                lnHorizontalCustomCenteredText("CANCEL LOGIN...");
                threading(DEFAULT_DELAY.getChoiceInt());
                break;
            }
            
            try {
                try(BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_INFO_FILE))){ //reader that read the accountFile.
                    isAccount_condition = false; //variable that used to know wether the user_input is existing account or not.
                    System.out.println();

                    accountNo = 0;
                    while ((line_Data = reader.readLine()) != null) { //check the accountFile by row or line until the reader detects a null.
                        if (line_Data.contains(arrayOf_username_password[0] + ":" + arrayOf_username_password[1] + ":")) { //check for the username and password of the given by the user.
                            isAccount_condition = true; //set the variable to true if the account is exist.
                            break; // termine the while-loop
                        }
                        
                        spinLoading(0, DEFAULT_NO_COLUMNS.getChoiceInt(), SHORTSTEP.getChoiceInt() - 2, "FINDING THE ACCOUNT", DEFAULT_ANIMATION_SPEED.getChoiceInt()); //simple spin animation.
                    }
                    
                    System.out.print("\r"); //return to the firstcolumn that overrides the spin animation
                    if(!isAccount_condition) { //account not exist.
                        print_alignText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), DEFAULT_NO_COLUMNS.getChoiceInt(), "ACCOUNT NOT FOUND! PLEASE ENTER A EXISTING ACCOUNT");
                        count = 0; //reset to set a username and password again.
                    } else{ //account exist
                        array_line_data = line_Data.split(":"); //split the line_data and become array.
                        accountNo = Integer.parseInt(array_line_data[0]); //index 0 is the accountNumber.
                        
                        for(String classification : college_classification) if(line_Data.contains(classification)) studentType = classification; //set the studentType.
                        username = arrayOf_username_password[0];
                        password = arrayOf_username_password[1];

                        print_alignText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), DEFAULT_NO_COLUMNS.getChoiceInt(), "ACCOUNT FOUND! ENTER ANY CHARACTER TO CONTINUE");
                        scan.nextLine(); //enter to proceed to the loading section.

                        System_start_out start = new System_start_out(); //new object to get the function below
                        start.signin_loading();
                        break;
                    }
                }
            } catch (IOException e) {//handles if the system has no Account.dat file. It also means that there's no existing accounts in the system.
                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt() + 10, "THERE'S NO EXISTING ACCOUNT.DAT FILE! NEED TO SIGNUP FIRST.");
                break; //to make the user sign-up first or create account to login.
            }
            
            threading(LONG_DELAY.getChoiceInt()); //just a treading to make a delay.
        } while (true);
    }

    private void signup(){
        do {
            arrayOf_username_password = setArray_username_password_value(count, "CREATE ACCOUNT");

            if(arrayOf_username_password[0].toUpperCase().equals("X")){
                lnHorizontalCustomCenteredText("CANCEL SIGNUP");
                threading(DEFAULT_DELAY.getChoiceInt());
                break;
            }

            try{
                if (!ACCOUNT_INFO_FILE.exists()) ACCOUNT_INFO_FILE.createNewFile(); //create a new accountFile if it didn't exist.
                isAccount_condition = false; //variable that holds if the username input have the same to existing accounts in the system.
        
                try(BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_INFO_FILE))) {
                    System.out.println(); //newLine

                    accountNo = 0; //to get the number of rows in the file. That will become the Account Number.
                    while ((line_Data = reader.readLine()) != null) {
                        if (line_Data.contains(arrayOf_username_password[0] + ":")) { //check if the username exist.
                            isAccount_condition = true;
                            break;
                        }

                        accountNo++;
                        spinLoading(0, DEFAULT_NO_COLUMNS.getChoiceInt(), SHORTSTEP.getChoiceInt() - 2, "PROCESSING THE ACCOUNT", DEFAULT_ANIMATION_SPEED.getChoiceInt());
                    }
                
                    System.out.print("\r");
                    if(!isAccount_condition){ //if the username that input by user is unique.
                        //set the username and password by using the array below that holds the input by the user.
                        username = arrayOf_username_password[0]; //holds the username.
                        password = arrayOf_username_password[1];//holds the password.

                        studentType = setCollegeClassification(); //set what type of student the user is.

                        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_INFO_FILE, true))){ //writer for the account file. Is has append feature that can add data's to it.
                            //write this account information in the file by this format.
                            writer.write(Year.now() + String.valueOf(accountNo + 1) + ":" + username + ":" + password + ":" + studentType);
                            writer.newLine();

                            lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), "ACCOUNT CREATED SUCCESSFULLY! GO TO LOGIN SECTION FOR OPEN THE ACCOUNT");
                            threading(DEFAULT_DELAY.getChoiceInt());
                            break; //break the loop to go back to the signup_login_menu.
                        }

                    } else {//if the username is exist.
                        print_alignText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), DEFAULT_NO_COLUMNS.getChoiceInt(), "THIS USERNAME WAS USED! PLEASE ENTER A UNIQUE USERNAME");
                    }
                }
            } catch(IOException e){}

            threading(LONG_DELAY.getChoiceInt());
        } while (true);
    }

    private String[] setArray_username_password_value(int count, String title){ //function that set the arrayOf_username_password
        for(int i = 0; i < arrayOf_username_password.length; i++) arrayOf_username_password[i] = "";

        LINELENGTH.setChoiceInt(60); //set the length of the line for method below.
        
        while(true){
            clear();
            newLinesHorizontalCustomCenteredTextln(15, HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            customHorizontalCenteredTextln(title);
            customHorizontalCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            lnprintlnWithTabs_spaces(7, 4, arrayForUI_user_pass[0].toUpperCase() + arrayOf_username_password[0]);
            lnprintlnWithTabs_spaces(7, 4, arrayForUI_user_pass[1].toUpperCase() + toAsterisk(arrayOf_username_password[1]));
            lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            println_alignText(DEFAULT_NO_COLUMNS.getChoiceInt() - 16, "*Enter 'x' to Return to Login/Signup Menu.");

            if (count == arrayOf_username_password.length) break;

            lnprint_alignText(DEFAULT_NO_COLUMNS.getChoiceInt() - 12, "Enter" + " " + arrayForUI_user_pass[count]);
            arrayOf_username_password[count] = scan.nextLine();

            if(arrayOf_username_password[count].isBlank() || arrayOf_username_password[count].isEmpty()) lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NEEDTOFILLUP.getMessage());
            else count++;
                
            threading(DEFAULT_DELAY.getChoiceInt());
        }

        return arrayOf_username_password;
    }

    private String setCollegeClassification(){
        LINELENGTH.setChoiceInt(45);
        
        while (true) {
            clear();
            newLinesHorizontalCustomCenteredText(16, HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            lnHorizontalCustomCenteredTextln("COLLEGE CLASSIFICATION");
            customHorizontalCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            for(int i = 0; i < college_classification.length; i++) lnprintlnWithTabs_spaces(7, 8, (i + 1) + ". " + college_classification[i]);
            lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            try{
                lnprint_alignText(DEFAULT_NO_COLUMNS.getChoiceInt() - 16, "Enter Your Type : ");
                int choice = Integer.parseInt(scan.nextLine());

                if (choice > 0 || choice <= 4) {
                    return college_classification[choice - 1];
                }
                else lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
            } catch(NumberFormatException e) {
                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getChoiceInt());
        }
    }

    private String toAsterisk(String password){
        String asterisk = "";
        for (int i = 0; i < password.length(); i++){
            asterisk += "*";
        }

        return asterisk;
    }
}

class System_start_out extends System_in{
    protected void signin_loading(){
        DEFAULT_ANIMATION_SPEED.setChoiceInt(40);
        clear();
        
        newLine(22);
        percentageLoading(DEFAULT_NO_COLUMNS.getChoiceInt());
        threading(700);
    
        MainUI main = new MainUI(); //object to access the main.
        main.systemMenu(); //function for mainUI.
        
        threading(700);
        sign_out();
    }
    
    private void sign_out(){ //function of system-ending.
        clear();
        threading(DEFAULT_DELAY.getChoiceInt());
        newLinesCustomHorizontalCenteredText_animation(22, DEFAULT_ANIMATION_SPEED.getChoiceInt() + 20, "ACCOUNT SIGNING-OUT...");
        threading(800);
        clear();
        spinLoading(22, DEFAULT_NO_COLUMNS.getChoiceInt(), DEFAULTSTEP.getChoiceInt(), "ISU-E CCSICT", DEFAULT_ANIMATION_SPEED.getChoiceInt() + 80); //spin-loading with a EXITING text.
    }
}
