package Others.System_IN;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class System_in implements Shortcuts, Animate, ConsoleClear{
  protected static long accountNo; //account number
  protected static String username; //the username of the user
  protected static String password; //the password of the user account
  protected static String studentType; //variable of what type of the student the user is.

  protected Scanner scan = new Scanner(System.in);

  protected File ASSESSMENT_FILE = new File("data\\Students Assessment\\" + accountNo + "_Assessment.txt");
  protected final File ACCOUNT_INFO_FILE = new File("data\\Accounts_info.dat"); //data file that saves the username, password, and studentType of the user.
  protected final File STUDENT_INFO_FILE  = new File("data\\Students Info\\" + accountNo + "Std_Info.dat"); //stores the student's personal information and course of the students.
  protected final File COURSE_INFO_FILE = new File("data\\Course.dat"); //stores the student's courseChoice
  protected final File PARTIALPAYMENT_TRANSACTION_FILE = new File("data\\Payments\\PartialPayment.dat");
  protected final File FULLPAYMENT_TRANSACTION_FILE = new File("data\\Payments\\FullPayment_cash.dat");
  protected final File ACCOUNT_FILE = new File("data\\Accounts.dat"); //file that contains accounts balance and etc.
  
  protected String line_Data; //variable that holds a line of data in a file that reads by the file reader.
  protected boolean isAccount_condition; //variable that holds a judgement about the conditions on file readings.

  protected static String getUsername(){ //for using the username variable with the value in different classes outside this package.
    return System_in.username;
  }

  protected static long getAccountNumber(){ //same as previous function but for getting the account number.
    return System_in.accountNo;
  }

  protected static String getStudentType(){
    return System_in.studentType;
  }

  protected void fileWrite(){} //can override function use for writing texts to the file.

  protected void setDirectory(String directory){
    new File(directory).mkdirs();
  }

  public void typeAnyCharacter(String str){ //use for holding loops in the system when printing;
    lnHorizontalCenteredText_animation(DEFAULT_ANIMATION_SPEED.getValueInt(), "ENTER ANY TYPE OF CHARACTER " + str);
    scan.nextLine();
  }

  public enum forDocument{ //enum containing values for writing and editing file.
    NOTEPAD_MAXCOLUMN(190), NOTEPAD_MIDCOLUMN(NOTEPAD_MAXCOLUMN.value / 2);

      private int value;

      private forDocument(int value){
          this.value = value;
      }

      public int getValue(){
          return value;
      }
  }

  public enum Messages { //enum containing messages that uses all through the system.
    MESSAGE(null), INVALIDCHOICE("INVALID INPUT! PLEASE ENTER VALID CHOICE"), NUMFORMATEXCEPTION("ONLY NUMBERS IS APPLICABLE TO INPUT!"),
    FILLUP("NEED TO FILL UP!"), BACKTOMAIN("BACK TO MAIN MENU...");
  
    private String message;
    
    private Messages(String message){
      this.message = message;
    }
  
    public String getMessage(){
      return message;
    }
  
    public String invalidInputModifier(String type){
      message = "INVALID INPUT! PLEASE ENTER VALID " + type + ".";
      return message;
    }
  }

  public Messages NUMBERFORMATEXCEPTION = Messages.NUMFORMATEXCEPTION;
  public Messages INVALIDCHOICE = Messages.INVALIDCHOICE;
  public Messages NEEDTOFILLUP = Messages.FILLUP;
  public Messages BACKTOMAINMENU = Messages.BACKTOMAIN;
  public Messages MESSAGE = Messages.MESSAGE;
  
  public interface StaticShortcuts{  //this interface is inside this class since it is static function. It can access by using the class and its own name.
    public static void sNewLines(int newLines){//print a lot of newlines depending to the parameter.
      for(int i = 0; i < newLines; i++) System.out.println();
    }
  
    public static void sPrintWithNewLines(int newLines, String string){ //print newlines with texts.
      sNewLines(newLines);
      System.out.print(string);
    }
  
    public static void sPrintWithNewLines_alignText(int newLine, int columns, String string){ //this funcion is the same to the previous but it can print multiple newlines before the text.
      for(int i = 0; i < newLine; i++) System.out.println();
      System.out.print(String.format("%" + ((columns - string.length()) /2) + "s%s", "", string)); /*this will print the text that have given paddings. For e.g(%75s%s). By using this string.format, the
      it will take up 75 columns following with given string.*/
    }

    public static void sNewLinesHorizontalCenteredTextln(int newLines, String string){
      int padding = (Animate.forAnimation_Design.DEFAULT_NO_COLUMNS.getValueInt() - string.length()) / 2;
  
      sNewLines(newLines);
      System.out.print(String.format("%" + padding + "s%s%" + padding + "s",  "", string, ""));
      System.out.println();
    }
  }
}

