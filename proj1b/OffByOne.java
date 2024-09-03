public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int a = y-x;
        if(a*a == 1)
            return true;
        return false;
    }
}
