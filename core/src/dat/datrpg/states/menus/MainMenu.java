package dat.datrpg.states.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import dat.datrpg.MainGame;
import dat.datrpg.assets.Assets;
import dat.datrpg.creation.CreateWorld;
import dat.datrpg.entities.Player;
import dat.datrpg.entities.World;
import dat.datrpg.saveload.Save;
import dat.datrpg.saveload.WorldInfo;
import dat.datrpg.states.State;
import dat.datrpg.states.TestHex;

public class MainMenu extends State {

    // Constants
    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 50;
    public static final float BUTTON_PAD = 5.0f;
    public static final float MAIN_PANEL_RATIO = 0.4375f;

    // Main
    private Stage stage;
    private Skin skin;

    // Main menu
    private Table mainTable;
    private TextButton newGameButton, loadButton, optionsButton, exitButton;
    private ButtonGroup<TextButton> mainButtonGroup;

    // New Game
    private Table newGameMainTable, newGameOptionsTable, newGameWorldTable, newGamePLayerTable;
    private TextField newGameNameField, newGameSeedField, newGameRadiusField, newGamePlayerNameField, newGamePlayerRaceField;
    private Label newGameNameLabel, newGameSeedLabel, newGameRadiusLabel, newGamePlayerNameLabel, newGamePlayerRaceLabel;
    private TextButton newGameCreateButton;

    // Load Game

    // Options

    // Exit
    private Table exitMainTable;
    private Label exitLabel;
    private TextButton exitButtonYes, exitButtonNo;

    public MainMenu(final MainGame game, final SpriteBatch batch, final Assets assets) {
        super(game, batch, assets);

        // Stage and skin
        stage = this.assets.getStage();
        skin = this.assets.getSkin();
        Gdx.input.setInputProcessor(stage);

        //
        // ## Main menu ##
        //
        mainTable = new Table();
        mainButtonGroup = new ButtonGroup<TextButton>();

        // New Game
        newGameButton = new TextButton("New Game", skin);
        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                stage.unfocusAll();
                exitMainTable.setVisible(false);
                exitLabel.setVisible(false);
                newGameMainTable.setVisible(true);
                newGameMainTable.setTouchable(Touchable.enabled);
            }
        });
        mainButtonGroup.add(newGameButton);
        mainTable.add(newGameButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(BUTTON_PAD, 0, BUTTON_PAD, 0).row();

        // Load
        loadButton = new TextButton("Load", skin);
        loadButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                stage.unfocusAll();
                exitMainTable.setVisible(false);
                exitLabel.setVisible(false);
                newGameMainTable.setVisible(false);
//                loadMainTable.setTouchable(Touchable.enabled);
            }
        });
        mainButtonGroup.add(loadButton);
        mainTable.add(loadButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(BUTTON_PAD, 0, BUTTON_PAD, 0).row();

        // Options
        optionsButton = new TextButton("Options", skin);
        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                stage.unfocusAll();
                exitMainTable.setVisible(false);
                exitLabel.setVisible(false);
                newGameMainTable.setVisible(false);
//                optionsMainTable.setTouchable(Touchable.enabled);
            }
        });
        mainButtonGroup.add(optionsButton);
        mainTable.add(optionsButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(BUTTON_PAD, 0, BUTTON_PAD, 0).row();

        // Exit
        exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                stage.unfocusAll();
                exitMainTable.setVisible(true);
                exitLabel.setVisible(true);
                newGameMainTable.setTouchable(Touchable.disabled);
//                loadMainTable.setTouchable(Touchable.disabled);
//                optionsMainTable.setTouchable(Touchable.disabled);
            }
        });
        exitButton.setDisabled(true);
