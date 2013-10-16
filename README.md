Checks for data inconsistancies using different stages all equally weighted in determination of data quality.


Steps: 	FieldValidation     - Checks if all fields are valid numerical values.
	RegressionAnalysis  - Performs linear regression analysis on all combinations (n choose k) tuple fields.  Any tuple field with a strong correlation (defaults to R > 0.9) is predicted against known actuals.  Confidence is calculated by a mutiplication chain of (1 - error) for each strong prediction.  The result is the overall confidence in the data showing a high likelyhood of validity in a naive way.
