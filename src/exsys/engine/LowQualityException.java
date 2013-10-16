package exsys.engine;




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
