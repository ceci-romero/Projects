package product;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Scoop
{
    private IceCreamFlavor flavor;
    private ArrayList<MixIn> mixins;

    public Scoop(IceCreamFlavor flavor)
    {
        this.flavor = flavor;
        this.mixins = new ArrayList<>();
    }
    public Scoop(BufferedReader in) throws IOException
    {
        this.flavor = new IceCreamFlavor(in);
        int numberOfFlavors = Integer.parseInt(in.readLine());
        this.mixins = new ArrayList<>();
        for( int i = 0 ; i < numberOfFlavors ; i ++)
        {
            MixIn mixin = new MixIn(in);
            MixIn(mixin); //this is my addmixin 
        }
    }

    public void save(BufferedWriter out) throws IOException
    {
        flavor.save(out);
        int numberOfMixIns = mixins.size();
        out.write("" + numberOfMixIns + "\n");
        for(int i = 0; i < numberOfMixIns; i++)
        {
            mixins.get(i).save(out);
        }
    }

    public void MixIn(MixIn mixin)
    {
        mixins.add(mixin);
    }


    @Override
    public String toString()
    {

        int index = 0;
        String mixIns = null;
        if (mixins.isEmpty())
        {
            return "" + flavor + "";
        }
        else
        {
            //if (index > 0)
             //{
                //mixIns = mixIns + ",";
             //}
            
            //mixIns = mixins.get(index);

            for(MixIn m: mixins)
            {
                if (index > 0)
             {
                mixIns = mixIns + ",";
             }

             if(index == 0)
             {
                mixIns = "" + mixins.get(index);
             }
             else
             {
                mixIns = mixIns + " " + mixins.get(index);
             }
                index++;
            }
            index = 0;
            return "" + flavor + " with " + mixIns;
            
        }
        

    }
}