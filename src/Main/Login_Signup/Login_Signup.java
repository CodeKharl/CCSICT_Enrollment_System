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
    String[] arrayOf_username_password;

    public final String[] college_classification = {"Freshman", "Old Student", "Transferee", "Graduate Student"};
    
    private int count = 0; //variable that uses in loops and index of the arrays.
    private static int lineCount; //holds the number of rows in the account info file.
    
    public void signin_signup_Menu(){
        String[] menu = {"LOGIN - ENTER EXISTING ACCOUNT", "SIGNUP - CREATE A ACCOUNT", "CLOSE - CLOSE THE PROGRAM"};
        
        OUTER:
        do {
            LINELENGTH.setValueInt(25);
            newLinesHorizontalCenteredTextln(15, "-".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()) + "> ISU-E CCSICT <" +
                                                    "-".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            for(String menu_s : menu) lnprintlnWithTabs_spaces(7, 7, menu_s);

            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt() + 37));

            lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 10, "TYPE " + ARROW.getValueString());
            String choice = scan.nextLine();

            if (choice.isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
            else{
                switch (choice.toUpperCase()) {
                    case "LOGIN" -> login(); //go to login-section
                    case "SIGNUP" -> signup(); //go to signup-section
                    case "CLOSE" -> { //close the program
                        clear();
                        break OUTER;
                    }
                    default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                }
            }

            threading(DEFAULT_DELAY.getValueInt());
            
        } while (true);
    }
    
    private void login(){
        do {
            //set the username and password of the user input
            if((arrayOf_username_password = setArray_username_password_value(count, "ACCOUNT LOG-IN")) == null) break;

            //0 == account number, 1 == username, 2 == password, 3 == student type.
            String[] accountDetails = getAccountInfo_data(arrayOf_username_password);

            System.out.print("\r"); //return to the firstcolumn that overrides the spin animation
            if(accountDetails == null) { //account not exist.
                print_alignText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), DEFAULT_NO_COLUMNS.getValueInt(), "ACCOUNT NOT FOUND! PLEASE ENTER A EXISTING ACCOUNT");
                count = 0; //reset to set a username and password again.
            } else{ //account exist
                accountNo = Integer.parseInt(accountDetails[0]); //index 0 is the accountNumber.
                username = accountDetails[1];
                password = accountDetails[2];
                studentType = accountDetails[3];
                
                print_alignText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), DEFAULT_NO_COLUMNS.getValueInt(), "ACCOUNT FOUND! ENTER ANY CHARACTER TO CONTINUE");
                scan.nextLine(); //enter to proceed to the loading section.
                signin_loading();
                break;
            }
            
            threading(LONG_DELAY.getValueInt()); //just a treading to make a delay.
        } while (true);
    }

    private String[] getAccountInfo_data(String[] userInput_userPas){ //use in login function
        try {
            try(BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_INFO_FILE))) {
                System.out.println();
                while((line_Data = reader.readLine()) != null){
                    spinLoading(0, DEFAULT_NO_COLUMNS.getValueInt(), SHORTSTEP.getValueInt() - 2, "FINDING THE ACCOUNT", SHORT_ANIMATION_SPEED.getValueInt());

                    if (line_Data.contains(userInput_userPas[0] + ":" + userInput_userPas[1] + ":"))  return line_Data.split(":"); //split the line_data and become array.
                }
            }
        } catch (IOException e) {}

        return null;
    }

    private void signup(){
        do {
            if ((arrayOf_username_password = setArray_username_password_value(count, "CREATE ACCOUNT")) == null) break;

            if (!ACCOUNT_INFO_FILE.exists()) setDirectory("data");

            if(!username_validation(arrayOf_username_password)){ //if the username that input by user is unique.
                //set the username and password by using the array below that holds the input by the user.
                username = arrayOf_username_password[0]; //holds the username.
                password = arrayOf_username_password[1];//holds the password.

                studentType = setCollegeClassification(); //set what type of student the user is.

                fileWrite(lineCount, arrayOf_username_password, studentType);

                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "ACCOUNT CREATED SUCCESSFULLY! GO TO LOGIN SECTION FOR OPEN THE ACCOUNT.");
                threading(SHORT_DELAY.getValueInt());
                break; //break the loop to go back to the signup_login_menu.
            } else{
                System.out.print("\r");
                print_alignText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), DEFAULT_NO_COLUMNS.getValueInt(), "THIS USERNAME WAS USED! PLEASE ENTER A UNIQUE USERNAME"); //if the username is exist.
            }
            threading(LONG_DELAY.getValueInt());
        } while (true);
    }

    private boolean username_validation(String[] userInput_userPas){  //used in sign-up function
        try {
            try(BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_INFO_FILE))){
                System.out.println();

                lineCount = 0;
                while ((line_Data = reader.readLine()) != null) {
                    spinLoading(0, DEFAULT_NO_COLUMNS.getValueInt(), SHORTSTEP.getValueInt() - 2, "PROCESSING THE ACCOUNT", DEFAULT_ANIMATION_SPEED.getValueInt() - 25);
                    if(line_Data.contains(userInput_userPas[0] + ":")) return true;

                    lineCount++;
                }
            }
        } catch (IOException e) {}

        return false;
    }

    public void fileWrite(int lineCount, String[] arrayOf_username_password, String studentType){
        try {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_INFO_FILE, true))){//writer for the account file. Is has append feature that can add data's to it.
                writer.append(Year.now() + String.valueOf(lineCount + 1));

                for(String details : arrayOf_username_password) writer.append(":" + details);
                writer.append(":" + studentType);
                writer.newLine();
            }
        } catch (IOException e) {}
    }

    private String[] setArray_username_password_value(int count, String title){ //function that set the arrayOf_username_password
        String[] arrayForUI_user_pass = {"Username : ", "Password : "}; //this arrays used with the a loop method for to visualize the following.
        
        arrayOf_username_password = new String[2];
        for(int i = 0; i < arrayOf_username_password.length; i++) arrayOf_username_password[i] = "";

        LINELENGTH.setValueInt(60); //set the length of the line for method below.
        
        while(true){
            clear();
            newLinesHorizontalCenteredTextln(15, "-".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln(title);
            horizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            lnprintlnWithTabs_spaces(7, 4, arrayForUI_user_pass[0].toUpperCase() + arrayOf_username_password[0]);
            lnprintlnWithTabs_spaces(7, 4, arrayForUI_user_pass[1].toUpperCase() + "*".repeat(arrayOf_username_password[1].length()));
            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));
            println_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 16, "*Enter 'x' to Return to Login/Signup Menu.");

            if (count == arrayOf_username_password.length) break;

            lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 12, "Enter" + " " + arrayForUI_user_pass[count]);
            arrayOf_username_password[count] = scan.nextLine();

            if(arrayOf_username_password[count].isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
            else if(arrayOf_username_password[count].toUpperCase().equals("X")){
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "CANCEL LOGIN...");
                return null;
            } else count++;
                
            threading(DEFAULT_DELAY.getValueInt());
        }

        return arrayOf_username_password;
    }

    private String setCollegeClassification(){
        LINELENGTH.setValueInt(45);
        
        while (true) {
            clear();
            newLinesHorizontalCenteredText(15, "-".repeat(LINELENGTH.getValueInt()));
            lnHorizontalCenteredTextln("COLLEGE CLASSIFICATION");
            horizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            for(int i = 0; i < college_classification.length; i++) lnprintlnWithTabs_spaces(7, 8, (i + 1) + ". " + college_classification[i]);
            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            try{
                lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 16, "Enter Your Type : ");
                int choice = Integer.parseInt(scan.nextLine());

                if (choice > 0 || choice <= 4) return college_classification[choice - 1];
                else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
            } catch(NumberFormatException e) {
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getValueInt());
        }
    }

    private void signin_loading(){
        DEFAULT_ANIMATION_SPEED.setValueInt(40);
        clear();
        
        newLine(22);
        percentageLoading(DEFAULT_NO_COLUMNS.getValueInt());
        threading(DEFAULT_DELAY.getValueInt());
    
        new MainUI().systemMenu(); //go to main menu
        
        threading(DEFAULT_DELAY.getValueInt());
        sign_out();
    }

    private void sign_out(){ //function of system-ending.
        clear();
        threading(DEFAULT_DELAY.getValueInt());
        newLinesHorizontalCenteredText_animation(22, DEFAULT_ANIMATION_SPEED.getValueInt() + 20, "ACCOUNT SIGNING-OUT...");
        threading(DEFAULT_DELAY.getValueInt() + 100);
        clear();
        spinLoading(22, DEFAULT_NO_COLUMNS.getValueInt(), DEFAULTSTEP.getValueInt(), "ISU-E CCSICT", DEFAULT_ANIMATION_SPEED.getValueInt() + 80); //spin-loading with a EXITING text.
    }

    
}