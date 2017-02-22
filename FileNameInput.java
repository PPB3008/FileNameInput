import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.io.*;
class FileNameInput 
{
	public static void main(String[] args)
	{
		new mainFrame();
	}
}

class mainFrame
{	
    private Frame f;
	private MenuBar mb;
	private Menu m;
	private MenuItem mi,mi1;
	private Button b,c;
	private TextArea ta;
	private TextField tf;
	private Dialog di;
	private int num=1;
	private Label l;
	
	mainFrame()
	{
      format();
	  event();
	}
   
    private void format()
	{
	  f=new Frame("FileNameInput");
	  mb=new MenuBar();
      m=new Menu("File");
      mi=new MenuItem("Save");
	  b=new Button("Type in Directory");
	  mi1=new MenuItem("Close");
	  tf=new TextField(60);
	  ta=new TextArea(58,120);
	  di=new Dialog(f,"Illegal Directory");
	  l=new Label("");
	  c=new Button("OK");
	  f.add(b);
	  f.add(tf);
	  f.add(ta);
	  f.setLocation(500,50);
	  f.setVisible(true);
	  f.setSize(1000,1000);
	  //f.setBounds(500,500,500,500);
	  f.setMenuBar(mb);
	  f.setLayout(new FlowLayout());
	  m.add(mi);
	  m.add(mi1);
	  mb.add(m);
	  di.setBounds(500,300,300,120);
	  di.setLocation(850,300);
	  di.setLayout(new FlowLayout());
	  di.add(l);
	  di.add(c);
	}
	
	private void event()
	{
		mi.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent g)
		{
			BufferedWriter bw=null;
			try
			{
			bw=new BufferedWriter(new FileWriter("FileName"+num+".txt"));
			bw.write(ta.getText());
			num++;
			}
			catch(IOException e)
			{
				System.out.println(e.toString());
			}
			try
			{
				bw.close();
			}
			catch(IOException e)
			{
				System.out.println(e.toString());
			}
		}
	});
	mi1.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent f)
		{
			System.exit(0);
		}
	});
	f.addWindowListener(new WindowAdapter()  
	{
	public void windowClosing(WindowEvent e)
	    {
		    System.exit(0);
	    }
	});
	b.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{   
		    if(!(new File(tf.getText()).isDirectory()))
			{
				di.setVisible(true);
				l.setText("The directory you type in"+tf.getText()+" is illegal,please type in another");
			}
			else{
		    ta.setText("");
			ListFunc(new File(tf.getText()));
			}
			/*
			File[] dir=new File(tf.getText()).listFiles();
		    for(File f:dir)
			{   
				String s=f.toString();
				ta.append(s+"\r\n");
			}*/
		}
	});
	b.addMouseListener(new MouseAdapter()
	{
		public void mouseEntered(MouseEvent e)
		{
			System.out.println("in");
		}
		public void mouseClicked(MouseEvent e)
		{   
		    ta.setText("");
			System.out.println("click"+num);
			num++;
		}
	});
	c.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{   
		  di.setVisible(false);
		  tf.setText("");
		}
	});
	di.addWindowListener(new WindowAdapter()  
	{
	public void windowClosing(WindowEvent e)
	    {
		    di.setVisible(false);
			tf.setText("");
	    }
	});
	}
	
	public void ListFunc(File f)
	{
		File[] dir=f.listFiles();
		for(int x=0;x<dir.length;x++)
		{
			if(dir[x].isDirectory())
			{
				ListFunc(dir[x]);
			}
			else
			{
				ta.append(dir[x].toString()+"\r\n");
			} 
		}
	}
}


/*class listener extends WindowAdapter
{
		public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
}
*/



