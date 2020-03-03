public class Palindrome {


    /**
     * store data(characters) into Deque
     */
    public Deque<Character> wordToDeque(String word) {

        Deque<Character> output = new ArrayDeque<>();       //initialize a Deque 不应该直接设为null， 应该用 "new"，由于是interface，所以两种都行
        for (int i = 0; i < word.length(); i++) {
            output.addLast(word.charAt(i));                  //string 的遍历 ：charAt
        }
        return output;           //这里的 output 是一个Deque（ ArrayDeque 或者 LinkedListDeque）
    }


    /**
     * check if the word is palindrome (solution 1 - make new word)
     */
    public boolean isPalindrome(String word) {
        Deque array = wordToDeque(word);                // create an array of "word"
        String reverse = "";

        for (int i = 0; i < word.length(); i++) {
            reverse += array.removeLast();
        }
        if (reverse.equals(word)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * check if the word is palindrome (solution 2 - recursion)
     */
    public boolean isPalindrome_solution2(Deque<Character> wordInDeque) {
        while (wordInDeque.size() > 1) {
            return wordInDeque.removeFirst() == wordInDeque.removeLast() && isPalindrome_solution2(wordInDeque);
        }
        return true;
    }

    public boolean isPalindrome_solution2(String word) {
        return isPalindrome_solution2(wordToDeque(word));
    }

    /**
     * check if the word is palindrome (with one diff + N diff)
     * 因为无论是OffByOne，还是OffByN，二者都有一个相同的interface：CharacterComparator
     */
    public boolean isPalindrome(Deque<Character> wordInDeque, CharacterComparator cc) {
        while (wordInDeque.size() > 1) {
            char a = wordInDeque.removeFirst();
            char b = wordInDeque.removeLast();
            //等号的意义在同时满足，当第一次满足 equalChars（a，b）时，为了获得return值，需要重复 call method。当出现一次不满足时
            //直接跳出return false结束，如果一致满足到跳出了while loop，直接return true》
            return cc.equalChars(a, b) && isPalindrome(wordInDeque, cc);
            //cc的值代表True or False, 别忘了boolean是一种数据类型
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }
}
