package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;

/**
 * A support class to define the operations. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-12-03
 */
public class OperationDefinitionDialog extends JDialog implements
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1478873242908074197L;
	private JPanel generalPanel = null;
	private JPanel panel = null;
	private JComboBox<String> operationType;
	private JTextField subActionsList;
	private JComboBox<String> executionType;
	private JTextField operationName;
	private JTextField menuName;
	private JComboBox<String> semanticType;
	private JComboBox<String> semanticAttributes;
	private int width = 380;
	private int height = 500;

	private JComboBox<String> operationTrigger;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public OperationDefinitionDialog(final VariamosGraphEditor editor) {
		super();
		setPreferredSize(new Dimension(width, height));
		setTitle("Operations Definition Dialog");

		panel = new JPanel();
		panel.setLayout(new SpringLayout());
		// setVisible(true);

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addElement("Simulation");
		listModel.addElement("SimulationScenarios");
		listModel.addElement("Add...");
		final JList<String> list = new JList<String>(listModel);

		list.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					String v = null;

					if (index != -1)
						v = list.getModel().getElementAt(index);
					operationName.setText(v);
				}
			}
		});

		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));

		generalPanel = new JPanel();
		generalPanel.setLayout(new BorderLayout());
		generalPanel.add(listScroller, BorderLayout.NORTH);

		JLabel lab = new JLabel("Operation Name: ");
		lab.setToolTipText("Name");
		panel.add(lab);
		operationName = new JTextField("Simulation");
		panel.add(operationName);
		lab = new JLabel("Operation Type: ");
		lab.setToolTipText("Verification, Single Execution or Iterative Execution");
		panel.add(lab);
		operationType = new JComboBox<String>();
		operationType.addItem("Verification (CauCos) - Combinatorial Partial");
		operationType.addItem("Verification (CauCos) - Combinatorial Full");
		operationType.addItem("Verification (CauCos) - Individual");
		operationType.addItem("Single Execution");
		operationType.addItem("Iterative Execution");
		panel.add(operationType);
		lab = new JLabel("Subactions: ");
		lab.setToolTipText("Define the subactions");
		panel.add(lab);
		subActionsList = new JTextField("Not for Verification");
		panel.add(subActionsList);
		lab = new JLabel("Execution Type: ");
		lab.setToolTipText("When the operation is executed");
		panel.add(lab);
		executionType = new JComboBox<String>();
		executionType.addItem("Single Element (Manual)");
		executionType.addItem("Single Element (Automatic) - General");
		executionType
				.addItem("Single Element (Automatic) - Specific Attributes");
		executionType.addItem("Model (Manual)");
		panel.add(executionType);
		lab = new JLabel("Menu (Manual): ");
		lab.setToolTipText("Menu name for mail execution");
		panel.add(lab);
		menuName = new JTextField("New Operation");
		panel.add(menuName);
		lab = new JLabel("Element Semantic Type (Automatic): ");
		lab.setToolTipText("The top semantic type");
		panel.add(lab);
		semanticType = new JComboBox<String>();
		semanticType.addItem("GeneralElement");
		semanticType.addItem("Goal");
		semanticType.addItem("...");
		panel.add(semanticType);
		lab = new JLabel("Element Semantic Attributes (Automatic): ");
		lab.setToolTipText("The attributes ");
		panel.add(lab);
		semanticAttributes = new JComboBox<String>();
		semanticAttributes.addItem("selected");
		semanticAttributes.addItem("notAvailable");
		semanticAttributes.addItem("isRoot");
		semanticAttributes.addItem("...");
		panel.add(semanticAttributes);
		lab = new JLabel("Operation Action: ");
		lab.setToolTipText("When the operation is triggered");
		panel.add(lab);
		operationTrigger = new JComboBox<String>();
		operationTrigger.addItem("After Edition");
		operationTrigger.addItem("On Gain Focus");
		operationTrigger.addItem("On Lost Focus");
		panel.add(operationTrigger);
		SpringUtilities.makeCompactGrid(panel, 8, 2, 4, 4, 4, 4);
		generalPanel.add(panel, BorderLayout.CENTER);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new SpringLayout());

		final JButton btnStart = new JButton();
		btnStart.setText("Close");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if( onAccept == null )
				// if (onStart.onAction()) {

				// }
				revalidate();
				repaint();
			}
		});

		buttonsPanel.add(btnStart);

		SpringUtilities.makeCompactGrid(buttonsPanel, 1, 1, 4, 4, 4, 4);

		generalPanel.add(buttonsPanel, BorderLayout.SOUTH);
		add(generalPanel);

		pack();
		revalidate();
		repaint();
	}

	public void center() {
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// if (evt.getPropertyName().equals("progress"))
		{
			revalidate();
			repaint();
		}

	}
}
