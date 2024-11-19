package Main.StudentP;

import Main.CourseP.CourseUI;
import Main.MainUI;
import Others.System_IN.System_in;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;

public class Student extends MainUI{
    protected final char[] partInfo = {'A', 'B'};
    
    //this array was also used in Account Class.
    public String[] array_StudentInfo = {"CLASSIFIED AS","FIRST NAME", "LAST NAME", "MIDDLE NAME", "SEX", "AGE", "DATE OF BIRTH", "BIRTH PLACE", "HOME ADDRESS", "PHONE NUMBER", "EMAIL ADDRESS"};
    private final String[] checkFor = {null, "checkForFirstName", "checkForLastName", "checkForMiddleName", "checkForSex", "checkForAge", "checkForBirthDay", "checkForBirthPlace", "checkForHomeAddress", "checkForPhoneNumber", "checkForEmailAddress"};    
    
    protected final String[] birthDate_format = {"BIRTH MONTH", "BIRTH DAY", "BIRTH YEAR"};
    protected final String[] location_format = {"BARANGGAY", "MUNICIPALITY", "PROVINCE"};

    public static String[] student_info = new String[11];
    //arrayList tha holding the user personal information.
    //0:studentType, 1:firstName, 2:lastName, 3:middleName, 4:sex, 5:age, 6:birthDay, 7:birthPlace, 8:homeAddress, 9:phoneNumber, 10:emailAddress

