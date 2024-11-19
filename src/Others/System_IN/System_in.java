package Others.System_IN;

import Main.Login_Signup.Login_Signup;
import java.io.File;
import java.io.IOException;
import java.time.MonthDay;
import java.time.Year;
import java.util.Scanner;

public class System_in implements Shortcuts, Animate, ConsoleClear{
  protected static long accountNo; //account number
  protected static String username; //the username of the user
  protected static String password; //the password of the user account
  protected static String studentType; //variable of what type of the student the user is.

  public File ASSESSMENT_FILE = new File( "data\\Students Assessment\\" + accountNo + "_Assessment.txt");
  protected final File ACCOUNT_INFO_FILE = new File("data\\Accounts_info.dat"); //data file that saves the username, password, and studentType of the user.
  public final File STUDENT_INFO_FILE  = new File("data\\Student Info\\" + accountNo + "Std_Info.dat"); //stores the student's personal information and course of the students.
  public final File COURSE_INFO_FILE = new File("data\\Course.dat"); //stores the student's courseChoice
  public String line_Data; //variable that holds a line of data in a file that reads by the file reader.
  public String[] array_line_data; //holds the data of line_data.
  public long line_count; //holds the number of lines in a file.
  public boolean isAccount_condition; //variable that holds a judgement about the conditions on file readings.

  public final String[] ASSESSMENTHEADER = {"ISABELA STATE UNIVERSITY", "Main Campus", "Echague, Isabela", "Subject Schedule and Assessment", "Date: " + MonthDay.now().getMonth() + " " + MonthDay.now().getDayOfMonth() + ", " + Year.now()};

  public enum forDocument{ //enum containing values for writing and editing file.
    NOTEPAD_MAXCOLUMN(190), NOTEPAD_MIDCOLUMN(NOTEPAD_MAXCOLUMN.value / 2);

      int value;

      forDocument(int value){
          this.value = value;
      }

      public int getValue(){
          return value;
      }
  }

  public enum Messages { //enum containing messages that uses all through the system.
    MESSAGE(null), INVALIDCHOICE("INVALID INPUT! PLEASE ENTER VALID CHOICE"), NUMFORMATEXCEPTION("ONLY NUMBERS IS APPLICABLE TO INPUT!"),
    FILLUP("NEED TO FILL UP!"), BACKTOMAIN("BACK TO MAIN MENU..."), DELETE_FILE_ERROR("FAILED TO DELETE THE FILE."), FILE_NOT_FOUND("FILE NOT FOUND!");
  
    private String message;
    Messages(String message){
      this.message = message;
    }
  
    public String getMessage(){
      return message;
    }
  
    public void setMessage(String message){
      this.message = message;
    }
  
    public String invalidInputModifier(String type){
      message = "INVALID INPUT! PLEASE ENTER VALID " + type;
  
      return message;
    }
  }

  public Messages NUMBERFORMATEXCEPTION = Messages.NUMFORMATEXCEPTION;
  public Messages INVALIDCHOICE = Messages.INVALIDCHOICE;
  public Messages NEEDTOFILLUP = Messages.FILLUP;
  public Messages BACKTOMAINMENU = Messages.BACKTOMAIN;
  public Messages MESSAGE = Messages.MESSAGE;
  public Messages DELETE_FILE_ERROR = Messages.DELETE_FILE_ERROR;
  public Messages FILE_NOT_FOUND = Messages.FILE_NOT_FOUND;


  public interface StaticShortcuts{  //this interface is inside this class since it is static function. It can access by using the class and its own name.
    public static void sNewLines(int newLines){//print a lot of newlines depending to the parameter.
      for(int i = 0; i < newLines; i++) System.out.println();
    }
  
    public static void sTabs(int tabs){
      for(int i = 0; i < tabs; i++) System.out.print("\t"); //for tabs
    }
  
    public static void sSpaces(int spaces){
      for(int i = 0; i < spaces; i++) System.out.print(" "); //for spaces
    }
  
    public static void sPrintWithNewLines(int newLines, String string){ //print newlines with texts.
      sNewLines(newLines);
      System.out.print(string);
    }
  
    public static void sPrint_alignText(int columns, String string){ //alternative function to align a text by columns
      int padding = (columns - string.length()) / 2; //this variable is the most important. For e.g(Animate.forAnimation_Design.DEFAULT_NO_COLUMNS.getChoiceInt()(columns) - 6(string.length) / 2) = 75.
  
      System.out.print(String.format("%" + padding + "s%s", "", string)); /*this will print the text that have given paddings. For e.g(%75s%s). By using this string.format, the
                                                                                    it will take up 75 columns following with given string.*/
    }
  
