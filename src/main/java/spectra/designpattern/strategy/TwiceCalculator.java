package spectra.designpattern.strategy;

public class TwiceCalculator implements ICalculator
{
    public int calculator(int num)
    {
        return 2 * num;
    }
}