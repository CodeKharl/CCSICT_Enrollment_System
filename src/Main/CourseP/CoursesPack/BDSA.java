package Main.CourseP.CoursesPack;

import Main.CourseP.Course;
import java.util.Arrays;

public class BDSA extends Course {

  @Override
  public void selectionProcess(boolean isCourseShowed){
    switch (String.valueOf(getYearChoice()) + String.valueOf(getSemesterChoice())) {
      case "11" -> {
        setCourseNo(Arrays.asList("DSA 111", "DSA 112", "DSA 113", "GEC 1", "GEC 2", "GEC 3", "PE 1"));
        setCourseTitle(Arrays.asList(
          "Fundamentals of Programming" + tabsAndSpacesAfterString(1, 5),
          "Discrete Structures" + tabsAndSpacesAfterString(2, 5),
          "Calculus 1" + tabsAndSpacesAfterString(3, 5),
          stringWithTabs_spaces(1, 1,
          "Understanding the Self") + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1,
          "Readings in Philippine History") + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1, "Mathematics in the Modern World") + tabsAndSpacesAfterString(0, 5),
          stringWithTabs_spaces(1, 1, "Physical Activity Towards") + lnstringWithTabs_spaces(6, 1, "Health and Fitness 1") + lnstringWithTabs_spaces(6, 1, "(Movement Patterns)") + tabsAndSpacesAfterString(2, 5),
          "National Service Training" + lnstringWithTabs_spaces(6, 1, "Program/Reserve Officers'") + lnstringWithTabs_spaces(6, 1, "Corps 1") + tabsAndSpacesAfterString(3, 5)));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 3));
        setLectures(Arrays.asList(2, 2, 3, 3, 3, 3, 2, 3));
        setLaboratory(Arrays.asList(1, 1, 0, 0, 0, 0, 0, 0));
                
        setPreRequisite(addSameStringValueInList(8, "None"));
        }
      
      case "12" -> {
        setCourseNo(Arrays.asList("DSA 121", "DSA 122", "DSA 123", "DSA 4", "GEC 4", "GEC 5", "PE 2", "NSTP/ROTC 2"));
        
        setCourseTitle(Arrays.asList(
          "Intermediate Programming" + tabsAndSpacesAfterString(1, 5),
          "Calculuts 2" + tabsAndSpacesAfterString(3, 5),
          "Introduction to Statistics" + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1, "Introduction to Data Science") + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1,
          "Purposive Communication") + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1, "Art Appreciation") + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Physical Activity Towards") + lnstringWithTabs_spaces(6, 1, "Health and Fitness 2") + lnstringWithTabs_spaces(6, 1, "(Exercise Program Based)") + tabsAndSpacesAfterString(1, 5),
          "National Service Training" + lnstringWithTabs_spaces(6, 1, "Program 2/Reserve Officers'") + lnstringWithTabs_spaces(6, 1, "Training Corps 2") + tabsAndSpacesAfterString(2, 5)));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 3));
        setLectures(Arrays.asList(2, 3, 2, 2, 3, 3, 2, 3));
        setLaboratory(Arrays.asList(1, 0, 1, 1, 0, 0, 0, 0));

        setPreRequisite(Arrays.asList("DSA 111", "None", "DSA 113", "None", "None", "None", "PE 1", "NSTP/ROTC 1"));
      }

      case "21" -> {
        setCourseNo(Arrays.asList("DSA 211", "DSA 212", "DSA 213", "DSA 214", "GEC 6", "GEC 7", "DSA Inst 1", "PE 3"));
        
        setCourseTitle(Arrays.asList(
          "Data Structures and Alogrithms" + tabsAndSpacesAfterString(1, 5),
          "Linear Algebra for Data Science" + tabsAndSpacesAfterString(0, 5),
          "Computer-Aide Statistical" + lnstringWithTabs_spaces(6, 1, "Inference") + tabsAndSpacesAfterString(3, 5),
          "Programming for Data Science" + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1,
          "Science, Technology,") + lnstringWithTabs_spaces(6, 1, "and Society") + tabsAndSpacesAfterString(3, 5),
          "Ethics" + tabsAndSpacesAfterString(5, 5),
          "Climate Change and Disaster" + lnstringWithTabs_spaces(6, 1, "Risk Management") + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Physical Activity Towards") + lnstringWithTabs_spaces(6, 1, "Health and Fitness 3") + lnstringWithTabs_spaces(6, 1, "(PATH-Fit)") + tabsAndSpacesAfterString(3, 5)));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 2));
        setLectures(Arrays.asList(2, 3, 2, 2, 3, 3, 2, 2));
        setLaboratory(Arrays.asList(1, 0, 1, 1, 0, 0, 0, 0));

        setPreRequisite(Arrays.asList("DSA 111," + stringWithTabs_newLines(2, 14, "121"), "None", "None", "DSA 121", "None", "None", "None", "PE 2"));
      }

      case "22" -> {
        setCourseNo(Arrays.asList("DSA 221", "DSA 222", "DSA 223", "DSA 224", "GEC 8", "GEC 9", "DSA Inst 2", "PE 4"));
        
        setCourseTitle(Arrays.asList(
          "Information Management" + tabsAndSpacesAfterString(2, 5),
          "Algorithm and Complexity" + tabsAndSpacesAfterString(1, 5),
          "Artificial Intelligence" + tabsAndSpacesAfterString(1, 5),
          "Information Presentation" + lnstringWithTabs_spaces(6, 1, "and Visualization") + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1,
          "The Contemporary World") + tabsAndSpacesAfterString(2, 5),
          "Life and Works of Rizal" + tabsAndSpacesAfterString(2, 5),
          "Entrepreneurial Management" + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1, "Physical Activity Towards") + lnstringWithTabs_spaces(6, 1, "Health and Fitness 4") + lnstringWithTabs_spaces(6, 1, "(PATH-Fit)") + tabsAndSpacesAfterString(3, 5)));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 2));
        setLectures(Arrays.asList(2, 2, 2, 2, 3, 3, 2, 2));
        setLaboratory(Arrays.asList(1, 1, 1, 1, 0, 0, 0, 0));

        setPreRequisite(Arrays.asList("DSA 124," + stringWithTabs_newLines(2, 14, "211"),
        "DSA 211", "DSA 211", "DSA 123," + stringWithTabs_newLines(1, 14, "212, 211"),
        "None", "None", "None", "PE 3"));
      }

      case "31" -> {
        setCourseNo(Arrays.asList("DSA 311", "DSA 312", "DSA 313", "DSA 314", "DSA 315", "DSA 316", "DSA Inst 3"));
        
        setCourseTitle(Arrays.asList(
          "Computational Statistics" + tabsAndSpacesAfterString(1, 5),
          "Exploratory Data Analysis" + tabsAndSpacesAfterString(1, 5),
          "Business Intelligence" + tabsAndSpacesAfterString(2, 5),
          "Machine Learning &" + lnstringWithTabs_spaces(6, 1, "Data Mining I") + tabsAndSpacesAfterString(3, 5),
          "Data Management and" + lnstringWithTabs_spaces(6, 1, "Warehousing") + tabsAndSpacesAfterString(3, 5),
          "Elective 1" + tabsAndSpacesAfterString(3, 5),
          "Creative and Critical" + lnstringWithTabs_spaces(6, 1, "Thinking Skills") +  tabsAndSpacesAfterString(2, 5)));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2));
        setLectures(Arrays.asList(3, 2, 2, 2, 2, 2, 2));
        setLaboratory(Arrays.asList(0, 1, 1, 1, 1, 1,  0));

        setPreRequisite(Arrays.asList("DSA 121" + stringWithTabs_newLines(1, 14, "213, 212\n"),
        "DSA 123", "DSA 224", "DSA 213," + stringWithTabs_newLines(1, 14, "DSA 211"),
        "None", "None", "None", "PE 3"));
      }

      case "32" -> {
        setCourseNo(Arrays.asList("DSA 321", "DSA 322","DSA 323", "DSA 324", "DSA Elec 1", "DSA 325"));
        
        setCourseTitle(Arrays.asList(
          "Princinples of Big Data" + tabsAndSpacesAfterString(1, 5),
          "Information Security and" + lnstringWithTabs_spaces(6, 1, "Data Privacy") + tabsAndSpacesAfterString(3, 5),
          "Special Topics in Data" + lnstringWithTabs_spaces(6, 1, "Science") + tabsAndSpacesAfterString(3, 5),
          "Researcy Methods" + tabsAndSpacesAfterString(2, 5),
          "Environmental Science" + tabsAndSpacesAfterString(2, 5),
          "Elective 2" + tabsAndSpacesAfterString(3, 5)));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3));
        setLectures(Arrays.asList(2, 2, 2, 2, 3, 2));
        setLaboratory(Arrays.asList(1, 1, 1, 1, 0, 1));

        setPreRequisite(Arrays.asList("DSA 315", "3rd Year" + stringWithTabs_newLines(2, 14, "Standing\n"),
        "3rd Year" + stringWithTabs_newLines(1, 14, "Standing"),
        "3rd Year" + stringWithTabs_newLines(1, 14, "Standing"),
        "3rd Year" + stringWithTabs_newLines(1, 14, "Standing"),
        "None", "None"));
      }

      case "41" -> {
        setCourseNo(Arrays.asList("DSA 411", "DSA 412", "DSA 413", "DSA 414", "DSA Elec 2", "DSA Elec 3"));
        
        setCourseTitle(Arrays.asList(
          "Social Issues and Professional" + lnstringWithTabs_spaces(6, 1, "Practice 1") + tabsAndSpacesAfterString(3, 5),
          "Capstone Project" + tabsAndSpacesAfterString(2, 5),
          "Elective 3" + tabsAndSpacesAfterString(3, 5),
          "Elective 4" + tabsAndSpacesAfterString(3, 5),
          "Gender and Society" + tabsAndSpacesAfterString(2, 5),
          "Foreign Language" + tabsAndSpacesAfterString(2, 5)));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3));
        setLectures(Arrays.asList(3, 2, 2, 2, 3, 3));
        setLaboratory(Arrays.asList(0, 1, 1, 1, 0, 0));

        setPreRequisite(Arrays.asList("4rd Year" + stringWithTabs_newLines(2, 14, "Standing\n"),
        "4rd Year" + stringWithTabs_newLines(2, 14, "Standing\n"),
        "None", "None", "None", "None"));
      }

      case "42" -> {
        setCourseNo(Arrays.asList("DSA 421"));
        
        setCourseTitle(Arrays.asList(
          "On the Job Training" + lnstringWithTabs_spaces(6, 1, "(320 Hours)") + tabsAndSpacesAfterString(3, 5)));
      
        setUnits(Arrays.asList(6));
        setLectures(Arrays.asList(6));
        setLaboratory(Arrays.asList(0));

        setPreRequisite(Arrays.asList(" "));
      }
    }

    connect(isCourseShowed, getCourseNo(), getCourseTitle(), getUnits(), getLectures(), getLaboratory(), getPreRequisite());
  }
}
