package dat.datrpg.saveload;

import dat.datrpg.entities.World;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Save {

    public static final String SAVEDIR = "save";
    public static final String EXTENSION = ".datrpg";

    public static boolean saveData(WorldInfo info, World world){
        // Adds the data
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(info);
        data.add(world);

        try {
            File saveDir = new File(SAVEDIR);
            if (!saveDir.isDirectory()){
                if (!saveDir.mkdir()){
                    return false;
                }
            }
            File file = new File(SAVEDIR + "\\" + info.getWorldName() + EXTENSION);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
            return true;
        } catch (IOException i) {
            i.printStackTrace();
        }
        return false;
    }
}
