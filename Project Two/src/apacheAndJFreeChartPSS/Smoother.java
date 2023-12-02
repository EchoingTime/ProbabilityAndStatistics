package apacheAndJFreeChartPSS;
import org.apache.commons.math4.legacy.analysis.interpolation.LoessInterpolator;
public class Smoother
{
	private LoessInterpolator smoother;
	
	public void smooth ()
	{
		smoother = new LoessInterpolator ();
	}
}
