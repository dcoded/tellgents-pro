package exsys;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import exsys.data.IngestEngine;
import exsys.data.Tuple;
import exsys.engine.LowQualityException;
import exsys.engine.QualityEvaluationEngine;
import exsys.engine.QualityReport;
import exsys.factor.FieldValidation;
import exsys.factor.RegressionAnalysis;



/**
 * 
 * @author Denis Coady
 * @version 0.0.1, Oct 2013 
 */
public class Main
{
	private static double MINIMUM_QUALITY_THRESHOLD = 0.5;
	
	private static File dataset = new File("dataset/elnino");
	
	private static QualityEvaluationEngine quality = new QualityEvaluationEngine
			(
				new FieldValidation(),
				new RegressionAnalysis()
			);
	
	
	private static int processed_entries = 0;
	
	private static List<QualityReport> lowq_reports = new LinkedList<QualityReport>();

    public static void main(String[] args)
    {
    	
    	// read each line of dataset
    	try(IngestEngine reader = new IngestEngine(dataset))
        {  
            Tuple entry = null;      
            
            // If not eof, then read next line as a filled Tuple object
            while ((entry = reader.next ()) != null)
            try
            {     
                quality.ingest(entry, MINIMUM_QUALITY_THRESHOLD);
                processed_entries++;
            }
            catch (LowQualityException e)
            {
                lowq_reports.add(e.report());
            }
            
            // output stats
            print_final_evaluation();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }
    
    private static void print_final_evaluation()
    {
    	System.out.println("File    : " + dataset.getAbsolutePath());
    	System.out.println("Entries : " + processed_entries);
    	System.out.println("Low Q   : " + lowq_reports.size());
    	
    	System.out.println("Rejected Entries:");
    	for(QualityReport r : lowq_reports)
    	{
    		System.out.println(r.tuple());
    	}
    }
}
