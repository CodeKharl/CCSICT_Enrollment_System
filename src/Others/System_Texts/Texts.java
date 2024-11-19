package Others.System_Texts;

import Others.System_IN.System_in;

public interface Texts{
  public static void showTexts(String word){
    switch(word){
      case "termsAndConditions" -> showTermsAndConditions();
      case "termsAndConditionsReferences" -> showTermsAndConditionsReference();
      case "mainMenuCover" -> showMainDesign();
      case "partialPaymentProcedure" -> showPartialPaymentProcedure();
      case "underBridgeState" -> showUnderbridgeProgramStatement();
      default -> System.out.println("No Found Texts.");
    }
  }

  private static void showMainDesign(){
    System_in.StaticShortcuts.sPrintWithNewLines(3, """
                                **********          **********          **************          *****************            **********       *****************
                               **********          **********          **************           *****************           **********        *****************
                              **********          **********          **************            *****************          **********         *****************
                             ***                 ***                 ***                               ***                ***                        ***
                            ***                 ***                 ***                                ***               ***                         ***
                           ***                 ***                   ***                               ***              ***                          ***
                          ***                 ***                     **************                   ***             ***                           ***
                          ***                 ***                      **************                  ***             ***                           ***
                          ***                 ***                       **************                 ***             ***                           ***
                           ***                 ***                                  ***                ***              ***                          ***
                            ***                 ***                                  ***               ***               ***                         ***
                             ***                 ***                                ***                ***                ***                        ***
                              **********          **********           ***************          *****************          **********                ***
                               **********          **********         ***************           *****************           **********               ***
                                **********          **********       ***************            *****************            **********              ***
            """);
  }

  private static void showTermsAndConditions(){
    System_in.StaticShortcuts.sPrintWithNewLines_alignText(8, System_in.forAnimation_Design.DEFAULT_NO_COLUMNS.getChoiceInt(), "CSSICT ENROLLMENT SYSTEM TERMS AND CONDITIONS");
    System_in.StaticShortcuts.sPrintWithNewLines(2, """
                                                    By using this CSSICT Enrollment System, you give your assent and approval to the School's
                                              Data Privacy Policy. You hereby authorize the School to collect, record, organize, update, use,
                                              combine, or delete your personal information for the following purposes:
                                              1. Reviewing applications for admission to the school;
                                              2. Ceating and managing student information systems;
                                              3. Sharing grades amongst and among faculty members and anyone with valid official nee for
                                                 academic deliberation and evaluation of student performance;
                                              4. Process scholarship applications, grants, and other kinds of financial aid;
                                              5. Recording, creating, and maintaining student records for academic, co-curricular, and
                                                 extracurricular monitoring;
                                              6. Creating and assembling reports for statistical and research purposes;
                                              7. Researching occurrences related to student discipline; and
                                              8. Provinding student services.

                                                    You also agree that any personal information you provide may be entered and maintained in
                                              the School's authorized information and communications system, which includes security measures
                                              to protect your personal information. Your personal information will be accessed and shared only
                                              by the School, its authorized personnel, and other persons or institutions as required by law.
                                              You agree that there must be a free flow of personal information to and from the School in order
                                              to provide effective student services and implement institutional measure. You confirm your
                                              rights to be informed, to object to processing, to access and correct, to suspend or remove your
                                              personal information, and to be compresated for damages under Republic Act No. 10137, also known
                                              as the Privacy Act of 2012, and its Implementinng Rules and Regulations.
              """);
  }

  private static void showTermsAndConditionsReference(){
    System.out.println("""
      Online Enrollment Application System.(n.d).
          https://enrollment.uc.edu.ph/college/Enrollment/Policies
      """);
  }

  private static void showUnderbridgeProgramStatement(){
    System_in.StaticShortcuts.sNewLines(17);
    System.out.println("""
                                                  As you haven't completed any undergraduate programs offered by CCSICT, you'll need to enroll
                                            in and finish the Underbridge Program first. The Underbridge Program is designed to prepare you for
                                            graduate-level studies in Information Technology.
              """);
  }

  private static void showPartialPaymentProcedure(){
    System_in.StaticShortcuts.sPrintWithNewLines_alignText(9, System_in.forAnimation_Design.DEFAULT_NO_COLUMNS.getChoiceInt(), "PARTIAL PAYMENT PROCEDURE");
    System_in.StaticShortcuts.sPrintWithNewLines(2, """
                                              To make a partial payment for your enrollment fees, please follow the steps below:
                                                
                                                  Access the Main Menu: Begin by opening the main menu from your dashboard.
                                                  
                                                  Navigate to Account: Select the Account option to view your account details and payment options.
                                                  
                                                  Go to Payments: Within the account section, locate and click on Payments.
                                                  
                                                  Select Unfinished Payments: Choose Unfinished Payments to see any pending or partial payments.
                                                  
                                                  Choose Enrollment Fees: From the list, select Enrollment Fees to proceed with your payment.
                                              
                                              
                                              IMPORTANT NOTES!
                                                
                                                  Daily Transaction Limit: Only one transaction for this payment type is permitted each day.
                                                      Please ensure each payment is finalized to avoid delays.
                                                  
                                                  Payment Deadline: All partial payments must be completed by the end of the semester.
                                                      Failure to complete the payments by this deadline may affect your enrollment status.
              """);
  }
}