    public static void sPrintWithNewLines_alignText(int newLine, int columns, String string){ //this funcion is the same to the previous but it can print multiple newlines before the text.
      for(int i = 0; i < newLine; i++) System.out.println();
      sPrint_alignText(columns, string);
    }

    public static void sNewLinesHorizontalCustomCenteredTextln(int newLines, String string){
      int padding = (Animate.forAnimation_Design.DEFAULT_NO_COLUMNS.getChoiceInt() - string.length()) / 2;
  
      sNewLines(newLines);
      System.out.print(String.format("%" + padding + "s%s%" + padding + "s",  "", string, ""));
      System.out.println();
    }

    public static void sPrintWithTabs_newLines_spaces(int newLine, int tabs, int spaces, String string){
      for(int i = 0; i < newLine; i++) System.out.println();
      for(int y = 0; y < tabs; y++) System.out.print("\t");
      for(int x = 0; x < spaces; x++) System.out.print(" ");
      System.out.print(string);
    }

    public static void sPrintbackTofirstPointOfCurrentLineWithSpaces(int columns, String string){
      System.out.print(String.format("\r%" + ((columns - string.length()) / 2) + "s%s", "", string));
    }
  
    public static void sPrintln_alignText(int columns, String string){
      int padding = (columns - string.length()) / 2;
  
      System.out.println(String.format("%" + padding + "s%s", "", string));
    }
  }

  public static void launch(){ //lunch function that needs to run this program in app class.
      Login_Signup signin_signup = new Login_Signup();
      signin_signup.signin_signup_Menu();
  }

  public static String getUsername(){ //for using the username variable with the value in different classes outside this package.
      return System_in.username;
  }

  public static long getAccountNumber(){ //same as previous function but for getting the account number.
      return System_in.accountNo;
  }

  public static String getStudentType(){
      return System_in.studentType;
  }

  public void fileWrite(){ //can override function use for writing texts to the file.
  }

  public void fileDelete(File file){
      if(!file.delete()) lnHorizontalCustomCenteredTextln(DELETE_FILE_ERROR.getMessage());
  }

  public void typeAnyCharacter(String str){ //use for holding loops in the system when printing;
    lnCustomHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getChoiceInt(), "ENTER ANY TYPE OF CHARACTER " + str);
    scan.nextLine();
  }
}

interface Shortcuts{
  Scanner scan = new Scanner(System.in);

  public Animate.forAnimation_Design DEFAULT_NO_COLUMNS = Animate.forAnimation_Design.DEFAULT_NO_COLUMNS;
  
  public Animate.forAnimation_Design SHORTSTEP = Animate.forAnimation_Design.SHORT_STEPS;
  public Animate.forAnimation_Design DEFAULTSTEP = Animate.forAnimation_Design.DEFAULT_STEPS;
  public Animate.forAnimation_Design LONGSTEP = Animate.forAnimation_Design.LONG_STEPS;

  public Animate.forAnimation_Design DEFAULT_DELAY = Animate.forAnimation_Design.DEFAULT_DELAY;
  public Animate.forAnimation_Design SHORT_DELAY = Animate.forAnimation_Design.SHORT_DELAY;
  public Animate.forAnimation_Design LONG_DELAY = Animate.forAnimation_Design.LONG_DELAY;

  public Animate.forAnimation_Design SHORT_ANIMATION_SPEED = Animate.forAnimation_Design.SHORT_ANIMATION_SPEED;
  public Animate.forAnimation_Design DEFAULT_ANIMATION_SPEED = Animate.forAnimation_Design.DEFAULT_ANIMATION_SPEED;
  public Animate.forAnimation_Design LONG_ANIMATION_SPEED = Animate.forAnimation_Design.LONG_ANIMATION_SPEED;

  public Animate.forAnimation_Design LINELENGTH = Animate.forAnimation_Design.LINELENGTH;
  public Animate.forAnimation_Design PADDING = Animate.forAnimation_Design.PADDING;

  public Animate.forAnimation_Design HORIZONTAL_LINE = Animate.forAnimation_Design.HORIZONTAL_LINE;
  public Animate.forAnimation_Design HORIZONTAL_DOUBLE_LINE = Animate.forAnimation_Design.HORIZONTAL_DOUBLE_LINE;
  public Animate.forAnimation_Design VERTICAL_LINE = Animate.forAnimation_Design.VERTICAL_LINE;
  public Animate.forAnimation_Design ARROW = Animate.forAnimation_Design.ARROW;
  
