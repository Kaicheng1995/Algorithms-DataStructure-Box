import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();     //固定格式！！！

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();                       //java中的character居然可以相加......代表字符并列....
        }
        assertEquals("persiflage", actual);
    }


    // Test diff == 0
    @Test
    public void testisPalindrome() {

        assertTrue(palindrome.isPalindrome("AAAaAAA"));
        assertTrue(palindrome.isPalindrome("ABBA"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("ACDC"));
        assertFalse(palindrome.isPalindrome("yang"));

        assertTrue(palindrome.isPalindrome_solution2("madam"));
        assertTrue(palindrome.isPalindrome_solution2("racecar"));
        assertTrue(palindrome.isPalindrome_solution2("a"));
        assertTrue(palindrome.isPalindrome_solution2(""));
        assertFalse(palindrome.isPalindrome_solution2("dog"));
        assertFalse(palindrome.isPalindrome_solution2("cat"));

    }

    // Test diff == 1
    @Test
    public void testIsPalindromeCc_diff_1() {
        //initialize first, 方便调用公式
        OffByOne obo = new OffByOne();

        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("zyzy", obo));
        assertTrue(palindrome.isPalindrome("yyxz", obo));
        assertTrue(palindrome.isPalindrome("yyyxz", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));
        assertFalse(palindrome.isPalindrome("xyz", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));
        assertFalse(palindrome.isPalindrome("zxzx", obo));
    }

    // Test diff == N
    @Test
    public void testIsPalindromeCc_diff_N() {
        OffByN offBy5 = new OffByN(5);
        assertTrue(palindrome.isPalindrome("af", offBy5));
        assertTrue(palindrome.isPalindrome("fa", offBy5));
        assertFalse(palindrome.isPalindrome("ab", offBy5));
    }
}
