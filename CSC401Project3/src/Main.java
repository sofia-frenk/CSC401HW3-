import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        String s1 = "CCGCTC";
        String s2 = "ACGGAT";
        String LCS = LongestCommonSubsequence(s1, s2, s1.length(), s2.length());
        System.out.println("Longest common subsequence: " + LCS);
    }

    public static String LongestCommonSubsequence(String s1, String s2, int i, int j)
    {
        /*1. compare the last letters of the strings
         *2. if the last two letters of the strings are the same
        *       add that letter to the LCS and get rid of both letters
        * 3. else
        *       take max of the recursion
        *       take the last letters of one of the strings and check again
        * */

        /*String[][] memo = /*new String[i-1][j-1];
        String[][] memo = FillMemo(i, j);*/
        String[][] memo = new String[i+1][j+1];
        memo = FillMemo(memo, i+1, j+1);

        char[] s1asCharArr = s1.toCharArray();
        char[] s2asCharArr = s2.toCharArray();

        ArrayList<Character> s1asCharList = convertStringToCharList(s1);
        ArrayList<Character> s2asCharList = convertStringToCharList(s2);

        String LCS = LongestCommonSubsequenceHelper(s1asCharList, s2asCharList,i-1, j-1, memo);
        return LCS;
    }

    public static String LongestCommonSubsequenceHelper(ArrayList<Character> charList1, ArrayList<Character> charList2, int i, int j, String[][] memo)
    {
        /*1. compare the last letters of the strings
         *2. if the last two letters of the strings are the same
         *       add that letter to the LCS and get rid of both letters
         * 3. else
         *       take max of the recursion
         *       take the last letters of one of the strings and check again
         * */

        String LCS = "";

        if (i == 0 || j == 0)
            return LCS;

        if (!memo[i][j].equals(""))
            return memo[i][j];

        //if the last two letters are the same, decrease both indexes and add to LCS:
        if (charList1.get(i) == charList2.get(j))
        {
            //charList1.remove(i);
            //charList2.remove(j);
            LCS = LongestCommonSubsequenceHelper(charList1, charList2, i - 1, j - 1, memo) + charList1.get(i);
        }
        //if last two letter are not the same, take max of reducing each index
        else
        {
            //charList1.remove(i);
            String LCSi = LongestCommonSubsequenceHelper(charList1, charList2, i-1, j, memo);

            //charList2.remove(i);
            String LCSj = LongestCommonSubsequenceHelper(charList1, charList2, i, j-1, memo);

            if (LCSi.length() >= LCSj.length())
                LCS = LCSi;
            else
                LCS = LCSj;
        }
        memo[i][j] = LCS;

        return memo[i][j];
    }

    public static ArrayList<Character> convertStringToCharList(String str)
    {

        // Create an empty List of character
        List<Character> chars = new ArrayList<>();

        // For each character in the String
        // add it to the List
        for (char ch : str.toCharArray()) {

            chars.add(ch);
        }

        // return the List
        return (ArrayList<Character>) chars;
    }

    public static String[][] FillMemo(String[][] memo, int i, int j)
    {
        for (int indexI = 0; indexI<i; indexI++) {
            for (int indexJ = 0; indexJ<j; indexJ++) {
                memo[indexI][indexJ] = "";
            }
        }
        return memo;

        /*
        int m = X.length();
        int n = Y.length();
        int[][] dp = new int[m + 1][n + 1];

        * for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                dp[i][j] = -1;
            }
        }*/
    }
}