  public default void newLine(int n){
    for(int i = 0; i < n; i++) System.out.println();
  }
  
  public default void tabs(int n){
    for(int i = 0; i < n; i++) System.out.print("\t");
  }

  public default void spaces(int n){
    for(int i = 0; i < n; i++) System.out.print(" ");
  }

  public default void newLine_tabs_spaces(int newLines, int tabs, int spaces){
    newLine(newLines);
    tabs(tabs);
    spaces(spaces);
  }
  
  public default void tabsAndSpaces(int tabs, int spaces){
    tabs(tabs);
    spaces(spaces);
  }

  public default void printWithTabs_newLines(int newLines, int tabs, String string){
    newLine(newLines);
    tabs(tabs);
    System.out.print(string);
  }

  public default void printWithTabs_spaces(int tabs, int spaces, String string){
    tabs(tabs);
    spaces(spaces);
    System.out.print(string);
  }

  public default void printWithTabs(int tabs, String string){
    tabs(tabs);
    System.out.print(string);
  }

  public default void printWithSpaces(int spaces, String string){
    spaces(spaces);
    System.out.print(string);
  }

  public default void printWithReturnBegin_tabs_spaces(int tabs, int spaces, String string){
    System.out.print("\r");
    tabs(tabs);
    spaces(spaces);
    System.out.print(string);
  }

  public default String lnstringWithTabs_spaces(int tabs, int spaces, String string){
    String textStruct = "\n";
    for (int y = 0; y < tabs; y++) textStruct += "\t";
    for (int x = 0; x < spaces; x++) textStruct += " ";
    textStruct += string;

    return textStruct;
  }

  public default String stringWithTabs_newLines_spaces(int newLines, int tabs, int spaces, String string){
    String textStruct = "";
    for (int i = 0; i < newLines; i++) textStruct += "\n";
    for (int y = 0; y < tabs; y++) textStruct += "\t";
    for (int x = 0; x < spaces; x++) textStruct += " ";
    textStruct += string;

    return textStruct;
  }

  public default String stringWithTabs_newLines(int newLines, int tabs, String string){
    String textStruct = "";
    for(int i = 0; i < newLines; i++) textStruct += "\n";
    for(int y = 0; y < tabs; y++) textStruct += "\t";
    textStruct += string;

    return textStruct;
  }

  public default String stringWithTabs_spaces(int tabs, int spaces, String string){
    String textStruct = "";
    for(int i = 0; i < tabs; i++) textStruct += "\t";
    for(int y = 0; y < spaces; y++) textStruct += " ";
    textStruct += string;

    return textStruct;
  }

  public default String stringWithTabs(int tabs, String string){
    String textStruct = "";
    for(int x = 0; x < tabs; x++) textStruct += "\t";
    textStruct += string;
    
    return textStruct;
  }

  public default String stringWithSpaces(int spaces, String string){
    String textStruct = "";
    for(int y = 0; y < spaces; y++) textStruct += " ";
    textStruct += string;
    
    return textStruct;
  }
  
  public default void lnprintWithTabs_spaces(int tabs, int spaces, String string){
    System.out.println();
    tabs(tabs);
    spaces(spaces);
    System.out.print(string);
  }

  public default void lnprintlnWithTabs_spaces(int tabs, int spaces, String string){
    System.out.println();
    tabs(tabs);
    spaces(spaces);
    System.out.println(string);
  }

  public default void printWithTabs_newLines_spaces(int newLine, int tabs, int spaces, String string){
    for (int i = 0; i < newLine; i++) System.out.print("\n");
    for (int y = 0; y < tabs; y++ ) System.out.print("\t");
    for(int x = 0; x < spaces; x++) System.out.print(" ");
    System.out.print(string);
  }

  public default String spacesAfterString(int spaces){
    String textStruct = "";
    for(int y = 0; y < spaces; y++) textStruct += " ";

    return textStruct;
  }

  public default String newLinesString(int newLine){
    String textStruct = "";
    for(int y = 0; y < newLine; y++) textStruct += "\n";

    return textStruct;
  }
  
  public default String tabsAndSpacesAfterString(int tabs, int spaces){
    String textStruct = "";
    for(int i = 0; i < tabs; i++) textStruct += "\t";
    for(int y = 0; y < spaces; y++) textStruct += " ";

    return textStruct;
  }

