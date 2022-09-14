package nl.ctasoftware.spaceinvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

public class SpaceInvadersGame extends Game {
    private static final int WORLD_WIDTH = 800;
    private static final int WORLD_HEIGHT = 600;
    public static final int INVADER_COUNT = 11;
    public static final int CELL_STEP_SIZE = 2;
    private final int invaderSpacingX = 46;
    private final int invaderSpacingY = 46;
    private final int GRID_CELL_SIZE = 44;
    private int GRID_WIDTH;
    private int TOTAL_INVADER_COLUMNS;
    private int direction = 1;
    private float centerX;
    private float centerY;

    OrthographicCamera camera;
    Viewport viewport;
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;

    private float elapsedTime = 0;
    private float lastSecond = 0;

    private InvaderManager invaderManager;

    @Override
    public void create() {
        GRID_WIDTH = (WORLD_WIDTH / getColumnStepSize()) - 2;
        TOTAL_INVADER_COLUMNS = INVADER_COUNT * CELL_STEP_SIZE;

        invaderManager = InvaderManager.getInstance();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(centerX, centerY, 0);
        camera.update();

        //Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        centerX = viewport.getWorldWidth() / 2f;
        centerY = viewport.getWorldHeight() / 2f;

        invaderManager.addInvaderRow(INVADER_COUNT, InvaderType.PINK);
        invaderManager.addInvaderRow(INVADER_COUNT, InvaderType.GREEN);
        invaderManager.addInvaderRow(INVADER_COUNT, InvaderType.GREEN);
        invaderManager.addInvaderRow(INVADER_COUNT, InvaderType.RED);
        invaderManager.addInvaderRow(INVADER_COUNT, InvaderType.RED);

        invaderManager.setColumn((GRID_WIDTH / 2) - INVADER_COUNT);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        float deltaTime = Gdx.graphics.getDeltaTime();
        elapsedTime += deltaTime;
        lastSecond += deltaTime;

        handleDirectionAndMovement();
        drawInvaders();

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.line(centerX, centerY - 100, centerX, centerY + 100);
        shapeRenderer.line(centerX - 200, centerY, centerX + 200, centerY);
        shapeRenderer.line(0, 0, WORLD_WIDTH, 0);
        shapeRenderer.line(0, WORLD_HEIGHT - 1, WORLD_WIDTH, WORLD_HEIGHT - 1);
        shapeRenderer.line(0, 0, 0, WORLD_HEIGHT);
        shapeRenderer.line(WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT);
        shapeRenderer.end();
    }

    private void drawInvaders() {
        var offsetX = (invaderManager.getColumn() * (GRID_CELL_SIZE / 2)) + 32;
        invaderManager.getInvaderRows().forEach(invaderRow -> {
            var yPos = WORLD_HEIGHT - GRID_CELL_SIZE - (invaderRow.getRowNumber() * GRID_CELL_SIZE) - (invaderManager.getCurrentRow() * GRID_CELL_SIZE);
            List<Animation<TextureAtlas.AtlasRegion>> invaderAnims = invaderRow.getInvaderAnims();
            for (int i = 0; i< invaderAnims.size(); i++){
                var xPos = offsetX + (i * GRID_CELL_SIZE);
                batch.draw(invaderAnims.get(i).getKeyFrame(elapsedTime, true), xPos, yPos);
            }
        });
    }

    private void handleDirectionAndMovement() {
        if (lastSecond > 1) {
            if (direction == 1) {
                int outerColumn = invaderManager.nextColumn() + TOTAL_INVADER_COLUMNS + 1;
                if (outerColumn > GRID_WIDTH) {
                    direction = direction * -1;
                    invaderManager.setColumn(GRID_WIDTH - TOTAL_INVADER_COLUMNS);
                    invaderManager.nextRow();
                }
            } else {
                int outerColumn = invaderManager.previousColumn();
                if (outerColumn <= 0) {
                    direction = direction * -1;
                    invaderManager.setColumn(0);
                    invaderManager.nextRow();
                }
            }
            lastSecond = 0;
        }
    }

    private int getColumnStepSize() {
       return GRID_CELL_SIZE / CELL_STEP_SIZE;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

    }

    @Override
    public void dispose() {
        batch.dispose();
        invaderManager.dispose();
    }
}
