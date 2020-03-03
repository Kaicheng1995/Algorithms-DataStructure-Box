public class Horse {
    Horse same;
    String jimmy;
    public Horse(String lee) {
        jimmy = lee;
    }
    public Horse function(Horse horse) {
        if (this.same != null) {
            Horse x = horse;
            x.same = horse;
            x = horse.same;  //再出现同名的情况下，貌似是以非this为主，如需调用主语，则需要加上this
        }
        return this.same.same;
    }
    public static void main(String[] args) {
        Horse horse = new Horse("you've been");
        Horse cult = new Horse("horsed");
        cult.same = cult;
        cult = cult.function(horse);
        System.out.println(cult.jimmy);
        System.out.println(horse.jimmy);
    }
}
