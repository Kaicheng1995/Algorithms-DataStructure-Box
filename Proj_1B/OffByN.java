public class OffByN implements CharacterComparator{

    public int N;

    public OffByN (int n) {
        N = n;
    }

    @Override
    public boolean equalChars(char a, char b){
        return a - b == N || b - a == N;
    }
}
