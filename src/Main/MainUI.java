package Main;
import Main.AccountP.Account;
import Main.StudentP.Student;
import Others.System_IN.System_in;
import Others.System_Texts.Texts;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class MainUI extends System_in{ //inherit the shortcuts class to access the function inside it
    public Account account;

    //Main UI of the System
    public void systemMenu(){
        String[] menu = {"ENROLL", "CHECK ACCOUNT", "ABOUT THIS SYSTEM", "SIGN-OUT"};

        OUTER: //use to break the this function.
        do{
            clear();
            newLine(5);
            Texts.showTexts("mainMenuCover"); //the main cover "CCSICT".

            LINELENGTH.setValueInt(41); //set the linelength that used below.
            newLinesHorizontalCenteredTextln(3, "=".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("ENROLLMENT SYSTEM");
            horizontalCenteredText("=".repeat(LINELENGTH.getValueInt()));

            for(int i = 0; i < menu.length; i++) lnprintWithTabs_spaces(8, 4, (i + 1) + ". " + menu[i]);

            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));
            
            try{
                enterChoice();

                switch(Integer.parseInt(scan.nextLine())){
                    case 1 -> enrollment();
                    case 2 -> accountProcess();
                    case 3 -> aboutSystem();
                    case 4 -> {
                        fileWrite(); //write the present account balance to retain it.
                        resetVariables();
                        break OUTER;
                    }
                    default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                }
                
            } catch(NumberFormatException e){
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(SHORT_ANIMATION_SPEED.getValueInt());
        } while(true);
    }
    
    //Enrollment Function
    private void enrollment(){
        OUTER:
        do {
            if(ASSESSMENT_FILE.exists()) {
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "YOU ARE CURRENTLY ENROLLED TO ONE OF THE PROGRAMS OF CCSICT.");
                threading(DEFAULT_DELAY.getValueInt());
                break;
            }

            clear();
            newLinesHorizontalCenteredTextln(19, "BEFORE YOU CONTINUE ENROLLING, READ THE TERMS AND CONDITIONS FIRST.");
            lnHorizontalCenteredTextln("""
                    Type "READ" to View the Terms and Conditions, "ESCAPE" to Cancel.""");
            lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 12, ARROW.getValueString());
            String choice = scan.nextLine();
            
            if(choice.isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
            else{
                switch (choice.toUpperCase()) {
                    case "READ" -> {
                        tadConfirmation();
                        break OUTER;
                    }

                    case "ESCAPE" -> {
                        lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), BACKTOMAINMENU.getMessage());
                        break OUTER;
                    }
                    
                    default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                }
            }

            threading(DEFAULT_DELAY.getValueInt());
        } while (true);
    }
    
    private void tadConfirmation(){ //function for terms and conditions
        OUTER:
        do{
            clear();
            Texts.showTexts("termsAndConditions");
            
            lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getValueInt() - 10, DEFAULT_NO_COLUMNS.getValueInt(), "DO YOU ACCEPT THIS TERMS AND CONDITIONS (YES or NO) : ");
            String enrollChoice = scan.nextLine();

            if(enrollChoice.isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
            else{
                switch (enrollChoice.toUpperCase()) {
                    case "YES" -> {
                        new Student().userInfoCheck();
                        break OUTER;
                    }

                    case "NO" -> {
                        lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), BACKTOMAINMENU.getMessage());
                        break OUTER;
                    }

                    default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                }
            }

            threading(DEFAULT_DELAY.getValueInt());
        } while(true);
    }
    
    private void accountProcess(){ //function that process accounts for payments.
        clear();
        newLine(21);
        
        account = new Account();

        //0 == accountNo., 1 == accountBalance
        String[] accountBalance_details = getAccountData();

        if(accountBalance_details == null) account.setAccountBalance(BigDecimal.ZERO); //if there's to existing data for the user account
        else account.setAccountBalance(BigDecimal.valueOf(Double.parseDouble(accountBalance_details[1]))); //set the account balance
        account.accountUI(); //go to the account user-interface.
    }

    private String[] getAccountData(){
        String accountData = "";
        try {
            try(BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))){
                while((line_Data = reader.readLine()) != null){
                    if (line_Data.startsWith(getAccountNumber() + ":")) accountData = line_Data;
                    spinLoading(0, DEFAULT_NO_COLUMNS.getValueInt(), SHORTSTEP.getValueInt() - 2, "PROCESSING THE ACCOUNT", DEFAULT_ANIMATION_SPEED.getValueInt() - 29);
                }
            }
        } catch (IOException e) {}

        return !accountData.isBlank() ? accountData.split(":") : null;
    }

    //about the System Function
    private void aboutSystem(){
        clear();
        Texts.showTexts("termsAndConditionsReferences");
        threading(DEFAULT_DELAY.getValueInt());
    }

    public void enterChoice(){
        lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getValueInt() - 20, DEFAULT_NO_COLUMNS.getValueInt() - 6, "ENTER CHOICE : ");
    }

    public void resetVariables(){
        accountNo = 0;
        username = null;
        password = null;
        studentType = null;
        
        new Account().setAccountBalance(BigDecimal.ZERO);
    }
}

