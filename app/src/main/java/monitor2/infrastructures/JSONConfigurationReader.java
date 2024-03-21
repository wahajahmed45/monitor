package monitor2.infrastructures;



import monitor2.domains.Configuration;
import monitor2.domains.ConfigurationReader;
import monitor2.domains.FileExceptions;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONConfigurationReader implements ConfigurationReader {
    @Override
    public Configuration read(String path) throws FileExceptions {
        Configuration configuration;
        ObjectMapper mapper = new ObjectMapper();
        try {
            configuration = mapper.readValue(new File(path), Configuration.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new FileExceptions("Probe : File not found");
        }
        return configuration;
    }
}