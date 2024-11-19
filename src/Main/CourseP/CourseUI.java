package Main.CourseP;

import Main.MainUI;
import Others.System_Texts.Texts;

public class CourseUI extends MainUI{
    private static int courseChoice;
    private static int yearChoice;
    private static int semesterChoice;
    private static int trackChoice;

    Course course; //SuperClass Object use to get the override function in Course from MainUI by Polymorphism.

    public enum forCourseInput{ //enum that used through this class. This was also used in the Course class.
        BSCS("BSCS"), BSIT("BSIT"), BLIS("BLIS"), BSIS("BSIS"), DSA("DSA"), MIT("MIT"),
        FY("First Year"), SY("Second Year"), TY("Third Year"), FOY("Fourth Year"),
        FS("First Semester"), SS("Second Semester"), MY("Mid Year"), UB("(UNDER BRIDGE PROGRAM)");
    
        private final String choice;
    
        private forCourseInput(String choice){
            this.choice = choice;
        }
    
        public String getChoice(){
            return choice;
        }
    }

    //array that holds the choices of course
    private final String[] courseChoices = {"BACHELOR OF SCIENCE IN COMPUTER SCIENCE",
                                            "BACHELOR OF SCIENCE IN INFORMATION TECHNOLOGY",
                                            "BACHELOR OF SCIENCE IN INFORMATION SCIENCE",
                                            "BACHELOR OF LIBRARY INFORMATION SCIENCE",
                                            "BACHELOR OF SCIENCE IN DATA SCIENCE AND ANALYTICS",
                                            "MASTER IN INFORMATION TECHNOLOGY"};

    //this array was also used in course class
    protected String[] sems = {forCourseInput.FS.getChoice().toUpperCase(), forCourseInput.SS.getChoice().toUpperCase(), forCourseInput.MY.getChoice().toUpperCase()}; //choices for getting the semester.
    //Course Choices array will be the global variable "menu". This array will change or manipulate in the semester function.

    public void courseSelectionProcess(){
        String title = null; //holds what type of program
        //student classification during the signup of the account.
        
        String[] courses = {};

        String[] years = {forCourseInput.FY.getChoice().toUpperCase()}; //the array has default First Year string.
        //handle the choices for getting the year. This will be manipulate by what type of student the user is.

        switch (studentType) {
            case "Freshman", "Old Student", "Transferee" -> { //same choices
                title = "UNDERGRADUATE PROGRAMS";
                courses = new String[6]; //5 undergraduate programs plus 1 for the "CANCEL ENROLLMENT".
                System.arraycopy(courseChoices, 0, courses, 0, courses.length); //the courseChoices above will set the menu
            }

            case "Graduate Student" -> {
                title = "GRADUATE PROGRAM";
                courses = new String[2];
                courses[0] = courseChoices[courseChoices.length - 1]; //Master in Information Technology

                years = new String[2];
                years[0] = forCourseInput.FY.getChoice().toUpperCase();
                years[1] = forCourseInput.SY.getChoice().toUpperCase();
            }
        }
        
        switch (studentType) {
            case "Old Student", "Transferee" -> {
                years = new String[3]; //set the array size
                years[0] = forCourseInput.SY.getChoice().toUpperCase();
                years[1] = forCourseInput.TY.getChoice().toUpperCase();
                years[2] = forCourseInput.FY.getChoice().toUpperCase();
            }
        }

        courses[courses.length - 1] = "CANCEL ENROLLMENT"; //last index

        courseMenu(title, courses, years, sems);
    }