    public void userInfoCheck(){
        int count = 1; //this local variable was used all over the function to make a faster response.

        LINELENGTH.setChoiceInt(65);

        student_info[0] = getStudentType();

        do{
            String output;

            clear();
            newLinesHorizontalCustomCenteredText(18 - count, HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            lnHorizontalCustomCenteredText("PERSONAL INFORMATION (" + partInfo[0] + ")");
            lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            
            for(int i = 0; i < count; i++) lnprintlnWithTabs_spaces(7, 4, (array_StudentInfo[i] + " : " + student_info[i]));

            lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            switch (array_StudentInfo[count]) {
                case "DATE OF BIRTH" -> output = date_location_inputProcess(count, birthDate_format, array_StudentInfo[count]);
                case "BIRTH PLACE", "HOME ADDRESS" -> output = date_location_inputProcess(count, location_format, array_StudentInfo[count]);
                default -> {
                    String addOns = "";
                    switch(array_StudentInfo[count]){
                        case "SEX" -> addOns = " (M or F)"; //contatinates to the array_studentInfo to handles the SEX option wether Male(M) or Female(F).
                        case "AGE" -> addOns =  " (MIN:16, MAX:80)"; //for age minimum and maximum.
                        case "PHONE NUMBER" -> addOns = " (11 Digits)";
                        case "EMAIL ADDRESS" -> addOns = " (*@gmail.com)";
                    }

                    output = scanAndCheckForUserInput(count, "ENTER " + array_StudentInfo[count] + addOns + " : ", null); //e.g Output: "ENTER FIRST NAME : "
                }
            }
            
            if (output != null){
                student_info[count] = output;
                count++;
            }

        } while(count < array_StudentInfo.length);

        studentInfoConfirmation();
    }

    private String date_location_inputProcess(int count, String[] textFormat, String title){
        String[] inputDates_location = new String[3]; //hold the temporary data of dates and location when processing the inputs of the user.
        int dateLocation_count = 0;

        String output = "";

        lnHorizontalCustomCenteredTextln(title);
        while(dateLocation_count < textFormat.length){
            inputDates_location[dateLocation_count] = scanAndCheckForUserInput(count, "ENTER " + textFormat[dateLocation_count] + " : ", inputDates_location); //e.g Output: "ENTER FIRST NAME : "
            
            if (inputDates_location[dateLocation_count] != null) {
                output += inputDates_location[dateLocation_count] + " ";
                dateLocation_count++;
            } else{
                output = null;
                break;
            }
        }

        return output;
    }

    private String scanAndCheckForUserInput(int count, String statement, String[] inputDates_location) {
        String input;

        try {
            lnprintWithTabs_spaces(7, 3, statement);
            input = scan.nextLine();

            if (input.isBlank() || input.isEmpty()) {
                lnprint_alignText_animation(30, DEFAULT_NO_COLUMNS.getChoiceInt(), NEEDTOFILLUP.getMessage());
                threading(DEFAULT_DELAY.getChoiceInt());
                return null;
            }
            else {
                switch (checkFor[count]) {
                    case "checkForFirstName", "checkForLastName", "checkForMiddleName", "checkForBirthPlace", "checkForHomeAddress" -> {
                        String[] strings = input.split(" "); //stores the input into an array.
                        
                        //set the first character in a string to uppercase and then contatinates the followings subString
                        try {
                            input = ""; //input will set to default;
                            for (String inputs : strings) input += Character.toUpperCase(inputs.charAt(0)) + inputs.substring(1) + " ";
                            return input; //return the modified firstName
                        } catch (StringIndexOutOfBoundsException e) {}
                    }
                    
                    case "checkForSex" -> {
                        switch (input.toUpperCase()) {
                            case "M", "MALE" -> {
                                return "MALE";
                            }

                            case "F", "FEMALE" -> {
                                return "FEMALE";
                            }
                        }
                    }

                    case "checkForAge" -> {
                        int ageCheck = Integer.parseInt(input);
                        
                        if (ageCheck > 15 && ageCheck <= 80) {
                            return input;
                        }
                    }

                    case "checkForBirthDay" -> {
                        int for_dateFormat = 0;  //needs to determine if it is day, month, year. Needs for printing the invalid message with the data format variable.

                        if (statement.contains(birthDate_format[0])) { //if the statement has month include.
                            int month_count = 1; //need for the loop below
                            
                            while (month_count <= Month.values().length) {
                                if(input.toUpperCase().equals(String.valueOf(Month.of(month_count))) || input.equals(String.valueOf(Month.of(month_count).getValue()))){
                                    input = String.valueOf(Month.of(month_count)); //the value from the Month class will become the value of this variable.
                                    return input;
                                }

                                month_count++;
                            }
                            
                        } else if (statement.contains(birthDate_format[1])) { //if it is Day
                            if(Integer.parseInt(input) > 0 && Integer.parseInt(input) <= Month.valueOf(inputDates_location[0]).maxLength()){
                                return input;
                            }

                            for_dateFormat++; //it will increment.

                        } else{ //for Year
                            if(Integer.parseInt(input) > 1990 && Integer.parseInt(input) <= 2020){
                                return input;
                            }

                            for_dateFormat = 2;
                        }

                        lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), MESSAGE.invalidInputModifier(birthDate_format[for_dateFormat]));
                        threading(DEFAULT_DELAY.getChoiceInt());

                        return null;
                    }

                    case "checkForPhoneNumber" -> {
                        if (input.length() == 11 && input.startsWith("09")) {
                            return input; //return phoneNumber
                        }
                    }
                    
                    case "checkForEmailAddress" -> {
                        String email = "@gmail.com";

                        if (input.endsWith(email) && !input.equalsIgnoreCase(email)){
                            return input; //return the emailAddress
                        }
                    }
                }

                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), MESSAGE.invalidInputModifier(array_StudentInfo[count]));
            }
            
        } catch (NumberFormatException e) { //throw an error when the input needs to be number for integer or long variables.
            lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NUMBERFORMATEXCEPTION.getMessage());
        }

        threading(LONG_DELAY.getChoiceInt());

        return null; //return null if the input does not approved by any conditions.
    }

    public void studentInfoShow(String title, String[] studentInfos){ //this function is also used in account class
        LINELENGTH.setChoiceInt(80);
        clear();
        
        newLinesHorizontalCustomCenteredText(7, HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
        lnHorizontalCustomCenteredText(title);
        lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
        
        for(int i = 0; i < student_info.length; i++) {
            PADDING.setChoiceInt(20 - array_StudentInfo[i].length()); //set a padding to make the text organized.
            lnprintlnWithTabs_spaces(7, 4, String.format("%s%" + PADDING.getChoiceInt() +"s: %s", array_StudentInfo[i], "", studentInfos[i])); //print the student information in appropriate structure.
        }

        lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
    }

    private void studentInfoConfirmation(){
        OUTER:
        do {
            studentInfoShow("INFO CONFIRMATION", student_info);
            lnHorizontalCustomCenteredTextln("""
                    TYPE "CONFIRM" TO CONTINUE, "ESCAPE" TO CANCEL.""");
            lnprint_alignText_animation(10, 146, ARROW.getChoiceString());
            String confirm = scan.nextLine();
            
            if (confirm.isBlank() || confirm.isEmpty()) lnHorizontalCustomCenteredText(NEEDTOFILLUP.getMessage());
            else {
                switch(confirm){
                    case "CONFIRM" -> {
                        System_in stdInfo = new StudentInfo_handling();
                        stdInfo.fileWrite();

                        lnHorizontalCustomCenteredText("ENTERING COURSE SELECTION");
                        threading(LONG_DELAY.getChoiceInt());
                        
                        CourseUI cui = new CourseUI();
                        cui.courseSelectionProcess();;
                        break OUTER;
                    }
                    
                    case "ESCAPE" -> {
                        lnHorizontalCustomCenteredText(BACKTOMAINMENU.getMessage());
                        threading(LONG_DELAY.getChoiceInt());
                        break OUTER;
                    }
                    default -> lnHorizontalCustomCenteredText(INVALIDCHOICE.getMessage());
                }
            }
                
            threading(LONG_DELAY.getChoiceInt());

        } while (true);
    }
}

class StudentInfo_handling extends Student{
    @Override
    public void fileWrite(){
        try {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_INFO_FILE))){
                for (String info : student_info) {
                    writer.append(info);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
        }
    }
}
