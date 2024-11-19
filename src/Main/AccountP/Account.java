package Main.AccountP;
import Main.Assessment_PaymentP.Assessment;
import Main.CourseP.Course;
import Main.CourseP.CourseUI;
import Main.MainUI;
import Main.StudentP.Student;
import Others.System_IN.System_in;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Account extends MainUI{ //Simple Account System that can handle payments
    private static long accountBalance;
    public final File ACCOUNTFILE = new File("data\\Accounts.dat"); //file that contains accounts balance and etc.

    String finishView = "TO FINISH VIEWING"; //used in typeAnyCharacter function.

    public void accountUI(){
        String[] accountMenu = {"BALANCE", "PAYMENTS", "ENROLLMENT STATUS", "RETURN TO MAIN MENU"};
        LINELENGTH.setChoiceInt(40);
        
        OUTER:
        do {
            clear();
            newLinesHorizontalCustomCenteredTextln(16, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            customHorizontalCenteredTextln(System_in.getUsername() + "'s Account");
            customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            for(int i = 0; i < accountMenu.length; i++) lnprintlnWithTabs_spaces(7, 6, (i + 1) + ". " + accountMenu[i]);

            lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            
            try{
                lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt() - 20, DEFAULT_NO_COLUMNS.getChoiceInt() - 6, "ENTER CHOICE : ");
                int choice = Integer.parseInt(scan.nextLine());

                switch(choice){
                    case 1 -> accountBalance();
                    case 2 -> {}
                    case 3 -> enrollmentStatus();
                    case 4 -> {
                        lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), BACKTOMAINMENU.getMessage());
                        threading(SHORT_DELAY.getChoiceInt());
                        break OUTER;
                    }
                    default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
                }
            } catch(NumberFormatException e){
                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getChoiceInt());

        } while (true);
    }

    private void enrollmentStatus(){
        String[] enrollmentStatus_menu = {"PERSONAL INFORMATION", "SHOW COURSE CURRICULUM", "SHOW ASSESSMENT FEE'S", "RETURN TO ACCOUNT MENU"};
        LINELENGTH.setChoiceInt(50);
        
        OUTER:
        do {
            clear();
            newLinesHorizontalCustomCenteredTextln(16, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            customHorizontalCenteredTextln("ENROLLMENT STATUS");
            customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            if (!ASSESSMENT_FILE.exists()) {
                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), "YOU ARE NOT CURRENTLY ENROLLED TO ANY CCSICT COURSE");
                threading(DEFAULT_DELAY.getChoiceInt());
                break;
            } else for(int i = 0; i < enrollmentStatus_menu.length; i++) lnprintlnWithTabs_spaces(7, 8, (i + 1) + ". " + enrollmentStatus_menu[i]);

            lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            try {
                lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt() - 20, DEFAULT_NO_COLUMNS.getChoiceInt() - 6, "ENTER CHOICE : ");
                int choice = Integer.parseInt(scan.nextLine());

                switch(choice){
                    case 1 -> studentInfoShow();
                    case 2 -> courseCurriculumShow();
                    case 3 -> assessmentFeesShow();
                    case 4 -> {
                        lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), "BACK TO ACCOUNT'S MENU");
                        threading(SHORT_DELAY.getChoiceInt());
                        break OUTER;
                    }
                }
            } catch (NumberFormatException e) {
                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            
        } while (true);
    }

    private void courseCurriculumShow(){
        try{
            try(BufferedReader reader = new BufferedReader(new FileReader(COURSE_INFO_FILE))){
                array_line_data = new String[5];
                isAccount_condition = false;

                System.out.println();
                while((line_Data = reader.readLine()) != null){
                    if (line_Data.startsWith(String.valueOf(accountNo) + ":")){
                        isAccount_condition = true;
                        break;
                    }

                    spinLoading(0, DEFAULT_NO_COLUMNS.getChoiceInt(), SHORTSTEP.getChoiceInt() - 2, "FINDING THE DATA", DEFAULT_ANIMATION_SPEED.getChoiceInt());
                }

                if(isAccount_condition){
                    array_line_data = line_Data.split(":");
                    CourseUI.setCourseChoice(Integer.parseInt(array_line_data[1]));
                    CourseUI.setYearChoice(Integer.parseInt(array_line_data[2]));
                    CourseUI.setSemesterChoice(Integer.parseInt(array_line_data[3]));
                    CourseUI.setTrackChoice(Integer.parseInt(array_line_data[4]));

                    Course course = new Course();
                    course.curriculumSetupVariables(true);
                    typeAnyCharacter(finishView);
                } else{
                    System.out.print("\r");
                    print_alignText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), DEFAULT_NO_COLUMNS.getChoiceInt(), "THERE'S NO COURSE DATA FOR THIS ACCOUNT.");
                }
            }
        }catch(IOException e){
            lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), "FILE NOT FOUND!");
        }

        threading(DEFAULT_DELAY.getChoiceInt());
    }

    private void studentInfoShow(){
        Student student = new Student();

        try{
            try(BufferedReader reader = new BufferedReader(new FileReader(STUDENT_INFO_FILE))){
                array_line_data = new String[student.array_StudentInfo.length]; //set the size to handle the personal information of the user.

                int count = 0;
                while((line_Data = reader.readLine()) != null){
                    array_line_data[count] = line_Data;
                    count++;
                }

                student.studentInfoShow("PERSONAL INFORMATION", array_line_data);
                typeAnyCharacter(finishView);
            }
        } catch(IOException e){
            lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), "THERE'S NO EXISTING FILE DATA");
            threading(DEFAULT_DELAY.getChoiceInt());
        }
    }

    private void assessmentFeesShow(){
        if (COURSE_INFO_FILE.exists()) {
            Assessment assessment = new Assessment();
            assessment.assessmentFees(true);
            typeAnyCharacter(finishView);
        } else{
            lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), "THE SYSTEM CANNOT PROVIDE THIS REQUEST");
            threading(DEFAULT_DELAY.getChoiceInt());
        }

        
    }

    private void accountBalance(){
        
        LINELENGTH.setChoiceInt(45);
        do {
            clear();

        } while (true);
    }

    public static long getAccountBalance(){
        return Account.accountBalance;
    }

    public void setAccountBalance(long accountBalance){
        Account.accountBalance = accountBalance;
    }
}
