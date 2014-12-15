package leetcode;

/*
Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String common = strs[0];
        for (int i = 1; i < strs.length; i++) {
            common = findCommonPrefix(common, strs[i]);
        }
        return common;
    }

    private String findCommonPrefix(String common, String str) {
        if (common.length() == 0 || str.length() == 0) return "";
        int i = 0;
        while (i < common.length() && i < str.length() && common.charAt(i) == str.charAt(i)) {
            i++;
        }
        if (i == 0) return "";
        return common.substring(0, i);
    }
}
