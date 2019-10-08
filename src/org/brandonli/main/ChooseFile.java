package org.brandonli.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

import java.awt.Font;
import java.io.File;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JEditorPane;
import java.awt.Toolkit;

public class ChooseFile {

	public static File chosenFile;

	private JFrame frmCsvGrapher;

	public static String graphName;
	public static String lineName;

	/**
	 * Launch the application.
	 */
	public static void start(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseFile window = new ChooseFile();
					window.frmCsvGrapher.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChooseFile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		frmCsvGrapher = new JFrame();
		frmCsvGrapher.setIconImage(Toolkit.getDefaultToolkit().getImage(ChooseFile.class.getResource("/resources/icon.png")));
		frmCsvGrapher.setTitle("CSV Grapher");
		frmCsvGrapher.setBounds(100, 100, 450, 330);
		frmCsvGrapher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCsvGrapher.getContentPane().setLayout(null);

		JLabel lblReadContentsInto = new JLabel("Read CSV File to Graph");
		lblReadContentsInto.setBounds(0, 11, 434, 36);
		lblReadContentsInto.setFont(new Font("Cambria", Font.BOLD, 30));
		lblReadContentsInto.setHorizontalAlignment(SwingConstants.CENTER);
		frmCsvGrapher.getContentPane().add(lblReadContentsInto);

		JLabel lblNewLabel = new JLabel(
				"<html><div style='text-align: center;'> Read a CSV File with two columns (x, y) and two titles and turn them into a graph.  </div></html>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 55, 414, 56);
		frmCsvGrapher.getContentPane().add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 50, 414, 2);
		frmCsvGrapher.getContentPane().add(separator);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(143, 165, 281, 20);
		frmCsvGrapher.getContentPane().add(editorPane);

		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBounds(143, 193, 281, 20);
		frmCsvGrapher.getContentPane().add(editorPane_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 115, 414, 2);
		frmCsvGrapher.getContentPane().add(separator_1);

		JLabel lblNewLabel_1 = new JLabel("Choose File:");
		lblNewLabel_1.setBounds(10, 130, 73, 14);
		frmCsvGrapher.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Not Chosen Yet");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(83, 130, 225, 14);
		frmCsvGrapher.getContentPane().add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Choose File");
		btnNewButton.setFocusable(false);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Choose Your CSV File");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jfc.getSelectedFile().isFile()) {
						if ((getFileExtension(jfc.getSelectedFile()).toLowerCase()).equals(".csv")) {
							lblNewLabel_2.setText(jfc.getSelectedFile().getName());
							chosenFile = jfc.getSelectedFile();
						} else {

							lblNewLabel_2.setText("Must end with .csv extension!");

						}
					}
				}

			}
		});
		btnNewButton.setBounds(318, 127, 106, 20);
		frmCsvGrapher.getContentPane().add(btnNewButton);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 155, 414, 2);
		frmCsvGrapher.getContentPane().add(separator_2);

		JButton btnNewButton_1 = new JButton("Read File");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (chosenFile.exists() || chosenFile == null) {

					graphName = editorPane.getText();
					lineName = editorPane_1.getText();
					Graph.start(graphName, lineName, chosenFile);

				}

				else {

					JOptionPane.showMessageDialog(frmCsvGrapher,
							"File is Invalid or not Chosen. Please select a File.");

				}
			}
		});
		btnNewButton_1.setBounds(10, 244, 110, 36);
		frmCsvGrapher.getContentPane().add(btnNewButton_1);

		JButton btnClose = new JButton("Close");
		btnClose.setFocusable(false);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frmCsvGrapher.setVisible(false);
				frmCsvGrapher.dispose();
				System.exit(0);
			}
		});
		btnClose.setBounds(314, 244, 110, 36);
		frmCsvGrapher.getContentPane().add(btnClose);

		JLabel lblNewLabel_3 = new JLabel("Name of Graph:");
		lblNewLabel_3.setBounds(10, 168, 123, 14);
		frmCsvGrapher.getContentPane().add(lblNewLabel_3);

		JLabel lblNameOfLine = new JLabel("Name of Line:");
		lblNameOfLine.setBounds(10, 195, 123, 14);
		frmCsvGrapher.getContentPane().add(lblNameOfLine);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 227, 414, 2);
		frmCsvGrapher.getContentPane().add(separator_3);
	}

	private String getFileExtension(File file) {
		String name = file.getName();
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return ""; // empty extension
		}
		return name.substring(lastIndexOf);
	}
}
