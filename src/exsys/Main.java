package exsys;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import exsys.factor.HumidityCheck;
import exsys.factor.LatitudeLongitudeCheck;
import exsys.factor.RegressionAnalysis;
import exsys.factor.TemperatureCheck;
import exsys.factor.WindVelocityCheck;



/**
 * 
 * @author Denis Coady
 * @version 0.0.1, Oct 2013 
 */
public class Main
{	
	private static QualityEvaluationEngine quality = new QualityEvaluationEngine
			(
				new FieldValidation(),
				new LatitudeLongitudeCheck(),
				new TemperatureCheck(),
				new HumidityCheck(),
				new WindVelocityCheck(),
				new RegressionAnalysis()
			);
	
	
	private static int processed_entries = 0;
	
	private static List<QualityReport> lowq_reports = new LinkedList<QualityReport>();

	private static File dataset = null;
	
	
    public static void main(String[] args)
    {
    	if(args.length < 1)
    	{
    		System.out.println("Usage: java IntSysProj <dataset>");
    		return;
    	}
    	
    	dataset = new File(args[0]);
    	if(dataset.exists() == false)
    	{
    		System.out.println("Dataset file not found at: " + dataset.getAbsolutePath());
    		return;
    	}
    	
    	PrintWriter good_quality = null;
    	PrintWriter bad_quality = null;
    	PrintWriter quality_log = null;
        
    	// read each line of dataset
    	try(IngestEngine reader = new IngestEngine(dataset))
        {  
            Tuple entry = null;   
            good_quality = new PrintWriter(dataset.getAbsoluteFile() + ".hq.txt", "UTF-8");
            bad_quality  = new PrintWriter(dataset.getAbsoluteFile() + ".lq.txt", "UTF-8");
            quality_log  = new PrintWriter(dataset.getAbsoluteFile() + ".log.txt", "UTF-8");
                   
            // If not eof, then read next line as a filled Tuple object
            while ((entry = reader.next ()) != null)
            {
            	QualityReport report = quality.ingest(entry);
            	
            	quality_log.println("[" + report.quality() + "] " + entry);
            	
            	if(report.quality() >= 0.65)
            	{
                    good_quality.println(entry);
            	}
            	else
            	{
            		 lowq_reports.add(report);
                     bad_quality.println(entry);
            	}
            	processed_entries++;
            }
            
            // output stats
            print_final_evaluation();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    	finally
    	{
    		good_quality.close();
    		bad_quality.close();
    		quality_log.close();
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
