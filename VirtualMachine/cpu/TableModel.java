package tp.pr5.mv.cpu;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] columns = {"DIRECCION", "VALOR"};
	private Vector<MemoryCell> table = new Vector<MemoryCell>();
	
	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return this.table.size();
	}

	public Object getValueAt(int row, int column) {
		if (row >= table.size())
			return null;
		else {
			MemoryCell currentCell = table.get(row);
			if (column==0)
				return currentCell.getPos();
			else if (column==1)
				return currentCell.getValue();				
			else
				return null;
		}
	}
	
	public String getColumnName(int column) {
		return columns[column];
	}
	
	private int existInTable(int pos) {
		for (int i=0; i<this.getRowCount(); i++) {
			int cellPos = (int) this.getValueAt(i, 0);
			if (cellPos == pos)
				return i;
		}
		
		return -1;	//Si no encuentra la posicion
	}
	
	private int nextPosToInsert(int pos) {
		for (int i=0; i<this.getRowCount(); i++){
			int cellPos = (int) this.getValueAt(i, 0);
			if (cellPos > pos)
				return i;
		}
		
		return this.getRowCount();
	}
	
	public void addToTable(int val, int pos) {
		MemoryCell cell = new MemoryCell(val, pos);
		
		if (this.getRowCount() == 0)
			this.table.add(cell);
		
		else {
			int p = this.existInTable(cell.getPos());
			
			if (p != -1)
				this.table.set(p, cell);	//Se sustituye la celda nueva por la antigua
			
			else {
				Vector<MemoryCell> newTable = new Vector<MemoryCell>();
				p = this.nextPosToInsert(cell.getPos());
				for (int i=0; i<p; i++)	//Colocamos en la nueva tabla las celdas anteriores a la insertada
					newTable.add(this.table.get(i));
				
				newTable.add(p, cell);	//Introducimos la nueva celda en su posicion en la nueva tabla
					
				for (int i=p; i<this.getRowCount(); i++)
					newTable.add(this.table.get(i));	//Introducimos las demas celdas en la nueva tabla

				this.table = newTable;
			}
		}
		
		this.fireTableDataChanged();
	}
}
