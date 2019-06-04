package algorithms.search;
import java.util.Comparator;
/**
 * A class that implements a comparator for states
 *  @author aviel avitan,ron shakutai
 */
public class AStateComprator implements Comparator<AState>{
    @Override
    public int compare(AState aState, AState t1) {
        if(aState.getCost()<t1.getCost())
            return 1;
        else if(aState.getCost()>t1.getCost())
            return -1;
        return 0;
    }
}
