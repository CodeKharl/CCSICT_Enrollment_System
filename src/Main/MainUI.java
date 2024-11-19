package Main;
import Main.AccountP.Account;
import Main.StudentP.Student;
import Others.System_IN.System_in;
import Others.System_Texts.Texts;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainUI extends System_in{ //inherit the shortcuts class to access the function inside it
    //Main UI of the System
    public void systemMenu(){
        String[] menu = {"1. ENROLL", "2. CHECK ACCOUNT", "3. ABOUT THIS SYSTEM", "4. SIGN-OUT"};

        OUTER: //use to break the this function.
        do{
            clear();
            newLine(5);
            Texts.showTexts("mainMenuCover"); //the main cover "CCSICT".

            LINELENGTH.setChoiceInt(41); //set the linelength that used below.
            newLinesHorizontalCustomCenteredTextln(3, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            customHorizontalCenteredTextln("ENROLLMENT SYSTEM");
            customHorizontalCenteredText(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            for(String content: menu) lnprintWithTabs_spaces(8, 4, content);

            lnHorizontalCustomCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            
            try{
                lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), 146, "ENTER YOUR CHOICE : ");
                int choice = Integer.parseInt(scan.nextLine());

                switch(choice){
                    case 1 -> enrollment();
                    case 2 -> accountProcess();
                    case 3 -> aboutSystem();
                    case 4 -> {
                        break OUTER;
                    }
                    default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
                }
                
            } catch(NumberFormatException e){
                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getChoiceInt());

        } while(true);
    }
    
    //Enrollment Function
    private void enrollment(){
        OUTER:
        do {
            clear();
            newLinesHorizontalCustomCenteredTextln(19, "BEFORE YOU CONTINUE ENROLLING, READ THE TERMS AND CONDITIONS FIRST.");
            lnHorizontalCustomCenteredTextln("""
                    Type "READ" to View the Terms and Conditions, "ESCAPE" to Cancel.""");
            lnprint_alignText( 144, ARROW.getChoiceString());
            String choice = scan.nextLine();
            
            if(choice.isEmpty() || choice.isBlank()) lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NEEDTOFILLUP.getMessage());
            else{
                switch (choice) {
                    case "READ" -> {
                        tadConfirmation();
                        break OUTER;
                    }

                    case "ESCAPE" -> {
                        lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), BACKTOMAINMENU.getMessage());
                        threading(DEFAULT_DELAY.getChoiceInt());
                        break OUTER;
                    }
                    
                    default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
                }
            }

            threading(DEFAULT_DELAY.getChoiceInt());

        } while (true);
    }
    
    private void tadConfirmation(){ //function for terms and conditions
        OUTER:
        do{
            clear();
            Texts.showTexts("termsAndConditions");
            
            lnprint_alignText_animation(20, DEFAULT_NO_COLUMNS.getChoiceInt(), "DO YOU ACCEPT THIS TERMS AND CONDITIONS (YES or NO) : ");
            String enrollChoice = scan.nextLine();

            if(enrollChoice.isEmpty() || enrollChoice.isBlank()) lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NEEDTOFILLUP.getMessage());
            else{
                switch (enrollChoice.toUpperCase()) {
                    case "YES" -> {
                        Student studentInfo = new Student();
                        studentInfo.userInfoCheck();
                        break OUTER;
                    }

                    case "NO" -> {
                        lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), BACKTOMAINMENU.getMessage());
                        threading(LONG_DELAY.getChoiceInt());
                        break OUTER;
                    }

                    default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
                }
            }

            threading(DEFAULT_DELAY.getChoiceInt());

        } while(true);
    }
    
    private void accountProcess(){ //function that process accounts for payments.
        clear();
        newLine(22);
        
        try {
            Account account = new Account();
            if(!account.ACCOUNTFILE.exists()) account.ACCOUNTFILE.createNewFile();

            isAccount_condition = false;
            try(BufferedReader reader = new BufferedReader(new FileReader(account.ACCOUNTFILE))){
                while((line_Data = reader.readLine()) != null){
                    if (line_Data.startsWith(String.valueOf(getAccountNumber()))) { //check for the account data.
                        isAccount_condition = true;
                        break;
                    }
                    spinLoading(0, DEFAULT_NO_COLUMNS.getChoiceInt(), SHORTSTEP.getChoiceInt(), "PROCESSING THE ACCOUNT", DEFAULT_ANIMATION_SPEED.getChoiceInt());
                }

                if(!isAccount_condition){ //if there's to existing data for the user account
                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(account.ACCOUNTFILE, true))) {
                        writer.write(getAccountNumber() + ":0");  //write the account number with balance of 0
                        writer.newLine(); //to make other input to the file go to nextline.
                    }
                } else{ //if it exist.
                    array_line_data = line_Data.split(":"); //the string will be split to arrays to separate the account number and balance
                    account.setAccountBalance(Long.parseLong(array_line_data[1])); //set the account balance by using the array at index 1 (0 == accountNo., 1 == accountBalance).
                }

                account.accountUI(); //go to the account user-interface.
            }
        } catch (IOException e) { //throw an error if the reader failed to read the file.
        }
    }

    //about the System Function
    private void aboutSystem(){
        clear();
        Texts.showTexts("termsAndConditionsReferences");
        threading(DEFAULT_DELAY.getChoiceInt());
    }

    public void infoShow(){ //this function was used in other class for showing the info that user inputs.
        System.out.println("NEED TO OVERRIDE");
    }
}
