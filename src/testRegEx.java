import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testRegEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        char c = (char) scanner.nextInt();
//        System.out.println(c);
//        try {
//            Date date = new SimpleDateFormat("DD-MM-YYYY").parse("13-12-2020");
//            DateFormat format = new SimpleDateFormat("DD-MM-YYYY");
//            Date date1 = format.parse("13-12-2020");
////            System.out.println(date);
//            String s =date1.toString();
//            System.out.println(s);
//            String string = "January 12, 2010";
//            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//            Date date = format.parse(string);
//            System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010
//            Date date1 = new Date();
//            SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
//            String stringDate = DateFor.format(date1);
//            System.out.println( "date = "+date1);
//            stringDate.matches()
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    String s = "I am learning java with all my heart";
//        System.out.println(s.replaceAll("I","you"));
//
//        //only first gets replaced  ^ sign is the boundry matcher for the beginning
//        // $ sign is boundary matcher to the end of the string.
//        System.out.println("abcDeeF12dasdG99abcDee".replaceAll("^abcDee","THE BEGINNING"));
//        System.out.println("abcDeeF12dasdG99abcDee".replaceAll("$abcDee","THE END"));
//        System.out.println("abcDseeF12dasdG99sabscDee".replaceAll("[aeiou]","X")); //each vowel letter gets replaced by X
//        System.out.println("abcDeeF12dasdG99abcDee".replaceAll("[aeiou]","replaced by X")); //each vowel letter gets replaced by entire sentence
//        System.out.println("abcDseeF12dasdG99sabscDee".replaceAll("[aeiou][Fs]","X")); //each vowel letter gets replaced by X if they are followed by F or s
//
//
//        System.out.println("helloe".matches("^hello")); //the whole string has to match regular expression for returning true
        System.out.println("helloWorld".replaceAll("[Hh]","H")); // every H and h gets replaced by H
        System.out.println("acvncakjhaerjyqrwqkrf".replaceAll("[^ej]","X")); //every letter except e and j gets replaces by X
        System.out.println( "ac965v4ncakj2h2a22erjy582q964rwqrf".replaceAll("[a-fA-F3-8]","X")); //letters from a to f,A to F, numbers from 3 to 8 gets replaced by X
        System.out.println( "ac965v4ncakj2h2a22erjy582q964rwqrf".replaceAll("(?i)[a-f3-8]","X")); //letters from a to f,A to F, numbers from 3 to 8 gets replaced by X this works for ascii
        System.out.println( "ac965v4ncakj2h2a22erjy582q964rwqrf".replaceAll("(?iu)[a-f3-8]","X")); //letters from a to f,A to F, numbers from 3 to 8 gets replaced by X this works for unicode
        System.out.println( "ac965v4ncakj2h2a22erjy582q964rwqrf".replaceAll("\\d","X")); //any digit gets replaced by X works same as specifying range [0-9]
        System.out.println( "ac965v4ncakj2h2a22erjy582q964rwqrf".replaceAll("\\D","X")); //any character other than digit gets replaced by X works same as specifying range [^0-9]
        //trim() removes whitespaces at beginning and the end of the string
        System.out.println("I am whitespace string \t i have a tab and \n new line".replaceAll("\\s",""));// removes all the whitespaces in a string
        System.out.println("I am whitespace string \t i have a tab and \n new line".replaceAll("\\S","X"));// works the opposite removes all other than whitespaces in a string
        System.out.println("I am whitespace string \t i have a tab and \n new line".replaceAll("\t",""));// removes all the tab character in a string
        System.out.println("I am whitespace string \t i have a tab and \n new line".replaceAll("\\w","X"));// upperCase LowerCase digits gets replaced by X
        System.out.println("I am whitespace string \t i have a tab and \n new line".replaceAll("\\W","X"));// opposite functionality anything but upperCase LowerCase digits gets replaced by X
        System.out.println("I am whitespace string \t i have a tab and \n new line".replaceAll("\\b","X"));//  each word in the string gets surrounded by X //useful when surrounding tags around text like HTML


        //Quantifiers
        System.out.println("adbbaaabjh23hb7bjkfjk841gdga".replaceAll("^adbba{3}]","YYY")); // equivalent to adbbaaa
        System.out.println("adbbaaabjh23hb7bjkfjk841gdga".replaceAll("^adbba+]","YYY")); // equivalent to adbbaa...a //i.e one or more number of a's
        System.out.println("adbbaaabjh23hb7bjkfjk841gdga".replaceAll("^adbba*]","YYY")); // equivalent to adbbaa...a //i.e at least 0 or more number of a's
        System.out.println("adbbaaabjh23hb7bjkfjk841gdga".replaceAll("^adbba{2,5}]","YYY")); // adbb followed by at least 2 and at most 5 number of a's gets replaced
        System.out.println("adbbaaajh23hb7bjkadbbaajfjk841gdga".replaceAll("b+a*j","Y")); //any number of b followed by zero or more a followed j gets replaced with Y
        StringBuilder string = new StringBuilder("<h1>This is main heading...</h1>");
        string.append("<h2> this is su heading </h2>");
        string.append("<p>This is paragraph which describe the details</p>");
        string.append("<h2>this is another heading,</h2>");
        string.append("<p>this is another paragraph</p>");
        String h2Pattern = "<h2>"; // this will return false because it matach the entire string to the given regular expression ..
        // will look for lowercase.. helpful to count the occurrences
        //String h2Pattern = ".*<h2>.*"; //anything before h2 tag and anything after the tag... handy for checking if a string contains the expression
        Pattern pattern = Pattern.compile(h2Pattern,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE); //the pattern will ignore case sensitivity
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.matches()); //we can only use the matcher once, matcher has internal state , it updates whenever we use it
        int count=0;

        //we have to reset the the matcher in order to execute while loop.. can be done by using reset method
        matcher.reset();
        //but this method is not specific enough.. to make it more specific use expression as "<h2>"
        while (matcher.find()){
            count +=1;
            //start and end method returns the indexes
            System.out.println("Occurrence " + count +": "+matcher.start()+ " to "+ matcher.end());
        }
        String h2GroupPattern = "(<h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher matcher1 = groupPattern.matcher(string);
        System.out.println(matcher1.matches());


//        String tvtest = "tstvtkt";
//        String tNotV = "t[^v]"; //occurrences of t that aint followed by V
//        Pattern tNotVpattern = Pattern.compile(tNotV);
//        Matcher tNotVmatcher = tNotVpattern.matcher(tvtest);
//        count=0;
//        while (tNotVmatcher.find()){
//            count++;
//            System.out.println("Occurence" +tNotVmatcher.start()+" to "+ matcher.end());
//        }
        String datee= "31-01-3000";
        String datePattern = "[0-2][0-9]|3[0-1]-(0[1-9]|1[0-2])-[0-9]{4}";
        Pattern DatePattern = Pattern.compile(datePattern);
        Matcher dateMatcher = DatePattern.matcher(datee);
        System.out.println("date pattern matches: "+dateMatcher.matches());

    }

}
