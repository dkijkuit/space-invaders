package nl.ctasoftware.spaceinvaders;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.List;

public class InvaderManager {
    private static InvaderManager instance;

    private List<InvaderRow> invaderRows;
    private int currentRow = 0;
    private int column = 0;
    private float offsetY = 240;

    public static InvaderManager getInstance() {
        if (instance == null) {
            instance = new InvaderManager();
        }

        return instance;
    }

    private InvaderManager() {
        this.invaderRows = new ArrayList<>();
    }

    public int addInvaderRow(int invaderCount, InvaderType invaderType){
        invaderRows.add(new InvaderRow(invaderRows.size(), invaderCount, invaderType));
        return invaderRows.size()-1;
    }

    public int nextRow() {
        return currentRow++;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public List<InvaderRow> getInvaderRows() {
        return invaderRows;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int nextColumn(){
        return column++;
    }

    public int previousColumn(){
        return column--;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public void dispose() {
        this.invaderRows = new ArrayList<>();
    }
}
