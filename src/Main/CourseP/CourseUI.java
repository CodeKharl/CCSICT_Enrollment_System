package Main.CourseP;

import Main.MainUI;
import Others.System_Texts.Texts;

public class CourseUI extends MainUI{
    private static int courseChoice;
    private static int yearChoice;
    private static int semesterChoice;
    private static int trackChoice;

    protected static boolean isFinish_Selecting; //use for looping the course menu. It was also used in Course.java

    public enum forCourseInput{ //enum that used through this class. This was also used in the Course class.
        BSCS("BSCS"), BSIT("BSIT"), BLIS("BLIS"), BSIS("BSIS"), DSA("DSA"), MIT("MIT"),
        FY("FIRST YEAR"), SY("SECOND YEAR"), TY("THIRD YEAR"), FOY("FOURTH YEAR"),
        FS("FIRST SEMESTER"), SS("SECOND SEMESTER"), MY("MID YEAR"), UB("UNDER BRIDGE PROGRAM");
    
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
    protected String[] sems = {forCourseInput.FS.getChoice(), forCourseInput.SS.getChoice(), forCourseInput.MY.getChoice()}; //choices for getting the semester.

    public void courseSelectionProcess(){
        //holds what type of program
        //student classification during the signup of the account.
        String title = "UNDERGRADUATE PROGRAMS"; //set the default variable to undergraduate.

        //the array has default values for undergraduate programs.
        String[] courses = new String[6];
        System.arraycopy(courseChoices, 0, courses, 0, courseChoices.length - 1);

        String[] years = {forCourseInput.FY.getChoice()}; //the array has default First Year string.
        //handle the choices for getting the year. This will be manipulate by what type of student the user is.

        switch (studentType) {
            case "Graduate Student" -> {
                title = "GRADUATE PROGRAM";
                courses = new String[2];
                courses[0] = courseChoices[courseChoices.length - 1]; //Master in Information Technology

                years = new String[2];
                years[0] = forCourseInput.FY.getChoice();
                years[1] = forCourseInput.SY.getChoice();
            }

            case "Old Student", "Transferee" -> {
                years = new String[3]; //set the array size
                years[0] = forCourseInput.SY.getChoice();
                years[1] = forCourseInput.TY.getChoice();
                years[2] = forCourseInput.FOY.getChoice();
            }
        }
        
        courses[courses.length - 1] = "CANCEL ENROLLMENT"; //last index

        isFinish_Selecting = false; //set it to false that will used it below.
        
        while (!isFinish_Selecting) courseMenu(title, courses, years, sems);
    }

