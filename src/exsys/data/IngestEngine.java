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


/**
 * IngestEngine class reads and parses the dataset into a friendlier
 * format as a POJO (see Tuple.java). It presents an iterator
 * approach to processing and hides the file operations and data
 * conversion.
 * 
 * @author Denis Coady
 * @version 0.0.1, Oct 2013
 */
public class IngestEngine implements AutoCloseable
{
    private BufferedReader reader_ = null;
    
    /**
	 * Constructor creates the needed file stream readers.
	 * 
	 * @param   dataset   the file containing the dataset
	 */
    public IngestEngine(File dataset) throws FileNotFoundException
    {
        reader_ = new BufferedReader(new FileReader(dataset.getAbsolutePath()));
    }
    
    /**
	 * Parses and returns the next entry in dataset as POJO using a
	 * generator pattern.
	 * 
	 * @return   the next parsed line as a Tuple object or
	 *           null if end of file
	 */
    public Tuple next() throws IOException
    {
        Tuple  tuple = null;
        String line  = null;

        if( (line = reader_.readLine()) != null)
        {
            tuple = parse_and_create(line);
        }
        
        return tuple;
    }
    
    /**
	 * Provides a forward control of the file stream for exception
	 * control.
	 */
    public void close() throws IOException
    {
        reader_.close();
    }
    
    /**
	 * Uses a Scanner to parse a line to a POJO iff all fields are
	 * sucessfully parsed.
	 * 
	 * @param    line   line in the dataset file
	 * 
	 * @return   the filled tuple or null on failure
	 */
    private Tuple parse_and_create(String line)
    {
        Tuple   tuple = null;
        Scanner scan  = new Scanner(line);

        try
        {
            tuple = new Tuple
                    (
                    	line,
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
            tuple = new Tuple(line);
        }
    
        scan.close();
        
        return tuple;
    }
}
