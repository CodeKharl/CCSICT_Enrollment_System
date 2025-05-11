package Main.StudentP;

import Main.CourseP.CourseUI;
import Main.MainUI;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;
import java.time.Year;

public class Student extends MainUI{
    //this array was also used in Account Class.
    public String[] array_StudentInfo = {"CLASSIFIED AS","FIRST NAME", "LAST NAME", "MIDDLE NAME", "SEX", "AGE", "DATE OF BIRTH", "BIRTH PLACE", "HOME ADDRESS", "PHONE NUMBER", "EMAIL ADDRESS"};

    public static String[] student_info = new String[11];
    //arrayList tha holding the user personal information.
    //0:studentType, 1:firstName, 2:lastName, 3:middleName, 4:sex, 5:age, 6:birthDay, 7:birthPlace, 8:homeAddress, 9:phoneNumber, 10:emailAddress

    public void userInfoCheck(){
        int count = 1; //this local variable was used all over the function to make a faster response.

        LINELENGTH.setValueInt(65);

        student_info[0] = getStudentType();

        do{
            String input = "";

            clear();
            newLinesHorizontalCenteredText(18 - count, "-".repeat(LINELENGTH.getValueInt()));
            lnHorizontalCenteredText("PERSONAL INFORMATION");
            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));
            
            for(int i = 0; i < count; i++) lnprintlnWithTabs_spaces(7, 4, (array_StudentInfo[i] + " : " + student_info[i]));

            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            if(count >= 6 && count <= 8){
                lnHorizontalCenteredTextln(array_StudentInfo[count]);
                input = "Null";
            }
            else{
                lnprintWithTabs_spaces(7, 3, "ENTER " + array_StudentInfo[count] + getAddOns(count) + " : ");
                input = scan.nextLine();
            }

