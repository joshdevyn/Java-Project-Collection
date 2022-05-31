import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class CycleLabs {
    private List<Example> examples = new LinkedList<>() {{
        add(new Example(
                Arrays.asList("The","quick","brown","fox","jumps","over","the","lazy","dog"),
                10
        ));
        add(new Example(
                Arrays.asList("Lorem","ipsum","dolor","sit","amet"),
                15
        ));
        add(new Example(
                Arrays.asList("Cycle","Labs","is","the","greatest","company","on","Earth","and","also","objectively","better","than","all","companies","on","Mars"),
                20
        ));
    }};

    public static void main(String[] args) {
        CycleLabs interviewContext = new CycleLabs();

        for (Example example : interviewContext.examples) {
            interviewContext.printWords(example.getWords(), example.getMaxLength());
        }
    }

    private void printWords(List<String> words, int maxLength) {
        CycleLabs interviewContext = new CycleLabs();

        System.out.println(String.format("Example %d:", ++exampleNumber));
        System.out.println("----------");

        /////////////////// START CANDIDATE CODE
        /**
         * Prints each word from the words List on a separate line.
         */
        System.out.println("1. Take the array of words and print them to the console, each on a separate line");

        // Prints each word in the word list on separate lines (one word per line)
        for (String word : words) {
            System.out.println(word);
        }

        /**
         * Prints concatenations from the words List if the concatentations are less than the maxlength and not the final line.
         * Needs refactoring. This is just a simple, easy-to-read, brute force method.
         *
         * wordBuffer: This holds the string being buffered into the foreach loop.
         * wordBufferSize: This holds the length of the word buffer being currently iterated over in the foreach loop.
         * wordsBuffer: This holds the rest of the concatenated string after the wordBuffer has been removed.
         * wordsBufferSize: This holds length of the entire string and is used to check if we're on the last line whose length is less than the max length
         */
        System.out.println("2. Take the array of words and combine them into lines that do not exceed maxLength. Print each line");

        // Variables as defined in comment block above
        String wordBuffer = "";
        int wordBufferSize = 0;
        String wordsBuffer = String.join(" ",words);
        int wordsBufferSize = wordsBuffer.length();

        // Loops over each word in the words list
        for (String word : words) {
            // 1 at the end accounts for trailing spaces sinces wordBuffer isn't trimmed until it's printed to the console
            wordBufferSize = wordBuffer.length() + word.length() + 1;

            // Checks to see if the word buffer size is less than the maxLength and the words (plural) buffer size is greater than the maxLength. Ensures that we're not on the final line less than maxLength
            if (wordBufferSize < maxLength && wordsBufferSize > maxLength) {
                wordBuffer += word + " ";
            }
            else {
                if (wordBufferSize > maxLength) {
                    System.out.println(wordBuffer.trim());
                    wordBuffer = word + " ";
                }
                else if (wordBufferSize <= maxLength) {
                    wordBuffer += word;
                    System.out.println(wordBuffer.trim());
                    wordBuffer = "";
                }
                else {
                    System.out.println(wordsBuffer);
                    wordBuffer = word + " ";
                }
            }
            wordBufferSize = 0;
            wordsBuffer = wordsBuffer.replaceFirst(word,"").trim();
            wordsBufferSize = wordsBuffer.length();
        }

        /**
         * Prints concatenation from the word list after justifying them. Needs to be turned into a factory/refactored.
         *
         * spaceBuffer: Holds the spaces needed to join for justifying text.
         * wordBuffer: Holds the current single word being iterated over.
         * wordBufferSize: Holds the length of the word iterated.
         * wordsBuffer: Buffers the entire sentence. Used as a quick fix for displaying last sentence at less than maxLength.
         * wordsBufferSize: Size the words being buffered.
         * justifiedWords: Holds the words that need to be justified using the spaceBuffer at run-time.
         * spaceCount: Counts the number of spaces. Was originally going to use an array stream so it's a long but can just be an int.
         * wordsBuffered: Array List of words being buffered that need to be justified, used to easily justify more than one word if needed.
         */
        System.out.println("3. Take the array of words and combine them into lines that do not exceed maxLength, " +
                "and are also justified left to right, such that each line fills the entire space available");

        // Variables as defined in comment block above
        String spaceBuffer = "";
        wordBuffer = "";
        wordBufferSize = 0;
        wordsBuffer = String.join(" ",words);
        wordsBufferSize = wordsBuffer.length();
        String justifiedWords = "";
        long spaceCount = 0;
        List<String> wordsBuffered = new ArrayList<>();

        for (String word : words) {
            // The added one is to account for a blank space at the end in case the buffer matches maxLength while having more than 1 word
            wordBufferSize = String.join(" ",wordsBuffered).length() + word.length() + 1;
            if (wordBufferSize < maxLength && wordsBufferSize > maxLength) {
                wordBuffer += word + " ";
                wordsBuffered.add(word);
            }
            else {
                if (wordsBuffer.length() <= maxLength) {

                    // Gets the words that need to be justified and counts the number of spaces
                    justifiedWords = wordBuffer.trim();
                    spaceCount = justifiedWords.length();

                    // Space buffer for second to last line to be printed
                    if (spaceCount != 0) {
                        int i = 1;
                        String space = " ";
                        while (justifiedWords.length() < (maxLength)) {
                            for (int j = 0; j < justifiedWords.length(); j++) {
                                if (justifiedWords.substring(j, Math.min(justifiedWords.length(), j + i)).equals(space)) {
                                    justifiedWords = justifiedWords.substring(0, j + 1) + "\s" + justifiedWords.substring(j+1, justifiedWords.length());
                                    j++;
                                }
                                if (justifiedWords.length() == maxLength) {
                                    break;
                                }
                            }
                            space += "\s";
                            i++;
                        }
                    }

                    // Joins the space buffer with the second to last line on each word in the words buffer list
                    System.out.println(justifiedWords);

                    // Reverts the words to be justified to be the current truncated words buffer string (not array)
                    justifiedWords = wordsBuffer.trim();
                    spaceCount = justifiedWords.length();

                    spaceBuffer = "";

                    // Splits the words buffer into an array so it can be joined using the space buffer. Accounts for >1 word in buffer.
                    String[] finalBuffer = wordsBuffer.split(" ");

                    // Counts spaces needed to justify the last line
                    if (spaceCount != 0 && finalBuffer.length > 1) {
                        int i = 1;
                        String space = " ";
                        while (justifiedWords.length() < (maxLength)) {
                            for (int j = 0; j < justifiedWords.length(); j++) {
                                if (justifiedWords.substring(j, Math.min(justifiedWords.length(), j + i)).equals(space)) {
                                    justifiedWords = justifiedWords.substring(0, j + 1) + "\s" + justifiedWords.substring(j+1, justifiedWords.length());
                                    j++;
                                }
                                if (justifiedWords.length() == maxLength) {
                                    break;
                                }
                            }
                            space += "\s";
                            i++;
                        }
                    }

                    // Prints the space buffered string
                    System.out.println(justifiedWords);

                    // Breaks loop on final word so it isn't printed twice
                    break;
                }
                else if (wordBufferSize > maxLength) {
                    // Gets the words that need to be justified and counts the number of spaces
                    justifiedWords = wordBuffer.trim();
                    spaceCount = justifiedWords.length();

                    // Counts the number of spaces needed to justify the text
                    if (spaceCount != 0 && wordsBuffered.size() > 1) {
                        int i = 1;
                        String space = " ";
                        while (justifiedWords.length() < (maxLength)) {
                            for (int j = 0; j < justifiedWords.length(); j++) {
                                if (justifiedWords.substring(j, Math.min(justifiedWords.length(), Math.min(justifiedWords.length(), j + i))).equals(space)) {
                                    justifiedWords = justifiedWords.substring(0, j + 1) + "\s" + justifiedWords.substring(j+1, justifiedWords.length());
                                    j++;
                                }
                                if (justifiedWords.length() == maxLength) {
                                    break;
                                }
                            }
                            space += "\s";
                            i++;
                        }
                    }

                    // Prints array joined by spaceBuffer
                    System.out.println(justifiedWords);

                    // Reverts wordBuffer and spaceBuffer back to original values and adds the current word to the wordBuffer
                    wordBuffer = word + " ";
                    spaceBuffer = "";

                    // Reverts wordsBuffered array and fills with current word
                    wordsBuffered.clear();
                    wordsBuffered.add(word);
                }
                else if (wordBufferSize <= maxLength) {

                    // Gets the words that need to be justified and counts the number of spaces
                    justifiedWords = wordBuffer.trim();
                    spaceCount = justifiedWords.length();

                    // Counts the number of spaces needed to justify the text
                    if (spaceCount != 0 && wordsBuffered.size() > 1) {
                        int i = 1;
                        String space = " ";
                        while (justifiedWords.length() < (maxLength)) {
                            for (int j = 0; j < justifiedWords.length(); j++) {
                                if (justifiedWords.substring(j, Math.min(justifiedWords.length(), j + i)).equals(space)) {
                                    justifiedWords = justifiedWords.substring(0, j + 1) + "\s" + justifiedWords.substring(j+1, justifiedWords.length());
                                    j++;
                                }
                                if (justifiedWords.length() == maxLength) {
                                    break;
                                }
                            }
                            space += "\s";
                            i++;
                        }
                    }

                    // Prints the justified string
                    System.out.println(String.join(spaceBuffer,wordsBuffered));

                    // Reverts buffers
                    wordBuffer = word + " ";
                    spaceBuffer = "";
                    wordsBuffered.clear();
                    wordsBuffered.add(word);
                }
            }
            // Resets the word buffer size and removes the buffered word from the words (plural) Buffer. Quick fix for displaying last line in string object, needs refactoring.
            wordBufferSize = 0;
            wordsBuffer = wordsBuffer.replaceFirst(word,"").trim();
            wordsBufferSize = wordsBuffer.length();
        }
        /////////////////// END CANDIDATE CODE
    }
    /**
     *#######################################################################
     * EXPECTED OUTPUT:
     *#######################################################################
     * Example 1:
     * ----------
     * The quick
     * brown fox
     * jumps over
     * the lazy
     * dog
     *
     * Example 2:
     * ----------
     * Lorem ipsum
     * dolor sit amet
     *
     * Example 3:
     * ----------
     * Cycle Labs
     * is the
     * greatest
     * company on
     * Earth and
     * also
     * objectively
     * better than
     * all
     * companies
     * on Mars
     */

    private int exampleNumber = 0;
    private static class Example {
        private List<String> words;
        private int maxLength;

        public Example(List<String> words, int maxLength) {
            this.words = words;
            this.maxLength = maxLength;
        }

        public List<String> getWords() { return words; }
        public int getMaxLength() { return maxLength; }
    }
}