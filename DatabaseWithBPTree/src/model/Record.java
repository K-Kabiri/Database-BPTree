package model;

import java.util.ArrayList;

public class Record {
    private int indexInTable;
    private ArrayList<Cell> columns;

    public Record(int indexInTable) {
        this.indexInTable=indexInTable;
        this.columns = new ArrayList<>();
    }

    // ----------- getter & setter -------------

    public int getIndexInTable() {
        return indexInTable;
    }

    public void setIndexInTable(int indexInTable) {
        this.indexInTable = indexInTable;
    }

    public ArrayList<Cell> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Cell> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Cell cell : this.columns){
            sb.append(cell.getValue());
            sb.append("   ");
        }
        return sb.toString();
    }
}
