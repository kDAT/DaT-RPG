package dat.datrpg.saveload;

import dat.datrpg.entities.World;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

public class Load {

    public static final String SAVEDIR = "save";
    public static final String EXTENSION = ".datrpg";

    public static ArrayList<WorldInfo> loadWorldInfo(){
        //
        ArrayList<Object> deserialized;
        ArrayList<WorldInfo> worldInfo = new ArrayList<WorldInfo>();

        try {
            File saveDir = new File(SAVEDIR);
            if (!saveDir.isDirectory()){
                return null;
            }
            File[] files = saveDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(EXTENSION);
                }
            });
            if (files == null){
                return null;
            }
            // Loop through the files
            for (File file: files){
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
            File[] files = saveDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(EXTENSION);
                }
            });
            if (files == null){
                return null;
            }

            File file = files[worldIndex];
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
