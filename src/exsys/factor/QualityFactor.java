package exsys.factor;
import exsys.data.Tuple;


public interface QualityFactor
{
    /* Should provide a value between 0 and 1 */
    float run(Tuple entry);
}
