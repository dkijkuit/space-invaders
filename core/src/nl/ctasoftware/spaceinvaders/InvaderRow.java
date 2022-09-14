package nl.ctasoftware.spaceinvaders;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.List;
import java.util.stream.IntStream;

public class InvaderRow {
    private final int rowNumber;
    private final List<Invader> invaders;

    public InvaderRow(int rowNumber, int invaderCount, InvaderType invaderType){
        this.rowNumber = rowNumber;
        this.invaders = IntStream.range(0, invaderCount).mapToObj(index -> new Invader(invaderType)).toList();
    }

    public List<Animation<TextureAtlas.AtlasRegion>> getInvaderAnims() {
        return invaders.stream().map(Invader::getInvaderAnims).toList();
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public List<Invader> getInvaders() {
        return invaders;
    }
}
