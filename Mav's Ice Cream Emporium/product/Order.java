package product;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Order
{
    private ArrayList<Serving> servings;

    public Order()
    {
        this.servings = new ArrayList<>();
    }


    public Order(BufferedReader in) throws IOException
    {
        this.servings = new ArrayList<>();
        int numberOfServings = Integer.parseInt(in.readLine());
        for( int i = 0 ; i < numberOfServings ; i ++)
        {

            Serving serving = new Serving(in);

        }
    }


    public void save(BufferedWriter out) throws IOException
    {
    
        int numberOfServings = servings.size();
        out.write("" + numberOfServings + "\n");
        for(int i = 0; i < numberOfServings; i++)
        {
            servings.get(i).save(out);
        }
    }

    public void addServing(Serving serving)
    {
        servings.add(serving);
    }


     
}