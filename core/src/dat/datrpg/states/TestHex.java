package dat.datrpg.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dat.datrpg.MainGame;
import dat.datrpg.assets.Assets;
import dat.datrpg.entities.Hex;
import dat.datrpg.entities.Player;
import dat.datrpg.entities.World;
import dat.datrpg.saveload.Save;
import dat.datrpg.states.menus.MainMenu;
import dat.datrpg.utils.HexTools;

public class TestHex extends State {

    private static final float C = 1.15f;
    private static final float L = 1.5f;

    private static final int JUMP_DIST_MIN = 3;

    private int hexQ;
    private int hexR;
    private int centerQ;
    private int centerR;
    private float centerOffsetX;
    private float centerOffsetY;

    private int midWidth;
    private int midHeight;

    private int mouseHexQ;
    private int mouseHexR;
    private boolean mouseInside;

    private World world;
    private Player player;
    private Hex hex;

    public TestHex(MainGame game, SpriteBatch batch, Assets assets, World world) {
        super(game, batch, assets);

        this.world = world;

        player = world.players.get(0);

        hexQ = player.getHexQ();
        hexR = player.getHexR();
        int arrayX = world.worldRadius + hexR;
        int arrayY = world.worldRadius + hexQ - Math.max(0, -hexR);
        hex = world.hexes.get(world.worldArray[arrayX][arrayY]);

        centerQ = player.getCenterQ();
        centerR = player.getCenterR();
        centerOffsetX = 0;
        centerOffsetY = 0;

        midWidth = Gdx.graphics.getWidth() / 2;
        midHeight = Gdx.graphics.getHeight() / 2;

        mouseHexQ = 0;
        mouseHexR = 0;
        mouseInside = false;

        Gdx.input.setInputProcessor(assets.getStage());
    }

    @Override
    protected void update(float delta) {
        // TMP
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            for (byte i = 0; i < 6; i++) {
                int jq = hex.jumpHexArray[i][0];
                int jr = hex.jumpHexArray[i][1];
                int jumpDist = (Math.abs(centerQ - jq)
                        + Math.abs(centerQ + centerR - jq - jr)
                        + Math.abs(centerR - jr)) / 2;
                if (jumpDist <= JUMP_DIST_MIN) {
                    int[] newqr = HexTools.addDirection(hexQ, hexR, i);
                    // How far from the center
                    int hexDist = (Math.abs(newqr[0])
                            + Math.abs(newqr[0] + newqr[1])
                            + Math.abs(newqr[1])) / 2;
                    if (hexDist <= world.worldRadius) {
                        hexQ = newqr[0];
                        hexR = newqr[1];
                        int arrayX = world.worldRadius + hexR;
                        int arrayY = world.worldRadius + hexQ - Math.max(0, -hexR);
                        hex = world.hexes.get(world.worldArray[arrayX][arrayY]);
                        int ni = (i + 3) % 6;
                        centerQ = hex.jumpHexArray[ni][0];
                        centerR = hex.jumpHexArray[ni][1];
                        player.updateCoords(hexQ, hexR, centerQ, centerR);
                        player.setRotation(i);
                    }
                    break;
                }
            }
        }