            try{
                String output;
                
                if (input.isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
                else{
                    output = switch(count){
                        case 1, 2, 3 -> nameChecker(input);
                        case 4 -> sexChecker(input);
                        case 5 -> ageChecker(input);
                        case 6 -> dateOfBirthChecker(Integer.parseInt(student_info[5]));
                        case 7, 8 -> addressChecker();
                        case 9 -> phoneNoChecker(input);
                        case 10 -> emailAddChecker(input);
                        default -> null;
                    };
                
                    if (output != null){
                        student_info[count] = output;
                        count++;
                    } else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), MESSAGE.invalidInputModifier(array_StudentInfo[count]));
                }
            } catch(NumberFormatException e){
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(SHORT_DELAY.getValueInt());
        } while(count < array_StudentInfo.length);

        studentInfoConfirmation();
    }

    private String getAddOns(int count){
        String addOns = switch(count){
            case 1, 2, 3 -> " (*Characters Only)";
            case 4 -> " (M or F)"; //contatinates to the array_studentInfo to handles the SEX option wether Male(M) or Female(F).
            case 5 -> " (Min : 15)"; //for age minimum and maximum.
            case 9 -> " (11 Digits)";
            case 10 -> " (*@gmail.com)";
            default -> null;
        };

        return addOns;
    }

    private String nameChecker(String name){
        return !name.matches(".*\\d.*") ? name.toUpperCase().charAt(0) + name.substring(1) : null;
    }

    private String ageChecker(String age){
        return Integer.parseInt(age) >= 15 ? age : null;
    }

    private String sexChecker(String sex){
        sex = switch(sex.toUpperCase()){
            case "MALE", "M" -> "MALE";
            case "FEMALE", "F" -> "FEMALE";
            default -> null;
        };

        return sex;
    }

    private String dateOfBirthChecker(int age){
        String[] birthDate_format = {"BIRTH MONTH", "BIRTH DAY", "BIRTH YEAR"};
        String[] input_holder = new String[3];

        int count = 0;
        while(count < input_holder.length){
            lnprintWithTabs_spaces(7, 6, "ENTER " + birthDate_format[count] + " : ");
            input_holder[count] = scan.nextLine();
            
            if (input_holder[count].isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
            else if ((input_holder[count] = forBirthChecker(count, input_holder, age)) != null) count++;
            else return null;
        }

        return input_holder[0] + ", " + input_holder[1] + ", " + input_holder[2];
    }

    private String forBirthChecker(int count, String[] input_holder, int age){
        switch(count){
            case 0 -> {
                for(int i = 1; i <= Month.values().length; i++){
                    String month = String.valueOf(Month.of(i));
                    if(input_holder[0].equalsIgnoreCase(month) || input_holder[0].equals(String.valueOf(Month.of(i).getValue()))) return month;
                }
            }

            case 1 -> {
                int day = Integer.parseInt(input_holder[1]);
                return day > 0 && day <=  Month.valueOf(input_holder[0]).maxLength() ? input_holder[1] : null;
            }

            case 2 -> {
                return Integer.parseInt(input_holder[2]) == (Year.now().getValue() - (age + 1)) ? input_holder[2] : null;
            }
        }

        return null;
    }

    private String addressChecker(){
        String[] location_format = {"BARANGGAY", "MUNICIPALITY", "PROVINCE"};
        String[] location_holder = new String[3];
        
        int count = 0;
        while(count < location_holder.length){
            lnprintWithTabs_spaces(7, 6, "ENTER " + location_format[count] + " : ");
            location_holder[count] = scan.nextLine().toUpperCase();

            if(location_holder[count].isBlank()) return null;
            else count++;
        }

        return location_holder[0] + ", " + location_holder[1] + ", " + location_holder[2];
    }

    private String phoneNoChecker(String phoneNumber){
        return (phoneNumber.startsWith("09") && phoneNumber.length() == 11) ? phoneNumber : null;
    }

    private String emailAddChecker(String email){
        String gmail = "@gmail.com";
        return email.endsWith(gmail) && !email.equals(gmail) ? email : null;
    }

    public void studentInfoShow(String title, String[] studentInfos){ //this function is also used in account class
        LINELENGTH.setValueInt(80);
        clear();

        newLinesHorizontalCenteredText(7, "-".repeat(LINELENGTH.getValueInt()));
        lnHorizontalCenteredText(title);
        lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));
        
        for(int i = 0; i < student_info.length; i++) {
            PADDING.setValueInt(20 - array_StudentInfo[i].length()); //set a padding to make the text organized.
            lnprintlnWithTabs_spaces(7, 4, String.format("%s%" + PADDING.getValueInt() +"s: %s", array_StudentInfo[i], "", studentInfos[i])); //print the student information in appropriate structure.
        }

        lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));
    }

    private void studentInfoConfirmation(){
        OUTER:
        do {
            studentInfoShow("INFO CONFIRMATION", student_info);
            lnHorizontalCenteredTextln("""
                    TYPE "CONFIRM" TO CONTINUE, "ESCAPE" TO CANCEL.""");
            lnprint_alignText_animation(10, 146, ARROW.getValueString());
            String confirm = scan.nextLine();
            
            if (confirm.isBlank()) lnHorizontalCenteredText(NEEDTOFILLUP.getMessage());
            else {
                switch(confirm.toUpperCase()){
                    case "CONFIRM" -> {
                        fileWrite();

                        lnHorizontalCenteredText("ENTERING COURSE SELECTION");
                        threading(LONG_DELAY.getValueInt());
                        
                        new CourseUI().courseSelectionProcess();
                        break OUTER;
                    }
                    
                    case "ESCAPE" -> {
                        lnHorizontalCenteredText(BACKTOMAINMENU.getMessage());
                        break OUTER;
                    }
                    default -> lnHorizontalCenteredText(INVALIDCHOICE.getMessage());
                }
            }
                
            threading(DEFAULT_DELAY.getValueInt());
        } while (true);
    }

    @Override
    public void fileWrite(){
        try {
            setDirectory("data\\Students Info");
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
