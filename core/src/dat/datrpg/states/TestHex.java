package dat.datrpg.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dat.datrpg.MainGame;
import dat.datrpg.assets.Assets;
import dat.datrpg.creation.CreateCity;
import dat.datrpg.entities.City;

import java.util.Random;

public class TestHex extends State {

    private static final int NUM_LEVELS = 4;

    private static final float C = 1.15f;
    private static final float L = 1.5f;

    private int centerQ;
    private int centerR;
    private float centerOffsetX;
    private float centerOffsetY;

    private int midWidth;
    private int midHeight;

    private int mouseHexQ;
    private int mouseHexR;
    private boolean mouseInside;
//    private float mouseOffsetX;
//    private float mouseOffsetY;

    private City city;

    private boolean printDist = false; // Tmp ######################### TODO delete variable

    public TestHex(MainGame game, SpriteBatch batch, Assets assets) {
        super(game, batch, assets);

        city = CreateCity.newCity(70, new Random(56));

        centerQ = 0;
        centerR = 0;
        centerOffsetX = 0;
        centerOffsetY = 0;

        midWidth = Gdx.graphics.getWidth()/2;
        midHeight = Gdx.graphics.getHeight()/2;

        mouseHexQ = 0;
        mouseHexR = 0;
        mouseInside = false;
//        mouseOffsetX = 0;
//        mouseOffsetY = 0;

        Gdx.input.setInputProcessor(assets.getStage());
    }

    @Override
    protected void update(float delta) {
        float mx = Gdx.input.getX();
        float my = Gdx.input.getY();

        // change color
        float xf = Gdx.input.getX() - Gdx.graphics.getWidth() / 2f;
        float yf = Gdx.graphics.getHeight() / 2f - Gdx.input.getY();
        float fq = (xf / Assets.HORIZONTAL_SPACE + yf / (2 * Assets.VERTICAL_SPACE));
        float fr = (-yf / Assets.VERTICAL_SPACE);
        int nq = roundHex(fq, fr)[0];
        int nr = roundHex(fq, fr)[1];
        mouseHexQ = nq;
        mouseHexR = nr;
        int cq = nq + centerQ;
        int cr = nr + centerR;
        if (Math.abs(cq) <= city.mapRadius && Math.abs(cr) <= city.mapRadius && Math.abs(-cq - cr) <= city.mapRadius) {
            mouseInside = true;
            int arrayX = city.mapRadius + cr;
            int arrayY = city.mapRadius + cq - Math.max(0, -cr);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                centerQ = cq;
                centerR = cr;

                // Center Offset
                centerOffsetX = qrToxy(nq, nr)[0] - midWidth + Assets.SPRITE_WIDTH/2f;
                centerOffsetY = qrToxy(nq, nr)[1] - midHeight + Assets.SPRITE_HEIGHT/2f;

                // Mouse offset
//                mouseOffsetX = centerOffsetX;
//                mouseOffsetY = centerOffsetY;
//                System.out.println("x: " + qrToxy(nq, nr)[0] + "\ny: " + qrToxy(nq, nr)[1]);
//                System.out.println("midWidth: " + midWidth + "\nmidHeight: " + midHeight);
//                System.out.println("offX: " + centerOffsetX + "\noffY: " + centerOffsetY);
//                printDist = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.B)) {
                city.mapArray[arrayX][arrayY][0] = 1;
                city.mapArray[arrayX][arrayY][1] = Assets.ID_DIRT_1;
            } else if (Gdx.input.isKeyPressed(Input.Keys.G)) {
                city.mapArray[arrayX][arrayY][0] = 1;
                city.mapArray[arrayX][arrayY][1] = Assets.ID_GRASS_1;
            } else if (Gdx.input.isKeyPressed(Input.Keys.V)) {
                city.mapArray[arrayX][arrayY][0] = 2;
                city.mapArray[arrayX][arrayY][1] = Assets.ID_WALL_VERTICAL;
            } else if (Gdx.input.isKeyPressed(Input.Keys.H)) {
                city.mapArray[arrayX][arrayY][0] = 2;
                city.mapArray[arrayX][arrayY][1] = Assets.ID_WALL_HORIZONTAL;
            } else if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
                city.mapArray[arrayX][arrayY][0] = 2;
                city.mapArray[arrayX][arrayY][1] = Assets.ID_DOOR_VERTICAL;
            } else if (Gdx.input.isKeyPressed(Input.Keys.X)) {
                city.mapArray[arrayX][arrayY][0] = 2;
                city.mapArray[arrayX][arrayY][1] = Assets.ID_DOOR_HORIZONTAL;
            } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                city.mapArray[arrayX][arrayY][0] = 3;
                city.mapArray[arrayX][arrayY][1] = Assets.ID_EMPTY;
            } else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
                dispose();
//                game.dispose();
                Gdx.app.exit();
            }
        } else {
            mouseInside = false;
        }

        // Update Offset
        float dist = (float) Math.sqrt(centerOffsetX * centerOffsetX + centerOffsetY * centerOffsetY);

        //#########
