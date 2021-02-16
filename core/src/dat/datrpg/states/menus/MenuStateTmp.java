package dat.datrpg.states.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dat.datrpg.MainGame;
import dat.datrpg.assets.Assets;
import dat.datrpg.states.State;
import dat.datrpg.states.TestHex;

public class MenuStateTmp extends State {

    private Stage stage;
    private Skin skin;
    private TextButton playButton, exitButton;

    public MenuStateTmp(final MainGame game, final SpriteBatch batch, final Assets assets) {
        super(game, batch, assets);

        // Stage and skin
//        stage = new Stage(super.getViewport());
//        skin = new Skin(Gdx.files.internal("skin/skin.json"));
        stage = this.assets.getStage();
        skin = this.assets.getSkin();

        // Creates the buttons
        playButton = new TextButton("Play", skin);
        exitButton = new TextButton("Exit", skin);

        // Sets the Width and Height
        playButton.setSize(200, 50);
        exitButton.setSize(200, 50);

        // Sets the position
        playButton.setPosition(Gdx.graphics.getWidth()/2f - 100, 500);
        exitButton.setPosition(Gdx.graphics.getWidth()/2f - 100, 300);

        // Listeners
//        playButton.addListener(new ClickListener(){
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                dispose();
//                game.setScreen(new TestState(game, batch));
//                return super.touchDown(event, x, y, pointer, button);
//            }
//        });
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dispose();
                System.out.println("Play");
                game.setScreen(new TestLoad(game, batch, assets));
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dispose();
                Gdx.app.exit();
            }
        });

        // Tests visible
        playButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                exitButton.setVisible(false);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                exitButton.setVisible(true);
            }
        });

        // Adds the buttons to the Stage
        stage.addActor(playButton);
        stage.addActor(exitButton);
//        playButton.setVisible(true);

        // Sets the input to the stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void update(float delta) {

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();

        // Updates the stage
        stage.act();
        stage.draw();

        batch.end();
    }

//    @Override
//    public void dispose() {
//        super.dispose();
//
//        // Disposes the stage and the skin
//        stage.dispose();
//        skin.dispose();
//    }
}
