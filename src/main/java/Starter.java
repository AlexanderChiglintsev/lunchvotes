import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.snx.lunchvotes.repository.UserRepository;
import ru.snx.lunchvotes.repository.datajpa.DataJpaUserRepository;

public class Starter {

    public static void main(String[] args) {
        try (GenericApplicationContext ctx = new GenericXmlApplicationContext(
                "spring/spring-app.xml",
                "spring/spring-db.xml"
        )) {
            System.out.println(ctx.getApplicationName());
            UserRepository userRepository = ctx.getBean(DataJpaUserRepository.class);
            System.out.println(userRepository.getByEmail("user@user.ru").getPassword());
        }
    }

}
