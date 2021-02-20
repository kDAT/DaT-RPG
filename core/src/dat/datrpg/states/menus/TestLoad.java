package dat.datrpg.states.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import dat.datrpg.MainGame;
import dat.datrpg.assets.Assets;
import dat.datrpg.states.State;

public class TestLoad extends State {

    // Constants
    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 50;

    private int buttonIndex;

    private Stage stage;
    private Skin skin;

    private Table container;
    private ScrollPane scrollPane;
    private Table table;
    private ButtonGroup<TextButton> textButtonGroup;
    private TextField textField;
    private TextButton playButton;
    private Label label;

    public TestLoad(MainGame game, SpriteBatch batch, Assets assets) {
        super(game, batch, assets);

        buttonIndex = 0;

        // Stage and skin
        stage = this.assets.getStage();
        skin = this.assets.getSkin();

        // TextButtons and Table
        table = new Table();
//        table.setFillParent(true);
        label = new Label("Current button: " + buttonIndex, skin);
        textButtonGroup = new ButtonGroup<TextButton>();
        playButton = new TextButton("Play", skin);
        playButton.setDisabled(true);
        playButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        for (int i = 0; i < 6; i++){
            TextButton button = new TextButton("Button " + i, skin);
//            button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    buttonIndex = textButtonGroup.getCheckedIndex();
                }
            });
            textButtonGroup.add(button);
            table.add(button).maxSize(BUTTON_WIDTH, BUTTON_HEIGHT).prefSize(BUTTON_WIDTH, BUTTON_HEIGHT).row();
        }
//        textButtonGroup.uncheckAll();

        table.align(Align.top);
        scrollPane = new ScrollPane(table, skin);
//        scrollPane.setHeight(3*BUTTON_HEIGHT);
        scrollPane.setSize(1.1f*BUTTON_WIDTH, 3*BUTTON_HEIGHT);

        textField = new TextField("Text here", skin);
        textField.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        textField.setTextFieldFilter(new TextField.TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return Character.toString(c).matches("[0-9-]");
            }
        });

        playButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                label.setText(textField.getText() + "  " + buttonIndex);
                playButton.setChecked(false);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        label.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

//        container = new Table();
//        container.setFillParent(true);
//        container.setPosition(500, 500);
//        container.add(scrollPane).row();
//        container.add(playButton).row();
//        container.add(label);
        scrollPane.setPosition(500, 300);
        textField.setPosition(500, 230);
        playButton.setPosition(500, 180);
        label.setPosition(500, 100);

        stage.addActor(scrollPane);
        stage.setScrollFocus(scrollPane);
        stage.addActor(playButton);
        stage.addActor(textField);
        stage.addActor(label);

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
}
