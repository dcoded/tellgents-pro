import java.util.HashMap;
import java.util.Map;


public class QualityEvaluationEngine
{    
    private QualityFactor[] components_;
    Map<Class<? extends QualityFactor>, Float> factors_;
    
    public QualityEvaluationEngine(QualityFactor...components)
    {
        components_ = components;
        for(QualityFactor com : components)
        {
            System.out.println("\tcomponent '" + com + "' loaded");
        }
        
        factors_ = new HashMap<Class<? extends QualityFactor> , Float>();
    }

    public void ingest(Tuple entry) throws LowQualityException
    {    
        for(QualityFactor factor : components_)
        {
            factors_.put(factor.getClass(), factor.run(entry));
        }
        
        QualityReport report = new QualityReport(entry, factors_);
        if(report.quality() < 0.5)
        {
            throw new LowQualityException(new QualityReport(entry, factors_));
        }
    }
}
