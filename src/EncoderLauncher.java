import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class EncoderLauncher {

    @Option(name = "-c", metaVar = "EncodingKey", usage = "Encoding Key")
    private String EncodingKey;

    @Option(name = "-d", metaVar = "DecodingKey", usage = "Decoding Key")
    private String DecodingKey;

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    @Argument(required = true, metaVar = "OutputName", index = 1, usage = "Output file name")
    private String outputFileName;

    public static void main(String[] args) {
        new EncoderLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar encoder.jar -c EncodingKey -d DecodingKey InputName OutputName");
            parser.printUsage(System.err);
            return;
        }

        String key;

        if (EncodingKey != null) {
            key = EncodingKey;
        } else {
            key = DecodingKey;
        }
        Encoder encoder = new Encoder(key);
        try {
            int result = encoder.encode(inputFileName, outputFileName, key);
            if (EncodingKey != null) {
                System.out.println("Total of " + result + " symbols encoded");
            } else {
                System.out.println("Total of " + result + " symbols decoded");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}