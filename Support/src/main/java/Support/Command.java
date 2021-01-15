package Support;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class Command extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e){

        Member member = e.getMember();
        String[] message = e.getMessage().getContentRaw().split(" ");
        String[] help = e.getMessage().getContentRaw().split(" ", 2);


        System.out.println(e.getMessage().toString());
        if(!(member.getUser().isBot())){
            if(message[0].equalsIgnoreCase("question")){

                //Retrive the last message
                List<Message> previousMessages = e.getChannel().getHistory().retrievePast(1).complete();

                //Delete the last message
                e.getChannel().purgeMessages(previousMessages);

                //Check if the user ask a question
                if(help.length == 1){
                    e.getChannel().sendMessage(EmbedMessage.get(member.getEffectiveName(), 1, "Aucune")).queue(msg -> {
                        msg.addReaction("⬇️").queue();
                        msg.addReaction("✅").queue();
                    });
                }else {
                    e.getChannel().sendMessage(EmbedMessage.get(member.getEffectiveName(), 1, help[1])).queue(msg -> {
                        msg.addReaction("⬇️").queue();
                        msg.addReaction("✅").queue();
                    });
                }
            }
        }


    }
}
