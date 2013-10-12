import java.util.Map;
import java.util.Map.Entry;


public class QualityReport
{
    private Tuple tuple_;
    
    private float quality_;
    
    private Map<Class<? extends QualityFactor> , Float> factors_;
    
    
    public QualityReport(Tuple tuple,  Map<Class<? extends QualityFactor>, Float> factors)
    {
        tuple_   = tuple;
        factors_ = factors;
    }
    
    public float quality()
    {
        float quality = 1.0f;
        
        for(Entry<Class<? extends QualityFactor>, Float> factor : factors_.entrySet())
        {
            quality *= factor.getValue();
        }
        
        return quality;
    }
    
    public Tuple tuple()
    {
        return tuple_;
    }
    
    public Map<Class<? extends QualityFactor>, Float> factors()
    {
        return factors_;
    }
    
    public void print()
    {
        for(Entry<Class<? extends QualityFactor>, Float> f : factors_.entrySet())
        {
            System.out.println(f.getKey().getName() + ": " + f.getValue());
        }
    }
}
