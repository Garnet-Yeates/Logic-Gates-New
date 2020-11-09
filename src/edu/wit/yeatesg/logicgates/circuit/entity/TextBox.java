package edu.wit.yeatesg.logicgates.circuit.entity;

import edu.wit.yeatesg.logicgates.circuit.Circuit;
import edu.wit.yeatesg.logicgates.circuit.entity.connectible.transmission.Wire;
import edu.wit.yeatesg.logicgates.datatypes.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TextBox extends Entity {

    private BoundingBox box;
    private String text;
    private CircuitPoint origin; // Left align, vertical center

    /**
     * Entity constructor template:
     * set main fields such as rotation, origin, etc based on the params in the constructor
     * call construct()
     *
     * @param c
     */
    public TextBox(CircuitPoint origin, String text) {
        super(origin.getCircuit());
        this.origin = origin.clone(); // 2 = grid height
    }

    public void constructBBox() {
        Text toFindSize = new Text(text);
        toFindSize.setTextAlignment(TextAlignment.CENTER);
        toFindSize.setFont(new Font("Consolas", 2*c.getScale()*1.55));
        toFindSize.applyCss();
        double lblWidth = toFindSize.getLayoutBounds().getWidth();
        double lblHeight = toFindSize.getLayoutBounds().getHeight();
        box = new BoundingBox(new PanelDrawPoint((int) origin.x, (int) (origin.y - lblHeight / 2), c).toCircuitPoint(),
                new PanelDrawPoint((int) (origin.x + lblWidth), (int) (origin.y + lblHeight / 2), c).toCircuitPoint());
    }

    @Override
    public boolean isSimilar(Entity other) {
        return false;
    }

    @Override
    public Entity getCloned(Circuit onto) {
        return null;
    }

    @Override
    public String toParsableString() {
        return null;
    }

    public void parse(String s, Circuit c) {
        String[] params = s.split(",");
    }

    @Override
    public void construct() {
        constructBBox();
    }

    @Override
    public BoundingBox getBoundingBox() {
        return null;
    }

    @Override
    public CircuitPointList getInvalidInterceptPoints(Entity e) {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public void move(Vector v) {
        origin = origin.getIfModifiedBy(v);
        reconstruct();
    }

    @Override
    public boolean canTheoreticalWireGoHere(Wire.TheoreticalWire theo, PermitList exceptions, boolean strictWithWires) {
        return false;
    }

    @Override
    public void draw(GraphicsContext g, Color col, double opacity) {

    }

    @Override
    public String getPropertyTableHeader() {
        return "Properties For: Text";
    }

    @Override
    public PropertyList getPropertyList() {
        PropertyList list = new PropertyList(this, c);
        list.add(new Property("text", text, text));
        return list;
    }

    @Override
    public void onPropertyChangeViaOperation(String propertyName, String old, String newVal) {
        if (propertyName.equals("text")) {
            this.text = newVal;
        }
        reconstruct();
    }

    @Override
    public void onPropertyChangeViaTable(String propertyName, String old, String newVal) {
        c.new PropertyChangeOperation(this, propertyName, newVal, true).operate();
    }

    @Override
    public void onDeselect() {
        super.onDeselect();
        if (text.equals(""))
            c.new EntityDeleteOperation(this, true).operate();
    }

    @Override
    public String getPropertyValue(String propertyName) {
        if (propertyName.equalsIgnoreCase("text"))
            return text;
        return null;
    }

    @Override
    public boolean hasProperty(String propertyName) {
        return propertyName.equalsIgnoreCase("text");
    }
}
