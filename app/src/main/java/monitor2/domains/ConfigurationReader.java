package monitor2.domains;

public interface ConfigurationReader {
    Configuration read(String path) throws FileExceptions;
}
