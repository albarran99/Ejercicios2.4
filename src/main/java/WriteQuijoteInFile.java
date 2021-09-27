import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;

public class WriteQuijoteInFile {

    private static final Logger log = LoggerFactory.getLogger(WriteQuijoteInFile.class);

    public static void main(String[] args) throws IOException {
        WriteQuijoteInFile writeQuijoteInFile = new WriteQuijoteInFile();
        String quijoteFile = writeQuijoteInFile.readQuijote(writeQuijoteInFile.classPath("/el_quijote.txt"));
        writeQuijoteInFile.writeContent(quijoteFile, new File("/tmp/el_quijote.txt"));

    }

    public File classPath(String path) {
        File file = null;

        try {
            file = new File(getClass().getResource(path).toURI());
        } catch (URISyntaxException e) {
            log.error("", e);
        }
        return file;
    }

    public void writeContent(String content, File destination) throws IOException {
        try(FileWriter write = new FileWriter(destination)) {
            write.write(content);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public String readQuijote(File file) {
        StringBuilder strBuild = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file)
        )

        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                strBuild.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return strBuild.toString();
    }


}
