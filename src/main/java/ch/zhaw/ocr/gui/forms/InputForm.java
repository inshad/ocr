package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class InputForm {
	
	final JFileChooser fc = new JFileChooser();
	
    private JFrame frame;
    private JTextField imagePath;
    private JTextArea analysedText;
    private JButton saveButton;
    private JButton browseButton;
    private JButton analyseButton;
    
    public static void main(String [ ] args) {
    	
    	new InputForm();

    }
    
    public InputForm() {
    	
    	openWindow();
    	
    }
    
    private void openWindow() {
    	
        frame = new JFrame("");
        frame.setLocation(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        JPanel contentPane = new JPanel(new BorderLayout());
        
        frame.getContentPane().add(contentPane);
       
        contentPane.add(loadTextArea(), BorderLayout.CENTER);
        contentPane.add(loadButtons(), BorderLayout.SOUTH);
        
        frame.setVisible(true);
        frame.pack();

    }
    
    private JComponent loadButtons() {
    	
        JPanel buttonPanel = new JPanel(new SpringLayout());
        
        JLabel browseImg = new JLabel("Browse Image:");
        
        imagePath = new JTextField();

        browseButton = new JButton("Browse");
        analyseButton = new JButton("Analyse");
        
        browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == browseButton) {
					
					fc.addChoosableFileFilter(new ImageFilter());
					fc.setAcceptAllFileFilterUsed(false);				
					
					int returnVal = fc.showOpenDialog(browseButton);
					
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            java.io.File file = fc.getSelectedFile();
			            imagePath.setText(file.getPath());
			        } 
				}
				
			}
        });
        
        buttonPanel.add(browseImg);
        buttonPanel.add(imagePath);
        buttonPanel.add(browseButton);
        buttonPanel.add(analyseButton);
        
        SpringUtilities.makeCompactGrid(buttonPanel, 1, 4, 5, 5, 5, 5);
        
        return buttonPanel;
    	
    }
    
    private JComponent loadTextArea() {
    	
        JPanel areaPanel = new JPanel(new SpringLayout());
        JPanel button2Panel = new JPanel();
        
        analysedText = new JTextArea(20,70);
        JScrollPane scrollAnalysed = new JScrollPane (analysedText);
        
        saveButton = new JButton("Save as text");
        
        button2Panel.setLayout(new BoxLayout(button2Panel, BoxLayout.LINE_AXIS));
        button2Panel.add(Box.createHorizontalGlue());
        button2Panel.add(saveButton);
        
        areaPanel.add(scrollAnalysed);
        areaPanel.add(button2Panel);
                
        SpringUtilities.makeCompactGrid(areaPanel, 2, 1, 10, 10, 10, 20);
    	
        return areaPanel;
    }

}