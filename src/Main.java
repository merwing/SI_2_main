public class Main
{
    public static void main(String[] Args)
    {
        //4,5,6,7,8,9,10,11
        //59,617,1447,2137,10177,104009
        NHetmansProblem nhp = new NHetmansProblem(11);
        nhp.startBT();
        nhp.startFc();
        Fibonacci f = new Fibonacci(123123);
        int []tab = f.getF_list().stream().mapToInt(Integer::intValue).toArray();
        f.StartFibBt(tab);

    }
}