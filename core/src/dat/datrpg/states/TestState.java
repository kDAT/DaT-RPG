package dat.datrpg.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dat.datrpg.MainGame;
import dat.datrpg.assets.Assets;
import dat.datrpg.states.menus.MenuStateTmp;

import java.util.Random;

public class TestState extends State {

    private Stage stage;
    private Skin skin;
    private TextButton textButton;

    private TextureAtlas textureAtlas;
    private NinePatch ninePatch;

    private float color;
    private String text = "You clicked me";

    public TestState(final MainGame game, final SpriteBatch batch, final Assets assets) {
        super(game, batch, assets);

        final Random random = new Random(123);

        stage = new Stage(super.getViewport());

        skin = new Skin(Gdx.files.internal("skin/skin.json"));
        textButton = new TextButton("Test Button", skin);

        color = 0f;

        textButton.setWidth(200);
        textButton.setPosition(100, 200);
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                textButton.setText(String.valueOf(random.nextInt(3)));
//                stage.clear();
                dispose();
                game.setScreen(new MenuStateTmp(game, batch, assets));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                textButton.setText("Hover");
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                textButton.setText("Test");
            }
        });

//        textButton.addListener(new InputListener(){
//            @Override
//            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
//                super.enter(event, x, y, pointer, fromActor);
//                textButton.setText("Hover");
//            }
//        });

//        textButton.addListener(new ClickListener(){
//            @Override
//            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
//                super.exit(event, x, y, pointer, toActor);
//                textButton.setText("Test Button");
//            }
//
//        });

//        textButton.addListener(new InputListener(){
//            @Override
//            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
//                super.exit(event, x, y, pointer, toActor);
//                textButton.setText("Test Button0");
//            }
//
//        });

        stage.addActor(textButton);

        Gdx.input.setInputProcessor(stage);
//        InputMultiplexer im = new InputMultiplexer(stage, this);
//        Gdx.input.setInputProcessor(im);

        textureAtlas = new TextureAtlas("skin/skin.atlas");
        ninePatch = textureAtlas.createPatch("button_up");

//        House house = CreateHouse.newHouse(game, random, 0, 0, (short) 0);
    }

    @Override
    protected void update(float delta) {
        textButton.setPosition(400, 200);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        stage.act();
        stage.draw();

        ninePatch.draw(batch, 400, 400, 200, 50);

        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();

        skin.dispose();
        stage.dispose();

        textureAtlas.dispose();
    }
}
