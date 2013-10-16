package exsys.data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;


/* Responsible to read data from file
 * 
 * Spec 2.1 - Data Source
 * 
 * We are going to attempt to use a dataset which is used to predict
 * a naturally occuring periodic weather event known as El Nino. We
 * will attempt to identify likely bad quality entries (that we might
 * have to create ourselves) using our expert system project.
 */


/* FIELDS
 * 
 * 0: buoy
 * 1: day
 * 2: latitude
 * 3: longitude
 * 4: zon.winds
 * 5: mer.winds
 * 6: humidity
 * 7: air temp.
 * 8: s.s.temp.
 */
public class IngestEngine implements AutoCloseable
{
    private String line_ = null;
    
    private BufferedReader reader_ = null;
    
    public IngestEngine(File dataset) throws FileNotFoundException
    {
        reader_ = new BufferedReader(new FileReader(dataset.getAbsolutePath()));
    }
    
    public Tuple next() throws IOException
    {
        Tuple tuple = null;

        if( (line_ = reader_.readLine()) != null)
        {
            tuple = parse_and_create();
        }
        
        return tuple;
    }
    
    public void close() throws IOException
    {
        reader_.close();
    }
    
    private Tuple parse_and_create()
    {
        Tuple   tuple = null;
        Scanner scan  = new Scanner(line_);

        try
        {
            tuple = new Tuple
                    (
                    	line_,
                        scan.nextInt(), // buoy
                        scan.nextInt(), // day
                        scan.nextDouble(), // latitude
                        scan.nextDouble(), // longitude
                        scan.nextDouble(), // zon_winds
                        scan.nextDouble(), // mer_winds
                        scan.nextDouble(), // humidity
                        scan.nextDouble(), // air_temp
                        scan.nextDouble() // sea temp
                    );
        }
        catch(NoSuchElementException e)
        {
            tuple = new Tuple(line_);
        }
    
        scan.close();
        
        return tuple;
    }
}