interface Shortcuts{
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

  public Animate.forAnimation_Design ARROW = Animate.forAnimation_Design.ARROW;
  
  public default void newLine(int n){
    for(int i = 0; i < n; i++) System.out.println();
  }
  
  public default void tabs(int n){
    for(int i = 0; i < n; i++) System.out.print("\t");
  }

  public default void spaces(int n){
    for(int i = 0; i < n; i++) System.out.print("\s");
  }

  public default void printWithTabs_spaces(int tabs, int spaces, String string){
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
  
  public default String tabsAndSpacesAfterString(int tabs, int spaces){
    String textStruct = "";
    for(int i = 0; i < tabs; i++) textStruct += "\t";
    for(int y = 0; y < spaces; y++) textStruct += " ";

    return textStruct;
  }

  public default void horizontalCenteredText(String string){
    PADDING.setValueInt((DEFAULT_NO_COLUMNS.getValueInt() - string.length()) / 2);
    System.out.print(String.format("%" + PADDING.getValueInt() + "s%s%" + PADDING.getValueInt() + "s",  "", string, ""));
  }

  public default void horizontalCenteredTextln(String string){
    PADDING.setValueInt((DEFAULT_NO_COLUMNS.getValueInt() - string.length()) / 2);
    System.out.println(String.format("%" + PADDING.getValueInt() + "s%s%" + PADDING.getValueInt() + "s",  "", string, ""));
  }

  public default void lnHorizontalCenteredText(String string){
    PADDING.setValueInt((DEFAULT_NO_COLUMNS.getValueInt() - string.length()) / 2);

    System.out.println();
    System.out.print(String.format("%" + PADDING.getValueInt() + "s%s%" + PADDING.getValueInt() + "s",  "", string, ""));
  }

  public default void lnHorizontalCenteredTextln(String string){
    PADDING.setValueInt((DEFAULT_NO_COLUMNS.getValueInt() - string.length()) / 2);

    System.out.println();
    System.out.print(String.format("%" + PADDING.getValueInt() + "s%s%" + PADDING.getValueInt() + "s",  "", string, ""));
    System.out.println();
  }

  public default void newLinesHorizontalCenteredText(int newLines, String string){
    PADDING.setValueInt((DEFAULT_NO_COLUMNS.getValueInt() - string.length()) / 2);

    newLine(newLines);
    System.out.print(String.format("%" + PADDING.getValueInt() + "s%s%" + PADDING.getValueInt() + "s",  "", string, ""));
  }

  public default void newLinesHorizontalCenteredTextln(int newLines, String string){
    PADDING.setValueInt((DEFAULT_NO_COLUMNS.getValueInt() - string.length()) / 2);

    newLine(newLines);
    System.out.print(String.format("%" + PADDING.getValueInt() + "s%s%" + PADDING.getValueInt() + "s",  "", string, ""));
    System.out.println();
  }

  public default void println_alignText(int columns, String string){
    PADDING.setValueInt((columns - string.length()) / 2);

    System.out.println(String.format("%" + PADDING.getValueInt() + "s%s", "", string));
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
    return String.format("%" + (columns - str.length()) + "s%s", "", str);
  }

  public default String document_center_StringAlign(String str){
    int padding = (System_in.forDocument.NOTEPAD_MAXCOLUMN.getValue() - str.length()) / 2;
    return String.format("%" + padding + "s%s%" + padding + "s", "", str, "");
  }
}

interface Animate{
  public enum forAnimation_Design{
    DEFAULT_STEPS(5), SHORT_STEPS(3), LONG_STEPS(7),
    SHORT_DELAY(300), DEFAULT_DELAY(600), LONG_DELAY(900),
    SHORT_ANIMATION_SPEED(10), DEFAULT_ANIMATION_SPEED(30), LONG_ANIMATION_SPEED(50),
    ARROW("--> "),
    DEFAULT_NO_COLUMNS(156),
    LINELENGTH(0), PADDING(0);

