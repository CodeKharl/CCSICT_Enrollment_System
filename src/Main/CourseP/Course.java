package Main.CourseP;

import Main.Assessment_PaymentP.Assessment;
import Main.CourseP.CoursesPack.*;
import Main.Login_Signup.Login_Signup;
import Main.StudentP.Student;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Course extends CourseUI{
  private final List<String> courseNo = new ArrayList<>();
  private final List<String> courseTitle = new ArrayList<>();
  private final List<Integer> units = new ArrayList<>();
  private final List<Integer> lecture = new ArrayList<>();
  private final List<Integer> laboratory = new ArrayList<>();
  private final List<String> preRequisite = new ArrayList<>();
  
    //this array contains the header for the assessment. I also use in the curriculum function in Curriculum_Generate class.
  protected final String[] ASSESSMENTHEADER = {"ISABELA STATE UNIVERSITY", "Main Campus", "Echague, Isabela", "Subject Schedule and Assessment", "Date: " + MonthDay.now().getMonth() + " " + MonthDay.now().getDayOfMonth() + ", " + Year.now()};

  /*This following conditions and variables are construct with the use of enums (forCourseInput) for the function .state and .Curriculum_Generate.curriculum. */
  public String getCourseAcronym(){
    String[] courseAcronyms = {forCourseInput.BSCS.getChoice(), forCourseInput.BSIT.getChoice(), forCourseInput.BSIS.getChoice(), forCourseInput.BLIS.getChoice(), forCourseInput.DSA.getChoice()};
    return studentType.equals(graduateStudent()) ? forCourseInput.MIT.getChoice() : courseAcronyms[getCourseChoice() - 1];
  }
  
  public String getCourseFullName(){
    //for getting the user input IT track.
    String track = getTrackChoice() == 1 ? lnstringWithTabs_spaces(7, 3, "Web and Mobile Applications Development") : lnstringWithTabs_spaces(7, 12, "Network and Security");

    String[] courseFullNames = {"BACHELOR OF SCIENCE IN COMPUTER SCIENCE SCIENCE " + "(" + getCourseAcronym() + ")" + lnstringWithTabs_spaces(7, 8, "With Specialization Track in" + lnstringWithTabs_spaces(8, 8, "Data Mining")),
                                "BACHELOR OF SCIENCE IN INFORMATION TECHNOLOGY " + "(" + getCourseAcronym() + ")" + lnstringWithTabs_spaces(7, 8, "With Specialization Track in" + track),
                                "BACHELOR OF SCIENCE IN INFORMATION SYSTEMS " + "(" + getCourseAcronym() + ")" + lnstringWithTabs_spaces(8, 0, "With Specialization Track in") + lnstringWithTabs_spaces(6, 1, "Service Management for Business Process Outsourcing (BPO)"),
                                stringWithTabs(1, "BACHELOR OF LIBRARY AND INFORMATION SCIENCE"),
                                stringWithSpaces(5, "BACHELOR OF SCIENCE IN DATA SCIENCE AND") + lnstringWithTabs_spaces(8, 6, "ANALYSIS " + "(" + getCourseAcronym() + ")"),
                                };
    
    return studentType.equals(graduateStudent()) ? stringWithTabs_spaces(1, 5, "MASTER IN INFORMATION TECHNOLOGY") : courseFullNames[getCourseChoice() - 1];
  }

  public String getCourseYear(){
    String[] yearChoices = {forCourseInput.FY.getChoice(), forCourseInput.SY.getChoice(), forCourseInput.TY.getChoice(), forCourseInput.FOY.getChoice()};

    return getYearChoice() == 0 ? "" : yearChoices[getYearChoice() - 1];
  }

  public String getCourseSemester(){
    // 0 == Underbridge program
    return getSemesterChoice() == 0 ? forCourseInput.UB.getChoice() : sems[getSemesterChoice() - 1];
  }

  private String graduateStudent(){
    Login_Signup log_sign = new Login_Signup();
    return log_sign.college_classification[log_sign.college_classification.length - 1];
  }

  public void courseInputShow(){
    String[] courseInfo = {"COURSE", "YEAR", "SEMESTER"};
    LINELENGTH.setValueInt(42);

    OUTER:
      do {
          clear();
          newLinesHorizontalCenteredTextln(15,"=".repeat(LINELENGTH.getValueInt()));
          horizontalCenteredTextln("COURSE CONFIRMATION");
          horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

          if (getYearChoice() == 0) lnHorizontalCenteredTextln(getCourseSemester());
          else {
            String[] courseInfoValue = {getCourseAcronym(), getCourseYear(), getCourseSemester()};
            for(int i = 0; i < courseInfo.length; i++) lnprintlnWithTabs_spaces(7, 10, String.format("%s%" + (10 - courseInfo[i].length()) + "s: %s", courseInfo[i], "", courseInfoValue[i]));
          }

          lnHorizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

          lnHorizontalCenteredTextln("""
              Type "CONFIRM" to process your Assessment and proceed to Payment Option. "ESCAPE" to Return to Course Selection Menu.""");

          lnprint_alignText(DEFAULT_NO_COLUMNS.getValueInt() - 6, ARROW.getValueString());
          String confirmation = scan.nextLine();

          if(confirmation.isBlank()) lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), NEEDTOFILLUP.getMessage());
          else{
              switch (confirmation.toUpperCase()) {
                case "CONFIRM" -> {
                  isFinish_Selecting = true; //set the global variable in courseUI to true to break the while loop of the couseMenu to proceed to next function which is the payment.
                  curriculumSetupVariables(false);  //set to false since it will not showed the curriculum.
                  new Assessment().assessmentFees(false); //same as the currucilumSetupVariables
                  break OUTER;
                }

                case "ESCAPE" -> {
                  returnTo_CourseMenu();
                  break OUTER;
                }

                default -> lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), INVALIDCHOICE.getMessage());
              }
          }

          threading(DEFAULT_DELAY.getValueInt());
      } while(true);
  }

  public void curriculumSetupVariables(boolean isCourseShowed){
    //conditions to make the system go to specific class to get the data like (Course Title, Course Number, etc.).
    if (studentType.equals("Graduate Student")) new MIT().selectionProcess(isCourseShowed);
    else{
      switch(getCourseChoice()){ //undergraduate, transferee, and old student have same choice of course.
        case 1 -> new BSCS().selectionProcess(isCourseShowed);
        case 2 -> new BSIT().selectionProcess(isCourseShowed);
        case 3 -> new BSIS().selectionProcess(isCourseShowed);
        case 4 -> new BLIS().selectionProcess(isCourseShowed);
        case 5 -> new BDSA().selectionProcess(isCourseShowed);
      }
    }
  }

  public void selectionProcess(boolean isCourseShowed){ //use on different course classes
    System.out.println("NEED TO OVERRIDE");
  }

  //this function was used in this class and to the Account Class.
  public void connect(boolean isCourseShowed, List<String> courseNo, List<String> courseTitle, List<Integer> units, List<Integer> lecture, List<Integer> laboratory, List<String> preRequisite){
    if (!isCourseShowed) fileWrite(courseTitle, units, totalCalculation(units)); //this will write the following course info into the assessment file of the user.
    else{
      clear();
      spinLoading(21, DEFAULT_NO_COLUMNS.getValueInt(), DEFAULTSTEP.getValueInt(), "SETTING UP THE CURRICULUM", 70);
      new Curriculum_Generate().curriculum(courseNo.size(), getCourseFullName(), getCourseYear(), getCourseSemester(), courseNo, courseTitle, units, totalCalculation(units), lecture, laboratory, preRequisite, totalCalculation(lecture), totalCalculation(laboratory));
    }
  }

  private void fileWrite(List<String> courseTitle, List<Integer> units, int totalUnits){
    String[] stdInfo_course = {"Student ID : " + accountNo, "Semester : " + getCourseSemester() + ", " + Year.now() + "-" + (Year.now().getValue() + 1),
                              "Name : " + Student.student_info[2].toUpperCase() + ", " + Student.student_info[1] + " " + Student.student_info[3],
                              "Course & Year : " + getCourseAcronym() + " " + getCourseYear()};
    String[] details = {"Sched", "Sub Code", "Subject Description", "Units"};

    try{
      setDirectory("data\\Students Assessment"); //create a folder that stores the assessment file of the users.
      
      try(BufferedWriter writer = new BufferedWriter(new FileWriter(ASSESSMENT_FILE))){ //write the assessment header
        for(int i = 0; i < ASSESSMENTHEADER.length - 2; i++){ //use for-loop to write the header. Last Index was not include
            writer.newLine();
            writer.append(document_center_StringAlign(ASSESSMENTHEADER[i])); //by using the stringAlign and the max column, it can center the text.
        }

        writer.append("\n\n" + document_center_StringAlign(ASSESSMENTHEADER[ASSESSMENTHEADER.length - 2]));
        writer.append("\n\n" + document_stringAlign(forDocument.NOTEPAD_MAXCOLUMN.getValue() - 36, ASSESSMENTHEADER[ASSESSMENTHEADER.length - 1]) + "\n"); //the last index in the array
                  
        for (int i = 0; i < stdInfo_course.length; i++){
          PADDING.setValueInt(68 - stdInfo_course[i].length());
          writer.append(lnstringWithTabs_spaces(5, 8, String.format("%s%" + PADDING.getValueInt() + "s", stdInfo_course[i], "")));
          i++;
          writer.append(stdInfo_course[i]);
        }

        LINELENGTH.setValueInt(120);
        writer.append("\n\n" + document_center_StringAlign("-".repeat(LINELENGTH.getValueInt())));
        writer.append(lnstringWithTabs_spaces(5, 8, String.format("%s%15s%35s%35s", details[0], details[1], details[2], details[3])));
        writer.append("\n" + document_center_StringAlign("-".repeat(LINELENGTH.getValueInt())) + "\n\n");
        
        for (int i = 0; i < units.size(); i++){
          String sched = "H0" + (1 + i);
          String subCode = "G" + (i + 1);

          writer.append(stringWithTabs_spaces(5, 8, String.format("%s%11s", sched, subCode)));

          PADDING.setValueInt(20 + subCode.length());
          //this will make a separate approach on courseTitle that was to long and have newlines on it.
          if(!courseTitle.get(i).contains("\n")){
            String stripStr = courseTitle.get(i).strip();
            
            writer.append(String.format("%" + PADDING.getValueInt() + "s%s%" + (51 - stripStr.length()) + "s%s", "", stripStr, "", units.get(i)) + "\n\n"); //default. No newlines
          } else{ //have 1 or more newlines
            String[] str = courseTitle.get(i).split("\n"); //split the string to an array by using the variable of regex.

            writer.append(String.format("%" + PADDING.getValueInt() + "s%s", "", str[0].strip())); //the first line
            
            for (int y = 1; y < str.length; y++){
              writer.newLine();
              writer.append(stringWithTabs_spaces(10, 4, str[y].strip())); //the second or more lines after the first line.
            }

            writer.append(String.format("%" + (65 - str[str.length - 1].length() + "s%s"), "", units.get(i)) + "\n\n"); //the units
          }
        }

        writer.append(document_center_StringAlign("-".repeat(LINELENGTH.getValueInt())));
        writer.append(String.format("\n%95s%42d", "Total Units", totalUnits) + "\n");
        writer.append(document_center_StringAlign("-".repeat(LINELENGTH.getValueInt())));
      }
    } catch (IOException e) {}
  }

  private int totalCalculation(List<Integer> setOfIntegers){
    int total = 0;
    for(Integer i : setOfIntegers) total += i;

    return total;
  }

  protected void setCourseNo(List<String> courseNo){
    this.courseNo.addAll(courseNo);
  }

  protected List<String> getCourseNo(){
    return courseNo;
  }

  protected void setCourseTitle(List<String> courseTitle){
    this.courseTitle.addAll(courseTitle);
  }

  protected void setLaboratory(List<Integer> laboratory){
    this.laboratory.addAll(laboratory);
  }

  protected void setLectures(List<Integer> lecture){
    this.lecture.addAll(lecture);
  }

  protected void setPreRequisite(List<String> preRequisite){
    this.preRequisite.addAll(preRequisite);
  }

  protected void setPreRequisite(ArrayList<String> preRequisite){
    this.preRequisite.addAll(preRequisite);
  }

  protected List<String> addSameStringValueInList(int howMany, String value){
    List<String> arrayList = new ArrayList<>();

    for(int i = 0; i < howMany; i++) arrayList.add(value);
    return arrayList;
  }

  protected List<Integer> addSameIntValueInList(int howMany, Integer value){
    List<Integer> intList = new ArrayList<>();
    for(int i = 0; i < howMany; i++) intList.add(value);
    return intList;
  }
  
  protected void setUnits(List<Integer> units){
    this.units.addAll(units);
  }

  protected List<String> getCourseTitle(){
    return courseTitle;
  }

  protected List<Integer> getUnits(){
    return units;
  }

  protected List<Integer> getLectures(){
    return lecture;
  }

  protected List<Integer> getLaboratory(){
    return laboratory;
  }

  protected List<String> getPreRequisite(){
    return preRequisite;
  }
}

