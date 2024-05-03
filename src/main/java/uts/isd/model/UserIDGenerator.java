package uts.isd.model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


public class UserIDGenerator {
private static int lastUserId = loadLastUserId();

public static synchronized int generateUserId(){
    lastUserId++;
    saveLastUserId(lastUserId);
    return lastUserId;
}
private static int loadLastUserId(){
    try{
        String data = new String(Files.readAllBytes(Paths.get("userId.txt")));
        return Integer.parseInt(data);
    }
    catch(IOException | NumberFormatException e){
        return 0;
    }
}
private static void saveLastUserId(int userId){
    try{
        Files.write(Paths.get("userId.txt"), String.valueOf(userId).getBytes());
    }
    catch(IOException e){
        e.printStackTrace();
    }
}
}
