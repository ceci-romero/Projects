package emporium;

import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Container;
import product.Order;
import product.Serving;

import java.util.ArrayList;
import java.lang.Object;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Emporium
{
    private ArrayList<MixInFlavor> mixInFlavors = new ArrayList<>(); 
    private ArrayList<IceCreamFlavor> iceCreamFlavors = new ArrayList<>();
    private ArrayList<Container> containers= new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public Emporium()
    {
        //empty for reasons
    }

    public Emporium(BufferedReader in) throws IOException
    {
        int numberOfM = Integer.parseInt(in.readLine());

        for( int i = 0 ; i < numberOfM ; i++)
        {
            MixInFlavor mixinflavor = new MixInFlavor(in);
            addMixInFlavor(mixinflavor); //this is my addmixin 
        }
        int numberOfIceCreamFlavors = Integer.parseInt(in.readLine());
        for( int i = 0 ; i < numberOfIceCreamFlavors ; i++)
        {
            IceCreamFlavor icecreamflavor = new IceCreamFlavor(in);
            addIceCreamFlavor(icecreamflavor); //this is my addmixin 
        }

        int numberOfC = Integer.parseInt(in.readLine());
        
        for(int i = 0; i < numberOfC; i++)
        {
            Container container = new Container(in);
            addContainer(container);
        }

        int numberOfOrders = Integer.parseInt(in.readLine());
        for( int i = 0 ; i < numberOfOrders ; i++)
        {
            Order order = new Order(in);
            addOrder(order); //this is my addmixin 
        }

    }

    public void save(BufferedWriter out) throws IOException
    {
        int numberOfM = mixInFlavors.size();
        out.write("" + numberOfM + "\n");
        for(int i = 0; i < numberOfM ; i++)
        {
            mixInFlavors.get(i).save(out);
        }
        
        int numberOfIceCreamFlavors = iceCreamFlavors.size();
        out.write("" + numberOfIceCreamFlavors + "\n");
        for(int i = 0; i < numberOfIceCreamFlavors; i++)
        {
            iceCreamFlavors.get(i).save(out);
        }

        int numberOfC = containers.size();
        out.write("" + numberOfC + "\n");
        for(int i = 0; i < numberOfC; i++)
        {
            containers.get(i).save(out);
        }

        int numberOfOrders = orders.size();
        out.write("" + numberOfOrders + "\n");
        for(int i = 0; i < numberOfOrders; i++)
        {
            orders.get(i).save(out);
        }


    }
    public void addMixInFlavor(MixInFlavor flavor)
    {
        mixInFlavors.add(flavor);
    }

    public void addIceCreamFlavor(IceCreamFlavor flavor)
    {
        iceCreamFlavors.add(flavor);
    }

    public void addContainer(Container container)
    {
        containers.add(container);
    }

    public void addOrder(Order order)
    {
        orders.add(order);
    }

    public Object[] mixInFlavors()
    {
        return mixInFlavors.toArray();
    }
    public Object[] iceCreamFlavors()
    {
        return iceCreamFlavors.toArray();
    }

    public Object[] containers()
    {
        return containers.toArray();
    }

    public Object[] orders()
    {
        return orders.toArray();
    }


    
}
