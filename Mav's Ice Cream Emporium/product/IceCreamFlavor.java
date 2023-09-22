package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class IceCreamFlavor extends Item
{
    public IceCreamFlavor(String name, String description, int cost, int price)
    {
        super(name, description, cost, price);
    }

    public IceCreamFlavor(BufferedReader in) throws IOException
    {
        super(in);
    }

/*
    public void save(BufferedWriter out)
    {
        out.write("" + name + '\n');        
        out.write("" + description + '\n');
        out.write("" + cost + '\n');
        out.write("" + price + '\n');
    }
    */
}