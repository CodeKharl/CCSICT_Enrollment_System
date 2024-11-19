package Main.CourseP.CoursesPack;
import Main.CourseP.Course;
import java.util.Arrays;

public class BLIS extends Course {

  @Override
  public void selectionProcess(boolean isCourseShowed){
    switch(String.valueOf(getYearChoice()) + String.valueOf(getSemesterChoice())){
      case "11" -> {
        setCourseNo(Arrays.asList("GEC 1", "GEC 4", "GEC 7", "GEC 10", "LIS 111", "LIS Elec 1", "LIS ICT 111", "PE 1", "NSTP 1"));
        
        setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Understanding the Self") + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Purposive Communication") + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1, "Ethics") + tabsAndSpacesAfterString(4, 5),
          "Kontekstwalisadong Komunikasyon" + lnstringWithTabs_spaces(6, 1, "sa Filipo") + tabsAndSpacesAfterString(3, 5),
          "Introduction to Library and" + lnstringWithTabs_spaces(6, 1, "Information Science") + tabsAndSpacesAfterString(2, 5),
          "School/Academic Librarianship" + tabsAndSpacesAfterString(1, 5),
          "Information Processing and" + lnstringWithTabs_spaces(6, 1, "Handling in Libraries") + lnstringWithTabs_spaces(6, 1, "and Information Center") + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Physical Education") + tabsAndSpacesAfterString(2, 5),
          "Civic Welfare Training" + lnstringWithTabs_spaces(6, 1, "Service 1, MS, LTS") + tabsAndSpacesAfterString(2, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 2, 3));
        setLectures(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 2, 3));
        setLaboratory(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 0, 0));

        setPreRequisite(addSameStringValueInArrayList(9, "None"));
      }

      case "12" -> {
        setCourseNo(Arrays.asList("GEC 2", "GEC 3", "GEC 11", "Inst 1", "IS 121", "LIS Elec 2", "LIS ICT 121", "PE 2", "NSTP 2"));
        
        setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Reading in Philippine History") + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1, "Mathematics in the Modern") + lnstringWithTabs_spaces(6, 1, "World") + tabsAndSpacesAfterString(4, 5),
          "Filipino sa Iba't Ibang" + lnstringWithTabs_spaces(6, 1, "Disiplina") + tabsAndSpacesAfterString(3, 5),
          "Climate Chance and Disaster"+ lnstringWithTabs_spaces(6, 1, "Risk Management") + tabsAndSpacesAfterString(2, 5),
          "Collection Management of" + lnstringWithTabs_spaces(6, 1, "Information Resources") + tabsAndSpacesAfterString(2, 5),
          "Special/Public Librarianship" + tabsAndSpacesAfterString(1, 5),
          "Web Technologies in" + lnstringWithTabs_spaces(6, 1, "Libraries and") + lnstringWithTabs_spaces(6, 1, "Information Science") + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Physical Education") + tabsAndSpacesAfterString(2, 5),
          "Civic Welfare Training" + lnstringWithTabs_spaces(6, 1, "Services 2, MS, LTS") + tabsAndSpacesAfterString(2, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 2, 3));
        setLectures(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 2, 3));
        setLaboratory(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 0, 0));
        setPreRequisite(Arrays.asList("None", "None", "GEC 10", "None", "LIS 111", "None", "LIS ICT 111", "PE 1", "NSTP 1"));
      }

      case "21" -> {
        setCourseNo(Arrays.asList("GEC 6", "GEC 7", "LIS GE" + lnstringWithTabs_spaces(4, 2, "Elec 1"), "LIS 211", "LIS 212", "LIS Elec 3", "LIS ICT 211", "PE 3"));
        
        setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Science, Technology, and" + lnstringWithTabs_spaces(6, 1, "Society") + tabsAndSpacesAfterString(3, 5)),
          stringWithTabs_spaces(1, 1, "The Contemporary World") + tabsAndSpacesAfterString(2, 5),
          "Thre Entrepreneurial Mind" + tabsAndSpacesAfterString(1, 5),
          "Information Resource and" + lnstringWithTabs_spaces(6, 1, "Services I") + tabsAndSpacesAfterString(3, 5),
          "Organization of Information" + lnstringWithTabs_spaces(6, 1, "Resources I") + tabsAndSpacesAfterString(3, 5),
          "Preservaiton of Information" + lnstringWithTabs_spaces(6, 1, "Resources") + tabsAndSpacesAfterString(3, 5),
          "Digital Libraries and" + lnstringWithTabs_spaces(6, 1, "Resources") + tabsAndSpacesAfterString(3, 5),
          stringWithTabs(1, "Physical Education 3") + tabsAndSpacesAfterString(2, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 2));
        setLectures(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 2));
        setLaboratory(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 0));
        setPreRequisite(Arrays.asList("None", "None", "None", "LIS 111," + stringWithTabs_newLines(1, 14, "LIS 121"), "LIS 111," + stringWithTabs_newLines(1, 14, "LIS 121"), "2nd Year" + stringWithTabs_newLines(1, 14, "Standing"), "LIS ICT 121", "PE 2"));
      }

      case "22" -> {
        setCourseNo(Arrays.asList("GEC 5", "LIS GE" + lnstringWithTabs_spaces(4, 2, "Elec 2"), "LIS 221", "LIS 222", "LIS 223", "LIS ICT 221", "LIS Elec 4", "PE 4"));
        
        setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Art Appreciation" + tabsAndSpacesAfterString(2, 5)),
          "Leadership and Management in" + lnstringWithTabs_spaces(6, 1, "the Profession") + tabsAndSpacesAfterString(3, 5),
          "Information Resources and" + lnstringWithTabs_spaces(6, 1, "Services II") + tabsAndSpacesAfterString(3, 5),
          "Organization of Information" + lnstringWithTabs_spaces(6, 1, "Resources II") + tabsAndSpacesAfterString(3, 5),
          "Library Materials for Children" + lnstringWithTabs_spaces(6, 1, "and Young Adults") + tabsAndSpacesAfterString(2, 5),
          "Programming Fundamentals" + tabsAndSpacesAfterString(1, 5),
          "Philosophies and Principles of" + lnstringWithTabs_spaces(6, 1, "Teaching") + tabsAndSpacesAfterString(3, 5),
          stringWithTabs(1, "Physical Education 4") + tabsAndSpacesAfterString(2, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 2));
        setLectures(Arrays.asList(3, 3, 3, 3, 3, 2, 3, 2));
        setLaboratory(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0));
        setPreRequisite(Arrays.asList("None", "None", "LIS 211", "LIS 212", "LIS 121", "LIS ICT 211", "2nd Year" + stringWithTabs_newLines(1, 14, "Standing"), "PE 3"));
      }

      case "31" -> {
      setCourseNo(Arrays.asList("GEC 9", "LIS GE" + lnstringWithTabs_spaces(4, 2, "Elec 3"), "LIS 311", "LIS 312", "LIS 313", "LIS ICT 311", "LIS Elec 5", "LIS Elec 6"));
        
      setCourseTitle(Arrays.asList(
        stringWithTabs_spaces(1, 1, "Life and Morks of Rizal" + tabsAndSpacesAfterString(1, 5)),
        "Environmental Science" + tabsAndSpacesAfterString(2, 5),
        "Indexing and Abstracting" + tabsAndSpacesAfterString(1, 5),
        "Introduction to Records" + lnstringWithTabs_spaces(6, 1, "Management and" + lnstringWithTabs_spaces(6, 1, "Archives")) + tabsAndSpacesAfterString(3, 5),
        "Library and Information" + lnstringWithTabs_spaces(6, 1, "Management in Academic" + lnstringWithTabs_spaces(6, 1, "Libraries")) + tabsAndSpacesAfterString(3, 5),
        "Systems Analysis and Design" + lnstringWithTabs_spaces(6, 1, "in Libraries and Information" + lnstringWithTabs_spaces(6, 1, "Centers")) + tabsAndSpacesAfterString(3, 5),
        "Educational Technology" + tabsAndSpacesAfterString(2, 5),
        "Indigenous Knowledge and" + lnstringWithTabs_spaces(6, 1, "Information Centers") + tabsAndSpacesAfterString(2, 5)
        ));
    
      setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 3));
      setLectures(Arrays.asList(3, 3, 3, 3, 3, 2, 3, 3));
      setLaboratory(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 0));
      setPreRequisite(Arrays.asList("None", "None", "LIS 212," + stringWithTabs_newLines(2, 14, "LIS 222"), "None", "LIS 221" + stringWithTabs_newLines(1, 14, "LIS 222"), "3nd Year" + stringWithTabs_newLines(1, 14, "Standing"), "3nd Year" + stringWithTabs_newLines(1, 14, "Standing"), "3rd" + stringWithTabs_newLines(1, 14, "Standing")));
      }

      case "32" -> {
        setCourseNo(Arrays.asList("LIS 321", "LIS 322", "LIS 323", "LIS ICT 321", "LIS ICT 322", "LIS Elec 7", "LIS Elec 8"));
        setCourseTitle(Arrays.asList(
          "Management of Libraries and" + lnstringWithTabs_spaces(6, 1, "Information Centers" + tabsAndSpacesAfterString(2, 5)),
          "Information Literacy" + tabsAndSpacesAfterString(2, 5),
          "Information Sources and" + lnstringWithTabs_spaces(6, 1, "Services in Academic") + lnstringWithTabs_spaces(6, 1, "Librarianship") + tabsAndSpacesAfterString(3, 5),
          "Database Design for Libraries" + tabsAndSpacesAfterString(1, 5),
          "Introduction to Data Science" + tabsAndSpacesAfterString(1, 5),
          "Seminar in Library and" + lnstringWithTabs_spaces(6, 1, "Information Science") + tabsAndSpacesAfterString(2, 5),
          "Foreign Language" + tabsAndSpacesAfterString(2, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3));
        setLectures(Arrays.asList(3, 3, 3, 2, 2, 3, 3));
        setLaboratory(Arrays.asList(0, 0, 0, 1, 1, 0, 0));
        setPreRequisite(Arrays.asList("LIS 111, " + stringWithTabs_newLines(1, 14, "LIS 311") + newLinesString(1), "LIS 111," + stringWithTabs_newLines(2, 14, "LIS 211,") + stringWithTabs_newLines(2, 14, "LIS 221,")  + stringWithTabs_newLines(1, 14, "LIS 212,") + stringWithTabs_newLines(1, 14, "LIS 311"), "LIS 313", "LIS ICT 311", "LIS ICT 311", "3nd Year" + stringWithTabs_newLines(1, 14, "Standing"), "3nd Year" + stringWithTabs_newLines(1, 14, "Standing")));
      }

      case "41" -> {
        setCourseNo(Arrays.asList("LIS 411", "LIS 412", "ICT LIS 411", "INST 2"));
        
        setCourseTitle(Arrays.asList(
          "Research Methods is Library and" + lnstringWithTabs_spaces(6, 1, "Information Sciences" + tabsAndSpacesAfterString(2, 5)),
          "Library Practice 1 (200 Hours)" + tabsAndSpacesAfterString(1, 5),
          "Interest Technologies and" + lnstringWithTabs_spaces(6, 1, "Information") + lnstringWithTabs_spaces(6, 1, "Services") + tabsAndSpacesAfterString(3, 5),
          "Course Audit 1" + tabsAndSpacesAfterString(3, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 6));
        setLectures(Arrays.asList(3, 3, 3, 6));
        setLaboratory(Arrays.asList(1, 0, 0, 1));
        setPreRequisite(Arrays.asList("LIS 111," + stringWithTabs_newLines(2, 14, "LIS 312"), "LIS ICT 111," + stringWithTabs_newLines(1, 14, "LIS ICT 322"), "LIS Elec 1," + stringWithTabs_newLines(1, 14, "LIS Elec 7"), "LIS 111," + stringWithTabs_newLines(1, 14, "LIS 323"), "4th Year" + stringWithTabs_newLines(1, 14, "Standing"), "4th Year" + stringWithTabs_newLines(1, 14, "Standing")
        ));
      }

      case "42" -> {
        setCourseNo(Arrays.asList("LIS 412", "INST 3"));
        setCourseTitle(Arrays.asList(
          "Thesis/Research Writing" + tabsAndSpacesAfterString(1, 5),
          "Course Audit 2" + tabsAndSpacesAfterString(3, 5)
          ));
      
        setUnits(Arrays.asList(3, 6));
        setLectures(Arrays.asList(2, 6));
        setLaboratory(Arrays.asList(1, 0));
        setPreRequisite(Arrays.asList("LIS 411", "INST 2"));
      }
    }
  
    connect(isCourseShowed, getCourseNo(), getCourseTitle(), getUnits(), getLectures(), getLaboratory(), getPreRequisite());
  }
}