    private String value;
  
    private forAnimation_Design(String value){
      this.value = value;
    }
    
    private forAnimation_Design(int value) {
      this.value = String.valueOf(value);
    }
  
    public void setValueInt(int value){
      this.value = String.valueOf(value);
    }
  
    public int getValueInt(){
      return Integer.parseInt(value);
    }
  
    public String getValueString(){
      return this.value;
    }
  }
  
  private void loading(int columns, char[] characterAni, int steps, int delay, String partOfAnimation){
    for (int i = 0; i < steps; i++) {
      for(char chars : characterAni){
        print_backToFirstPoint_currentLine_spaces(columns, partOfAnimation + " " + chars + " ");
        threading(delay);
      }
    }
  }

  public default void percentageLoading(int columns){
    for(int i = 0; i <= 100; i++){
      print_backToFirstPoint_currentLine_spaces(columns, "LOADING " + i + "% ");
      threading(20);
    }
  }

  public default void spinLoading(int newLine, int columns, int steps, String text, int delay){
    char[] rotation = {'|', '/', '-', '\\'};

    for(int i = 0; i < newLine; i++) System.out.println();
    loading(columns, rotation, steps, delay, text);
  }

  private void print_backToFirstPoint_currentLine_spaces(int columns, String string){ //have return to firstline method.
    int padding = (columns - string.length()) / 2;
    System.out.print(String.format("\r%" + padding + "s%s%" + padding + "s", "", string, ""));
  }

  public default void threading(int delay){
    try{
      Thread.sleep(delay);
    } catch(InterruptedException e){}
  }

  public default void lnHorizontalCenteredText_animation(int delay, String string){
    System.out.print("\n" + String.format("%" + (Animate.forAnimation_Design.DEFAULT_NO_COLUMNS.getValueInt() - string.length()) / 2 + "s", "", ""));
    for(char chars : string.toCharArray()){
      System.out.print(chars);
      threading(delay);
    }
  }

  public default void newLinesHorizontalCenteredText_animation(int newLines, int delay, String string){
    for(int i = 0; i < newLines; i++) System.out.println();

    System.out.print(String.format("%" + (Animate.forAnimation_Design.DEFAULT_NO_COLUMNS.getValueInt() - string.length()) / 2 + "s", "", ""));
    
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
    System.out.println();
    System.out.print(String.format("%" + ((columns - string.length()) / 2) + "s", "", ""));
    
    for(int i = 0; i < string.length(); i++){
      System.out.print(string.charAt(i));
      threading(delay);
    }
  }

  public default void printWithNewLines_alignText_animation(int newLine, int delay, int columns, String string){
    for(int i = 0; i < newLine; i++) System.out.println();

    System.out.print(String.format("%" + (columns - string.length()) / 2 + "s", "", ""));
    
    for(int i = 0; i < string.length(); i++){
      System.out.print(string.charAt(i));
      threading(delay);
    }
  }
}

interface ConsoleClear{
  public default void clear(){
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (IOException | InterruptedException e) {
      System.out.println("Failed to Clear the Console.");
    }
  }
}