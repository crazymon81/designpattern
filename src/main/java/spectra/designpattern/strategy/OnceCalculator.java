package spectra.designpattern.strategy;

public class OnceCalculator implements ICalculator
{
    public int calculator(int num)
    {
        return 1 * num;
    }
}
