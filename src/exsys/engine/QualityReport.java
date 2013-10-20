package exsys.engine;
import java.util.Map;
import java.util.Map.Entry;

import exsys.data.Tuple;
import exsys.factor.QualityFactor;

/**
 * QualityReport holds results for analysis chain tests as well as
 * the generated reasons given by tests for outcome score.
 * 
 * @author Denis Coady
 * @version 0.0.1, Oct 2013
 */
public class QualityReport
{
    private Tuple tuple_;
    
    private Map<Class<? extends QualityFactor> , Float> factors_;
    
    
    /**
	 * Constructor is given the Tuple (see Tuple.java) object
	 * analyzed, the tests performed, and the resulting score.
	 * 
	 * @param   tuple         is the Tuple analyzed
	 * 
	 * @param   factors       holds the tests and individual scores
	 */
    public QualityReport(Tuple tuple,  Map<Class<? extends QualityFactor>, Float> factors)
    {
        tuple_   = tuple;
        factors_ = factors;
    }
    
    /**
	 * Calculates and returns the aggregate score from individual tests
	 * 
	 * @return   the aggregate quality score
	 */
    public float quality()
    {
        float quality = 1.0f;
        
        for(Entry<Class<? extends QualityFactor>, Float> factor : factors_.entrySet())
        {
            quality *= factor.getValue();
        }
        
        return quality;
    }
    
    /**
	 * Returns the tuple object
	 * 
	 * @return   the tuple object
	 */
    public Tuple tuple()
    {
        return tuple_;
    }
    
    /**
	 * Returns the tests and their scores of the tuple
	 * 
	 * @return   a map of <test,score> for the tuple
	 */
    public Map<Class<? extends QualityFactor>, Float> factors()
    {
        return factors_;
    }
    
    /**
	 * Prints component tests name and component scores
	 */
    public void print()
    {
        for(Entry<Class<? extends QualityFactor>, Float> f : factors_.entrySet())
        {
            System.out.println(f.getKey().getName() + ": " + f.getValue());
        }
    }
}
