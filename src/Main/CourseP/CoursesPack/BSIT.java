package Main.CourseP.CoursesPack;
import Main.CourseP.Course;
import java.util.Arrays;

public class BSIT extends Course {
  @Override
  public void selectionProcess(boolean isCourseShowed){
    switch (String.valueOf(getTrackChoice() + String.valueOf(getYearChoice()) + String.valueOf(getSemesterChoice()))) {
      case "111" -> {
        setCourseNo(Arrays.asList("GEC 4", "GEC 5", "IT INST 1", "IT GE ELEC 1", "IT GE ELEC 2", "IT 111", "IT 112", "PE 1", "NSTP 1"));
        
        setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Purposive Communication") + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1,"Art Appreciation") + tabsAndSpacesAfterString(2, 5),
          "Climate Change and Disaster" + lnstringWithTabs_spaces(6, 1, "Risk Management") + tabsAndSpacesAfterString(2, 5),
          "Health and Wellness Science" + tabsAndSpacesAfterString(1, 5),
          "Foreign Language 1" + tabsAndSpacesAfterString(2, 5),
          "Introduction to Computing" + tabsAndSpacesAfterString(1, 5),
          "Computer Programming 1" + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Physical Activity Towards") + lnstringWithTabs_spaces(6, 1, "Health and Fitness 1") + lnstringWithTabs_spaces(6, 1, "(Movement Enhancement)") + tabsAndSpacesAfterString(2, 5),
          "CWTS/LTS/MS 1" + tabsAndSpacesAfterString(3, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 2, 3, 3, 3, 3, 2, 3));
        setLectures(Arrays.asList(3, 3, 2, 3, 3, 2, 2, 2, 3));
        setLaboratory(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 0, 0));

        setPreRequisite(addSameStringValueInArrayList(9, "None"));
      }
      
