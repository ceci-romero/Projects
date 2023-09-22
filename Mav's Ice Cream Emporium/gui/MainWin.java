package gui;

import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Container;
import product.Order;
import product.Serving;
import javax.swing.JOptionPane;

import emporium.Emporium;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.ImageIO;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
//import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.Graphics2D;

public class MainWin extends JFrame 
{
    
    private Emporium emporium;
    private JLabel display;

    private File filename; 


    public MainWin()
    {
        super("Mavs Ice Cream Emporium");
        this.emporium = new Emporium();
        this.display = new JLabel();
        //display.setLocation(100, 100);
        add(display, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300,400);
        filename = new File("untitled.mice");


        //MENU BAR CODE
        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem quit       = new JMenuItem("Quit");
        JMenu     view       = new JMenu("View");
        JMenuItem viewICF    = new JMenuItem("+- Ice Cream Flavor");
        JMenuItem viewMIF    = new JMenuItem("+- Mix In Flavor");
        JMenuItem viewC      = new JMenuItem("+- Container"); 
        JMenuItem viewO      = new JMenuItem("+- Order");
        JMenu     create     = new JMenu("Create");
        JMenuItem createICF  = new JMenuItem("+- Ice Cream Flavor");
        JMenuItem createMIF  = new JMenuItem("+- Mix In Flavor");
        JMenuItem createC    = new JMenuItem("+- Container");  
        JMenuItem createO   = new JMenuItem("+- Order");
        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");

        file.add(quit);
        view.add(viewICF);
        view.add(viewMIF);
        view.add(viewC);
        view.add(viewO);
        create.add(createICF);
        create.add(createMIF);
        create.add(createC);
        create.add(createO);
        help.add(about);

        // The action listeners
        createICF.addActionListener(event -> onCreateIceCreamFlavorClick());
        quit.addActionListener(event -> onQuitClick());
        createMIF.addActionListener(event -> onCreateMixInFlavor());
        createC.addActionListener(event -> onCreateContainerClick());
        createO.addActionListener(event-> onCreateOrderClick());
        about.addActionListener(event -> onAboutClick());
        viewICF.addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));
        viewMIF.addActionListener(event -> view(Screen.MIX_IN_FLAVORS));
        viewC.addActionListener(event -> view(Screen.CONTAINERS));
        viewO.addActionListener(event-> view(Screen.ORDERS));

        menubar.add(file);
        menubar.add(view);
        menubar.add(create);
        menubar.add(help);
        setJMenuBar(menubar);



        /// TOOL BAR/////////////////////////////////////////

        JToolBar toolbar = new JToolBar("MICE Controls");
        
        //******* these are the File controls
        //Save Button
        JButton Save = new JButton(new ImageIcon("Save.png"));

         Save.setActionCommand("Save");
         Save.setToolTipText("Save");
         toolbar.add(Save);
         //comeback to add action listener
         Save.addActionListener(event -> onSaveClick());

         //SaveAs
        JButton SaveAs = new JButton(new ImageIcon("SaveAs.png"));

         SaveAs.setActionCommand("Save As");
         SaveAs.setToolTipText("Save As");
         toolbar.add(SaveAs);
         //Comeback to add action Listener
         SaveAs.addActionListener(event -> onSaveAsClick());

        //Open
        JButton Open = new JButton(new ImageIcon("Open.png"));

         Open.setActionCommand("Open File");
         Open.setToolTipText("Open File");
         toolbar.add(Open);
         //Comeback to add action listener
         Open.addActionListener(event -> onOpenClick());

         //MICE CONTROLS//////////////////////////////////////////////

        JButton AddMixIn = new JButton(new ImageIcon("AddMixIn.png"));

         AddMixIn.setActionCommand("Add MixIn Flavor");
         AddMixIn.setToolTipText("Add MixIn Flavor");
         toolbar.add(AddMixIn);
         AddMixIn.addActionListener(event -> onCreateMixInFlavor());

         JButton AddIC = new JButton(new ImageIcon("AddIC.png"));
         AddIC.setActionCommand("Add Ice Cream Flavor");
         AddIC.setToolTipText("Add Ice Cream Flavor");
         toolbar.add(AddIC);
         AddIC.addActionListener(event -> onCreateIceCreamFlavorClick());

        JButton AddOrder= new JButton(new ImageIcon("AddScoop.png"));
         AddOrder.setActionCommand("Add An Order");
         AddOrder.setToolTipText("Add An Order");
         toolbar.add(AddOrder);
         AddOrder.addActionListener(event -> onCreateOrderClick());

         JButton addContainer = new JButton(new ImageIcon("AddContainer.png"));
         addContainer.setActionCommand("Add Container");
         addContainer.setToolTipText("Add Container");
         toolbar.add(addContainer);
         addContainer.addActionListener(event-> onCreateContainerClick());



         //VIEW TOOLBAR ***********************************************************************************

         JButton MixIn = new JButton(new ImageIcon("MixIn.png"));
         MixIn.setActionCommand("View Mix In Flavors");
         MixIn.setToolTipText("View Mix In Flavors");
         toolbar.add(MixIn);
         MixIn.addActionListener(event -> view(Screen.MIX_IN_FLAVORS));

         JButton IC = new JButton(new ImageIcon("IC.png"));
         IC.setActionCommand("View Ice Cream Flavors");
         IC.setToolTipText("View Ice Cream Flavors");
         toolbar.add(IC);
         IC.addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));

         JButton O = new JButton(new ImageIcon("Scoop.png"));
         O.setActionCommand("View Orders");
         O.setToolTipText("View Orders");
         toolbar.add(O);
         O.addActionListener(event -> view(Screen.ORDERS));

         JButton C = new JButton(new ImageIcon("Container.png"));
         C.setActionCommand("View Containers");
         C.setToolTipText("View Containers");
         toolbar.add(C);
         C.addActionListener(event->view(Screen.CONTAINERS));

         toolbar.add(Box.createHorizontalStrut(25));

         getContentPane().add(toolbar, BorderLayout.PAGE_START);

        toolbar.add(Box.createHorizontalStrut(25));
        


        setVisible(true);
    }


    //METHODS 

    public void onQuitClick()
    {
        System.exit(0);
    }

    public void onCreateIceCreamFlavorClick()
    {
        
		String name = JOptionPane.showInputDialog(this, "Name?");
	
		String description = JOptionPane.showInputDialog(this, "Description");

        String cost = JOptionPane.showInputDialog(this, "Price?");

        String price = JOptionPane.showInputDialog(this, "Cost?");

        IceCreamFlavor flavor = new IceCreamFlavor(name, description, Integer.parseInt(cost), Integer.parseInt(price));

        emporium.addIceCreamFlavor(flavor);

        //comeback to set view
        view(Screen.ICE_CREAM_FLAVORS);
        
    }

    public void onCreateMixInFlavor()
    {
        String name = JOptionPane.showInputDialog(this, "Name?");
	
		String description = JOptionPane.showInputDialog(this, "Description");

        String cost = JOptionPane.showInputDialog(this, "Price?");

        String price = JOptionPane.showInputDialog(this, "Cost?");

        MixInFlavor flavor = new MixInFlavor(name, description, Integer.parseInt(cost), Integer.parseInt(price));

        emporium.addMixInFlavor(flavor);

        //comeback to set view
        view(Screen.MIX_IN_FLAVORS);



    }

    public void onCreateContainerClick()
    {
        String name = JOptionPane.showInputDialog(this, "Name?");
	
		String description = JOptionPane.showInputDialog(this, "Description");

        String maxScoops = JOptionPane.showInputDialog(this, "Max Scoops?");

        Container container = new Container(name, description, Integer.parseInt(maxScoops));

        emporium.addContainer(container);
 
        //comeback to set view
        view(Screen.CONTAINERS);



    }

    public Scoop onCreateScoop()
    {
        int flag =1;
        Scoop scoop = null;
        Object IceCreamScoop = JOptionPane.showInputDialog(this, "Pick an ice cream flavor:", "Ice Cream Flavors", 
        JOptionPane.QUESTION_MESSAGE, null, emporium.iceCreamFlavors(), JOptionPane.DEFAULT_OPTION);
        if(IceCreamScoop != null)
        {
        IceCreamFlavor IceCreamChoice = (IceCreamFlavor) IceCreamScoop;
        scoop = new Scoop(IceCreamChoice);
        //emporium.addScoop(scoop);

        do
        {
        Object MixInScoop = JOptionPane.showInputDialog(this, "Pick a mix in flavor:", "Mix In Flavors", 
        JOptionPane.QUESTION_MESSAGE, null, emporium.mixInFlavors(), JOptionPane.DEFAULT_OPTION);
        if(MixInScoop == null) {flag = 0;}
        if(MixInScoop != null)
        {
        MixInFlavor MixInChoice = (MixInFlavor) MixInScoop;

        Object MixInAmountScoop = JOptionPane.showInputDialog(this, "How much?", "Mix In Amount", 
        JOptionPane.QUESTION_MESSAGE, null, MixInAmount.values(), JOptionPane.DEFAULT_OPTION);

        MixInAmount amountChoice = (MixInAmount) MixInAmountScoop;

        MixIn mixin = new MixIn(MixInChoice, amountChoice);
        scoop.MixIn(mixin);

        }
        }while (flag == 1) ;
        }
        
        return scoop;
        
       // view(Screen.SCOOPS);
    }


    public Serving onServing()
    {
        int flag =1;
        Object Container = JOptionPane.showInputDialog(this, "Pick a Container:", "Containers:", 
        JOptionPane.QUESTION_MESSAGE, null, emporium.containers(), JOptionPane.DEFAULT_OPTION);
        if(Container != null)
        {
            Container containerChoice = (Container) Container;
            Serving serving = new Serving(containerChoice);
            //emporium.addScoop(scoop);

            do
            {
            //Comeback to validate atleast one scoop was entered

            Scoop scoop = onCreateScoop();
            if(scoop != null)
            {
            serving.addScoop(scoop);

            Object MixInServing = JOptionPane.showInputDialog(this, "Pick a topping:", "Mix In Flavors", 
            JOptionPane.QUESTION_MESSAGE, null, emporium.mixInFlavors(), JOptionPane.DEFAULT_OPTION);
            if(MixInServing == null) {flag = 0;}
            if(MixInServing != null)
            {
                MixInFlavor MixInChoice = (MixInFlavor) MixInServing;

                Object MixInAmountServing = JOptionPane.showInputDialog(this, "How much?", "Mix In Amount", 
                JOptionPane.QUESTION_MESSAGE, null, MixInAmount.values(), JOptionPane.DEFAULT_OPTION);

                MixInAmount amountChoice = (MixInAmount) MixInAmountServing;

                MixIn mixin = new MixIn(MixInChoice, amountChoice);
                serving.addTopping(mixin);

            }
            return serving;
            }
            }while (flag == 1) ;

            if(Container == null)
            {
                serving = null;
                return serving;
            }
        //comeback to add if statement for cancel
        //if(scoop != null)
        //{
        //emporium.addScoop(scoop);
        //return Serving;
        //}

        
        }

        return null;
    }


    public void onCreateOrderClick()
    {
        Order order = new Order();
        Serving serving = onServing();

        do
        {
            //Serving serving = onServing();

            if(serving != null)
            {
                order.addServing(serving);
                emporium.addOrder(order);
            }

        }while(serving != null);

        view(Screen.ORDERS);

    }


    public void onAboutClick()
    {
        
        JDialog about = new JDialog();
        about.setLayout(new FlowLayout());
        about.setTitle("Mavs Ice Cream Emporium");

        //about.add(new Canvas.paintComponent(Graphics2D.drawLine));
        Canvas canvas = new Canvas();
        canvas.repaint();
        about.add(canvas);
        canvas.repaint();
        
        //canvas.paintComponent(g);
       // @Override
       // Canvas.paintComponent();
        
        try {
            BufferedImage myPicture = ImageIO.read(new File("ice-cream-1429596_640.png"));
            JLabel logo = new JLabel(new ImageIcon(myPicture));
            about.add(logo);
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>MICE</font></p>"
          + "</html>");
        about.add(title);

        JLabel artists = new JLabel("<html>"
          + "<p>Version 0.2</p>"
          + "<p>Copyright 2022 by Cecilia Romero</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "<p>Logo by Schmidsi, per the Pixabay License</p>"
          + "<p>Ice Cream Icons created by Cecilia Romero</p>"
          + "<p>File Icons created by Cecilia Romero</p>"
          + "<p><font size=-2>https://pixabay.com/en/ice-ice-cream-cone-ice-ball-pink-1429596/</font></p>"
          + "</html>");
        about.add(artists);

        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        about.add(ok);
        
        about.setSize(800,800);
        about.setVisible(true);

        
    }
    

    private void view(Screen screen)
    {
        
        if(screen == Screen.ICE_CREAM_FLAVORS)
        {
            
            String theListOutput = null;
            int index = 0;
            //if statements work
            // loop through array to get list return of icecream flavors
            for(Object i: emporium.iceCreamFlavors())
            {
                if (index == 0)
                {
                    theListOutput = (emporium.iceCreamFlavors())[index] + "";
                }
                else
                {
                theListOutput = theListOutput + ", " + (emporium.iceCreamFlavors())[index];
                }
                index++;
            }
            display.setText("Ice Cream Flavors: " + theListOutput);
            

        }
        if(screen == Screen.MIX_IN_FLAVORS)
        {
            //loop through array to get list return of mixin flavors
            String theListOutput = null;
            int index = 0;
            //if statements work
            // loop through array to get list return of icecream flavors
            for(Object i: emporium.mixInFlavors())
            {
                if (index == 0)
                {
                    theListOutput = (emporium.mixInFlavors())[index] + "";
                }
                else
                {
                theListOutput = theListOutput + ", " + (emporium.mixInFlavors())[index];
                }
                index++;
            }
            display.setText("Mix In Flavors: " + theListOutput);
        
        }

        if(screen == Screen.CONTAINERS)
        {
            String theListOutput = null;
            int index = 0;
            for(Object i: emporium.containers())
            {
                if (index == 0)
                {
                    theListOutput = (emporium.containers())[index] + "";
                }
                else
                {
                theListOutput = theListOutput + "| " + (emporium.containers())[index];
                }
                index++;
            }

            display.setText("Containers: " + theListOutput);

        }
            
        if(screen == Screen.ORDERS)
        {
            //get loop of scoop list and display
            String theListOutput = null;
            int index = 0;
            //if statements work
            // loop through array to get list return of icecream flavors
            for(Object i: emporium.orders())
            {
                if (index == 0)
                {
                    theListOutput = (emporium.orders())[index] + "";
                }
                else
                {
                theListOutput = theListOutput + "| " + (emporium.orders())[index];
                }
                index++;
            }

            display.setText("Orders: " + theListOutput);

        }

        
    }

    public void onSaveClick()
    {
                // Create a new game
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            //bw.write(MAGIC_COOKIE + '\n');
            //bw.write(FILE_VERSION + '\n');
            emporium.save(bw);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open " + filename + '\n' + e,
                "Failed", JOptionPane.ERROR_MESSAGE); 
        }
    

    }

    public void onSaveAsClick()
    {         // Create a new game
        final JFileChooser fc = new JFileChooser(filename);  // Create a file chooser dialog
        FileFilter miceFiles = new FileNameExtensionFilter("MICE files", "mice");
        fc.addChoosableFileFilter(miceFiles);         // Add "Nim file" filter
        fc.setFileFilter(miceFiles);                  // Show Nim files only by default
        
        int result = fc.showSaveDialog(this);        // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile();         // Obtain the selected File object
            if(!filename.getAbsolutePath().endsWith(".mice"))  // Ensure it ends with ".nim"
                filename = new File(filename.getAbsolutePath() + ".mice");
            onSaveClick();                       // Delegate to Save method
        }
    }

    

    public void onOpenClick()
    {
        final JFileChooser fc = new JFileChooser(filename);  // Create a file chooser dialog
        FileFilter miceFiles = new FileNameExtensionFilter("MICE files", "mice");
        fc.addChoosableFileFilter(miceFiles);         // Add "Nim file" filter
        fc.setFileFilter(miceFiles);                  // Show Nim files only by default
        
        int result = fc.showOpenDialog(this);        // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION)
         { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile();        // Obtain the selected File object
            
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) 
            {
                // String magicCookie = br.readLine();
                // if(!magicCookie.equals(MAGIC_COOKIE)) throw new RuntimeException("Not a Nim file");
                //String fileVersion = br.readLine();
                //if(!fileVersion.equals(FILE_VERSION)) throw new RuntimeException("Incompatible Nim file format");
                
                emporium = new Emporium(br);                   // Open a new game
                //setSticks();                         // Update the user interface
                view(Screen.ORDERS);
            } catch (Exception e) 
            {
                JOptionPane.showMessageDialog(this,"Unable to open " + filename + '\n' + e, 
                    "Failed", JOptionPane.ERROR_MESSAGE); 
             }
        } 
    }

   
        
}