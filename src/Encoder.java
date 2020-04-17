import java.io.*;

public class Encoder {
    private final String key;

    public Encoder(String key) {
        this.key = key;
    }

    public int encode(InputStream in, OutputStream out) throws IOException {
        int _byte = in.read();
        int[] byteKey = new int[key.length()/2];
        for (int i = 0; i < key.length() - 1; i+=2) {
            byteKey[i/2] = Integer.parseInt(key.substring(i, i+2), 16);
        }
        int count = 0;
        int i = 0;
        while (_byte != -1) {
            if (i > byteKey.length - 1) i = 0;
            out.write(_byte ^ byteKey[i]);
            i++;
            count++;
            _byte = in.read();
        }
        return count;
    }

    public int encode(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                return encode(inputStream, outputStream);
            }
        }
    }
}