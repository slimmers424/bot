import Support.Command;
import Support.Emote;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class bot {

    public static void main(String[] args)
            throws LoginException, InterruptedException
    {

        String token = "";
        // Note: It is important to register your ReadyListener before building
        JDA jda = JDABuilder.createDefault(token).build();

        // optionally block until JDA is ready
        jda.awaitReady();


        jda.addEventListener(new Command());
        jda.addEventListener(new Emote());

    }
}
