package videos;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {


        Path p1 = Paths.get("/users/baumwolle/Desktop/DataDUMP.sql");
        System.out.println("File name: " + p1.getFileName());
        System.out.println("Parent: " + p1.getParent());
        System.out.println("Root: " + p1.getRoot());
        System.out.println("Absolute: " + p1.toAbsolutePath());
        System.out.println("Relative: " + p1.toUri());
        System.out.println("IsAbsolute: " + p1.isAbsolute());
    }
}
