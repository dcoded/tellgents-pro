/* 2.2.3 Regression Analysis and Field Correlation Checks
 * 
 * The fields of the dataset may be calculated for Pearson product-moment
 * correlation coefficients. These coefficients will be used to check
 * incoming tuples independent of the data set. If the field correlation
 * coefficients do not agree with that of the data set then it shows
 * erroneous behavior in measurement. Correlations may not exist or be
 * strong enough to determine correctness of new data points.
 */

public class FieldCorrelationCheck implements QualityFactor
{
    private RunningStat x = new RunningStat();
    private RunningStat y = new RunningStat();
    
    double ppmcc = -2;
    
    @Override
    public float run(Tuple entry)
    {
        if(!entry.valid())
        {
            return 0.0f;
        }
    
        double air = entry.air_temp;
        double sea = entry.sea_temp;
        
        x.push(air);
        y.push(sea);
        
        double cov_xy = (air - x.mean()) * (sea - y.mean());
        
        double x_sd = x.standard_deviation();
        double y_sd = y.standard_deviation();
        
        double ppmcc_new = cov_xy / (x_sd * y_sd);
        
        
        float result;
        if(x.count() == 1)
        {
            result =  1.0f;
        }
        else if(x.count() == 2)
        {
            ppmcc = ppmcc_new;
            result = 1.0f;
        }
        else
        {
            int x;
            if(Math.abs(ppmcc_new) > 1 || Math.abs(ppmcc) > 1 || Double.isInfinite(ppmcc) || Double.isInfinite(ppmcc_new))
            {
                
                x = 5;
                
                
            }
            
            
            float percent_error = (float) Math.abs(ppmcc_new - ppmcc) / 2;

            //double influence = (1 - Math.log10((percent_error*9)+1));        
            double influence = 1 - percent_error;
            ppmcc = (ppmcc_new - ppmcc) * influence;
            
            result = 1-percent_error; // 1 - expected error
        }
        
        return result;
        
    }

}
