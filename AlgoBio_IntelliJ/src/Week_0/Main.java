package Week_0;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    /* Beim packen der Jars können wir hier die Aufgabennummer angeben und dann in der main methode mit
     * der Fallunterscheidung die jeweiligen passenden Algorithmen ausführen. */
    static int packagingIndex = 1;

    public static void main(String[] args) {

        ArrayList<Integer> inputSequence = new ArrayList<Integer>();   // enthält die eingelesenen Zahlen
        SequenceAlgorithm currentAlgorithm = null;   // führt je nach Flag die richtige Implementation aus
        String outputPath = null;   // Pfad zur output Datei
        List<int[]> results = null; // Liste der gefundenen MSS




        Options options = new Options();

        Option input = new Option("i", true, "input file");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", true, "output file");
        output.setRequired(true);
        options.addOption(output);

        options.addOption("n", false, "naive algorithm");
        options.addOption("l", false, "linear algorithm");
        options.addOption("d", false, "dynamic algorithm");
        options.addOption("dc", false, "divide and conquer algorithm");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Main", options);
            System.exit(1);
            return;
        }

        if(!(cmd.hasOption("n")|cmd.hasOption("l")|cmd.hasOption("d")|cmd.hasOption("dc"))){
            System.out.println("Choose an algorithm flag ([-n|-l|-d|-dc])");
            formatter.printHelp("Main", options);
            System.exit(1);
            return;
        }



        outputPath = cmd.getOptionValue("o");


        try(BufferedReader br = new BufferedReader(new FileReader(cmd.getOptionValue("i")))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                if(!line.startsWith("#")) {
                    for (String s : line.split("\t")) {
                        inputSequence.add(Integer.parseInt(s));
                    }
                }
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(cmd.hasOption("n")){
            currentAlgorithm = new Naive();
        }else if(cmd.hasOption("dc")){
            currentAlgorithm = new DivideAndConquer();
        }else if(cmd.hasOption("d")){
            currentAlgorithm = new Dynamic();
        }else if(cmd.hasOption("l")){
            currentAlgorithm = new Linear();
        }


        Integer[] inputArray  = inputSequence.toArray(new Integer[inputSequence.size()]);

        /** Hier ist die Fallunterscheidung für die jeweiligen Jars */

        if (packagingIndex==1){
            results = currentAlgorithm.MSS(inputArray,true);

        }else if(packagingIndex==2){
            results = currentAlgorithm.MSS(inputArray,false);

        }


        try {
            PrintWriter writer = new PrintWriter(outputPath, "UTF-8");
            for (int i = 0; i < results.size(); i++) {
                writer.println(results.get(i)[0]+"\t"+results.get(i)[1]+"\t"+results.get(i)[2]);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }




    }

}
