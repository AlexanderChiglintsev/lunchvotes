import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Starter {

    public static void main(String[] args) {
        try (GenericApplicationContext ctx = new GenericXmlApplicationContext(
                "spring/spring-app.xml",
                "spring/spring-db.xml"
        )) {
            System.out.println(ctx.getApplicationName());
        }
    }

}