    public void courseMenu(String title, String[] courses, String[] years, String[] sems){
        
        LINELENGTH.setChoiceInt(70);

        OUTER:
        do{
            clear();
            newLinesHorizontalCustomCenteredTextln(14, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            customHorizontalCenteredTextln("AVAILABLE CCSICT COURSES");
            customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            
            lnprintln_alignText(128, title);

            for(int i = 0; i < courses.length; i++) lnprintWithTabs_spaces(6, 6, (i + 1) + ". " + courses[i]);
            
            newLinesHorizontalCustomCenteredTextln(2, HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            
            try {
                lnprint_alignText(134, "ENTER YOUR COURSE CHOICE : ");
                courseChoice = Integer.parseInt(scan.nextLine());
                
                if(courseChoice == courses.length) { //when the user choice last number in the choices.
                    lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), BACKTOMAINMENU.getMessage());
                    threading(DEFAULT_DELAY.getChoiceInt());
                    break;
                }

                switch(studentType){
                    case "Freshman", "Old Student", "Transferee" -> {
                        if(courseChoice == 2){
                            BSITtrackSelection(years, sems);
                            break OUTER;
                        }

                        if (courseChoice > 0 && courseChoice < courses.length) {
                            yearMenu(years, sems);
                            break OUTER;
                        }
                    }

                    case "Graduate Student" -> {
                        if(courseChoice > 0 && courseChoice < courses.length){
                            MITConfirmation(courses, years, sems);
                            break OUTER;
                        }
                    }
                }

                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
                
            } catch (NumberFormatException e) {
                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getChoiceInt());
        } while(true);
    }

