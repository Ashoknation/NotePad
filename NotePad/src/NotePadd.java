import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

 class NotePadd {

	JFrame frame;
	JTextArea textArea;
	
	
	String filename;
	String fileAddress;
	
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotePadd window = new NotePadd();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	NotePadd() {
		initialize();
	}

	
	  void initialize() {
		frame = new JFrame();
		frame.setTitle("NOTEPAD");
		frame.setResizable(false);
		frame.setBounds(100, 100, 991, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 985, 30);
		frame.getContentPane().add(menuBar);
		
		JMenu File = new JMenu("File      ");
		menuBar.add(File);
		
		JMenuItem newFile = new JMenuItem("NewFile");
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		newFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				frame.setTitle("NEW");
				
			}
		});
		File.add(newFile);
		File.addSeparator();
		
		JMenuItem OpenFile = new JMenuItem("Open File");
		OpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		OpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser=new JFileChooser(new File("C:\\Users\\Ashok kumar\\Desktop"));
				fileChooser.setDialogTitle("OPEN A FILE");
				
				
				int x = fileChooser.showOpenDialog(null);
				if(x == JFileChooser.APPROVE_OPTION)
				{
					try
					{
						File f=fileChooser.getSelectedFile();
						BufferedReader br = new BufferedReader(new FileReader(f.getPath()));
						String line = "";
						String s = "";
						while((line=br.readLine())!=null)
						{
							s = line + "\n";
							textArea.setText(s);
							
						}
						br.close();
						
						
					}
					catch(Exception e1)
					{
						System.out.println(e1);
					}
				}
				
								
			}
		});
		File.add(OpenFile);
		
		JMenuItem OpenProject = new JMenuItem("Open Project");
		File.add(OpenProject);
		File.addSeparator();
		
		JMenuItem Save = new JMenuItem("Save");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fileDialog=new FileDialog(frame,"Save",FileDialog.SAVE);
				fileDialog.setVisible(true);
				
				if(fileDialog.getFile()!=null);
				{
					filename=fileDialog.getDirectory() + fileDialog.getFile();
					frame.setTitle(filename);
				}
				try
				{
					FileWriter fileWriter=new FileWriter(filename);
					fileWriter.write(textArea.getText());
					frame.setTitle(filename);
					fileWriter.close();
					
				}
				catch(Exception e2)
				{
					System.out.println("FILE NOT SAVE");
				}
				
				
				
			}
		});
		Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		File.add(Save);
		File.addSeparator();
		
		JMenuItem Exit = new JMenuItem("Exit");
		File.add(Exit);
		
		JMenu Edit = new JMenu("Edit      ");
		menuBar.add(Edit);
		
		JMenuItem Undo = new JMenuItem("Undo");
		Undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		Edit.add(Undo);
		
		JMenuItem Redo = new JMenuItem("Redo");
		Redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		Edit.add(Redo);
		Edit.addSeparator();
		
		JMenuItem Cut = new JMenuItem("Cut");
		Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		Edit.add(Cut);
		
		JMenuItem Copy = new JMenuItem("Copy");
		Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		Edit.add(Copy);
		
		JMenuItem Paste = new JMenuItem("Paste");
		Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		Edit.add(Paste);
		
		JMenuItem Delete = new JMenuItem("Delete");
		Delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		Edit.add(Delete);
		
		JMenu Format = new JMenu("Format  ");
		menuBar.add(Format);
		
		JMenu Help = new JMenu("Help   ");
		menuBar.add(Help);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 52, 968, 419);
		frame.getContentPane().add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 29, 985, 537);//scrollpane hmesa textarea ko select krke rkhne ke baad user se daalna hoga
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		frame.getContentPane().add(scrollPane);
		
		
	}
}
