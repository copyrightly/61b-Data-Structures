/* Write a program called "Nuke2.java" containing a class called Nuke2 whose main
method reads a string from the keyboard and prints the same string, with its
second character removed.  (That's character number 1, since Java numbers
characters in a string starting from zero.)  In other words, after you run
"java Nuke2", if you type in the string "skin", the program will print "sin".
*/

import java.net.*;
import java.io.*;

public class Nuke2{
    public static void main(String[] args) throws Exception {
        BufferedReader keyboard;
        String inputLine;

        keyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter a string:");
        //System.out.flush();        /* Make sure the line is printed immediately. */
        inputLine = keyboard.readLine();
        char[] stringToCharArray = inputLine.toCharArray();
        String s = "" + stringToCharArray[0];
        for(int i = 2; i < stringToCharArray.length; i++){
            s = s + stringToCharArray[i];
        }
        System.out.println(s);
    }
}