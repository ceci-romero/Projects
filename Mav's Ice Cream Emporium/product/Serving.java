package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;


public class Serving
{
    private Container container;
    private ArrayList<Scoop> scoops;
    private ArrayList<MixIn> toppings;

    public Serving(Container container)
    {
        this.container = container;
        this.scoops = new ArrayList<>();
        this.toppings = new ArrayList<>();
    }

    public Serving(BufferedReader in) throws IOException
    {
       // this.container = container;
       // this.scoops = new ArrayList<>();
       // this.toppings = new ArrayList<>();


        this.container= new Container(in);
        int numberOfscoops = Integer.parseInt(in.readLine());
        this.scoops = new ArrayList<>();
        for( int i = 0 ; i < numberOfscoops ; i ++)
        {
            Scoop scoop = new Scoop(in);
           // MixIn(mixin); //this is my addmixin 
        }



        int numberOftoppings = Integer.parseInt(in.readLine());
        this.toppings = new ArrayList<>();
        for( int i = 0 ; i < numberOftoppings ; i ++)
        {
            MixIn mixin = new MixIn(in);
           // addMixIn(mixin); //this is my addmixin 
        }

    }

   public void save(BufferedWriter out) throws IOException
   {
        container.save(out);
        int numberOfscoops = scoops.size();
        out.write("" + numberOfscoops + "\n");
        for(int i = 0; i < numberOfscoops; i++)
        {
            scoops.get(i).save(out);
        }

        int numberOftoppings = toppings.size();
        out.write("" + toppings + "\n");
        for(int i = 0; i < numberOfscoops; i++)
        {
            toppings.get(i).save(out);
        }


   }
   public void addScoop(Scoop scoop)
        {
            scoops.add(scoop);
        }


        public void addTopping(MixIn topping)

        {
            toppings.add(topping);
        }



}