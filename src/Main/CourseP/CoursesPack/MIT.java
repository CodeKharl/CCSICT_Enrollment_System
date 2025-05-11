package Main.CourseP.CoursesPack;

import Main.CourseP.Course;
import java.util.Arrays;

public class MIT extends Course {
  @Override
  public void selectionProcess(boolean isCourseShowed){
    // separation of courseNo, courseTitle, etc. using switch condition (conversion of yearChoice and semesterChoice to String).
    switch (String.valueOf(getYearChoice()) + String.valueOf(getSemesterChoice())) {
      case "00" -> { // underBridge Program
        setCourseNo(Arrays.asList("IT 101", "IT 102", "IT 103", "IT 104"));
        
        setCourseTitle(Arrays.asList(
          "Structing Programming Languages",
          "Database Management Systems" + tabsAndSpacesAfterString(1, 5),
          "Operating Systems" + tabsAndSpacesAfterString(2, 5),
          "Data Communication and Computer" +
          lnstringWithTabs_spaces(6, 1, "Networks") +
          tabsAndSpacesAfterString(3, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 3));
        
        setPreRequisite(addSameStringValueInList(4, "None"));
      }
      case "11" -> {
        setCourseNo(Arrays.asList("IT 101", "IT 102", "IT 103", "IT 104"));
        
        setCourseTitle(Arrays.asList(
          "Advanced Data Structure and Algorithm" + lnstringWithTabs_spaces(6, 1, "Analysis") + tabsAndSpacesAfterString(3, 5),
          "Data Warehousing and Data Mining" + tabsAndSpacesAfterString(0, 5),
          "Advanced Database Systems" + tabsAndSpacesAfterString(1, 5),
          "Advanced Systems Design and" + lnstringWithTabs_spaces(6, 1, "Implementation") + tabsAndSpacesAfterString(3, 5)
          ));
          
        setUnits(addSameIntValueInList(4, 3));
        setPreRequisite(addSameStringValueInList(4, "None"));
      }

      case "12" -> {
        setCourseNo(Arrays.asList("MIT 211", "MIT 212", "MIT 213", "MIT 214"));
        
        setCourseTitle(Arrays.asList(
          "IT Project Management" + tabsAndSpacesAfterString(2, 5),
          "Web-based Applications Development and" + lnstringWithTabs_spaces(6, 1, "Management") + tabsAndSpacesAfterString(3, 5),
          "Distributed Database System" + tabsAndSpacesAfterString(1, 5),
          "Security Management in Information" + lnstringWithTabs_spaces(6, 1, "System") + tabsAndSpacesAfterString(3, 5)
          ));

          setUnits(addSameIntValueInList(4, 3));
          setPreRequisite(addSameStringValueInList(4, "None"));
      }

      case "13" -> {
        setCourseNo(Arrays.asList("MIT 215", "MIT 216"));
        
        setCourseTitle(Arrays.asList(
          "Cloud Computing" + tabsAndSpacesAfterString(2, 5),
          "Applied Machine Learning" + tabsAndSpacesAfterString(1, 5)
          ));
      
        setUnits(Arrays.asList(3, 3));

        setPreRequisite(Arrays.asList("None", "MIT 202"));
      }

      case "21" -> {
        setCourseNo(Arrays.asList("MIT Inst. 1", "MIT 301"));
        
        setCourseTitle(Arrays.asList(
          "Climate Change and Disaster Risk" + lnstringWithTabs_spaces(6, 1, "Management") + tabsAndSpacesAfterString(3, 5),
          "Capstone in Information" + lnstringWithTabs_spaces(6, 1, "Technology 1") + tabsAndSpacesAfterString(3, 5)
          ));
      
        setUnits(Arrays.asList(3, 3));
  
        setPreRequisite(Arrays.asList("None", "MIT 202"));
      }

      case "22" -> {
        setCourseNo(Arrays.asList("MIT 302"));
        
        setCourseTitle(Arrays.asList(
          "Capstone in Information Technology 2"
          ));
      
        setUnits(Arrays.asList(3));
  
        setPreRequisite(Arrays.asList("MIT 301"));
      }
    }

    connect(isCourseShowed, getCourseNo(), getCourseTitle(), getUnits(), getLectures(), getLaboratory(), getPreRequisite());
  }
}

