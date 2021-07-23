package gui;

import java.io.*;

public class ScriptPython {

    public static void execVisualization(){
        try{
            String s = null;
            Process p = Runtime.getRuntime().exec("python /Users/pw-home/IdeaProjects/Optimierungsalgorithmen/src/gui/visualization.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while((s = in.readLine()) != null){
                System.out.println(s);
            }
        } catch (IOException ie){
            ie.printStackTrace();
        }
    }
}