        float xf = Gdx.input.getX() - Gdx.graphics.getWidth() / 2f;
        float yf = Gdx.graphics.getHeight() / 2f - Gdx.input.getY();
        float fq = (xf / Assets.HORIZONTAL_SPACE + yf / (2 * Assets.VERTICAL_SPACE));
        float fr = (-yf / Assets.VERTICAL_SPACE);
        int nq = HexTools.roundHex(fq, fr)[0];
        int nr = HexTools.roundHex(fq, fr)[1];
        mouseHexQ = nq;
        mouseHexR = nr;
        int cq = nq + centerQ;
        int cr = nr + centerR;
        if (Math.abs(cq) <= hex.mapRadius && Math.abs(cr) <= hex.mapRadius && Math.abs(-cq - cr) <= hex.mapRadius) {
            mouseInside = true;
            int arrayX = hex.mapRadius + cr;
            int arrayY = hex.mapRadius + cq - Math.max(0, -cr);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                centerQ = cq;
                centerR = cr;
                // TODO Print
                System.out.println("\nq: " + centerQ + "\nr: " + centerR);
                // Center Offset
                centerOffsetX = qrToxy(nq, nr)[0] - midWidth + Assets.SPRITE_WIDTH / 2f;
                centerOffsetY = qrToxy(nq, nr)[1] - midHeight + Assets.SPRITE_HEIGHT / 2f;
                //
                player.updateCoords(hexQ, hexR, centerQ, centerR);
            } else if (Gdx.input.isKeyPressed(Input.Keys.B)) {
                hex.mapArray[arrayX][arrayY][0] = 1;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_DIRT_1;
            } else if (Gdx.input.isKeyPressed(Input.Keys.G)) {
                hex.mapArray[arrayX][arrayY][0] = 1;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_GRASS_1;
            } else if (Gdx.input.isKeyPressed(Input.Keys.V)) {
                hex.mapArray[arrayX][arrayY][0] = 2;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_WALL_VERTICAL;
            } else if (Gdx.input.isKeyPressed(Input.Keys.H)) {
                hex.mapArray[arrayX][arrayY][0] = 2;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_WALL_HORIZONTAL;
            } else if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
                hex.mapArray[arrayX][arrayY][0] = 2;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_DOOR_VERTICAL;
            } else if (Gdx.input.isKeyPressed(Input.Keys.X)) {
                hex.mapArray[arrayX][arrayY][0] = 2;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_DOOR_HORIZONTAL;
            } else if (Gdx.input.isKeyPressed(Input.Keys.F)) {
                hex.mapArray[arrayX][arrayY][0] = 1;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_FLOOR_WOOD_1;
            } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                hex.mapArray[arrayX][arrayY][0] = 3;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_EMPTY;
            } else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                Save.saveData(world.worldInfo, world);
                dispose();
                game.setScreen(new MainMenu(game, batch, assets));
            }
        } else {
            mouseInside = false;
        }

        // Update Offset
        float dist = (float) Math.sqrt(centerOffsetX * centerOffsetX + centerOffsetY * centerOffsetY);

        if (dist > L) {
            centerOffsetX /= C;
            centerOffsetY /= C;
            // If map is moving, don't draw the mouse
            mouseInside = false;
        } else {
            centerOffsetX = 0;
            centerOffsetY = 0;
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();

        int q;
        int r;
        int drawRadius = 2 * hex.mapRadius;
//        int drawRadius = 30;

        for (int lev = 0; lev < Assets.NUM_LEVELS; lev++) {
            q = centerQ;
            r = centerR;

            drawHexBatch(q, r, lev);

            for (int k = 1; k <= drawRadius; k++) {
                q = centerQ - k;
                r = centerR + k;
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < k; j++) {
                        int[] newqr = HexTools.addDirection(q, r, i);
                        q = newqr[0];
                        r = newqr[1];
                        if (Math.abs(q) <= hex.mapRadius && Math.abs(r) <= hex.mapRadius && Math.abs(-q - r) <= hex.mapRadius) {
                            drawHexBatch(q, r, lev);
                        }
                    }
                }
            }
        }

        // Draw the Center Hex
        float hexX = midWidth - Assets.SPRITE_WIDTH / 2f;
        float hexY = midHeight - Assets.SPRITE_HEIGHT / 2f;
        batch.setColor(assets.getColor(player.getColor()));
        batch.draw(assets.getTexture(player.getTexture_id()), hexX, hexY, Assets.SPRITE_WIDTH, Assets.SPRITE_HEIGHT,
                0, 0, Assets.SPRITE_WIDTH, Assets.SPRITE_HEIGHT, player.isFlipX(), player.isFlipY());
        batch.setColor(Color.WHITE);

        // Draw the Mouse Hex
        hexX = qrToxy(mouseHexQ, mouseHexR)[0]; // - mouseOffsetX + centerOffsetX;
        hexY = qrToxy(mouseHexQ, mouseHexR)[1]; // - mouseOffsetY + centerOffsetY;
        if (mouseInside) {
            batch.draw(assets.getTexture(Assets.ID_CLICK), hexX, hexY, Assets.SPRITE_WIDTH, Assets.SPRITE_HEIGHT);
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        midWidth = width / 2;
        midHeight = height / 2;
    }

    private void drawHexBatch(int hexq, int hexr, int level) {
        int hexX = qrToxy(hexq - centerQ, hexr - centerR)[0] + (int) centerOffsetX;
        int hexY = qrToxy(hexq - centerQ, hexr - centerR)[1] + (int) centerOffsetY;
        int arrayX = hex.mapRadius + hexr;
        int arrayY = hex.mapRadius + hexq - Math.max(0, -hexr);

        if (level == 0 && assets.getLevel(hex.mapArray[arrayX][arrayY][1]) == 1) {
            batch.draw(assets.getTexture(hex.mapArray[arrayX][arrayY][1] - 1), hexX, hexY, Assets.SPRITE_WIDTH, Assets.SPRITE_HEIGHT);
        } else if (level == assets.getLevel(hex.mapArray[arrayX][arrayY][1])) {
            batch.draw(assets.getTexture(hex.mapArray[arrayX][arrayY][1]), hexX, hexY, Assets.SPRITE_WIDTH, Assets.SPRITE_HEIGHT);
        } else if (level == (Assets.NUM_LEVELS - 1)) {
            batch.draw(assets.getTexture(Assets.ID_EMPTY), hexX, hexY, Assets.SPRITE_WIDTH, Assets.SPRITE_HEIGHT);
        }
    }


    public int[] qrToxy(int q, int r) {
        int hexX = midWidth + q * Assets.HORIZONTAL_SPACE + (r - 1) * Assets.HORIZONTAL_SPACE / 2;
        int hexY = midHeight - (r + 1) * Assets.VERTICAL_SPACE;
        return new int[]{hexX, hexY};
    }
}
