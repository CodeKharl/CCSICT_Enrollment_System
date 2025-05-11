package Main.CourseP.CoursesPack;

import Main.CourseP.Course;
import java.util.Arrays;

public class BSCS extends Course{
  @Override
  public void selectionProcess(boolean isCourseShowed){
    switch (String.valueOf(getYearChoice()) + String.valueOf(getSemesterChoice())) {
      case "11" -> {
        setCourseNo(Arrays.asList("CS Inst 1", "GEC 3", "GEC 4", "GE ELec CS1", "GE Elec CS 2", "CS 111", "CS 112", "PE 1", "NSTP 1"));

        setCourseTitle(Arrays.asList("Climate Change and Disaster " +
        lnstringWithTabs_spaces(6, 1, "Risk Management" + tabsAndSpacesAfterString(2, 5)),
        stringWithTabs_spaces(1, 1, "Mathematics in the Modern World" + spacesAfterString(5)),
        stringWithTabs_spaces(1, 1, "Purposive Communication") + tabsAndSpacesAfterString(1,5),
        "Health and Wellness Science" + tabsAndSpacesAfterString(1, 5),
        "Gender and Society" + tabsAndSpacesAfterString(2, 5),
        "Introduction to Computing" + tabsAndSpacesAfterString(1, 5),
        "Fundamentals of Programming" + tabsAndSpacesAfterString(1, 5),
        stringWithTabs_spaces(1, 1, "Physical Activity Towards " +
        lnstringWithTabs_spaces(6, 1, "Health Fitness 1 ") +
        lnstringWithTabs_spaces(6, 1, "(Movement Enhancement)") +
        tabsAndSpacesAfterString(2, 5)),
        "CWTS/LTS/MS 1" + tabsAndSpacesAfterString(3, 5)));
      
        setUnits(Arrays.asList(2, 3, 3, 3, 3, 3, 3, 2, 3));
        setLectures(Arrays.asList(2, 3, 3, 3, 3, 2, 2, 2, 3));
        setLaboratory(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 0, 0));

        setPreRequisite(addSameStringValueInList(9, "None"));
      }
      
      case "12" -> {
        setCourseNo(Arrays.asList("GEC 1", "GEC 2", "GEC 5", "GE Elec CS 3", "CS 121", "CS 122", "PE 2", "NSTP 2"));
        
        setCourseTitle(Arrays.asList(stringWithTabs_spaces(1, 1, "Understanding the Self" + tabsAndSpacesAfterString(2, 5)),
        stringWithTabs_spaces(1, 1, "Readings in Philippine History" + tabsAndSpacesAfterString(1, 5)),
        stringWithTabs_spaces(1, 1, "Art Appreciation" + tabsAndSpacesAfterString(2, 5)),
        "Foreign Language 1" + tabsAndSpacesAfterString(2, 5),
        "Discrete Structures 1" + tabsAndSpacesAfterString(2, 5),
        "Intermediate Programming" + tabsAndSpacesAfterString(1, 5),
        stringWithTabs_spaces(1, 1, "Physical Activity Towards" +
        lnstringWithTabs_spaces(6, 1, "Health Fitness II" +
        lnstringWithTabs_spaces(6, 1, "(Fitness Exercise)" + tabsAndSpacesAfterString(2, 5)))),
        "CWTS/LTS/MS 2" + tabsAndSpacesAfterString(3, 5)));

        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 3));
        setLectures(Arrays.asList(3, 3, 3, 3, 3, 2, 2, 3));
        setLaboratory(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 0));

        setPreRequisite(Arrays.asList("None", "None", "None", "None", "GEC 3", "CS 112", "PE 1", "NSTP 1"));
        setPreRequisite(addSameStringValueInList(4, "None"));
      }

      case "21" -> {
        setCourseNo(Arrays.asList("GEC 6", "GEC 8", "CS Inst 2", "CS 211", "CS 212", "CS 213", "CS 214", "CS Elec 1", "PE 3"));

        setCourseTitle(Arrays.asList(stringWithTabs_spaces(1, 1, "Science, Technology and Society" + spacesAfterString(5)),
        stringWithTabs_spaces(1, 1, "The Comtemporary World" + tabsAndSpacesAfterString(2, 5)),
        "Creative and Critical Thinking" + tabsAndSpacesAfterString(1, 5),
        "Discrete Structures 2" + tabsAndSpacesAfterString(2, 5),
        "Object-Oriented Programming" + tabsAndSpacesAfterString(1, 5),
        "Data Structured and Algorithms" + tabsAndSpacesAfterString(1, 5),
        "Calculus with Analytic Geometry" + spacesAfterString(5),
        "CS Elective 1" + tabsAndSpacesAfterString(3, 5),
        stringWithTabs_spaces(1, 1, "Physical Activity Towards Health " +
        lnstringWithTabs_spaces(6, 1, "Fitness III " +
        lnstringWithTabs_spaces(6, 1, "(Dance & indiv/Dual Sports)" + tabsAndSpacesAfterString(1, 5))))));

        setUnits(Arrays.asList(3, 3, 2, 3, 3, 3, 3, 3, 2));
        setLectures(Arrays.asList(3, 3, 1, 3, 2, 2, 3, 2, 2));
        setLaboratory(Arrays.asList(0, 0, 1, 0, 1, 1, 0, 1, 0));

        setPreRequisite(Arrays.asList("None", "None", "None", "CS 121", "CS 122", "CS 122", "2nd Year " + stringWithTabs_newLines(1, 14, "Standing"), "2nd Year " + stringWithTabs_newLines(1, 14, "Standing"), "PE 1"));
      }

      case "22" -> {
        setCourseNo(Arrays.asList("GEC 7", "GEC 9", "CS GE Elec 1", "CS GE Elec 4", "CS 221", "CS 222", "CS DM 1", "CS DM 2", "PE 4"));
        setCourseTitle(Arrays.asList(stringWithTabs_spaces(1, 1, "Ethics" + tabsAndSpacesAfterString(4, 5)),
        stringWithTabs_spaces(1, 1, "Life and Works of Rizal" + tabsAndSpacesAfterString(1, 5)),
        "The Entrepreurial Mind" + tabsAndSpacesAfterString(2, 5),
        "Technical Writing" + tabsAndSpacesAfterString(2, 5),
        "Algorithms and Complexity" + tabsAndSpacesAfterString(1, 5),
        "Information Management" + tabsAndSpacesAfterString(2, 5),
        "Statistical Methods for Daya" +
        lnstringWithTabs_spaces(6, 1, "Analysis and Inference" + tabsAndSpacesAfterString(2, 5)),
        "Data Preparation and Analysis" + tabsAndSpacesAfterString(1, 5),
        stringWithTabs_spaces(1, 1, "Physical Activity Towards Health " + lnstringWithTabs_spaces(6, 1, "Fitness IV (Team Sports/Outdoor" + lnstringWithTabs_spaces(6, 1, "& Adventure Activitie/Dance)" + tabsAndSpacesAfterString(1, 5))))));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 3, 2));
        setLectures(Arrays.asList(3, 3, 3, 3, 3, 2, 2, 2, 2));
        setLaboratory(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 0));

        setPreRequisite(Arrays.asList("None", "None", "CS Inst 2", "None", "CS 213" +
        stringWithTabs_newLines(1, 14, "CS 211"), "CS1 22",
        "2nd " + stringWithTabs_newLines(1, 14, "Standing"),
        "2nd " + stringWithTabs_newLines(1, 14, "2nd Year"),
        "PE 1, PE 2" + stringWithTabs_newLines(1, 14, "PE 3")));
      }
      
      case "31" -> {
      setCourseNo(Arrays.asList("CS 311", "CS 312", "CS 313", "CS 314", "CS 315", "CS DM 3", "CS DM 4"));
      setCourseTitle(Arrays.asList("Automata Theory and Formal" +
      lnstringWithTabs_spaces(6, 1, "Languages" + tabsAndSpacesAfterString(3, 5)),
      "Architecture and Organization" + tabsAndSpacesAfterString(1, 5),
      "Information Assurance and Security" + spacesAfterString(2),
      "Sofware Engineering 1" + tabsAndSpacesAfterString(2, 5),
      "Social and Professional Issues" + tabsAndSpacesAfterString(1, 5),
      "Data Mining Techniques and" + lnstringWithTabs_spaces(6, 1, "Tools" + tabsAndSpacesAfterString(4, 5)),
      "Data Mining Applicaton" + tabsAndSpacesAfterString(2, 5)));
      
      setUnits(Arrays.asList(3, 3, 2, 2, 3, 2, 2));
      setLectures(Arrays.asList(3, 3, 2, 2, 3, 2, 2));
      setLaboratory(Arrays.asList(0, 0, 0, 1, 0, 1, 1));
      
      setPreRequisite(Arrays.asList("CS 221", "CS 213," + stringWithTabs_newLines(1, 14, "CS 121"), "CS 22", "CS 222," + stringWithTabs_newLines(1, 14, "CS 212"), "Co-requi -" + stringWithTabs_newLines(1, 14, "site with") + stringWithTabs_newLines(1, 14, "CS 314"), "3rd Year" + stringWithTabs_newLines(1, 14, "Standing"), "3rd Year" + stringWithTabs_newLines(1, 14, "Standing")));
      }

      case "32" -> {
      setCourseNo(Arrays.asList("CS 321", "CS 322", "CS 323", "CS 324", "CS Elec 2", "CS DM 5", "CS DM 6"));
      setCourseTitle(Arrays.asList("CS Thesis Writing 1" + tabsAndSpacesAfterString(2, 5),
      "Software Engineering 2" + tabsAndSpacesAfterString(2, 5),
      "Application Development and" + lnstringWithTabs_spaces(6, 1, "Emerging Technologies") + tabsAndSpacesAfterString(2, 5),
      "Programming Languages" + tabsAndSpacesAfterString(2, 5),
      "CS Elective 2" + tabsAndSpacesAfterString(3, 5),
      "Algorithms for Data Mining" + tabsAndSpacesAfterString(1, 5),
      "Data Mining Applicaton" + tabsAndSpacesAfterString(2, 5)));
      
      setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3));
      setLectures(Arrays.asList(2, 2, 2, 2, 2, 2, 2));
      setLaboratory(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1));
      
      setPreRequisite(Arrays.asList("CS 313," +
      stringWithTabs_newLines(1, 14, "CS GE -") + stringWithTabs_newLines(1, 14, "Elec 4"), "CS 314", "CS 222", "CS 213", "3rd Year" + stringWithTabs_newLines(1, 14, "Standing"), "CS DM 3 & 4", "CS DM 3 & 4"));
      }

      case "33" -> {
        setCourseNo(Arrays.asList("CS Elec 3", "CS GE Elec 2", "CS GE Elec 3"));
        setCourseTitle(Arrays.asList("CS Elective 3" + tabsAndSpacesAfterString(3, 5),
        "Multi-cultural Education" + tabsAndSpacesAfterString(1, 5),
        "Leadership and Management" + lnstringWithTabs_spaces(6, 1, "in the Profession") + tabsAndSpacesAfterString(2, 5)));
        setUnits(Arrays.asList(3, 3, 3));
        setLectures(Arrays.asList(2, 3, 3));
        setLaboratory(Arrays.asList(1, 0, 0));
        setPreRequisite(Arrays.asList("4th Year" + stringWithTabs_newLines(2, 14, "Standing\n"), "None", "None"));
      }

      case "41" -> {
        setCourseNo(Arrays.asList("CS 412", "CS 413", "CS 414", "CS 411"));
        setCourseTitle(Arrays.asList("Human-Computer Interaction" + tabsAndSpacesAfterString(1, 5),
        "Networks and Communications" + tabsAndSpacesAfterString(1, 5),
        "Operating Systems" + tabsAndSpacesAfterString(2, 5),
        "CS Thesis Writing 2" + tabsAndSpacesAfterString(2, 5)));
        
        setUnits(Arrays.asList(1, 3, 3, 3));
        setLectures(Arrays.asList(1, 2, 2, 2));
        setLaboratory(Arrays.asList(0, 1, 1, 1));
        
        setPreRequisite(Arrays.asList("CS 122", "CS 213", "CS 213", "CS 321"));
      }

      case "42" -> {
      setCourseNo(Arrays.asList("CS 421"));
      setCourseTitle(Arrays.asList("Practicum (486 hours)" + tabsAndSpacesAfterString(2, 5)));
      
      setUnits(Arrays.asList(9));
      setLectures(Arrays.asList(0, 0));
      setLaboratory(Arrays.asList(0, 0));
      
      setPreRequisite(Arrays.asList("4th Year" + stringWithTabs_newLines(2, 14, "Standing\n")));
      }
    }

    connect(isCourseShowed, getCourseNo(), getCourseTitle(), getUnits(), getLectures(), getLaboratory(), getPreRequisite());
  }
}

