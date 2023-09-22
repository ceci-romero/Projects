/*package gui;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

class ItemDialog extends JFrame
{
    private final static int width = 450;  
    private final static int height = 110;


    private JTextField name;
    private JTextField description;
    private JTextField cost;
    private JTextField price;

    public ItemDialog()
    {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Enter Information");
        button.setBounds(20,20,120, 30);  // position & size
        button.addActionListener(
            event -> onButtonClick());
        add(button);                  
		
        setSize(width,height);
        setVisible(true); 


    }

    pubilc void onButtonClick()
    {

        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        names = new JTextField(20); 

        JLabel Description = new JLabel("<HTML><br/>Description</HTML>");
        Description = new JTextField(20); 

        JLabel Cost = new JLabel("<HTML><br/>Cost</HTML>");
        Cost = new JTextField(20); 

        
        
    }

}

*/