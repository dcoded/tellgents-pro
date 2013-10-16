//package oldbroken;
//import QualityFactor;
//import Tuple;
//
//import java.lang.reflect.Field;
//import java.util.Arrays;
//
//import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
//import org.apache.commons.math3.util.ArithmeticUtils;
//
///* 2.2.3 Regression Analysis and Field Correlation Checks
// * 
// * The fields of the dataset may be calculated for Pearson product-moment
// * correlation coefficients. These coefficients will be used to check
// * incoming tuples independent of the data set. If the field correlation
// * coefficients do not agree with that of the data set then it shows
// * erroneous behavior in measurement. Correlations may not exist or be
// * strong enough to determine correctness of new data points.
// */
//
//public class FieldCorrelation implements QualityFactor
//{
//	private static int HISTORY_SIZE = 1024;
//	private PearsonsCorrelation corr_ = new PearsonsCorrelation();
//	private double[] correlations_;
//	
//	private Field[] fields_;
//	private int length_ = -1;
//	private boolean filled_ = false;
//	
//	private double[][] history_ = null;
//	private int next_ = 0;
//	
//	public FieldCorrelation()
//	{
//		fields_  = Tuple.class.getFields();
//		length_	 = fields_.length;
//		history_ = new double[length_][HISTORY_SIZE];
//		
//		int combs = (int) ArithmeticUtils.binomialCoefficient(length_, 2);
//		correlations_     = new double[combs];
//	}
//	
//	@Override
//	public float run(Tuple tuple)
//	{
//		try {
//			insert(tuple);
//		} catch (IllegalArgumentException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		if(filled_ || next_ > 1)
//			correlate();
//		
//		next_ = (next_ + 1) % history_[0].length;
//		if(next_ == 0)
//		{
//			filled_ = true;
//		}
//		// TODO Auto-generated method stub
//		return 0;
//	}
//   
//	private void insert(Tuple tuple) throws IllegalArgumentException, IllegalAccessException
//	{
//		for(int i = 0; i < length_; i++)
//		{
//			history_[i][next_] = fields_[i].getDouble(tuple);
//		}
//	}
//	
//	public double correlate()
//	{
//		double[][] data = null;
//		if(filled_ == false)
//		{
//			data = new double[length_][];
//			for(int i = 0; i < length_; i++)
//			{
//				data[i] = Arrays.copyOfRange(history_[i], 0, next_ + 1);
//			}
//		}
//		else
//		{
//			data = history_;
//		}
//		
//		
//		int c = 0;
//		for(int a = 0; a < length_; a++)
//		{
//			for(int b = a + 1; b < length_; b++)
//			{
//				correlations_[c] = corr_.correlation(data[a], data[b]);
//				
//				System.out.print(fields_[a].getName() + ':' + fields_[b].getName() + ' ' + data[a].length + '\t');
//				double val = corr_.correlation(data[a], data[b]);
//				System.out.println(val);
//				c++;
//			}
//		}
//		return 0;
//	}
//}