    private void yearMenu(String[] years, String[] sems){//funcion for selecting year
        LINELENGTH.setChoiceInt(40); //set the linelength for repeating the HORIZONTAL_DOUBLE_LINE

        do {
            clear(); //clear the console
            newLinesHorizontalCustomCenteredTextln(17, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            customHorizontalCenteredTextln("YEAR SELECTION");
            customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            for(int i = 0; i < years.length; i++) lnprintlnWithTabs_spaces(7, 5, (i + 1) + ". " + years[i]);
            
            lnHorizontalCustomCenteredText(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            try {
                printWithNewLines_alignText(2, 136, "ENTER YEAR : ");
                yearChoice = Integer.parseInt(scan.nextLine());
                
                if(yearChoice > 0 && yearChoice <= years.length) {
                    semester(sems);
                    break;
                } else lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());

            } catch (NumberFormatException e) {
                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
            }

            threading(DEFAULT_DELAY.getChoiceInt());

        } while (true);
    }

    private void semester(String[] sems){
        LINELENGTH.setChoiceInt(41);

        do{
            clear();
            newLinesHorizontalCustomCenteredTextln(17, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            customHorizontalCenteredTextln("SEMESTER SELECTION");
            customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            int count = sems.length - 1; //don't have mid year.
            switch (String.valueOf(courseChoice) + String.valueOf(yearChoice)) {
                case "13", "22", "61" -> count = sems.length; //have mid year.
            }

            for (int i = 0; i < count; i++) lnprintlnWithTabs_spaces(7, 8, (i + 1) + ". " + sems[i]);
            
            lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            try {
                lnprint_alignText(DEFAULT_NO_COLUMNS.getChoiceInt() - 20, "ENTER SEMESTER : ");
                semesterChoice = Integer.parseInt(scan.nextLine());

                if(semesterChoice > 0 && semesterChoice <= count) break;
                else lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
                
            } catch (NumberFormatException e) {
                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getChoiceInt());

        } while(true);

        course = new Course();
        course.couseInputShow(courseChoice, yearChoice, semesterChoice, trackChoice);
    }

    private int BSITtrackSelection(String[] years, String[] sems){
        String[] tracks = {"1. WEB AND MOBILE APPLICATIONS" + lnstringWithTabs_spaces(7, 8, "DEVELOPMENT"),
                            "2. NETWORK SECURITY"};
        
        LINELENGTH.setChoiceInt(50);

        do{
            clear();
            newLinesHorizontalCustomCenteredTextln(17, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            customHorizontalCenteredTextln("TRACK SELECTION");
            customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            for(String track : tracks) lnprintlnWithTabs_spaces(7, 7, track);

            lnHorizontalCustomCenteredText(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            try{
                printWithNewLines_alignText(2, 138, "ENTER TRACK : ");
                trackChoice = Integer.parseInt(scan.nextLine());
                
                if (trackChoice > 0 && trackChoice < 3) {
                    yearMenu(years, sems);
                    break;
                } else lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
            } catch(NumberFormatException e){
                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getChoiceInt());

        } while(true);

        return trackChoice;
    }

    public void MITConfirmation(String[] courses, String[] years, String[] sems){
        OUTER:
        do{
            clear();

            newLinesHorizontalCustomCenteredTextln(19, "BEFORE YOU ENROLL TO MASTER IN INFORMATION TECHNOLOGRY, WE NEED TO KNOW THE UNDERGRADUATE PROGRAM YOU FINISH.");
            lnHorizontalCustomCenteredTextln("""
                    Enter "CHOOSE" to CONTINUE and "RETURN" to Back Menu.""");
            lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), 150, ARROW.getChoiceString());

            String confirmation = scan.nextLine();

            if (confirmation.isEmpty() || confirmation.isBlank()) lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NEEDTOFILLUP.getMessage());
            else{
                switch(confirmation){
                    case "CHOOSE" -> {
                        MITMenu(courses, years, sems);
                        break OUTER;
                    }
    
                    case "RETURN" -> {
                        lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), BACKTOMAINMENU.getMessage());
                        threading(DEFAULT_DELAY.getChoiceInt());
                        break OUTER;
                    }
    
                    default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
                }
            }
            
            threading(DEFAULT_DELAY.getChoiceInt());

        } while(true);
    }

    public void MITMenu(String[] courses, String[] years, String[] sems){
        courses = new String[7];
        
        System.arraycopy(courseChoices, 0, courses, 0, courses.length - 1);
        
        courses[courses.length - 2] = "NONE OF THE ABOVE";
        courses[courses.length - 1] = "CANCELL IT";
        
        LINELENGTH.setChoiceInt(70);
        OUTER:
        do{
            clear();
            newLinesHorizontalCustomCenteredTextln(11, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
            customHorizontalCenteredTextln("UNDERGRADUATE PROGRAMS");
            customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            for(int i = 0; i < courses.length; i++){
                if (i == 5) lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
                lnprintlnWithTabs_spaces(6, 7, (i + 1) + ". " + courses[i]);
            }
            
            lnHorizontalCustomCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

            lnprint_alignText(140, "ENTER THE UNDERGRADUATE PROGRAM : ");
            
            try {
                int choice = Integer.parseInt(scan.nextLine());
                switch (choice) {
                    case 1, 2, 3, 4, 5 -> {
                        yearMenu(years, sems);
                        break OUTER;
                    }
                    case 6 -> {
                        underBridgeProgram();
                        break OUTER;
                    }
                    case 7 -> {
                        lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), BACKTOMAINMENU.getMessage());
                        threading(DEFAULT_DELAY.getChoiceInt());
                        break OUTER;
                    }
                    default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
                }
            } catch (NumberFormatException e) {
                lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getChoiceInt());
        }while(true);
    }

    public void underBridgeProgram(){
        OUTER:
        do{
            clear();
            Texts.showTexts("underBridgeState");
            newLinesHorizontalCustomCenteredTextln(2, """
                    Enter "ACCEPT" to continue the enrollment and go to Curriculum Section. Enter "ESCAPE" to Cancel.""");

            lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), 150, ARROW.getChoiceString());
            String confirmation = scan.nextLine();

            if(!confirmation.isEmpty() || !confirmation.isBlank()){
                switch (confirmation) {
                    case "ACCEPT" -> {
                        course = new Course();
                        course.infoShow();
                        break OUTER;
                    }

                    case "ESCAPE" -> {
                        lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), BACKTOMAINMENU.getMessage());
                        threading(DEFAULT_DELAY.getChoiceInt());
                        break OUTER;
                    }

                    default -> lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), INVALIDCHOICE.getMessage());
                }

            } else lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NEEDTOFILLUP.getMessage());
            
            threading(DEFAULT_DELAY.getChoiceInt());

        } while(true);
    }

    public static int getCourseChoice() {
        return courseChoice;
    }

    public static int getYearChoice() {
        return yearChoice;
    }

    public static int getSemesterChoice() {
        return semesterChoice;
    }

    public static int getTrackChoice(){
        return trackChoice;
    }

    public static void setCourseChoice(int courseChoice){
        CourseUI.courseChoice = courseChoice;
    }

    public static void setYearChoice(int yearChoice){
        CourseUI.yearChoice = yearChoice;
    }

    public static void setSemesterChoice(int semesterChoice){
        CourseUI.semesterChoice = semesterChoice;
    }

    public static void setTrackChoice(int trackChoice){
        CourseUI.trackChoice = trackChoice;
    }
}
