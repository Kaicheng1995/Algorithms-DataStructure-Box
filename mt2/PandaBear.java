import java.util.Arrays;

public class PandaBear extends Bear implements Comparable<PandaBear> {
	@Override
    public int compareTo(PandaBear that) {
        if (this.weight != that.weight) {
            return this.weight - that.weight;
        } else {
            int thisFood = 0, thatFood = 0;
            for (int i = 0; i < this.foodSupply.length; i++) {
                thisFood += this.foodSupply[i];
            }
            for (int i = 0; i < that.foodSupply.length; i++) {
                thatFood += that.foodSupply[i];
            }
            return thisFood - thatFood;
        }
    }

    public PandaBear(int[] supply, int w) {
        super(supply, w);
    }
}