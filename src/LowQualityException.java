
public class LowQualityException extends Exception
{
	private QualityReport report_;
	
	public LowQualityException(QualityReport report) {
		report_ = report;
	}

	public QualityReport report()
	{
		return report_;
	}
}