      case "112" -> {
        setCourseNo(Arrays.asList("GEC 1", "GEC 2", "GEC 3", "GEC 7", "IT 121", "IT 122", "IT 123", "PE 2", "NSTP 2"));
        
        setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Understading the Self") + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1,"Reading in Philippine History") + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1, "Mathematics in Modern World") + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1, "Ethics") + tabsAndSpacesAfterString(4, 5),
          "Computer Programming 2" + tabsAndSpacesAfterString(2, 5),
          "Human-Computer Interaction 1" + tabsAndSpacesAfterString(1, 5),
          "Discrete Mathematics" + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Physcial Activity Towards") + lnstringWithTabs_spaces(6, 1, "Health and Fitness 1") + lnstringWithTabs_spaces(6, 1, "(Fitness Exercise)") + tabsAndSpacesAfterString(2, 5),
          "CWTS/LTS/MS 2" + tabsAndSpacesAfterString(3, 5)
          ));
      
          setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 2, 3));
          setLectures(Arrays.asList(3, 3, 3, 3, 2, 2, 3, 2, 3));
          setLaboratory(Arrays.asList(0, 0, 0, 0, 1, 1, 0, 0, 0));

          setPreRequisite(Arrays.asList("None", "None", "None", "None",
          "IT 112", "IT 112", "None", "PE 1", "NSTP"));
      }

      case "121" -> {
        setCourseNo(Arrays.asList("GEC 6", "GEC 8", "IT INST 2", "IT GE ELEC 3", "IT 211", "IT ELEC 1", "IT ELEC 2", "IT BPO 1", "PE 3"));
        
        setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Science, Technology") + lnstringWithTabs_spaces(6, 1, "and Society") + tabsAndSpacesAfterString(3, 5),
          stringWithTabs_spaces(1, 1,"The Contemporary World") + tabsAndSpacesAfterString(2, 5),
          "Creative and Critical Thinking" + tabsAndSpacesAfterString(1, 5),
          "Foreign Language 2" + tabsAndSpacesAfterString(2, 5),
          "Data Structures and Algorithms" + tabsAndSpacesAfterString(1, 5),
          "Platform Technologies" + tabsAndSpacesAfterString(2, 5),
          "Object-Oriented Programming" + tabsAndSpacesAfterString(1, 5),
          "Business Communication" + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Physcial Activity Towards") + lnstringWithTabs_spaces(6, 1, "Health and Fitness III") + lnstringWithTabs_spaces(6, 1, "(Dance and Individual" + lnstringWithTabs_spaces(6, 1, "/Dual Sports)") + tabsAndSpacesAfterString(3, 5))
          ));
      
        setUnits(Arrays.asList(3, 3, 2, 3, 3, 3, 3, 3, 2));
        setLectures(Arrays.asList(3, 3, 1, 3, 2, 2, 2, 3, 2));
        setLaboratory(Arrays.asList(0, 0, 1, 0, 1, 1, 1, 0, 0));

        setPreRequisite(Arrays.asList("None", "None", "None", "None", "IT 121", "IT 121", "IT 121", "None", "PE 1, PE 2"));
      }

      case "122" -> {
        setCourseNo(Arrays.asList("IT GE ELEC 4", "GEC 9", "IT 221", "IT 222", "IT 223", "IT 224", "IT 225", "IT APPDEV 1", "PE 4"));
        
        setCourseTitle(Arrays.asList(
          "The Entrepreneurial Mind" + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1,"The Life and Works of RIzal") + tabsAndSpacesAfterString(1, 5),
          "Information Management" + tabsAndSpacesAfterString(2, 5),
          "Networking 1" + tabsAndSpacesAfterString(3, 5),
          "Quantitative Methods (Including" + lnstringWithTabs_spaces(6, 1, "Modeling and Simulation)") + tabsAndSpacesAfterString(1, 5),
          "Integrative Programming and" + lnstringWithTabs_spaces(6, 1, "Technologies") + tabsAndSpacesAfterString(3, 5),
          "Accounting for Information" + lnstringWithTabs_spaces(6, 1, "Technology") + tabsAndSpacesAfterString(3, 5),
          "Fundamentals of Mobile Tecnologies" + tabsAndSpacesAfterString(0, 2),
          stringWithTabs_spaces(1, 1, "Physcial Activity Towards") + lnstringWithTabs_spaces(6, 1, "Health and Fitness IV") + lnstringWithTabs_spaces(6, 1, "(Dance, Team Sports, Outdoor" + lnstringWithTabs_spaces(6, 1, "and Adventure Activities))") + tabsAndSpacesAfterString(1, 5))
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 3, 2));
        setLectures(Arrays.asList(3, 3, 2, 2, 3, 2, 3, 2, 2));
        setLaboratory(Arrays.asList(0, 0, 1, 1, 0, 1, 0, 1, 0));

        setPreRequisite(Arrays.asList("None", "None", "IT 121", "IT Elec 1", "IT 211," + stringWithTabs_newLines(1, 14, "IT 123"), "IT Elec 1," + stringWithTabs_newLines(1, 14, "IT Elec 2"), "None", "IT Elec 1," + stringWithTabs_newLines(1, 14, "IT Elec 2"), "PE 1, PE 2," + stringWithTabs_newLines(1, 14, "PE 3")));
      }
      
      case "123" -> {
        setCourseNo(Arrays.asList("IT 226", "IT ELEC 3"));
        
        setCourseTitle(Arrays.asList(
          "Applications Development and" + lnstringWithTabs_spaces(6, 1, "Emerging Technologies") + tabsAndSpacesAfterString(2, 5),
          "Web Systems and Technologies" + tabsAndSpacesAfterString(1, 5)
          ));
      
        setUnits(Arrays.asList(3, 3));
        setLectures(Arrays.asList(2, 2));
        setLaboratory(Arrays.asList(1, 1));

        setPreRequisite(Arrays.asList("IT 221", "None"));
      }

      case "131" -> {
        setCourseNo(Arrays.asList("IT Inst 3", "IT 311", "IT 312", "IT 313", "IT 314", "IT APPDEV 2", "IT APPDEV 3"));
        
        setCourseTitle(Arrays.asList(
          "Data Science Analytics" + tabsAndSpacesAfterString(2, 5),
          "Advance Database Systems" + tabsAndSpacesAfterString(1, 5), "Networking 2" + tabsAndSpacesAfterString(3, 5),
          "System Integration and" + lnstringWithTabs_spaces(6, 1, "Architecture") + tabsAndSpacesAfterString(3, 5), "Information Assurance" + lnstringWithTabs_spaces(6, 1, "and Security 1") + tabsAndSpacesAfterString(3, 5), "Web Applications" + tabsAndSpacesAfterString(2, 5), "Mobile Applications" + tabsAndSpacesAfterString(2, 5)
          ));
      
        setUnits(Arrays.asList(2, 3, 3, 3, 3, 3, 3));
        setLectures(Arrays.asList(1, 2, 2, 2, 2, 2, 2 ));
        setLaboratory(Arrays.asList(1, 1, 1, 1, 1, 1, 1));

        setPreRequisite(Arrays.asList("None", "IT 221", "IT 222", "IT 224", "IT 224", "IT Elec 3", "IT APPDEV 1"));
      }

      case "132" -> {
        setCourseNo(Arrays.asList("IT GE ELEC 5", "IT 321", "IT 322", "IT 323", "IT APPDEV 4", "IT APPDEV 5"));
        
        setCourseTitle(Arrays.asList(
          "Multicultural Education" + tabsAndSpacesAfterString(1, 5),
          "Information Assurance and" + lnstringWithTabs_spaces(6, 1, "Security 2") + tabsAndSpacesAfterString(3, 5), "Social and Professional" + lnstringWithTabs_spaces(6, 1, "Issues") + tabsAndSpacesAfterString(4, 5),
          "Capstone Project and" + lnstringWithTabs_spaces(6, 1, "Research 1") + tabsAndSpacesAfterString(3, 5), "Game Development" + tabsAndSpacesAfterString(2, 5), "Cloud Computing" + tabsAndSpacesAfterString(2, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3));
        setLectures(Arrays.asList(3, 2, 3, 2, 2, 2));
        setLaboratory(Arrays.asList(0, 1, 0, 1, 1, 1));

        setPreRequisite(Arrays.asList("None", "IT 314", "GEC 7", "IT 226" + stringWithTabs_newLines(1, 14, "IT 314"),
        "IT APPDEV 2" + stringWithTabs_newLines(1, 14, "IT APPDEV 3"),
        "IT APPDEV 2" + stringWithTabs_newLines(1, 14, "IT APPDEV 3")
        ));
      }

      case "141" -> {
        setCourseNo(Arrays.asList("IT GE ELEC 6", "IT 411", "IT ELEC 4", "IT 412"));
        
        setCourseTitle(Arrays.asList(
          "Leadership and Management in" + lnstringWithTabs_spaces(6, 1, "the Profession") + tabsAndSpacesAfterString(3, 5),
          "System Administration and" + lnstringWithTabs_spaces(6, 1, "Maintenance") + tabsAndSpacesAfterString(3, 5),
          "Human-Computer Interaction 2" + tabsAndSpacesAfterString(1, 5),
          "Capstone Project and" + lnstringWithTabs_spaces(6, 1, "Research 2") + tabsAndSpacesAfterString(3, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 3));
        setLectures(Arrays.asList(3, 2, 2, 2));
        setLaboratory(Arrays.asList(0, 1, 1, 1));

        setPreRequisite(Arrays.asList("None", "IT 321", "IT 122", "IT 323"));
      }

      case "142" -> {
        setCourseNo(Arrays.asList("IT 421"));
        
        setCourseTitle(Arrays.asList(
          "Intership/ OJT/ Practicum" + lnstringWithTabs_spaces(6, 1, "(486)") + tabsAndSpacesAfterString(4, 5)
          ));
      
        setUnits(Arrays.asList(9));
        setLectures(Arrays.asList(0));
        setLaboratory(Arrays.asList(0));

        setPreRequisite(Arrays.asList("4th Year" + stringWithTabs_newLines(2, 14, "Standing") + newLinesString(1)));
      }

      case "211" -> {
        setCourseNo(Arrays.asList("GEC 1", "GEC 4", "GEC 3", "GEC 10", "IT 111", "IT 112", "PE 1", "NSTP 1"));
        
        setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Understadning the Self") + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1,1, "Purposive Communication") + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1, 1, "Mathematics in the Modern") + lnstringWithTabs_spaces(6, 1, "World") + tabsAndSpacesAfterString(4, 5),
          "Konteswalisadong Komunikasyon" + lnstringWithTabs_spaces(6, 1, "sa Filipino") + tabsAndSpacesAfterString(3, 5),
          "Introduction to Computing" + tabsAndSpacesAfterString(1, 5),
          "Computer Programming 1" + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Physical Education 1") + tabsAndSpacesAfterString(2, 5),
          "Civic Welfare Training" + lnstringWithTabs_spaces(6, 1, "Services 1") + tabsAndSpacesAfterString(3, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 3));
        setLectures(Arrays.asList(3, 3, 3, 3, 2, 2, 2, 3));
        setLaboratory(Arrays.asList(0, 0, 0, 0, 1, 1, 0, 0));

        setPreRequisite(addSameStringValueInArrayList(8, "None"));
  
      }

      case "212" -> {
        setCourseNo(Arrays.asList("GEC 2", "GEC 8", "GEC 11", "IT 121", "IT 122", "IT 133", "PE 2", "NSTP 2"));
        
        setCourseTitle(Arrays.asList(
          stringWithTabs_spaces(1, 1, "Reading in Philippine History") + tabsAndSpacesAfterString(1, 5),
          stringWithTabs_spaces(1,1, "The Contemporary World") + tabsAndSpacesAfterString(2, 5),
          "Filipino sa Ibat-ibang" + lnstringWithTabs_spaces(6, 1, "Disiplina") + tabsAndSpacesAfterString(3, 5),
          "Computer Programming 2" + tabsAndSpacesAfterString(2, 5),
          "Human Computer Interaction 1" + tabsAndSpacesAfterString(1, 5),
          "Discrete Mathematics" + tabsAndSpacesAfterString(2, 5),
          stringWithTabs_spaces(1, 1, "Physical Education 2") + tabsAndSpacesAfterString(2, 5),
          "Civic Welfare Training" + lnstringWithTabs_spaces(6, 1, "Services 2") + tabsAndSpacesAfterString(3, 5)
          ));
      
        setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 3));
        setLectures(Arrays.asList(3, 3, 3, 2, 2, 3, 2, 3));
        setLaboratory(Arrays.asList(0, 0, 0, 1, 1, 0, 0, 0));
        
        setPreRequisite(Arrays.asList("None", "None", "GEC 10", "IT 112", "IT 112", "GEC 3", "PE 1", "NSTP 1"));
      }

    case "221" -> {
      setCourseNo(Arrays.asList("GEC 4", "GEC 7", "GEC", "IT 221", "IT Elec 1", "IT Elec 2", "IT BPO 1", "PE 3"));
        
      setCourseTitle(Arrays.asList(
        stringWithTabs_spaces(1, 1, "Science, Technology and") + lnstringWithTabs_spaces(6, 1, "Society") + tabsAndSpacesAfterString(3, 5),
        stringWithTabs_spaces(1,1, "Ethics") + tabsAndSpacesAfterString(4, 5),
        stringWithTabs_spaces(1, 1, "Art Appreciation") + tabsAndSpacesAfterString(2, 5),
        "Data Structure and Algorithms" + tabsAndSpacesAfterString(1, 5),
        "Platform Technologies" + tabsAndSpacesAfterString(2, 5),
        "Object Oriented Programming" + tabsAndSpacesAfterString(1, 5),
        "Business Communication" + tabsAndSpacesAfterString(2, 5),
        stringWithTabs_spaces(1, 1, "Physical Education 3") + tabsAndSpacesAfterString(2, 5)
        ));
    
      setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 2));
      setLectures(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 2));
      setLaboratory(Arrays.asList(0, 0, 0, 1, 1, 1, 0, 0));
      
      setPreRequisite(Arrays.asList("None", "None", "None", "IT 121", "IT 121", "IT 121", "None", "PE 2"));
    }

    case "222" -> {
      setCourseNo(Arrays.asList("IT Inst 1", "IT 222", "IT 223", "IT 224", "IT 225", "IT NS 1", "PE 4"));
        
      setCourseTitle(Arrays.asList(
        "Climate Change and Disaster" + lnstringWithTabs_spaces(6, 1, "Management") + tabsAndSpacesAfterString(3, 5),
        "Networking 1" + tabsAndSpacesAfterString(3, 5),
        "Quantitative Methods (including" + lnstringWithTabs_spaces(6, 1, "Modeling & Simulation)") + tabsAndSpacesAfterString(2, 5),
        "Integrative Programming &" + lnstringWithTabs_spaces(6, 1, "Technologies") + tabsAndSpacesAfterString(3, 5),
        "Accounting for Information" + lnstringWithTabs_spaces(6, 1, "Technology") + tabsAndSpacesAfterString(3, 5),
        "Cybersecurity Principles and" + lnstringWithTabs_spaces(6, 1, "Emerging Challenges") + tabsAndSpacesAfterString(2, 5),
        stringWithTabs_spaces(1, 1, "Physical Education 4") + tabsAndSpacesAfterString(2, 5)
        ));
    
      setUnits(Arrays.asList(3, 3, 3, 3, 3, 3, 2));
      setLectures(Arrays.asList(3, 3, 3, 2, 3, 2, 2));
      setLaboratory(Arrays.asList(0, 0, 0, 1, 0, 1, 0));
      
      setPreRequisite(Arrays.asList("None", "IT Elec 1", "IT 211", "IT 211", "None", "IT 212," + stringWithTabs_newLines(1, 14, "IT 222"), "PE 3"));
    }

    case "223" -> {
      setCourseNo(Arrays.asList("IT 226", "IT Elec 3"));
        
      setCourseTitle(Arrays.asList(
        "Applications Development and" + lnstringWithTabs_spaces(6, 1, "Emerging Technologies") + tabsAndSpacesAfterString(2, 5),
        "Web Systems & Technologies" + tabsAndSpacesAfterString(1, 5)
        ));
    
      setUnits(Arrays.asList(3, 3));
      setLectures(Arrays.asList(2, 2));
      setLaboratory(Arrays.asList(1, 1));
      
      setPreRequisite(Arrays.asList("IT 221", "None"));
    }

    case "231" -> {
      setCourseNo(Arrays.asList("IT 311", "IT 312", "IT 313", "IT 314", "IT NS 2"));
        
      setCourseTitle(Arrays.asList(
        "Advance Database Systems" + tabsAndSpacesAfterString(1, 5),
        "Networking 2" + tabsAndSpacesAfterString(3, 5),
        "System Integration &" + lnstringWithTabs_spaces(6, 1, "Architecture") + tabsAndSpacesAfterString(3, 5),
        "Information Assurance &" + lnstringWithTabs_spaces(6, 1, "Security 1") + tabsAndSpacesAfterString(3, 5),
        "Virtual Systems and Services" + tabsAndSpacesAfterString(1, 5)
        ));
    
      setUnits(Arrays.asList(3, 3, 3, 3, 3));
      setLectures(Arrays.asList(2, 2, 2, 2, 2));
      setLaboratory(Arrays.asList(1, 1, 1, 1, 1));
      
      setPreRequisite(Arrays.asList("IT 221", "IT 222", "IT 224", "IT 224", "IT 222"));
    }

    case "232" -> {
      setCourseNo(Arrays.asList("GEC 9", "IT GE Elec 2", "IT 321", "IT 322", "IT 323", "IT NS 3"));
        
      setCourseTitle(Arrays.asList(
        stringWithTabs_spaces(1, 1, "The Life and Work of Rizal") + tabsAndSpacesAfterString(1, 5),
        "Multicultural Education" + tabsAndSpacesAfterString(1, 5),
        "Information Assurance &" + lnstringWithTabs_spaces(6, 1, "Security 2") + tabsAndSpacesAfterString(3, 5),
        "Social & Professional Issues" + tabsAndSpacesAfterString(1, 5),
        "Capstone Project & Research 1" + tabsAndSpacesAfterString(1, 5),
        "Applied Networks" + tabsAndSpacesAfterString(2, 5)
        ));
    
      setUnits(Arrays.asList(3, 3, 3, 3, 3, 3));
      setLectures(Arrays.asList(3, 3, 2, 3, 2, 2));
      setLaboratory(Arrays.asList(0, 0, 1, 0, 1, 1));
      
      setPreRequisite(Arrays.asList("None", "None", "IT 314", "GEC 7", "IT 226," + stringWithTabs_newLines(1, 14, "IT 314"), "IT 312" + stringWithTabs_newLines(1, 14, "IT NS 1")));
    }

    case "241" -> {
      setCourseNo(Arrays.asList("IT GE Elec 3", "IT 411", "IT Elec 4", "IT 412", "IT BA 5", "IT NS 5"));
        
      setCourseTitle(Arrays.asList(
        "Leadership and Management" + lnstringWithTabs_spaces(6, 1, "in the Profession") + tabsAndSpacesAfterString(2, 5),
        "System Administration &" + lnstringWithTabs_spaces(6, 1, "Maintenance") + tabsAndSpacesAfterString(3, 5),
        "Human Computer Interaction 2" + tabsAndSpacesAfterString(1, 5),
        "Capstone Project & Research 2" + tabsAndSpacesAfterString(1, 5),
        "Analytics Application" + tabsAndSpacesAfterString(2, 5),
        "Introduction to Network Career" + lnstringWithTabs_spaces(6, 1, "Certification") + tabsAndSpacesAfterString(3, 5)
        ));
    
      setUnits(Arrays.asList(3, 3, 3, 3, 3, 3));
      setLectures(Arrays.asList(3, 2, 2, 2, 2, 3));
      setLaboratory(Arrays.asList(0, 1, 1, 1, 1, 0));
      
      setPreRequisite(Arrays.asList("None", "IT 321", "IT 122", "IT 323", "IT BA 2," + stringWithTabs_newLines(1, 14, "IT BA 3"), "4th Year" + stringWithTabs_newLines(1, 14, "Standing")));

    }
    
    case "242" -> {
      setCourseNo(Arrays.asList("IT 421"));
        
      setCourseTitle(Arrays.asList(
        "Interships/OJT/Practicum" + lnstringWithTabs_spaces(6, 1, "(486)") + tabsAndSpacesAfterString(3, 5)
        ));
    
      setUnits(Arrays.asList(9));
      setLectures(Arrays.asList(0));
      setLaboratory(Arrays.asList(0));
      
      setPreRequisite(Arrays.asList("4th Year" + stringWithTabs_newLines(1, 14, "Standing")));
    }
  }

    connect(isCourseShowed, getCourseNo(), getCourseTitle(), getUnits(), getLectures(), getLaboratory(), getPreRequisite());
  }

}
