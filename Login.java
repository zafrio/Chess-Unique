import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Login extends JFrame implements ActionListener
{
	public Login()
	{
		super("Login");
		Container c = getContentPane();
		c.setLayout(new GridLayout(3,2));
		JLabel label1 = new JLabel ("User Name");
		JLabel label2 = new JLabel ("Password");
		JTextField box1 = new JTextField(20);
		JTextField box2 = new JTextField(20);
		JButton Enter = new JButton ("Enter");
		c.add(label1);
		c.add(box1);
		c.add(label2);
		c.add(box2);
		c.add(Enter);
		Enter.addActionListener(this);
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null,"Success");
	}
	public static void main(String[] args)
	{
		Login GUI = new Login();
	}
}