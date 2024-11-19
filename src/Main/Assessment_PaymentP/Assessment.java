package Main.Assessment_PaymentP;
import Main.MainUI;

public class Assessment extends MainUI{
    public void assessmentFees(boolean isAssessmentFees_show){
        AssessmentVar_Show assessment = new AssessmentVar_Show(150.00, 2200.00, 900, 50.00, 20.00, 100.00,
        50.00, 50.00, 60.00, 50.00, 100.00, 25.00, 40.00, 50.00, 40.00, 500.00, 100.00);

        if (isAssessmentFees_show) assessment.assessmentShow();
        else{
            Payment payment = new Payment();
            payment.paymentSelection(assessment.getTotalAssessment());
        }
    }

}

class AssessmentVar_Show extends Assessment{
    protected final double tuitionA, tuitionB;
    private final double computerLaboratory;
    private final double athletics, guidance, library, medicalAndDental, registration, sbo_ssc_sscf,
                    schoolPaper_universityOrgran, ID, socioCultural, insurrance, journal, internet,
                    studentDevelopment, entrance;
    
    private double totalTuition;
    private double totalMiscellaneous;
    private double totalAssessment;

    AssessmentVar_Show(double tuitionA, double tuitionB, double computerLaboratory, double athletics, double guidance, double library,
                        double medicalAndDental, double registration, double sbo_ssc_sscf, double schoolPaper_universityOrgran, double ID,
                        double socioCultural, double insurrance, double journal, double internet, double studentDevelopment, double entrance){
        this.tuitionA = tuitionA;
        this.tuitionB = tuitionB;
        this.computerLaboratory = computerLaboratory;
        this.athletics = athletics;
        this.guidance = guidance;
        this.library = library;
        this.medicalAndDental = medicalAndDental;
        this.registration = registration;
        this.sbo_ssc_sscf = sbo_ssc_sscf;
        this.schoolPaper_universityOrgran = schoolPaper_universityOrgran;
        this.ID = ID;
        this.socioCultural = socioCultural;
        this.insurrance = insurrance;
        this.journal = journal;
        this.internet = internet;
        this.studentDevelopment = studentDevelopment;
        this.entrance = entrance;
    }

    public void assessmentShow(){
        String[] tuition = {"Tuition Fees", "Tuition Fee : 3 x 50/unit", "Tuition Fee : 22 x 100/unit"};
        String[] laboratory = {"Laboratory Fees", "Computer Laboratory : 2 x 450/subject"};
        String[] miscellaneous = {"Miscallaneous Fees", "Athletics Fee", "Guidance Fee", "Library Fee", "Medical and Dental Fee",
                                    "Registration Fee", "SBO/SCC/SSCF", "School Paper/University Organ Fee", "ID Fee", "Socio Cultural Fee",
                                    "Insurrance Fee", "Journal Fee", "Internet Fee", "Student Development Fee", "Entrance Fee"};
        
        double[] assessment = {tuitionA, tuitionB, computerLaboratory, athletics, guidance, library, medicalAndDental, registration, sbo_ssc_sscf,
                                schoolPaper_universityOrgran, ID, socioCultural, insurrance, journal, internet, studentDevelopment, entrance};
        
        clear();

        LINELENGTH.setChoiceInt(78);
        lnHorizontalCustomCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
        lnHorizontalCustomCenteredTextln("ASSESSMENT OF FEES");
        lnHorizontalCustomCenteredTextln(HORIZONTAL_DOUBLE_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
        
        lnprintln_alignText(106, tuition[0]);
        lnprintWithTabs_spaces(6, 2, tuition[1] + tabsAndSpacesAfterString(2, 2) + tuitionA +
        lnstringWithTabs_spaces(6, 2, tuition[2] + tabsAndSpacesAfterString(2, 2) + tuitionB +
        stringWithTabs_spaces(1, 2, String.valueOf(getTotalTuition()))));
    
        printlnWithNewLines_alignText(3, 108, laboratory[0]);
        lnprintWithTabs_spaces(6, 2, laboratory[1] + " : " + assessment[2] +
        stringWithTabs_spaces(2, 2, String.valueOf(computerLaboratory)));
        
        printlnWithNewLines_alignText(3, 112, miscellaneous[0]);
        
        for (int i = 1; i < miscellaneous.length; i++) {
            int tabsAfter = 3;
            int spacesAfter = 2;
            String forLast = " ";

            switch(i){
                case 4, 13 -> tabsAfter = 2;
                case 7 -> tabsAfter = 1;
                case 8 -> tabsAfter = 4;
                case 14 -> forLast = tabsAndSpacesAfterString(2, spacesAfter) + String.valueOf(assessment[assessment.length - 1]);
            }

            lnprintWithTabs_spaces(6, 2, miscellaneous[i] + " : " + tabsAndSpacesAfterString(tabsAfter, spacesAfter) + assessment[i + 2] + forLast);
        }

        newLinesHorizontalCustomCenteredTextln(2, HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
        println_alignText(122, "Total Assessment : " + stringWithTabs_spaces(6, 2, String.valueOf(getTotalAssessment())));
        printlnWithNewLines_alignText(1, 114, "Balance : " + stringWithTabs_spaces(7, 2, String.valueOf(getTotalAssessment())));

        customHorizontalCenteredTextln(HORIZONTAL_LINE.getChoiceString().repeat(LINELENGTH.getChoiceInt()));
    }

    double getTotalTuition(){
        totalTuition = tuitionA + tuitionB;

        return  totalTuition;
    }

    double getTotalMiscellaneous(){
        totalMiscellaneous = athletics + guidance + library + medicalAndDental + registration + sbo_ssc_sscf + schoolPaper_universityOrgran +
        ID + socioCultural + insurrance + journal + internet + studentDevelopment + entrance;
        
        return totalMiscellaneous;
    }

    double getTotalAssessment(){
        totalAssessment = getTotalTuition() + computerLaboratory + getTotalMiscellaneous();

        return totalAssessment;
    }
}