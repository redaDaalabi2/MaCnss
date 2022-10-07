package Helpers;

import java.util.Scanner;
import java.util.UUID;

public class GlobalHelpers {
    public static void Print(String Message){

        System.out.println(Message);
    }
    public static void Print(String Message,ConsoleForeground ForeColor,ConsoleBackground BackgroundColor){
        System.out.print(ForeColor.getValue());
        System.out.print(BackgroundColor.getValue());

        System.out.println(Message);

        System.out.print(ConsoleForeground.RESET.getValue());
        System.out.print(ConsoleBackground.RESET.getValue());
    }
    public static void Print(String Message,ConsoleForeground ForeColor){
        System.out.print(ForeColor.getValue());

        System.out.println(Message);


        System.out.print(ConsoleForeground.RESET.getValue());

    }
    public static void Print(String Message,ConsoleBackground BackgroundColor){
        System.out.print(BackgroundColor.getValue());

        System.out.println(Message);

        System.out.print(ConsoleForeground.RESET.getValue());
    }
    public static int ReadInt(String Message) {
        boolean error=false;
        do {
            error=false;
            try {
                System.out.println(Message);
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (Exception ex) {
                System.out.println("Value entered is not valid.");
                error=true;
            }
        }while (error);
        return -1;
    }

    public static String randomAlphaNumeric(int i) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(i);
        for (int j = 0; j < i; j++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
    public static String genereteMatricule(){
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        int chaytz = (13-filterStr.length());
        for (int i = 0; i < chaytz; i++) {
            double n = Math.random() * 10;
            filterStr = filterStr + ((int)n);
        }
        filterStr=filterStr.replaceAll("-", ""+ (int)Math.random() * 10);
        return filterStr;
    }
}
