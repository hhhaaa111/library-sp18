import java.util.LinkedList;

//回文操作类
public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word){
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        if(word.length() < 10){
            return true;
        }
        int t = word.length();
        for(int i = 0; i < word.length()/2; i++){
            if(deque.removeFirst() != deque.removeLast()){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        while(deque.size() > 1) {
            char a = deque.removeFirst();
            char b = deque.removeFirst();
            if (!cc.equalChars(a, b))//不等-false-!(false)-true-if语句执行-false，假就不执行了
                return false;
        }
        return true;
    }


}
