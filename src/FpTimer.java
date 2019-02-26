/*Created by:
 * Rhajeem Crawford - 1501389
 * Santana Broderick - 1500711
 * Jahvier Small - 1501224
 */

public class FpTimer 
{
    private long start;

   public FpTimer()
   {
       start = System.currentTimeMillis();
   }

    public long getDiff() 
    {
       start = start - System.currentTimeMillis();
        return start;
    }




}
