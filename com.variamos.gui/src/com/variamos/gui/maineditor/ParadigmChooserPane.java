package com.variamos.gui.maineditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import com.variamos.gui.core.maineditor.models.ParadigmTypeEnum;

public class ParadigmChooserPane extends JDialog implements ActionListener, MouseMotionListener, MouseListener{
	/**
	 * Auto generated serial version ID
	 */
	private static final long serialVersionUID = -6263024574690996345L;
		
	private Point initialClick;
	private JButton butClose;
	private JLabel labMessage;
	private ArrayList<JButton> butProjects;
	
	private ParadigmTypeEnum optionChoosed;
	
	public final static String WITH_OPEN_MODEL = "WITH_OPEN_MODEL";
	public final static String WITHOUT_OPEN_MODEL = "WITHOUT_OPEN_MODEL";
	
	private ParadigmChooserPane(Frame owner, String openModelOption) {
		super(owner,true);
		setUndecorated(true); // Remove title bar
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		
		labMessage = new JLabel("Select the type of project in which you will work", SwingConstants.CENTER);
		labMessage.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
	
		butClose = new JButton(new ImageIcon(ParadigmChooserPane.class.getResource("/com/variamos/gui/maineditor/images/close.png")));
		butClose.setOpaque(false);
		butClose.setContentAreaFilled(false);
		butClose.setBorderPainted(false);
		butClose.setFocusPainted(false);
		butClose.setActionCommand(ParadigmTypeEnum.EMPTY+"");
		butClose.addActionListener(this);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(butClose, BorderLayout.EAST);
		topPanel.add(labMessage, BorderLayout.CENTER);
		
		butProjects = new ArrayList<>();
		for(ParadigmTypeEnum p: ParadigmTypeEnum.values()) {
			if(!p.equals(ParadigmTypeEnum.EMPTY)&&(/*!p.equals(ParadigmTypeEnum.CUSTOMIZED)||*/openModelOption.equals(WITH_OPEN_MODEL))) {
				JButton b = new JButton(p.getLabel(), new ImageIcon(ParadigmChooserPane.class.getResource("/com/variamos/gui/maineditor/images/"+p.name().toLowerCase()+".png")));
				b.setActionCommand(p.name());
				butProjects.add(b);
			}
		}
				
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		
		JPanel middlePanel = new JPanel();
		middlePanel.setBackground(Color.WHITE);
		middlePanel.setLayout(new FlowLayout());
		
		for (int i = 0; i < butProjects.size(); i++) {
			butProjects.get(i).addActionListener(this);
			butProjects.get(i).setHorizontalTextPosition(SwingConstants.CENTER);			
			butProjects.get(i).setVerticalTextPosition(SwingConstants.BOTTOM);
			butProjects.get(i).setOpaque(false);
			butProjects.get(i).setContentAreaFilled(false);
			butProjects.get(i).setBorderPainted(false);
			butProjects.get(i).setFocusPainted(false);			
			middlePanel.add(butProjects.get(i));
		}
		
		JScrollPane scrollPane = new JScrollPane(middlePanel);
		//scrollPane.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		addMouseMotionListener(this);
		addMouseListener(this);
		addEscapeListener();
		pack();
		setLocationRelativeTo(owner);		
	}

	public void addEscapeListener() {
	    ActionListener escListener = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
				closeDialog();			
	        }
	    };

	    getRootPane().registerKeyboardAction(escListener,
	            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);

	}
	
	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
		// get location of Window
        int thisX = getLocation().x;
        int thisY = getLocation().y;

        // Determine how much the mouse moved since the initial click
        int xMoved = (thisX + mouseEvent.getX()) - (thisX + initialClick.x);
        int yMoved = (thisY + mouseEvent.getY()) - (thisY + initialClick.y);

        // Move window to this position
        int X = thisX + xMoved;
        int Y = thisY + yMoved;
        setLocation(X, Y);	
    }

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		initialClick = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String command = actionEvent.getActionCommand();
		if(command.equals(ParadigmTypeEnum.EMPTY.name())) {
			closeDialog();
		}else {
			System.out.println(command+" choosed!");
			optionChoosed = ParadigmTypeEnum.valueOf(command);
			dispose();
		}
	}
	
	private void closeDialog() {
		int option = JOptionPane.showConfirmDialog(this,
				"Are you sure to close the dialog without choosing "
				+ "any option and use the default metamodels "
				+ "pre-loaded in VariaMos instead?",
				"Closing Paradigm Choose Dialog",
				JOptionPane.YES_NO_OPTION 
				);
		if(option==JOptionPane.YES_OPTION) {
			optionChoosed = ParadigmTypeEnum.EMPTY;
			dispose();
		}
	}
	
	public ParadigmTypeEnum getOptionChoosed() {
		return optionChoosed;
	}
	
	public static ParadigmTypeEnum showInputDialog(Frame f) {
		//Frame f = new Frame();
		ParadigmTypeEnum optionCh = showInputDialog(f,WITH_OPEN_MODEL);
		//f.dispose();
		return optionCh;
	}
	
	public static ParadigmTypeEnum showInputDialog(String openModelOption) {
		//Frame f = new Frame();
		ParadigmTypeEnum optionCh = showInputDialog(null,openModelOption);
		//f.dispose();
		return optionCh;
	}
	
	public static ParadigmTypeEnum showInputDialog() {
		//Frame f = new Frame();
		ParadigmTypeEnum optionCh = showInputDialog(null,WITH_OPEN_MODEL);
		//f.dispose();
		return optionCh;
	}
	
	public static ParadigmTypeEnum showInputDialog(Frame f,String openModelOption) {
		ParadigmChooserPane pcp = new ParadigmChooserPane(f,openModelOption);
		pcp.setVisible(true);
		return pcp.getOptionChoosed();
	}
	
	public static void main(String[] args) {
		ParadigmTypeEnum optionChoosed = ParadigmChooserPane.showInputDialog();
		System.out.println("La opciÃ³n elegida es: "+optionChoosed);
	}
}
