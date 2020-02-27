public class Cat{
    public String name;
    public static String noise;          //出现了static variable时，仅存在一个恒定的值。所以无论是以任何形式的改变，其值都将发生改变，并保存下来

    public Cat(String name, String noise){
        this.name = name;
        this.noise = noise;
    }

    public void play(){
        System.out.println(noise + " I'm " + name + " the cat!");
    }

    public static void anger(){
        noise = noise.toUpperCase();
    }

    public static void calm(){
        noise = noise.toLowerCase();
    }

    public static void main(String[] agrs){
        Cat a = new Cat("Cream", "Meow!");
        Cat b = new Cat("Tubbs", "Nyan!");
        a.play();
        b.play();
        Cat.anger();
        a.calm();
        a.play();
        b.play();
    }
}
