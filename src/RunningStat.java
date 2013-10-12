
// Found at: http://www.johndcook.com/standard_deviation.html
public class RunningStat {

    private int n_ = 0;
    private double m_old_, m_new_, s_old_, s_new_;
    
    public RunningStat()
    {
    }
    
    public void clear()
    {
        n_ = 0;
    }
    
    public void push(double x)
    {
        n_++;
        
        if(n_ == 1)
        {
            m_old_ = m_new_ = x;
            s_old_ = 0.0;
        }
        else
        {
            m_new_ = m_old_ + (x - m_old_) / n_;
            s_new_ = s_old_ + (x - m_old_) * (x - m_new_);
            
            m_old_ = m_new_;
            s_old_ = s_new_;
        }
    }
    
    public int count()
    {
        return n_;
    }

    public double mean()
    {
        return (n_ > 0) ? m_new_ : 0.0;
    }

    public double variance()
    {
        return ( (n_ > 1) ? s_new_ / (n_ - 1) : 0.0 );
    }

    public double standard_deviation()
    {
        return Math.sqrt( variance() );
    }
}
