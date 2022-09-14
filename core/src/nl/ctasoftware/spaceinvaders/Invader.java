package nl.ctasoftware.spaceinvaders;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import nl.ctasoftware.spaceinvaders.exceptions.UnknownEnemyException;

public class Invader {
    private static final TextureAtlas invaderAtlas = new TextureAtlas("invaders.txt");

    private final TextureAtlas.AtlasRegion[] invaderRegions;
    private final Animation<TextureAtlas.AtlasRegion> invaderAnims;
    private final InvaderType invaderType;

    public Invader(final InvaderType invaderType) {
        this.invaderType = invaderType;
        this.invaderRegions = new TextureAtlas.AtlasRegion[2];

        switch(invaderType){
            case GREEN -> {
                invaderRegions[0] = invaderAtlas.findRegion("1");
                invaderRegions[1] = invaderAtlas.findRegion("2");
            }
            case RED -> {
                invaderRegions[0] = invaderAtlas.findRegion("3");
                invaderRegions[1] = invaderAtlas.findRegion("4");
            }
            case PINK -> {
                invaderRegions[0] = invaderAtlas.findRegion("0");
                invaderRegions[1] = invaderAtlas.findRegion("7");
            }
            default -> {
                throw new UnknownEnemyException("Unknown invader type: " + invaderType.name());
            }
        }

        invaderAnims = new Animation<>(1 / 2f, invaderRegions);
    }

    public InvaderType getInvaderType() {
        return invaderType;
    }

    public Animation<TextureAtlas.AtlasRegion> getInvaderAnims() {
        return invaderAnims;
    }
}