  public default void customHorizontalCenteredText(String string){
    PADDING.setChoiceInt((DEFAULT_NO_COLUMNS.getChoiceInt() - string.length()) / 2);
    System.out.print(String.format("%" + PADDING.getChoiceInt() + "s%s%" + PADDING.getChoiceInt() + "s",  "", string, ""));
  }

  public default void customHorizontalCenteredTextln(String string){
    PADDING.setChoiceInt((DEFAULT_NO_COLUMNS.getChoiceInt() - string.length()) / 2);
    System.out.println(String.format("%" + PADDING.getChoiceInt() + "s%s%" + PADDING.getChoiceInt() + "s",  "", string, ""));
  }

  public default void lnHorizontalCustomCenteredText(String string){
    PADDING.setChoiceInt((DEFAULT_NO_COLUMNS.getChoiceInt() - string.length()) / 2);

    System.out.println();
    System.out.print(String.format("%" + PADDING.getChoiceInt() + "s%s%" + PADDING.getChoiceInt() + "s",  "", string, ""));
  }

  public default void lnHorizontalCustomCenteredTextln(String string){
    PADDING.setChoiceInt((DEFAULT_NO_COLUMNS.getChoiceInt() - string.length()) / 2);

    System.out.println();
    System.out.print(String.format("%" + PADDING.getChoiceInt() + "s%s%" + PADDING.getChoiceInt() + "s",  "", string, ""));
    System.out.println();
  }

  public default void newLinesHorizontalCustomCenteredText(int newLines, String string){
    PADDING.setChoiceInt((DEFAULT_NO_COLUMNS.getChoiceInt() - string.length()) / 2);

    newLine(newLines);
    System.out.print(String.format("%" + PADDING.getChoiceInt() + "s%s%" + PADDING.getChoiceInt() + "s",  "", string, ""));
  }

  public default void newLinesHorizontalCustomCenteredTextln(int newLines, String string){
    PADDING.setChoiceInt((DEFAULT_NO_COLUMNS.getChoiceInt() - string.length()) / 2);

    newLine(newLines);
    System.out.print(String.format("%" + PADDING.getChoiceInt() + "s%s%" + PADDING.getChoiceInt() + "s",  "", string, ""));
    System.out.println();
  }

  public default void println_alignText(int columns, String string){
    PADDING.setChoiceInt((columns - string.length()) / 2);

    System.out.println(String.format("%" + PADDING.getChoiceInt() + "s%s", "", string));
  }

  public default void lnprint_alignText(int columns, String string){
    int padding = (columns - string.length()) / 2;

    System.out.println();
    System.out.print(String.format("%" + padding + "s%s", "", string));
  }

  public default void lnprintln_alignText(int columns, String string){
    int padding = (columns - string.length()) / 2;

    System.out.println();
    System.out.println(String.format("%" + padding + "s%s", "", string));
  }

  public default void print_alignText(int columns, String string){
    int padding = (columns - string.length()) / 2;

    System.out.print(String.format("%" + padding + "s%s", "", string));
  }

  public default void printWithNewLines_alignText(int newLine, int columns, String string){
    for(int i = 0; i < newLine; i++) System.out.println();

    print_alignText(columns, string);
  }

  public default void printlnWithNewLines_alignText(int newLine, int columns, String string){
    for(int i = 0; i < newLine; i++) System.out.println();

    println_alignText(columns, string);
  }
  
  public default String document_stringAlign(int columns, String str){
    int padding = columns - str.length();

    String string = String.format("%" + padding + "s%s", "", str);

    return string;
  }

  public default String document_center_StringAlign(String str){
    int padding = (System_in.forDocument.NOTEPAD_MAXCOLUMN.getValue() - str.length()) / 2;
    String string = String.format("%" + padding + "s%s%" + padding + "s", "", str, "");

    return string;
  }
}

interface Animate{
  public enum forAnimation_Design{
    DEFAULT_STEPS(5), SHORT_STEPS(3), LONG_STEPS(7),
    SHORT_DELAY(300), DEFAULT_DELAY(600), LONG_DELAY(900),
    SHORT_ANIMATION_SPEED(10), DEFAULT_ANIMATION_SPEED(30), LONG_ANIMATION_SPEED(50),
    HORIZONTAL_LINE("-"), HORIZONTAL_DOUBLE_LINE("="), VERTICAL_LINE("|"), ARROW("--> "),
    DEFAULT_NO_COLUMNS(156),
    LINELENGTH(0), PADDING(0);

