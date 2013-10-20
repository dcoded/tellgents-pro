package exsys.engine;



/**
 * LowQualityException is generated when a tuple quality score falls
 * below the minimum threshold.
 * 
 * @author Denis Coady
 * @version 0.0.1, Oct 2013
 */
public class LowQualityException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6225881678226242507L;
	private QualityReport report_;
    
    public LowQualityException(QualityReport report) {
        report_ = report;
    }

    public QualityReport report()
    {
        return report_;
    }
}
