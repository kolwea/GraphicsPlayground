package Resources;

public class ResourceLoader {
    private static ResourceLoader ourInstance = new ResourceLoader();

    public static ResourceLoader getInstance() {
        return ourInstance;
    }

    private ResourceLoader() {
    }
}