    String choice;
  
    private forAnimation_Design(String choice){
      this.choice = choice;
    }
    
    private forAnimation_Design(int choice) {
      this.choice = String.valueOf(choice);
    }
  
    public void setChoiceInt(int choice){
      this.choice = String.valueOf(choice);
    }
  
    public int getChoiceInt(){
      return Integer.parseInt(choice);
    }
  
    public String getChoiceString(){
      return this.choice;
    }
  }

  String[] rotation = {"|", "/", "-", "\\"};
  
  private void loading(int columns, String[] characterAni, int steps, int delay, String partOfAnimation){
    for (int i = 0; i < steps; i++) {
      for(String string : characterAni){
        printBackToFirstPointOfCurrentLineWithSpaces(columns, partOfAnimation + " " + string + " ");
        threading(delay);
      }
    }
  }

  public default void percentageLoading(int columns){
    for(int i = 0; i < 101; i++){
      printBackToFirstPointOfCurrentLineWithSpaces(columns, "LOADING " + i + "% ");
      threading(28);
    }
  }

  public default void spinLoading(int newLine, int columns, int steps, String text, int delay){
    for(int i = 0; i < newLine; i++) System.out.println();
    loading(columns, rotation, steps, delay, text);
  }

  private void printBackToFirstPointOfCurrentLineWithSpaces(int columns, String string){ //have return to firstline method.
    System.out.print(String.format("\r%" + ((columns - string.length()) / 2) + "s%s", "", string));
  }

  public default void threading(int delay){
    try{
      Thread.sleep(delay);
    } catch(InterruptedException e){}
  }

  public default void lnCustomHorizontalCenteredText_animation(int delay, String string){
    char[] characters = string.toCharArray();
    
    System.out.println();
    System.out.print(String.format("%" + (Animate.forAnimation_Design.DEFAULT_NO_COLUMNS.getChoiceInt() - string.length()) / 2 + "s", "", ""));
    for(char chars : characters){
      System.out.print(chars);
      threading(delay);
    }
  }

  public default void newLinesCustomHorizontalCenteredText_animation(int newLines, int delay, String string){
    for(int i = 0; i < newLines; i++) System.out.println();

    System.out.print(String.format("%" + (Animate.forAnimation_Design.DEFAULT_NO_COLUMNS.getChoiceInt() - string.length()) / 2 + "s", "", ""));
    
    for(int i = 0; i < string.length(); i++){
      System.out.print(string.charAt(i));
      threading(delay);
    }
  }

  public default void print_alignText_animation(int delay, int columns, String string){
    System.out.print(String.format("%" + (columns - string.length()) / 2 + "s", "", ""));
    
    for(int i = 0; i < string.length(); i++){
      System.out.print(string.charAt(i));
      threading(delay);
    }
  }

  public default void lnprint_alignText_animation(int delay, int columns, String string){
    int padding = (columns - string.length()) / 2;

    System.out.println();
    System.out.print(String.format("%" + padding + "s", "", ""));
    
    for(int i = 0; i < string.length(); i++){
      System.out.print(string.charAt(i));
      threading(delay);
    }
  }

  public default void println_alignText_animation(int delay, int columns, String string){
    System.out.print(String.format("%" + (columns - string.length()) / 2 + "s", "", ""));
    
    for(int i = 0; i < string.length(); i++){
      System.out.print(string.charAt(i));
      threading(delay);
    }

    System.out.println();
  }

  public default void printWithNewLines_alignText_animation(int newLine, int delay, int columns, String string){
    for(int i = 0; i < newLine; i++) System.out.println();

    System.out.print(String.format("%" + (columns - string.length()) / 2 + "s", "", ""));
    
    for(int i = 0; i < string.length(); i++){
      System.out.print(string.charAt(i));
      threading(delay);
    }
  }

  public default void printlnWithNewLines_alignText_animation(int newLine, int delay, int columns, String string){
    for(int i = 0; i < newLine; i++) System.out.println();
    System.out.print(String.format("%" + (columns - string.length()) / 2 + "s%s", "", ""));
    
    for(int i = 0; i < string.length(); i++){
      System.out.print(string.charAt(i));
      threading(delay);
    }

    System.out.println();
  }
}

interface ConsoleClear{ //separate object
  public default void clear(){ //function that clears the console
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //this function is only applicable in command prompt of windows.
    } catch (IOException | InterruptedException e) { //throw an error if the process builder failed to load
      System.out.println("Failed to Clear the Console!");
    }
  }
}