//        mainButtonGroup.add(exitButton);
        mainTable.add(exitButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(BUTTON_PAD, 0, BUTTON_PAD, 0);
        // Uncheck all buttons
        mainButtonGroup.uncheckAll();
        // Position of the table
        mainTable.setPosition(Gdx.graphics.getWidth()*MAIN_PANEL_RATIO/2f - mainTable.getWidth()/2f,
                Gdx.graphics.getHeight()/2f - mainTable.getHeight()/2f);
        stage.addActor(mainTable);

        //
        // ## New Game
        //
        newGameMainTable = new Table();
        newGameOptionsTable = new Table();
        newGameWorldTable = new Table();
        newGamePLayerTable = new Table();

        // World table
        // Labels
        newGameNameLabel = new Label("World name", skin);
        newGameSeedLabel = new Label("Seed", skin);
        newGameRadiusLabel = new Label("World radius", skin);
        // TextFields
        newGameNameField = new TextField("",skin);
        newGameNameField.setTextFieldFilter(new TextField.TextFieldFilter() {
            // Accepts all Alphanumeric Characters
            public boolean acceptChar(TextField textField, char c) {
                return Character.toString(c).matches("[a-zA-Z_ 0-9]");
            }
        });
        newGameSeedField = new TextField("", skin);
        newGameSeedField.setTextFieldFilter(new TextField.TextFieldFilter() {
            // Accepts all Alphanumeric Characters
            public boolean acceptChar(TextField textField, char c) {
                return Character.toString(c).matches("[0-9]");
            }
        });
        newGameRadiusField = new TextField("", skin);
        newGameRadiusField.setTextFieldFilter(new TextField.TextFieldFilter() {
            // Accepts all Alphanumeric Characters
            public boolean acceptChar(TextField textField, char c) {
                return Character.toString(c).matches("[0-9]");
            }
        });
        // Add to the table
        newGameWorldTable.add(newGameNameLabel).size(BUTTON_WIDTH, BUTTON_HEIGHT).row();
        newGameWorldTable.add(newGameNameField).size(BUTTON_WIDTH, BUTTON_HEIGHT).row();
        newGameWorldTable.add(newGameSeedLabel).size(BUTTON_WIDTH, BUTTON_HEIGHT).padTop(BUTTON_PAD).row();
        newGameWorldTable.add(newGameSeedField).size(BUTTON_WIDTH, BUTTON_HEIGHT).row();
        newGameWorldTable.add(newGameRadiusLabel).size(BUTTON_WIDTH, BUTTON_HEIGHT).padTop(BUTTON_PAD).row();
        newGameWorldTable.add(newGameRadiusField).size(BUTTON_WIDTH, BUTTON_HEIGHT);

        // Player table
        // Labels
        newGamePlayerNameLabel = new Label("Player name", skin);
        newGamePlayerRaceLabel = new Label("Race", skin);
        // TextFields
        newGamePlayerNameField = new TextField("", skin);
        newGamePlayerNameField.setTextFieldFilter(new TextField.TextFieldFilter() {
            // Accepts all Alphanumeric Characters
            public boolean acceptChar(TextField textField, char c) {
                return Character.toString(c).matches("[a-zA-Z_0-9]");
            }
        });
        newGamePlayerRaceField = new TextField("", skin);
        newGamePlayerRaceField.setTextFieldFilter(new TextField.TextFieldFilter() {
            // Accepts all Alphanumeric Characters
            public boolean acceptChar(TextField textField, char c) {
                return Character.toString(c).matches("[a-zA-Z_0-9]");
            }
        });
        // Add to the table
        newGamePLayerTable.add(newGamePlayerNameLabel).size(BUTTON_WIDTH, BUTTON_HEIGHT).row();
        newGamePLayerTable.add(newGamePlayerNameField).size(BUTTON_WIDTH, BUTTON_HEIGHT).row();
        newGamePLayerTable.add(newGamePlayerRaceLabel).size(BUTTON_WIDTH, BUTTON_HEIGHT).padTop(BUTTON_PAD).row();
        newGamePLayerTable.add(newGamePlayerRaceField).size(BUTTON_WIDTH, BUTTON_HEIGHT);

        // Add both tables
        newGameOptionsTable.add(newGameWorldTable);
        newGameOptionsTable.add(newGamePLayerTable).align(Align.top).padLeft(2*BUTTON_PAD);

        // Creates the Create button
        newGameCreateButton = new TextButton("Create World", skin);
        newGameCreateButton.setDisabled(true);
        newGameCreateButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // TODO Change to a load screen
                Player player = new Player(newGamePlayerNameField.getText(), newGamePlayerRaceField.getText());
                WorldInfo worldInfo = new WorldInfo(newGameNameField.getText(), newGameSeedField.getText(),
                        newGameRadiusField.getText());
                World world = CreateWorld.newWorld(worldInfo, player);
                Save.saveData(worldInfo, world);
                dispose();
                game.setScreen(new TestHex(game, batch, assets, world));
            }
        });

        // Join all
        newGameMainTable.add(newGameOptionsTable).row();
        newGameMainTable.add(newGameCreateButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).padTop(2*BUTTON_PAD);

        // Position the main table
        newGameMainTable.setPosition(Gdx.graphics.getWidth()*(MAIN_PANEL_RATIO + 1)/2f - newGameMainTable.getWidth()/2f,
                Gdx.graphics.getHeight()/2f - newGameMainTable.getHeight()/2f);
        newGameMainTable.setVisible(false);
//        newGameMainTable.setTouchable(Touchable.disabled);
        stage.addActor(newGameMainTable);
        // TODO Finish the new game

        //
        // ## Load
        // TODO

        //
        // ## Options
        // TODO

        //
        // ## Exit
        //
        exitMainTable = new Table();
        // Yes button
        exitButtonYes = new TextButton("Yes", skin);
        exitMainTable.add(exitButtonYes).size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(0, BUTTON_PAD, 0, BUTTON_PAD);
        exitButtonYes.setDisabled(true);
        exitButtonYes.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dispose();
                Gdx.app.exit();
            }
        });

        // No button
        exitButtonNo = new TextButton("No", skin);
        exitMainTable.add(exitButtonNo).size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(0, BUTTON_PAD, 0, BUTTON_PAD);
        exitButtonNo.setDisabled(true);
        exitButtonNo.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                exitMainTable.setVisible(false);
                exitLabel.setVisible(false);
                newGameMainTable.setTouchable(Touchable.enabled);
//                loadMainTable.setTouchable(Touchable.enabled);
//                optionsMainTable.setTouchable(Touchable.enabled);
            }
        });
        // Position of the table
        float exitTableX = Gdx.graphics.getWidth()*(MAIN_PANEL_RATIO + 1)/2f - exitMainTable.getWidth()/2f;
        float exitTableY = Gdx.graphics.getHeight()/2f - BUTTON_HEIGHT*0.5f;
        exitMainTable.setPosition(exitTableX, exitTableY);
        exitMainTable.setVisible(false);
        // Label
        exitLabel = new Label("Do you wish to leave?\n\n\n\n", skin);
        exitLabel.setSize(2.3f*BUTTON_WIDTH, 3*BUTTON_HEIGHT);
        exitLabel.setPosition(Gdx.graphics.getWidth()*(MAIN_PANEL_RATIO + 1)/2f - exitLabel.getWidth()/2f,
                Gdx.graphics.getHeight()/2f - exitLabel.getHeight()/2f);
        exitLabel.setAlignment(Align.center);
        exitLabel.setVisible(false);
        // Add to stage
        stage.addActor(exitLabel);
        stage.addActor(exitMainTable);
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

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        // TODO reset the position of all the tables
    }
}
