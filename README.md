Checks for data inconsistancies using different stages all equally weighted in determination of data quality.


Steps:

1. FieldValidation
    Checks if all fields are valid numerical values.

2. RegressionAnalysis
    Performs linear regression analysis on all combinations (n choose k) tuple 
    fields.  Any tuple field with a strong correlation (defaults to R > 0.9) is 
    predicted against known actual.  Confidence is calculated by a multiplication 
    chain of (1 - error) for each strong prediction.  The result is the overall 
    confidence in the data showing a high likelihood of validity in a naive way.
