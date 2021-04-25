package dat.datrpg.saveload;

import dat.datrpg.entities.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

public class Load {

    public static final String SAVEDIR = "save";

    public static ArrayList<WorldInfo> loadWorldInfo(){
        //
        ArrayList<Object> deserialized;
        ArrayList<WorldInfo> worldInfo = new ArrayList<WorldInfo>();

        try {
            File saveDir = new File(SAVEDIR);
            if (!saveDir.isDirectory()){
                return null;
            }
            if (saveDir.listFiles() == null){
                return null;
            }
            // Loop through the files
            for (File file: saveDir.listFiles()){
                FileInputStream fileIn = new FileInputStream(file);
                GZIPInputStream gzipIn = new GZIPInputStream(fileIn);
                ObjectInputStream in = new ObjectInputStream(gzipIn);
                deserialized = (ArrayList<Object>)in.readObject();
                in.close();
                gzipIn.close();
                fileIn.close();
                worldInfo.add((WorldInfo) deserialized.get(0));
            }
            return worldInfo;
        } catch(IOException | ClassNotFoundException i){
            i.printStackTrace();
        }
        return null;
    }

    public static World loadWorld(int worldIndex){
        //
        ArrayList<Object> deserialized = new ArrayList<Object>();
        World world;

        try {
            File saveDir = new File(SAVEDIR);
            if (!saveDir.isDirectory()){
                return null;
            }
            if (saveDir.listFiles() == null){
                return null;
            }

            File file = saveDir.listFiles()[worldIndex];
            FileInputStream fileIn = new FileInputStream(file);
            GZIPInputStream gzipIn = new GZIPInputStream(fileIn);
            ObjectInputStream in = new ObjectInputStream(gzipIn);
            deserialized = (ArrayList<Object>)in.readObject();
            in.close();
            gzipIn.close();
            fileIn.close();
            return (World) deserialized.get(1);
        } catch(IOException | ClassNotFoundException i){
            i.printStackTrace();
        }
        return null;
    }
}