//        if (printDist){
//            System.out.println("dist: " + dist);
//            printDist = false;
//        }
        if (dist > L){  // TODO set the constant
            centerOffsetX /= C;
            centerOffsetY /= C;

            // If map is moving, don't draw the mouse
            mouseInside = false;
//            System.out.println("offX: " + centerOffsetX + "\t\toffY: " + centerOffsetY);
        } else {
            centerOffsetX = 0;
            centerOffsetY = 0;

//            mouseOffsetX = 0;
//            mouseOffsetY = 0;
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();

        int q;
        int r;
        int drawRadius = 2 * city.mapRadius;

        for (int lev = 0; lev < NUM_LEVELS; lev++) {
            //q e r
            q = centerQ;
            r = centerR;

            //aplica o drawning (batch, q, r)
            drawHexBatch(q, r, lev);

            //desenha o fundo
            for (int k = 1; k <= drawRadius; k++) {
                q = centerQ - k;
                r = centerR + k;
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < k; j++) {
                        int[] newqr = addDirection(q, r, i);
                        q = newqr[0];
                        r = newqr[1];
                        //System.out.println(q + "\t" + r);
                        if (Math.abs(q) <= city.mapRadius && Math.abs(r) <= city.mapRadius && Math.abs(-q - r) <= city.mapRadius) {
                            drawHexBatch(q, r, lev);
                        }
                    }
                }
            }
        }

        // Draw the Center Hex
        float hexX = midWidth - Assets.SPRITE_WIDTH/2f;
        float hexY = midHeight - Assets.SPRITE_HEIGHT/2f;
        batch.draw(assets.getTexture(Assets.ID_CENTER), hexX, hexY, Assets.SPRITE_WIDTH, Assets.SPRITE_HEIGHT);

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

        midWidth = width/2;
        midHeight = height/2;
    }

    private void drawHexBatch(int hexq, int hexr, int level){
        int hexX = qrToxy(hexq - centerQ,hexr - centerR)[0] + (int)centerOffsetX;
        int hexY = qrToxy(hexq - centerQ,hexr - centerR)[1] + (int)centerOffsetY;
//        int hexX = midWidth + (hexq - centerQ) * Assets.HORIZONTAL_SPACE + ((hexr - centerR) - 1) * Assets.HORIZONTAL_SPACE / 2;
//        int hexY = midHeight - ((hexr - centerR) + 1) * Assets.VERTICAL_SPACE;  // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  +  -->  -
//        int hexY = midHeight + (hexr - 1) * hVerticalSpace;
        int arrayX = city.mapRadius + hexr;
        int arrayY = city.mapRadius + hexq - Math.max(0, -hexr);

        if (level == 0 && city.mapArray[arrayX][arrayY][0] == 1){
//            System.out.println(city.mapArray[arrayX][arrayY][1] - 1); // #################
            batch.draw(assets.getTexture(city.mapArray[arrayX][arrayY][1] - 1), hexX, hexY, Assets.SPRITE_WIDTH, Assets.SPRITE_HEIGHT);
        }
        else if (level == city.mapArray[arrayX][arrayY][0]){
            batch.draw(assets.getTexture(city.mapArray[arrayX][arrayY][1]), hexX, hexY, Assets.SPRITE_WIDTH, Assets.SPRITE_HEIGHT);
        }
        else if (level == (NUM_LEVELS-1)){
            batch.draw(assets.getTexture(Assets.ID_EMPTY), hexX, hexY, Assets.SPRITE_WIDTH, Assets.SPRITE_HEIGHT);
        }
    }

    public int[] addDirection(int oldq, int oldr, int direction) {
        int[] newqr = new int[]{oldq, oldr};
        switch (direction) {
            case 0:
                newqr[0]++;
                return newqr;
            case 1:
                newqr[0]++;
                newqr[1]--;
                return newqr;
            case 2:
                newqr[1]--;
                return newqr;
            case 3:
                newqr[0]--;
                return newqr;
            case 4:
                newqr[0]--;
                newqr[1]++;
                return newqr;
            case 5:
                newqr[1]++;
                return newqr;
            default:
                return newqr;
        }
    }

    public int[] roundHex(float fx, float fz) {
        float fy = -fx - fz;

        int ix = Math.round(fx);
        int iz = Math.round(fz);
        int iy = Math.round(fy);

        float xDiff = Math.abs(ix - fx);
        float zDiff = Math.abs(iz - fz);
        float yDiff = Math.abs(iy - fy);

        if (xDiff > yDiff && xDiff > zDiff) {
            ix = -iy - iz;
        } else if (yDiff > zDiff) {
            iy = -ix - iz;
        } else {
            iz = -ix - iy;
        }
        return new int[]{ix, iz};
    }

    public int[] qrToxy(int q, int r){
        int hexX = midWidth + q * Assets.HORIZONTAL_SPACE + (r - 1) * Assets.HORIZONTAL_SPACE / 2;
        int hexY = midHeight - (r + 1) * Assets.VERTICAL_SPACE;
        return new int[]{hexX, hexY};
    }
}
