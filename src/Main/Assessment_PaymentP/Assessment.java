package Main.Assessment_PaymentP;
import Main.MainUI;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Assessment extends MainUI{
    private final String title = "ASSESSMENT OF FEE'S";

    /* Indexs -> tuitionA = 0, tuitionB = 1, computerLaboratory = 2, athletics = 3, guidance = 4, library = 5, medicalAndDental = 6, registration = 7, sbo_ssc_sscf = 8,
    schoolPaper_universityOrgran = 9, ID = 10, socioCultural = 11, insurrance = 12, journal = 13, internet = 14, studentDevelopment = 15, entrance = 16 */

    private final String[][] assessmentDetails = {
        {"Tuition Fees", "Tuition Fee : 3 x 50/unit", "Tuition Fee : 22 x 100/unit"},
        {"Laboratory Fees", "Computer Laboratory : 2 x 450/subject"},
        {"Miscellaneous Fees", "Athletics Fee", "Guidance Fee", "Library Fee", "Medical and Dental Fee",
        "Registration Fee", "SBO/SCC/SSCF", "School Paper/University Organ Fee", "ID Fee", "Socio Cultural Fee",
        "Insurance Fee", "Journal Fee", "Internet Fee", "Student Development Fee", "Entrance Fee"}
    };

    private final double[][] assessmentFees = {
        {150.00, 2200.00},
        {900.00},
        {50.00, 20.00, 100.00, 50.00, 50.00, 60.00, 50.00, 100.00, 25.00, 40.00, 50.00, 40.00, 500.00, 100.00}
    };


    public void assessmentFees(boolean isAssessmentFees_show){ //this function includes the processing of assessment fee's.
        if (isAssessmentFees_show) assessmentShow();
        else{
            fileWrite();
            new Payment().paymentSelection(getTotalAssessmentFee());
        }
    }

    private void assessmentShow(){
        clear();

        LINELENGTH.setValueInt(78);
        newLinesHorizontalCenteredTextln(2,"=".repeat(LINELENGTH.getValueInt()));
        lnHorizontalCenteredTextln(title);
        lnHorizontalCenteredText("=".repeat(LINELENGTH.getValueInt()));
        
        double[] totals = {getTotalTuition(), assessmentFees[1][0], getTotalMiscellaneous()};
        for (int i = 0; i < assessmentDetails.length; i++) assessmentShow_alignment(assessmentDetails[i], assessmentFees[i], totals[i]);
        
        lnHorizontalCenteredText("-".repeat(LINELENGTH.getValueInt()));

        String[] downDetails = {"Total Assessment", "Balance"};
        for(String details : downDetails) lnprintlnWithTabs_spaces(6, 2, String.format("%s : %" + (53 - details.length()) + "s%s", details, "", getTotalAssessmentFee()));
    
        horizontalCenteredTextln("-".repeat(LINELENGTH.getValueInt()));
    }

    private void assessmentShow_alignment(String[] assessmentDetails, double[] fees, double total){
        printWithTabs_newLines_spaces(2, 5, 7, assessmentDetails[0] + "\n"); //print the type of the fee.
        
        for (int i = 1; i <= fees.length; i++) lnprintWithTabs_spaces(6, 2, String.format("%s%" + (40 - assessmentDetails[i].length()) + "s%s", assessmentDetails[i], "", fees[i - 1])); //print the details part with the fees.
        
        System.out.print(String.format("%" + (16 - String.valueOf(fees[fees.length - 1]).length() + "s%s\n"), "", String.valueOf(total))); //set the total for every type of fees to appropriate alignment
    }

    private double getTotalTuition(){
        return assessmentFees[0][0] + assessmentFees[0][1]; //adds tuition a + tuition b
    }

    private double getTotalMiscellaneous(){
        double total = 0;
        for(int i = 0; i < assessmentFees[2].length; i++) total += assessmentFees[2][i];
        return total;
    }

    private double getTotalAssessmentFee(){
        return getTotalTuition() + assessmentFees[1][0] + getTotalMiscellaneous(); //adds all fee's
    }

    @Override
    public void fileWrite(){ //writing the assessment fee's to the text file
        try {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(ASSESSMENT_FILE, true))){
                LINELENGTH.setValueInt(110);

                writer.append("\n\n\n" + stringWithTabs_spaces(5, 8, title) + "\n");
                writer.append(document_center_StringAlign("-".repeat(LINELENGTH.getValueInt())) + "\n\n");

                for (int i = 0; i < assessmentDetails.length; i++) document_assessmentFees_alignment(assessmentFees[i], assessmentDetails[i], writer);

                writer.append(document_center_StringAlign("-".repeat(LINELENGTH.getValueInt())));
                writer.append(lnstringWithTabs_spaces(7, 4, String.format("%s : %48s%s", "Total Fee", "", getTotalAssessmentFee())));
                writer.append("\n" + document_center_StringAlign("-".repeat(LINELENGTH.getValueInt())));
            }
        } catch (IOException e) {
        }
    }

    private void document_assessmentFees_alignment(double[] fees, String[] feesDetails, BufferedWriter writer) throws IOException{
        writer.append(stringWithTabs_spaces(6, 7, feesDetails[0]) + "\n\n");
        
        for(int i = 1; i < feesDetails.length; i++){
            writer.append(stringWithTabs_spaces(7, 4, String.format("%s%" + (60 - feesDetails[i].length()) + "s%s", feesDetails[i], "", fees[i - 1])));
            writer.newLine();
        }
        
        writer.newLine();
    }
}
