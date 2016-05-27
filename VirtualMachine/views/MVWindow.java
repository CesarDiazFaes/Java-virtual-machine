package tp.pr5.mv.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.controllers.SwingController;
import tp.pr5.mv.cpu.TableModel;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.observers.*;

public class MVWindow extends JFrame implements StackObserver, MemoryObserver, CPUObserver {
	
	private static final long serialVersionUID = 1L;
	private SwingController controller;
	
	private Container mainPanel;
	private JButton bStep;
	private JButton bRun;
	private JButton bExit;
	private JButton bPush;
	private JButton bPop;
	private JButton bWrite;
	private JButton bPause;
	private JLabel stopMachine;
	private JLabel lbl_numInstructions;
	private JTextArea program;
	private JTextArea inText;
	private JTextArea outText;
	private JList<Object> list;
	private JTable table;
	private JTextField txtValue;
	private JTextField txtPos;
	private JTextField txtValMem;
	private JCheckBox chk_mem;
	private JCheckBox chk_stack;
	
	private DefaultListModel<Object> stackModel;
	private TableModel memoryModel;
	private int insCounter = 0;	//Cuenta el numero de instrucciones ejecutadas.
	
	public MVWindow(SwingController cntrl) {
		super("Maquina virtual de TP");
		this.controller = cntrl;
		
		this.mainPanel = this.getContentPane();
		this.mainPanel.setLayout(new BorderLayout());
		
		//--BOTONES--
		JPanel p_buttons = new JPanel();
		p_buttons.setBorder(new TitledBorder("Acciones"));
		this.mainPanel.add(p_buttons, BorderLayout.NORTH);
		bStep = new JButton("STEP", new ImageIcon("icons/step.png"));
		p_buttons.add(bStep);
		bStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UnSelectStateBar();
					controller.executeProgram();
					
				} catch (MyException ex) {
					JOptionPane.showMessageDialog(null,
							ex.toString(),
							"Error de ejecución",
							JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null,
							ex.toString(),
							"Error", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
			}
		});

		bRun = new JButton("RUN", new ImageIcon("icons/run.png"));
		p_buttons.add(bRun);
		bRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//UnSelectStateBar();
					controller.executeRun();
						
				} catch (MyException ex) {
					JOptionPane.showMessageDialog(null,
							ex.toString(),
							"Error de ejecución",
							JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null,
							ex.toString(),
							"Error", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				} 
			}
		});
		
		bPause = new JButton("PAUSE", new ImageIcon("icons/pause.png"));
		p_buttons.add(bPause);
		bPause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//No implementado(Uso de hebras)
			}
		});
		
		bExit = new JButton("EXIT", new ImageIcon("icons/exit.png"));
		p_buttons.add(bExit);
		bExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//--PROGRAMA--
		JPanel p_program = new JPanel();
		p_program.setBorder(new TitledBorder("Programa"));
		this.mainPanel.add(p_program, BorderLayout.WEST);
		this.program = new JTextArea(31, 18);
		this.program.setEditable(false);
		JScrollPane scr_program = new JScrollPane();
		scr_program.setViewportView(this.program);
		p_program.add(scr_program);
		
		//--PILA--
		JPanel p_stackmem = new JPanel();
		p_stackmem.setLayout(new GridLayout());
		JPanel p_moreComponents = new JPanel();
		p_moreComponents.setLayout(new GridLayout(3, 1));
		this.mainPanel.add(p_moreComponents, BorderLayout.CENTER);
		JPanel p_stack = new JPanel();
		JPanel p_upStack = new JPanel();
		JPanel p_downStack = new JPanel();
		p_downStack.setLayout(new FlowLayout());
		p_stack.setBorder(new TitledBorder("Pila de operandos"));
		p_stack.setLayout(new GridLayout(2, 1));
		p_upStack.setLayout(new GridLayout());
		p_stack.add(p_upStack);
		p_stack.add(p_downStack);
		this.stackModel = new DefaultListModel<Object>();
		list = new JList<Object>();
		list.setModel(stackModel);
		JScrollPane scr_list = new JScrollPane();
		scr_list.setViewportView(list);
		JLabel lblValue = new JLabel("Valor:");
		txtValue = new JTextField(4);
		bPush = new JButton("Push");
		bPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int num = Integer.parseInt(txtValue.getText());
					controller.executePush(num);
					
				} catch (MyException ex) {
					JOptionPane.showMessageDialog(null,
						ex.toString(),
						"Error de ejecución",
						JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null,
							ex.toString(),
							"Error", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
							
				}  catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
						"Sólo se aceptan enteros para la instrucción PUSH",
						"Error",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		bPop = new JButton("Pop");
		bPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.executePop();
						
				} catch (MyException ex) {
					JOptionPane.showMessageDialog(null,
						ex.toString(),
						"Error de ejecución",
						JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null,
							ex.toString(),
							"Error", JOptionPane.ERROR_MESSAGE);
					System.exit(1);				
				}
			}
		});
		
		p_upStack.add(scr_list);
		p_downStack.add(lblValue);
		p_downStack.add(txtValue);
		p_downStack.add(bPush);
		p_downStack.add(bPop);
		p_stackmem.add(p_stack);
		
		//--MEMORIA--
		JPanel p_mem = new JPanel();
		p_mem.setBorder(new TitledBorder("Memoria de la máquina"));
		JPanel p_upmem = new JPanel();
		JPanel p_downmem = new JPanel();
		p_mem.setLayout(new GridLayout(2, 1));
		p_upmem.setLayout(new GridLayout());
		p_downmem.setLayout(new FlowLayout());
		table = new JTable();
		this.memoryModel = new TableModel();
		table.setModel(this.memoryModel);
		JScrollPane scr_mem = new JScrollPane();
		scr_mem.setViewportView(table);
		p_upmem.add(scr_mem);
		JLabel lblPos = new JLabel("Pos:");
		p_downmem.add(lblPos);
		txtPos = new JTextField(4);
		p_downmem.add(txtPos);
		JLabel lblVal = new JLabel("Val:");
		p_downmem.add(lblVal);
		txtValMem = new JTextField(4);
		p_downmem.add(txtValMem);
		bWrite = new JButton("Write");
		p_downmem.add(bWrite);
		bWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int p = Integer.parseInt(txtPos.getText());
					int v = Integer.parseInt(txtValMem.getText());
					controller.executeWrite(v, p);
					
				} catch (MyException ex) {
					JOptionPane.showMessageDialog(null,
						ex.toString(),
						"Error de ejecución",
						JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null,
							ex.toString(),
							"Error", JOptionPane.ERROR_MESSAGE);
					System.exit(1);	
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
						"Sólo se aceptan enteros para la instrucción WRITE",
						"Mensaje de Error",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		p_mem.add(p_upmem);
		p_mem.add(p_downmem);
		p_stackmem.add(p_mem);
		p_moreComponents.add(p_stackmem);
		
		//--ENTRADA PROGRAMA--
		JPanel p_inprog = new JPanel();
		p_inprog.setBorder(new TitledBorder("Entrada de programa-p"));
		p_inprog.setLayout(new GridLayout());
		JScrollPane scr_inp = new JScrollPane();
		inText = new JTextArea();
		inText.setEditable(false);
		scr_inp.setViewportView(inText);
		p_inprog.add(scr_inp);
		p_moreComponents.add(p_inprog);
		
		//--SALIDA PROGRAMA--
		JPanel p_outprog = new JPanel();
		p_outprog.setBorder(new TitledBorder("Salida de programa-p"));
		p_outprog.setLayout(new GridLayout());
		outText = new JTextArea();
		outText.setEditable(false);
		JScrollPane scr_outp = new JScrollPane();	
		scr_outp.setViewportView(outText);
		p_outprog.add(scr_outp);
		p_moreComponents.add(p_outprog);
		
		//BARRA DE ESTADO
		JPanel p_statementBar = new JPanel();
		p_statementBar.setLayout(new FlowLayout());
		this.mainPanel.add(p_statementBar, BorderLayout.SOUTH);
		stopMachine = new JLabel("Máquina parada");
		stopMachine.setVisible(false);
		stopMachine.setForeground(Color.RED);
		p_statementBar.add(stopMachine);
		JLabel lblIns = new JLabel("Num. Instrucciones ejecutadas: ");
		p_statementBar.add(lblIns);
		lbl_numInstructions = new JLabel("0");
		p_statementBar.add(lbl_numInstructions);
		chk_mem = new JCheckBox("Memoria modificada");
		p_statementBar.add(chk_mem);
		chk_stack = new JCheckBox("Pila modificada");
		p_statementBar.add(chk_stack);
		
		this.setBounds(80, 80, 800, 660);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void UnSelectStateBar() {
		this.chk_mem.setSelected(false);
		this.chk_stack.setSelected(false);
	}
	
	private void disableButtons() {
		this.bStep.setEnabled(false);
		this.bPush.setEnabled(false);
		this.bWrite.setEnabled(false);
		this.bRun.setEnabled(false);
		this.bPop.setEnabled(false);
		this.chk_mem.setEnabled(false);
		this.chk_stack.setEnabled(false);
	}

	public void onPush(int n) {
		DefaultListModel<Object> newModel = new DefaultListModel<Object>();
		newModel.addElement(n);
		
		if (this.stackModel.getSize() > 0) {
			for (int i=0; i<this.stackModel.getSize(); i++)
				newModel.addElement(this.stackModel.elementAt(i));
		}

		this.stackModel = newModel;
		this.list.setModel(this.stackModel);
		this.chk_stack.setSelected(true);
	}

	public void onPop() {
		this.stackModel.removeElementAt(0);
		this.chk_stack.setSelected(true);
	}
	
	public void onWrite(int val, int pos) {	
		this.memoryModel.addToTable(val, pos);
		System.out.println();
		this.chk_mem.setSelected(true);
	}	

	public void newProgram(String prog) {
		this.program.setText(this.controller.showProgram());
	}
	
	public void programState(String prog) {
		this.program.setText(prog);
	}

	public void onHalt() {
		this.disableButtons();
		this.stopMachine.setVisible(true);
	}

	public void updateInStrat(String in) {
		this.inText.setText(in);	
	}

	public void updateOutStrat(String out) {
		this.outText.setText(out);
	}
	
	public void onStep() {
		this.insCounter++;
		Integer counter = this.insCounter;
		String s = counter.toString();
		this.lbl_numInstructions.setText(s);
	}
	
	public void currentInstruction(Instruction ins) {

	}
}
