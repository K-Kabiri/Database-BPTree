package database.model;

public class Cell<E> {
    private DataType dataType;
    private E value;
    private String columnName;

    public Cell(DataType dataType, E value, String columnName) {
        this.dataType = dataType;
        this.value = value;
        this.columnName = columnName;
    }

    // ----------- getter & setter -------------

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
