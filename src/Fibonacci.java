import java.util.ArrayList;
import java.util.StringJoiner;

public class Fibonacci
{
    private ArrayList<Integer> f_list;
    private int value;
    final int one = 1;
    final int zero = 0;
    private ArrayList<Integer> iteration_list;

    public ArrayList<Integer> getF_list()
    {
        return f_list;
    }
    public Fibonacci() {}

    public Fibonacci(int value)
    {
        this.value = value;
        this.f_list = new ArrayList<>();
        generateSet();
        display();
        iteration_list = new ArrayList<>();
    }

    public void generateSet()
    {
        int first_number = zero;
        int second_number = one;
        int sum = 0;
        while (sum <= value)
        {
            sum = first_number+second_number;
//            if(sum > value)
//            {
//                break;
//
            f_list.add(sum);
            second_number = first_number;
            first_number = sum;
        }
        f_list.remove(0);
        f_list.remove(f_list.size()-1);
    }

    public void display()
    {
        for(Integer i : f_list)
        {
            System.out.print(i+", ");
        }
    }

//    public boolean isValueGet()
//    {
//
//    }

    public void StartFibBt(int[] tab)
    {
        long start_time = System.nanoTime();
        int[] set = new int[tab.length];

        boolean isSetFound = findSet(tab, set, value, 0, 0);
        if (isSetFound)
        {
//            System.out.println();
//            for(int i =0; i < subset.length; i++)
//            {
//                if(subset[i] !=0)
//                System.out.print(subset[i]+", ");
//            }
            System.out.print("\nFibonacci subset " + value + " : ");
            StringJoiner info = new StringJoiner(",", "{", "}");

            for (int temp = 0, i = 0; temp != value; temp += set[i], i++)
            {
                info.add(String.valueOf(set[i]));
            }
            System.out.print(info.toString());
        }
        else
            {
            System.out.println("Error");
        }
        calculateIterations();
        long finish_time = System.nanoTime();
        long difference = finish_time - start_time;
        System.out.println("Time: " + difference * Math.pow(10, 6) + " [ms]");
    }

    private boolean findSet(int[] tab, int[] set, int sum, int tab_index, int setIndex) {
        int itera = 0;
        if (tab_index == tab.length) {
            return false;
        }

        if (tab[tab_index] == sum) {
            set[setIndex] = tab[tab_index];
            return true;
        }

        for (int i = tab_index; i < tab.length; i++)
        {
            if (tab[i] > sum) {
                break;
            }
            if (tab[i] == sum) {
                set[setIndex] = tab[i];
                return true;
            }
            set[setIndex] = tab[i];
            if (findSet(tab, set, sum - tab[i], i + 1, setIndex + 1)) {
                return true;
            }
            itera++;
        }
        iteration_list.add(itera);
        return false;
    }
    public void calculateIterations()
    {
        int sum = 0;
        for(Integer i : iteration_list)
        {
            sum += i;
        }

        System.out.println("\nIterations: " + sum);
    }
}
