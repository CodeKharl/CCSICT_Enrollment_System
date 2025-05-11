package Main.CourseP.CoursesPack;

import Main.CourseP.Course;
import java.util.Arrays;

public class BSIS extends Course{
  @Override
  public void selectionProcess(boolean isCourseShowed){
    switch (String.valueOf(getYearChoice()) + String.valueOf(getSemesterChoice())) {
        case "11" -> {
          setCourseNo(Arrays.asList("GEC 4", "GEC 5", "IS 111", "IS 112", "IS GEC Elec 1", "IS GE Elec 2", "PE 1", "NSTP 1"));

          setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Purposive Communication") + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1, "Art Appreciation" + tabsAndSpacesAfterString(2, 5)),
          "Introduction to Computing"+ tabsAndSpacesAfterString(1,5),
          "Computer Programming 1" + tabsAndSpacesAfterString(2, 5),
          "Health and Wellness Science" + tabsAndSpacesAfterString(1, 5),
          "Multicultural Education" + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1, "Physical Activity Towards") + lnstringWithTabs_spaces(6, 1, "Health Fitness 1") + lnstringWithTabs_spaces(6, 1, "(Movement Patterns)") + tabsAndSpacesAfterString(2, 5),
          "CWTS/LTS/MS 1" + tabsAndSpacesAfterString(3, 5)));
        
          setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 3));
          setLectures(Arrays.asList(3, 3, 2, 2, 3,  3, 2, 3));
          setLaboratory(Arrays.asList(0, 0, 1, 1, 0, 0, 0, 0));

          setPreRequisite(addSameStringValueInList(8, "None"));
        }

        case "12" -> {
          setCourseNo(Arrays.asList("GEC 1", "GEC 2", "GEC 3", "IS GEC Elec 2", "IS INST 1", "IS 121", "IS 122", "PE 2", "NSTP 2"));
          setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Understanding the Self") + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Readings in Philippine History" + tabsAndSpacesAfterString(1, 5)),
          stringWithTabs_spaces(1, 1, "Mathematics in the Modern World") + tabsAndSpacesAfterString(0,5),
          "Gender and Society" + tabsAndSpacesAfterString(2, 5),
          "Climate Change and Disaster Risk" + lnstringWithTabs_spaces(6, 1, "Management") + tabsAndSpacesAfterString(3, 5),
          "Fundamentals of Information" + lnstringWithTabs_spaces(6, 1, "Systems") + tabsAndSpacesAfterString(3, 5),
          "Computer Programming 2" + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Physical Activity Towards") + lnstringWithTabs_spaces(6, 1, "Health Fitness II") + lnstringWithTabs_spaces(6, 1, "(Exercise Programm)") + tabsAndSpacesAfterString(2, 5),
          "CWTS/LTS/MS 2" + tabsAndSpacesAfterString(3, 5)));
        
          setUnits(Arrays.asList(3, 3, 3, 3, 2, 3, 3, 2, 3));
          setLectures(Arrays.asList(3, 3, 3, 3, 2, 3, 2, 2, 3));
          setLaboratory(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 0, 0));

          setPreRequisite(Arrays.asList("None", "None", "None", "None", "None", "IS 111", "IS 112", "PE 1", "NSTP 1"));
        }

        case "21" -> {
          setCourseNo(Arrays.asList("GEC 6", "GEC 8", "IS Inst 2", "IS 211", "IS 212", "IS 213", "IS 214", "PE 3", "IS BPO 211"));
          setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Science, Technology, and" + lnstringWithTabs_spaces(6, 1,  "Society") + tabsAndSpacesAfterString(3, 5)),
          stringWithTabs_spaces(1, 1, "The Contemporary World" + tabsAndSpacesAfterString(2, 5)),
          "Creative and Critical Thinking" + tabsAndSpacesAfterString(0,6),
          "Data Structures and Algorithms" + tabsAndSpacesAfterString(1, 5),
          "Professional Issues in" + lnstringWithTabs_spaces(6, 1, "Information Management") + tabsAndSpacesAfterString(2, 5),
          "IT Infrastructure and Network" + lnstringWithTabs_spaces(6, 1, "Technologies Systems") + tabsAndSpacesAfterString(2, 5),
          "Organization and Management" + lnstringWithTabs_spaces(6, 1, "Concepts") + tabsAndSpacesAfterString(3, 5),
          stringWithTabs_spaces(1, 1, "Physical Activity Towards Health") + lnstringWithTabs_spaces(6, 1, "Fitness III (Dance, Sports," + lnstringWithTabs_spaces(6, 1, "Group Exercise, Outdoor" + lnstringWithTabs_spaces(6, 1, "Adventure Activities)"))) + tabsAndSpacesAfterString(2, 5),
          "Business Communication for BPO" + tabsAndSpacesAfterString(1, 5)));
        
          setUnits(Arrays.asList(3, 3, 2, 3, 3, 3, 3, 2, 3));
          setLectures(Arrays.asList(3, 3, 1, 2, 3, 2, 3, 2, 3));
          setLaboratory(Arrays.asList(0, 0, 1, 1, 0, 1, 0, 0, 0));
  
          setPreRequisite(Arrays.asList("None", "None", "None", "IS 122", "IS 111," + stringWithTabs_newLines(1, 14, "IS 121"),
          "IS 111," + stringWithTabs_newLines(1, 14, "IS 121"), "IS 111", "PE 1, PE 2", "GEC 4"));
        }

        case "22" -> {
          setCourseNo(Arrays.asList("GEC 7", "GEC 9", "IS Ge Elec 1", "IS 211", "IS 222", "IS Elec 1", "PE 4", "IS BPO 221"));
        
          setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1,
          "Ethics" + tabsAndSpacesAfterString(4, 5)),
          stringWithTabs_spaces(1, 1,
          "Life and Works of Rizal" + tabsAndSpacesAfterString(1, 5)), "Entrepreneurial Mind" + tabsAndSpacesAfterString(2, 5),
          "Systems Analysis and Design" + tabsAndSpacesAfterString(1,5),
          "Financial Management" + tabsAndSpacesAfterString(2, 5),
          "IT Audit and Controls" + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1,
          "Physical Activity Towards Health") + lnstringWithTabs_spaces(6, 1,
          "Fitness III (Dance, Sports," + lnstringWithTabs_spaces(6, 1,
          "Group Exercise, Outdoor" + lnstringWithTabs_spaces(6, 1, "Adventure Activities)"))) + tabsAndSpacesAfterString(2, 5),
          "Service Culture" + tabsAndSpacesAfterString(1, 5) + tabsAndSpacesAfterString(1, 5)));
        
          setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 3));
          setLectures(Arrays.asList(3, 3, 3, 2, 3, 2, 2, 2));
          setLaboratory(Arrays.asList(0, 0, 0, 1, 0, 1, 0, 1));
  
          setPreRequisite(Arrays.asList("None", "None", "None", "2nd Year" + stringWithTabs_newLines(2, 14, "Standing"), "IS 214",
          "2nd Year" + stringWithTabs_newLines(1, 14, "Standing"), "PE 1, PE 2," + stringWithTabs_newLines(1, 14, "PE 3"), "IS BPO 211"));
        }

        case "31" -> {
        setCourseNo(Arrays.asList("IS 311", "IS 312", "IS 313", "IS 314", "IS Elec 2", "IS BPO 311", "IS BPO 312"));
        
        setCourseTitle(Arrays.asList(
        "Information Management" + tabsAndSpacesAfterString(2, 5),
        "Enterprise Architecture" + tabsAndSpacesAfterString(1, 5), "Business Process Management" + tabsAndSpacesAfterString(1, 5),
        "Quantitative Methods" + tabsAndSpacesAfterString(2,5),
        "Data Mining" + tabsAndSpacesAfterString(3, 5),
        "Fundamentals of Business Process" + lnstringWithTabs_spaces(6, 1, "Outsourcing 101") + tabsAndSpacesAfterString(2, 5),
        "Principles of Systems Thinking" + tabsAndSpacesAfterString(1, 5)));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 3));
        setLectures(Arrays.asList(3, 3, 3, 2, 3, 2, 2, 2));
        setLaboratory(Arrays.asList(0, 0, 0, 1, 0, 1, 0, 1));

        setPreRequisite(Arrays.asList("None", "None", "None", "2nd Year" + stringWithTabs_newLines(2, 14, "Standing"), "IS 214",
        "2nd Year" + stringWithTabs_newLines(1, 14, "Standing"), "PE 1, PE 2," + stringWithTabs_newLines(1, 14, "PE 3"), "IS BPO 211"));
        }

        case "32" -> {
          setCourseNo(Arrays.asList("IS GEC" + lnstringWithTabs_spaces(4, 2, "Elec 3"), "IS 321", "IS 322", "IS 323", "IS Elec 3", "IS GE Elec 3", "IS BPO 102"));
        
          setCourseTitle(Arrays.asList(
          "Foreign Language 1" + tabsAndSpacesAfterString(2, 5),
          "IS Project Management 1" + tabsAndSpacesAfterString(1, 5), "Evaluation of Business" + lnstringWithTabs_spaces(6, 1, "Performance") + tabsAndSpacesAfterString(3, 5),
          "Capstone Project 1" + tabsAndSpacesAfterString(2,5),
          "IT Security and Risks Management" + tabsAndSpacesAfterString(0, 4), "Leadership and Management" + lnstringWithTabs_spaces(6, 1, "in the Profession") + tabsAndSpacesAfterString(2, 5),
          "Fundamentals Of Business" + lnstringWithTabs_spaces(6, 1, "Process Outsourcing 102") + tabsAndSpacesAfterString(1, 5)));
        
          setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3));
          setLectures(Arrays.asList(3, 3, 3, 2, 2, 3, 2));
          setLaboratory(Arrays.asList(0, 0, 0, 1, 1, 0, 1));
  
          setPreRequisite(Arrays.asList("None", "IS 221", "IS 313", "3rd Year" + stringWithTabs_newLines(2, 14, "Standing"), "3rd Year" + stringWithTabs_newLines(1, 14, "Standing"),
          "None",
          "IS BPO 211," + stringWithTabs_newLines(1, 14, "IS BPO 211,") + stringWithTabs_newLines(1, 14, "IS BPO 311")));
        }

        case "41" -> {
          setCourseNo(Arrays.asList("IS 411", "IS 412", "IS 413", "IS Elec 4", "IS FOR 411"));
        
          setCourseTitle(Arrays.asList(
            "IS Strategy, Management" + lnstringWithTabs_spaces(6, 1, "and Acquisition") + tabsAndSpacesAfterString(2, 5),
            "Applications Development and" + lnstringWithTabs_spaces(6, 1, "Emerging Technologies") + tabsAndSpacesAfterString(2, 5),
            "Capstone Project 2" + tabsAndSpacesAfterString(2, 5),
            "IS Innovations and New" + lnstringWithTabs_spaces(6, 1, "Technologies") + tabsAndSpacesAfterString(3, 5),
            "Foreign Lanuage" + tabsAndSpacesAfterString(2, 5)
          ));
        
          setUnits(Arrays.asList(3, 3, 3, 3, 3));
          setLectures(Arrays.asList(3, 2, 2, 2, 3));
          setLaboratory(Arrays.asList(0, 1, 1, 1, 0));
  
          setPreRequisite(Arrays.asList(
            "4th Year" + stringWithTabs_newLines_spaces(2, 13, 8, "Standing") + tabsAndSpacesAfterString(1, 1),
            "4th Year" + stringWithTabs_newLines(2, 14, "Standing"),
            "IS 323",
            "4th Year" + stringWithTabs_newLines(1, 14, "Standing"),
            "None"
          ));
        }

        case "42" -> {
          setCourseNo(Arrays.asList("IS 421"));
        
          setCourseTitle(Arrays.asList(
            "Practicum for Information" + lnstringWithTabs_spaces(6, 1,
            "Systems/Intership Program") + lnstringWithTabs_spaces(6, 1,
            "(486 hours)") + tabsAndSpacesAfterString(3, 5)));
        
          setUnits(Arrays.asList(9));
          setLectures(Arrays.asList(9));
          setLaboratory(Arrays.asList(0));
  
          setPreRequisite(Arrays.asList("4th Year" + stringWithTabs_newLines(2, 14, "Standing\n")));
        }
    }
    connect(isCourseShowed, getCourseNo(), getCourseTitle(), getUnits(), getLectures(), getLaboratory(), getPreRequisite());
  }
}
