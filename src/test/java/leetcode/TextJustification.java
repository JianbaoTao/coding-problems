package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int L) {
        List<String> lines = new ArrayList<String>();
        if (L <= 1 ) {
            for (String word : words) {
                lines.add(word);
            }
            return lines;
        }

        int inext = 0;
        while (inext < words.length) {
            inext = findNextStartIndex(words, inext, L, lines);
        }
        return lines;
    }

    private int findNextStartIndex(String[] words, int start, int L, List<String> lines) {
        // find end index for a line
        int i = start;
        List<String> lineWords = new ArrayList<String>();
        int len = 0;
        while (len < L && i < words.length) {
            String word = words[i];
            if (len + word.length() <= L) {
                lineWords.add(word);
                len += 1 + word.length();
                i++;
            } else {
                break;
            }
        }
        lines.add(formatLine(lineWords, L, i == words.length || lineWords.size() == 1));
        return i;
    }

    private String formatLine(List<String> lineWords, int L, boolean isLastLine) {
        if (isLastLine) {
            return join(lineWords, " ", L);
        } else {
            return evenJoin(lineWords, L, " ");
        }
    }

    private String evenJoin(List<String> lineWords, int L, String s) {
        int slots = lineWords.size() - 1;
        int totalSpaces = totalSpaces(lineWords, L);
        int normal = totalSpaces / slots;
        int extra = totalSpaces - normal * slots; // 0 to extra - 1 slots have normal + 1 spaces
        StringBuilder builder = new StringBuilder(lineWords.get(0));
        for (int i = 1; i < lineWords.size(); i++) {
            int spaces = (i - 1 >= extra) ? normal : normal + 1; // number of spaces
            String slot = spaceSlot(spaces);
            builder.append(slot).append(lineWords.get(i));
        }
        return builder.toString();
    }

    private String spaceSlot(int spaces) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < spaces; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private int totalSpaces(List<String> lineWords, int L) {
        for (String s : lineWords) {
            L -= s.length();
        }
        return L;
    }

    private String join(List<String> lineWords, String s, int L) {
        StringBuilder builder = new StringBuilder(lineWords.get(0));
        for (int i = 1; i < lineWords.size(); i++) {
            builder.append(s).append(lineWords.get(i));
        }
        while( builder.length() < L ) {
            builder.append(s);
        }
        return builder.toString();
    }

    @Test
    public void test() {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> lines = fullJustify(words, 16);
        for (String line : lines) {
            System.out.println(line);
        }

    }

    //["a","b","c","d","e"], 3

    @Test
    public void test2() {
        String[] words = {"a","b","c","d","e"};
        List<String> lines = fullJustify(words, 3);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    // "Listen","to","many,","speak","to","a","few."
    @Test
    public void test3() {
        String[] words = {"Listen","to","many,","speak","to","a","few."};
        List<String> lines = fullJustify(words, 6);
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