    public void courseMenu(String title, String[] courses, String[] years, String[] sems){
        LINELENGTH.setValueInt(70);

        OUTER:
        do{
            clear();
            newLinesHorizontalCenteredTextln(14,"=".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("AVAILABLE CCSICT COURSES");
            horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));
            
            lnprintln_alignText(128, title);

            for(int i = 0; i < courses.length; i++) lnprintWithTabs_spaces(6, 6, (i + 1) + ". " + courses[i]);
            
            newLinesHorizontalCenteredTextln(2, "-".repeat(LINELENGTH.getValueInt()));
            
            try {
                lnprint_alignText(134, "ENTER YOUR COURSE CHOICE : ");
                courseChoice = Integer.parseInt(scan.nextLine());
                
                if(courseChoice == courses.length) { //when the user choice last number in the choices. It will return to Main menu and cancell the enrollment.
                    STUDENT_INFO_FILE.delete();
                    lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), BACKTOMAINMENU.getMessage());
                    isFinish_Selecting = true;
                    threading(SHORT_DELAY.getValueInt());
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

                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
            } catch (NumberFormatException e) {
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getValueInt());
        } while(true);
    }

    private void yearMenu(String[] years, String[] sems){//funcion for selecting year
        LINELENGTH.setValueInt(40); //set the linelength for repeating a string

        do {
            clear(); //clear the console
            newLinesHorizontalCenteredTextln(17,"=".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("YEAR SELECTION");
            horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

            for(int i = 0; i < years.length; i++) lnprintlnWithTabs_spaces(7, 5, (i + 1) + ". " + years[i]);
            
            lnHorizontalCenteredText("-".repeat(LINELENGTH.getValueInt()));

            try {
                printWithNewLines_alignText(2, 136, "ENTER YEAR : ");
                yearChoice = Integer.parseInt(scan.nextLine());
                
                if(yearChoice > 0 && yearChoice <= years.length) {
                    if (studentType.equals("Old Student") || studentType.equals("Transferee")) yearChoice += 1;
                    semester(sems);
                    break;
                } else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
            } catch (NumberFormatException e) {
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
            }

            threading(DEFAULT_DELAY.getValueInt());
        } while (true);
    }

    private void semester(String[] sems){
        LINELENGTH.setValueInt(41);

        do{
            clear();
            newLinesHorizontalCenteredTextln(17,"=".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("SEMESTER SELECTION");
            horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

            int count = switch (studentType.charAt(0) + String.valueOf(courseChoice) + String.valueOf(yearChoice)) {
                case "O13", "O22", "T13", "T22", "G11" -> sems.length; //have mid year.
                default -> sems.length - 1;
            };

            for (int i = 0; i < count; i++) lnprintlnWithTabs_spaces(7, 8, (i + 1) + ". " + sems[i]);
            
            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            try {
                lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 20, "ENTER SEMESTER : ");
                semesterChoice = Integer.parseInt(scan.nextLine());

                if(semesterChoice > 0 && semesterChoice <= count) break;
                else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                
            } catch (NumberFormatException e) {
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getValueInt());
        } while(true);

        new Course().courseInputShow();
    }

    private int BSITtrackSelection(String[] years, String[] sems){
        String[] tracks = {"1. WEB AND MOBILE APPLICATIONS" + lnstringWithTabs_spaces(7, 8, "DEVELOPMENT"),
                            "2. NETWORK SECURITY"};
        
        LINELENGTH.setValueInt(50);

        do{
            clear();
            newLinesHorizontalCenteredTextln(17,"=".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("TRACK SELECTION");
            horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

            for(String track : tracks) lnprintlnWithTabs_spaces(7, 7, track);

            lnHorizontalCenteredText("-".repeat(LINELENGTH.getValueInt()));

            try{
                printWithNewLines_alignText(2, 138, "ENTER TRACK : ");
                trackChoice = Integer.parseInt(scan.nextLine());
                
                if (trackChoice > 0 && trackChoice < 3) {
                    yearMenu(years, sems);
                    break;
                } else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
            } catch(NumberFormatException e){
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getValueInt());
        } while(true);

        return trackChoice;
    }

    private void MITConfirmation(String[] courses, String[] years, String[] sems){
        OUTER:
        do{
            clear();

            newLinesHorizontalCenteredTextln(19, "BEFORE YOU ENROLL TO MASTER IN INFORMATION TECHNOLOGRY, WE NEED TO KNOW THE UNDERGRADUATE PROGRAM YOU FINISH.");
            lnHorizontalCenteredTextln("""
                    Enter "CHOOSE" to CONTINUE and "RETURN" to Course Selection Menu.""");
            lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), 150, ARROW.getValueString());

            String confirmation = scan.nextLine();

            if (confirmation.isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
            else{
                switch(confirmation.toUpperCase()){
                    case "CHOOSE" -> {
                        MITMenu(courses, years, sems);
                        break OUTER;
                    }
    
                    case "RETURN" -> {
                        returnTo_CourseMenu();
                        break OUTER;
                    }
    
                    default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                }
            }
            
            threading(DEFAULT_DELAY.getValueInt());
        } while(true);
    }

    private void MITMenu(String[] courses, String[] years, String[] sems){
        courses = new String[7];
        
        System.arraycopy(courseChoices, 0, courses, 0, courses.length - 1);
        
        courses[courses.length - 2] = "NONE OF THE ABOVE";
        courses[courses.length - 1] = "RETURN TO COURSE MENU";
        
        LINELENGTH.setValueInt(70);
        OUTER:
        do{
            clear();
            newLinesHorizontalCenteredTextln(11,"=".repeat(LINELENGTH.getValueInt()));
            horizontalCenteredTextln("UNDERGRADUATE PROGRAMS");
            horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

            for(int i = 0; i < courses.length; i++){
                if (i == 5) lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));
                lnprintlnWithTabs_spaces(6, 7, (i + 1) + ". " + courses[i]);
            }
            
            lnHorizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));

            lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 16, "ENTER THE UNDERGRADUATE PROGRAM : ");
            
            try {
                switch (Integer.parseInt(scan.nextLine())) {
                    case 1, 2, 3, 4, 5 -> {
                        yearMenu(years, sems);
                        break OUTER;
                    }
                    case 6 -> {
                        underBridgeProgram();
                        break OUTER;
                    }
                    case 7 -> {
                        returnTo_CourseMenu();
                        break OUTER;
                    }
                    default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                }
            } catch (NumberFormatException e) {
                lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NUMBERFORMATEXCEPTION.getMessage());
            }

            threading(DEFAULT_DELAY.getValueInt());
        }while(true);
    }

    private void underBridgeProgram(){
        OUTER:
        do{
            clear();
            Texts.showTexts("underBridgeState");
            newLinesHorizontalCenteredTextln(2, """
                    Enter "ACCEPT" to continue the enrollment and go to Payment Option. Enter "ESCAPE" to Return to Course Selection Menu.""");

            lnprint_alignText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), 150, ARROW.getValueString());
            String confirmation = scan.nextLine();

            if(!confirmation.isBlank()){
                switch (confirmation.toUpperCase()) {
                    case "ACCEPT" -> {
                        setYearChoice(0); //to locate the under bridge program
                        setSemesterChoice(0);
                        new Course().courseInputShow();
                        break OUTER;
                    }

                    case "ESCAPE" -> {
                        returnTo_CourseMenu();
                        break OUTER;
                    }

                    default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
                }

            } else lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
            
            threading(DEFAULT_DELAY.getValueInt());
        } while(true);
    }

    protected void returnTo_CourseMenu(){
        lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "RETURN TO COURSE SELECTION MENU...");
        threading(SHORT_DELAY.getValueInt());
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

