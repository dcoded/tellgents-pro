package exsys.engine;

import java.util.HashMap;
import java.util.Map;

import exsys.data.Tuple;
import exsys.factor.QualityFactor;

/**
 * QualityEvaluationEngine creates an analysis chain and aggregates
 * quality results in a QualityReport (see QualityReport.java) 
 * 
 * @author Denis Coady
 * @version 0.0.1, Oct 2013
 */
public class QualityEvaluationEngine
{  
    private QualityFactor[] components_;
    
    Map<Class<? extends QualityFactor>, Float> factors_;
    
    /**
	 * Constructor builds the analysis chain given user passed
	 * QualityFactor (see QualityFactor.java) tests.
	 * 
	 * @param   components   The tests to be used in determining
	 *                       quality metrics.
	 */
    public QualityEvaluationEngine(QualityFactor...components)
    {
        components_ = components;
        factors_    = new HashMap<Class<? extends QualityFactor> , Float>();
    }

    /**
	 * Parses and returns the next entry in dataset as POJO using a
	 * generator pattern.
	 * 
	 * @param   entry         is a Tuple (see Tuple.java) object
	 * 
	 * @param   min_quality   is the threshold of acceptable quality
	 *                        which triggers an exception below this
	 */
    public void ingest(Tuple entry, double min_quality)
    throws LowQualityException
    { 
    	QualityReport report = null;
    	
    	// run tuple through each test
        for(QualityFactor factor : components_)
        {
            factors_.put(factor.getClass(), factor.run(entry));
        }
        
        report = new QualityReport(entry, factors_);
        
        if(report.quality() < 0.5)
        {
            throw new LowQualityException(new QualityReport(entry, factors_));
        }
    }
}
