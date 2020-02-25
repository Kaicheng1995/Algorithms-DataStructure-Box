public class Pokemon {
    public String name;
    public int level;

    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static void main(String[] args) {
        Pokemon p = new Pokemon("Pikachu", 17);
        int level = 100;
        change(p, level);
        System.out.println("Name: " + p.name + ", Level: " + p.level);
    }

    public static void change(Pokemon poke, int level) {     //p带入change后不要等同，只是新建一个poke指针，这里要特别注意
        poke.level = level;
        level = 50;
        poke = new Pokemon("Gengar", 1);
    }
}
