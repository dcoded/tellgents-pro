import java.io.File;
import java.io.IOException;


public class Main
{
    public static void main(String[] args)
    {
        File dataset = new File("C:\\Users\\denis_000\\workspace\\IntSysProj\\dataset\\elnino");
        
        try(IngestEngine reader = new IngestEngine(dataset))
        {
            System.out.println("loading analysis engine");
            QualityEvaluationEngine quality = new QualityEvaluationEngine(
                                                  new FieldValidation(),
                                                  new FieldCorrelationCheck()
                                              );
             
             
            System.out.println("running analysis");
            
            Tuple entry = null;
            int good = 0;
            int bad  = 0;
            int n    = 0;
            
            while ((entry = reader.next ()) != null)
            try
            {
                n++;
                System.out.println("\t[" + n + "] " + entry.toString());
                quality.ingest(entry);
                good++;
            }
            catch (LowQualityException e)
            {
                QualityReport report = e.report();
                bad++;
                
                report.print();
            }
            
            System.out.println( n        + " entries processed: " +
                                good     + " valid, " +
                                bad      + " invalid");
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }
}