class Curriculum_Generate extends Course{
	public void curriculum(int numberOfSubs, String courseFullName, String year, String semester, List<String> courseNo, List<String> courseTitle, List<Integer> units, int totalUnits, List<Integer> lecture, List<Integer> laboratory, List<String> preRequisite, int totalLecture, int totalLaboratory){
    if(courseFullName.endsWith("(BPO)") && year.equals("First Year") && semester.equals("First Semester")){
      totalUnits -= 3;
      totalLecture -= 3;
    }
    
    // curriculum header
    LINELENGTH.setValueInt(91);
    clear();
    lnHorizontalCenteredTextln("Republic of the Philippines");
    horizontalCenteredTextln(ASSESSMENTHEADER[0]);
    horizontalCenteredText(ASSESSMENTHEADER[2]);
    lnHorizontalCenteredTextln("College of Computing Studies, Information and Communication Technology");
    horizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));

    printWithTabs_spaces(6, 3, courseFullName);

    newLinesHorizontalCenteredText(2, year);
    lnprintWithTabs_spaces(4, 2, semester);

    lnHorizontalCenteredTextln("=".repeat(LINELENGTH.getValueInt()));
    
    print_alignText(82, "Course No.  " + "|" );

    // curriculum for graduate program
    if(courseFullName.contains("MASTER")){
      printWithTabs_spaces(3, 1, "CourseTitle " +
      stringWithTabs(3, ("|" )) +
      stringWithSpaces(2, "Units") +
      stringWithSpaces(2, "|" ) +
      stringWithSpaces(2, "Pre-requisite") +
      lnstringWithTabs_spaces(4, 14, "|"  +
      tabsAndSpacesAfterString(3, 13) + stringWithTabs(3, "|" )) +
      stringWithSpaces(9, "|" ) +
      lnstringWithTabs_spaces(4, 14, "|"  +
      tabsAndSpacesAfterString(3, 13) + stringWithTabs(3, "|" ) +
      stringWithSpaces(9, "|" )) +
      stringWithTabs_newLines(1, 4,"=".repeat(LINELENGTH.getValueInt())));
      
      for (int i = 0; i < numberOfSubs; i++) {
        lnprintWithTabs_spaces(4, 2, courseNo.get(i) +
        stringWithTabs_spaces(1, 1, courseTitle.get(i) +
        tabsAndSpacesAfterString(2, 5) +
        units.get(i) +
        stringWithTabs(2, preRequisite.get(i))) +
        stringWithTabs_newLines(1, 4, "-".repeat(LINELENGTH.getValueInt())));
      }

      lnprintWithTabs_spaces(10, 3, "Total Units" +
      stringWithSpaces(2, "|" ) +
      stringWithSpaces(4, String.valueOf(totalUnits)) +
      stringWithSpaces(4, "|" ));
    }
    
    // curriculum for undergraduate program
    else{
      printWithTabs_spaces(2, 1, "CourseTitle " +
      stringWithTabs(2, "|" ) +
      stringWithSpaces(2, "Units") +
      stringWithSpaces(2, "|" ) +
      stringWithSpaces(5, "Units") +
      stringWithSpaces(5, "|" ) +
      stringWithSpaces(2, "Pre-requisite") +
      lnstringWithTabs_spaces(4, 14, "|" ) +
      stringWithTabs_spaces(2, 13, stringWithTabs(2, "|" )) +
      stringWithTabs_spaces(1, 2, "|" ) +
      "-".repeat(15) +
      "|"  + lnstringWithTabs_spaces(4, 14, "|" ) +
      stringWithTabs_spaces(2, 13, stringWithTabs(2, "|" )) +
      stringWithSpaces(9, "|" ) +
      stringWithSpaces(2, "Lec" +
      stringWithSpaces(2, "|" )) +
      stringWithSpaces(2, "Lab") +
      stringWithSpaces(2, "|" ) +
      stringWithTabs_newLines(1, 4,"=".repeat(LINELENGTH.getValueInt())) + "\n");

      for (int i = 0; i < numberOfSubs; i++) {
        printWithTabs_spaces(4, 2, courseNo.get(i) +
        stringWithTabs_spaces(1, 1, courseTitle.get(i)) +
        units.get(i) +
        stringWithTabs_spaces(1, 6, String.valueOf(lecture.get(i))) +
        stringWithTabs_spaces(1, 6, String.valueOf(laboratory.get(i))) +
        stringWithTabs(2, preRequisite.get(i) +
        stringWithTabs_newLines(1, 4, "-".repeat(LINELENGTH.getValueInt())) + "\n"));
      }

      printWithTabs_spaces(7, 11, "Total Units" +
      stringWithSpaces(3, "|"  +
      stringWithSpaces(3, String.valueOf(totalUnits))) +
      stringWithSpaces(3, "|" ) +
      stringWithSpaces(3, String.valueOf(totalLecture)) +
      stringWithSpaces(3, "|" ) +
      stringWithSpaces(3, String.valueOf(totalLaboratory)));
    }
    
    lnprintWithTabs_spaces(4, 0,"=".repeat(LINELENGTH.getValueInt()) + "\n");
  }
}
