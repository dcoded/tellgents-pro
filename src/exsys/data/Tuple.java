package exsys.data;




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


/* Object to hold fields for entry */

public class Tuple
{
    private boolean error_ = true;
    
    public int buoy;
    public int day;
    
    /* The latitude and longitude in the data showed that the bouys
     * moved around to different locations. The latitude values stayed
     * within a degree from the approximate location. Yet the longitude
     * values were sometimes as far as five degrees off of the
     * approximate location. 
     */
    public double latitude;
    public double longitude;
    

    /* Looking at the wind data, both the zonal and meridional winds
     * fluctuated between -10 m/s and 10 m/s. The plot of the two wind
     * variables showed no linear relationship. Also, the plots of each
     * wind variable against the other three meteorolgical data showed no
     * linear relationships. 
     */
    public double zon_winds; // west  < 0 < east
    public double mer_winds; // south < 0 < north
    
    
    /* The relative humidity values in the tropical Pacific were typically
     * between 70% and 90%.
     */
    public double humidity;
    
    
    /* Both the air temperature and the sea surface temperature fluctuated
     * between 20 and 30 degrees Celcius. The plot of the two temperatures
     * variables shows a positive linear relationship existing. The two
     * temperatures when each plotted against time also have similar plot
     * designs. Plots of the other meteorological variables against the
     * temperature variables showed no linear relationship. 
     */
    public double air_temp;
    public double sea_temp;
    
    public Tuple(int buoy, int day, double latitude, double longitude,
                 double zon_winds, double mer_winds, double humidity,
                 double air_temp, double sea_temp)
    {
        this.buoy      = buoy;
        this.day       = day;
        this.latitude  = latitude;
        this.longitude = longitude;
        this.zon_winds = zon_winds;
        this.mer_winds = mer_winds;
        this.humidity  = humidity;
        this.air_temp  = air_temp;
        this.sea_temp  = sea_temp;
        
        this.error_ = false;
    }
    
    public Tuple()
    {
        error_ = true;
    }
    
    public boolean valid()
    {
        return !error_;
    }
    
}
