/* 2.2.1 Field Validation
 * 
 * We will first attempt to invalidate the entire entry by checking for
 * missing fields, or fields containing impossible values. Otherwise our
 * system will attempt to determine if the value makes sense according
 * to the previous history of entries. A field like temperature should
 * not dramatically increase or decrease for instance.
 */

public class FieldValidation implements QualityFactor
{
    @Override
    public float run(Tuple entry)
    {
        if(entry.valid())
        {
            return 1.0f;
        }
        else
        {
            return 0.0f;
        }
    }

}
