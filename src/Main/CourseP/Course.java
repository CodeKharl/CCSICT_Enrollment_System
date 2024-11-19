package Main.CourseP;

import Main.Assessment_PaymentP.Assessment;
import Main.CourseP.CoursesPack.*;
import Main.StudentP.Student;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Course extends CourseUI{
  private final ArrayList<String> courseNo = new ArrayList<>();
  private final ArrayList<String> courseTitle = new ArrayList<>();
  private final ArrayList<Integer> units = new ArrayList<>();
  private final ArrayList<Integer> lecture = new ArrayList<>();
  private final ArrayList<Integer> laboratory = new ArrayList<>();
  private final ArrayList<String> preRequisite = new ArrayList<>();
  
  private int totalUnits;
  private int numberOfSubs;
  private int totalLecture;
  private int totalLaboratory;

  /*This following conditions and variables are construct with the use of enums (forCourseInput) for the function .state and .Curriculum_Generate.curriculum. */
  public String getCourseAcronym(){
    String[] courseAcronyms = {forCourseInput.BSCS.getChoice(), forCourseInput.BSIT.getChoice(), forCourseInput.BSIS.getChoice(), forCourseInput.BLIS.getChoice(), forCourseInput.DSA.getChoice(), forCourseInput.MIT.getChoice()};
    return courseAcronyms[getCourseChoice() - 1];
  }
  
  public String getCourseFullName(){
    String track; //for getting the user input course track.
    if (getTrackChoice() == 1) track = lnstringWithTabs_spaces(7, 3, "Web and Mobile Applications Development");
    else track = lnstringWithTabs_spaces(7, 12, "Network and Security");

    String[] courseFullNames = {"BACHELOR OF SCIENCE IN COMPUTER SCIENCE SCIENCE " + "(" + getCourseAcronym() + ")" + lnstringWithTabs_spaces(7, 8, "With Specialization Track in" + lnstringWithTabs_spaces(8, 8, "Data Mining")),
                                "BACHELOR OF SCIENCE IN INFORMATION TECHNOLOGY " + "(" + getCourseAcronym() + ")" + lnstringWithTabs_spaces(7, 8, "With Specialization Track in" + track),
                                "BACHELOR OF SCIENCE IN INFORMATION SYSTEMS " + "(" + getCourseAcronym() + ")" + lnstringWithTabs_spaces(8, 0, "With Specialization Track in") + lnstringWithTabs_spaces(6, 1, "Service Management for Business Process Outsourcing (BPO)"),
                                stringWithTabs(1, "BACHELOR OF LIBRARY AND INFORMATION SCIENCE"),
                                stringWithSpaces(5, "BACHELOR OF SCIENCE IN DATA SCIENCE AND") + lnstringWithTabs_spaces(8, 6, "ANALYSIS " + "(" + getCourseAcronym() + ")"),
                                stringWithTabs_spaces(1, 5, "MASTER IN INFORMATION TECHNOLOGY")};
    
    return courseFullNames[getCourseChoice() - 1];
  }

  public String getCourseYear(){
    String[] yearChoices = {forCourseInput.FY.getChoice(), forCourseInput.SY.getChoice(), forCourseInput.TY.getChoice(), forCourseInput.FY.getChoice()};
    return yearChoices[getYearChoice() - 1];
  }

  public String getCourseSemester(){
    if (getSemesterChoice() == 0) return forCourseInput.UB.getChoice(); //not a sem (for Under Bridge Program).
    return sems[getSemesterChoice() - 1];
  }

  public void couseInputShow(int courseChoice, int yearChoice, int semesterChoice, int trackChoice){
    String[] courseInfo = {"COURSE   : " + getCourseAcronym(), "YEAR     : " + getCourseYear(), "SEMESTER : " + getCourseSemester()};

    LINELENGTH.setChoiceInt(42);

    OUTER:
      do {
          clear();
          newLinesHorizontalCustomCenteredTextln(15, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
          customHorizontalCenteredTextln("COURSE CONFIRMATION");
          customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

          if (getCourseChoice() == 6 && getYearChoice() == 0) lnHorizontalCustomCenteredTextln(getCourseSemester());
          else for(String info : courseInfo) lnprintlnWithTabs_spaces(7, 8, info);
          
          lnHorizontalCustomCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

          lnHorizontalCustomCenteredTextln("""
              Type "CONFIRM" to Continue and Generate the Curriculum. "ESCAPE" to Cancel.""");

          lnprint_alignText(DEFAULT_NO_COLUMNS.getChoiceInt() - 6, ARROW.getChoiceString());
          String confirmation = scan.nextLine();

          if(confirmation.isBlank() || confirmation.isEmpty()) lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), NEEDTOFILLUP.getMessage());
          else{
              switch (confirmation) {
                case "CONFIRM" -> {
                  curriculumSetupVariables(false);
                  Assessment assessment = new Assessment();
                  assessment.assessmentFees(false);
                  break OUTER;
                }

                case "ESCAPE" -> {
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





  public void curriculumSetupVariables(boolean isCourseShowed){
    Course CS = new BSCS();
    Course IS = new BSIS();
    Course IT = new BSIT();
    Course DS = new BDSA();
    Course MI = new MIT();
    Course BL = new BLIS();

    switch(getCourseChoice()){
      case 1 -> CS.selectionProcess(isCourseShowed);
      case 2 -> IT.selectionProcess(isCourseShowed);
      case 3 -> IS.selectionProcess(isCourseShowed);
      case 4 -> BL.selectionProcess(isCourseShowed);
      case 5 -> DS.selectionProcess(isCourseShowed);
      case 6 -> MI.selectionProcess(isCourseShowed);
    }
  }

  public void selectionProcess(boolean isCourseShowed){
    System.out.println("NEED TO OVERRIDE");
  }

  //this function was used in this class and to the Account Class.
  public void connect(boolean isCourseShowed, ArrayList<String> courseNo, ArrayList<String> courseTitle, ArrayList<Integer> units, ArrayList<Integer> lecture, ArrayList<Integer> laboratory, ArrayList<String> preRequisite){
    totalUnits = totalCalculation(units);
    totalLecture = totalCalculation(lecture);
    totalLaboratory = totalCalculation(laboratory);
    numberOfSubs = courseNo.size();
    
    if (!isCourseShowed) fileWrite(courseTitle, units, totalUnits);
    else{
      Curriculum_Generate cg = new Curriculum_Generate();
      clear();
      spinLoading(21, DEFAULT_NO_COLUMNS.getChoiceInt(), DEFAULTSTEP.getChoiceInt(), "SETTING UP THE CURRICULUM", 100);
      cg.curriculum(numberOfSubs, getCourseFullName(), getCourseYear(), getCourseSemester(), courseNo, courseTitle, units, totalUnits, lecture, laboratory, preRequisite, totalLecture, totalLaboratory);
    }
  }


  private void fileWrite(ArrayList<String> courseTitle, ArrayList<Integer> units, float totalUnits){
    try {
      try(BufferedWriter writer = new BufferedWriter(new FileWriter(COURSE_INFO_FILE, true))){
        int[] choices = {CourseUI.getCourseChoice(), CourseUI.getYearChoice(), CourseUI.getSemesterChoice(), CourseUI.getTrackChoice()};
        
        writer.append(String.valueOf(accountNo));
        for(int choice : choices) writer.append(":" + choice);
        writer.newLine();
      }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ASSESSMENT_FILE))){ //write the assessment header
          LINELENGTH.setChoiceInt(60);
          for(int i = 0; i < ASSESSMENTHEADER.length - 2; i++){ //use for-loop to write the header. Last Index was not include
              writer.newLine();
              writer.append(document_center_StringAlign(ASSESSMENTHEADER[i])); //by using the stringAlign and the max column, it can center the text.
          }

          writer.append(newLinesString(2) + document_center_StringAlign(ASSESSMENTHEADER[ASSESSMENTHEADER.length - 2]));
          writer.append(newLinesString(2) + document_stringAlign(forDocument.NOTEPAD_MAXCOLUMN.getValue() - 20, ASSESSMENTHEADER[ASSESSMENTHEADER.length - 1]) + "\n\n"); //the last index in the array
          String[] stdInfo_course = {"Student ID : " + accountNo, "Semester : " + getCourseSemester() + ", " + Year.now() + "-" + (Year.now().getValue() + 1),
                                      "Name : " + Student.student_info[2].toUpperCase() + ", " + Student.student_info[1] + " " + Student.student_info[3],
                                      "Course & Year : " + getCourseAcronym() + " " + getCourseYear()};
          
          for (int i = 0; i < stdInfo_course.length; i++){
            PADDING.setChoiceInt(70 - stdInfo_course[i].length());
            writer.append(lnstringWithTabs_spaces(5, 8, String.format("%s%" + PADDING.getChoiceInt() + "s", stdInfo_course[i], "")));
            i++;
            writer.append(stdInfo_course[i]);
          }

          writer.append("\n" + HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

      }
    } catch (IOException e) {}
  }

  private int totalCalculation(ArrayList<Integer> setOfIntegers){
    int total = 0;
    for(Integer i : setOfIntegers) total += i;

    return total;
  }

  public void setTotalUnits(int totalUnits){
    this.totalUnits = totalUnits;
  }

  public int getTotalUnits(){
    return totalUnits;
  }

  public void setNumberOfSubs(int numberOfSubs){
    this.numberOfSubs = numberOfSubs;
  }

  public int getNumberOfSubs(){
    return numberOfSubs;
  }

  public void setCourseNo(List<String> courseNo){
    this.courseNo.addAll(courseNo);
  }

  public ArrayList<String> getCourseNo(){
    return courseNo;
  }

  public void setCourseTitle(List<String> courseTitle){
    this.courseTitle.addAll(courseTitle);
  }

  public void setLaboratory(List<Integer> laboratory){
    this.laboratory.addAll(laboratory);
  }

  public void setLectures(List<Integer> lecture){
    this.lecture.addAll(lecture);
  }

  public void setPreRequisite(List<String> preRequisite){
    this.preRequisite.addAll(preRequisite);
  }

  public void setPreRequisite(ArrayList<String> preRequisite){
    this.preRequisite.addAll(preRequisite);
  }

  public ArrayList<String> addSameStringValueInArrayList(int howMany, String value){
    ArrayList<String> arrayList = new ArrayList<>();
    for(int i = 0; i < howMany; i++) arrayList.add(value);
    return arrayList;
  }

  public ArrayList<Integer> addSameIntValueInArrayList(int howMany, Integer value){
    ArrayList<Integer> integers = new ArrayList<>();
    for(int i = 0; i < howMany; i++) integers.add(value);

    return integers;
  }
  

  public void setUnits(List<Integer> units){
    this.units.addAll(units);
  }

  public ArrayList<String> getCourseTitle(){
    return courseTitle;
  }

  public ArrayList<Integer> getUnits(){
    return units;
  }

  public ArrayList<Integer> getLectures(){
    return lecture;
  }

  public ArrayList<Integer> getLaboratory(){
    return laboratory;
  }

  public ArrayList<String> getPreRequisite(){
    return preRequisite;
  }

  public int getTotalLaboratory() {
      return totalLaboratory;
  }

  public int getTotalLectures() {
      return totalLecture;
  }

}

class Curriculum_Generate extends Course{
	public void curriculum(int numberOfSubs, String courseFullName, String year, String semester, ArrayList<String> courseNo, ArrayList<String> courseTitle, ArrayList<Integer> units, int totalUnits, ArrayList<Integer> lecture, ArrayList<Integer> laboratory, ArrayList<String> preRequisite, int totalLecture, int totalLaboratory){
    clear();

    if(courseFullName.endsWith("(BPO)") && year.equals("First Year") && semester.equals("First Semester")){
      totalUnits -= 3;
      totalLecture -= 3;
    }
    
    // curriculum header
    LINELENGTH.setChoiceInt(91);
    clear();
    newLinesHorizontalCustomCenteredTextln(1, "Republic of the Philippines");
    customHorizontalCenteredTextln(ASSESSMENTHEADER[0]);
    customHorizontalCenteredText(ASSESSMENTHEADER[2]);
    lnHorizontalCustomCenteredTextln("College of Computing Studies, Information and Communication Technology");
    customHorizontalCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));

    printWithTabs_spaces(6, 3, courseFullName +
    stringWithTabs_newLines_spaces(2, 8, 8, year) +
    lnstringWithTabs_spaces(4, 2, semester));

    lnHorizontalCustomCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
    
    print_alignText(82, "Course No. " + stringWithSpaces(1, VERTICAL_LINE.getChoiceString()));

    // curriculum for graduate program
    if(courseFullName.contains("MASTER")){
      printWithTabs_newLines_spaces(0, 3, 1, "CourseTitle" +
      spacesAfterString(1) +
      stringWithTabs(3, (VERTICAL_LINE.getChoiceString())) +
      stringWithSpaces(2, "Units") +
      stringWithSpaces(2, VERTICAL_LINE.getChoiceString()) +
      stringWithSpaces(2, "Pre-requisite") +
      lnstringWithTabs_spaces(4, 14, VERTICAL_LINE.getChoiceString() +
      tabsAndSpacesAfterString(3, 13) + stringWithTabs(3, VERTICAL_LINE.getChoiceString())) +
      stringWithSpaces(9, VERTICAL_LINE.getChoiceString()) +
      lnstringWithTabs_spaces(4, 14, VERTICAL_LINE.getChoiceString() +
      tabsAndSpacesAfterString(3, 13) + stringWithTabs(3, VERTICAL_LINE.getChoiceString()) +
      stringWithSpaces(9, VERTICAL_LINE.getChoiceString())) +
      stringWithTabs_newLines(1, 4, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt())));
      
      for (int i = 0; i < numberOfSubs; i++ ) {
        printWithTabs_newLines_spaces(1, 4, 2, courseNo.get(i) +
        stringWithTabs_spaces(1, 1, courseTitle.get(i) +
        tabsAndSpacesAfterString(2, 5) +
        units.get(i) +
        stringWithTabs(2, preRequisite.get(i))) +
        stringWithTabs_newLines(1, 4, HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt())));
      }

        printWithTabs_newLines_spaces(1, 10, 3, "Total Units" +
        stringWithSpaces(2, VERTICAL_LINE.getChoiceString()) +
        stringWithSpaces(4, String.valueOf(totalUnits)) +
        stringWithSpaces(4, VERTICAL_LINE.getChoiceString()) +
        newLinesString(1));
    }
    
    // curriculum for undergraduate program
    else{
      printWithTabs_spaces(2, 1, "CourseTitle" +
      spacesAfterString(1) +
      stringWithTabs(2, VERTICAL_LINE.getChoiceString()) +
      stringWithSpaces(2, "Units") +
      stringWithSpaces(2, VERTICAL_LINE.getChoiceString()) +
      stringWithSpaces(5, "Units") +
      stringWithSpaces(5, VERTICAL_LINE.getChoiceString()) +
      stringWithSpaces(2, "Pre-requisite") +
      lnstringWithTabs_spaces(4, 14, VERTICAL_LINE.getChoiceString()) +
      stringWithTabs_spaces(2, 13, stringWithTabs(2, VERTICAL_LINE.getChoiceString())) +
      stringWithTabs_spaces(1, 2, VERTICAL_LINE.getChoiceString()) +
      stringWithSpaces(0, HORIZONTAL_LINE.getChoiceString().repeat(15)) +
      VERTICAL_LINE.getChoiceString() + lnstringWithTabs_spaces(4, 14, VERTICAL_LINE.getChoiceString()) +
      stringWithTabs_spaces(2, 13, stringWithTabs(2, VERTICAL_LINE.getChoiceString())) +
      stringWithSpaces(9, VERTICAL_LINE.getChoiceString()) +
      stringWithSpaces(2, "Lec" +
      stringWithSpaces(2, VERTICAL_LINE.getChoiceString())) +
      stringWithSpaces(2, "Lab") +
      stringWithSpaces(2, VERTICAL_LINE.getChoiceString()) +
      stringWithTabs_newLines(1, 4, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt())) +
      newLinesString(1));

      for (int i = 0; i < numberOfSubs; i++) {
        printWithTabs_spaces(4, 2, courseNo.get(i) +
        stringWithTabs_spaces(1, 1, courseTitle.get(i)) +
        units.get(i) +
        stringWithTabs_spaces(1, 6, String.valueOf(lecture.get(i))) +
        stringWithTabs_spaces(1, 6, String.valueOf(laboratory.get(i))) +
        stringWithTabs(2, preRequisite.get(i) +
        stringWithTabs_newLines(1, 4, HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()))));
        newLine(1);
      }

      printWithTabs_spaces(4, 34, "Total Units" +
      stringWithSpaces(3, VERTICAL_LINE.getChoiceString() +
      stringWithSpaces(3, String.valueOf(totalUnits))) +
      stringWithSpaces(3, VERTICAL_LINE.getChoiceString()) +
      stringWithSpaces(3, String.valueOf(totalLecture)) +
      stringWithSpaces(3, VERTICAL_LINE.getChoiceString()) +
      stringWithSpaces(3, String.valueOf(totalLaboratory)));
    }

    printWithTabs_newLines(1, 4, HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()) +
    newLinesString(1));
  }
}