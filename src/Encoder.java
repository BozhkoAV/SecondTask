import java.io.*;

public class Encoder {
    private final String key;

    public Encoder(String key) {
        this.key = key;
    }

    public int encode(InputStream in, OutputStream out, String key) throws IOException {
        try (InputStream reader = in) {
            try (OutputStream writer = out) {
                int bit = reader.read();
                int count = 0;
                int i = 0;
                char[] keyBit = key.toCharArray();
                while (bit != -1) {
                    if (i > keyBit.length - 1) i = 0;
                    writer.write(bit ^ (int)keyBit[i]);
                    i++;
                    count++;
                    bit = reader.read();
                }
                return count;
            }
        }
    }

    public int encode(String inputName, String outputName, String key) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                return encode(inputStream, outputStream, key);
            }
        }
    }
}
