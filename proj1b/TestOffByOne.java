import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

   @Test
    // Your tests go here.
    public void testOffByOne(){
        OffByOne obo = new OffByOne();
        obo.equalChars('a', 'b');
        obo.equalChars('r', 'q');
    }

    @Test
    public void testOffByN() {
        OffByN offBy5 = new OffByN(5);
        offBy5.equalChars('a', 'f');  // true
        offBy5.equalChars('f', 'a');  // true
        offBy5.equalChars('f', 'h');
    }
}
