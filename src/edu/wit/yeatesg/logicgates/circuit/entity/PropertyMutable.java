package edu.wit.yeatesg.logicgates.circuit.entity;

public interface PropertyMutable {

    String getPropertyTableHeader();

    PropertyList getPropertyList();

    void onPropertyChangeViaOperation(String propertyName, String old, String newVal);

    void onPropertyChangeViaTable(String propertyName, String old, String newVal);

    default void onPropertyChangeViaTable(String propertyName, String newVal) {
        onPropertyChangeViaTable(propertyName, getPropertyValue(propertyName), newVal);
    }


    default void onPropertyChangeViaOperation(String propertyName, String newValue) {
        onPropertyChangeViaOperation(propertyName, getPropertyValue(propertyName), newValue);
    }

    String getPropertyValue(String propertyName);

    boolean hasProperty(String propertyName);
}
