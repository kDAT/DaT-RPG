package dat.datrpg.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dat.datrpg.MainGame;
import dat.datrpg.assets.Assets;

public abstract class State implements Screen {

    protected final MainGame game;
    protected final SpriteBatch batch;
    protected final Assets assets;

    protected final OrthographicCamera camera;
    protected final Viewport viewport;

    public State(MainGame game, SpriteBatch batch, Assets assets) {
        this.game = game;
        this.batch = batch;

        camera = new OrthographicCamera();
        viewport = new ScalingViewport(Scaling.none, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply(true);

        assets.setStage(viewport);
        this.assets = assets;
    }

    protected abstract void update(float delta);

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        // clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.setWorldSize(width, height);
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public Viewport getViewport() {
        return viewport;
    }
}
