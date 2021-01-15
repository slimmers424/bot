package Support;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Emote extends ListenerAdapter {

    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e){


        String emoji = e.getReactionEmote().getEmoji();
        Message message = e.retrieveMessage().complete();
        List<MessageEmbed> embed = message.getEmbeds();

        if(!e.getUser().isBot()) {
            if (!embed.isEmpty()) {
                if (e.getMember().getEffectiveName().equals(embed.get(0).getAuthor().getName()) || e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                    if (e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                        if(e.getReactionEmote().getEmoji().equals("⬇️")) {

                            List<Member> author = e.getGuild().getMembersByEffectiveName(embed.get(0).getAuthor().getName(), true);

                            try {
                                System.out.println(author.get(0).getVoiceState().getChannel().toString());
                                e.getGuild().moveVoiceMember(e.getMember(), author.get(0).getVoiceState().getChannel()).queue();
                                if (!embed.get(0).getFooter().getText().contains("Réponse")) {
                                    message.editMessage(EmbedMessage.answer(embed.get(0).getAuthor().getName(), embed.get(0).getFields().get(0), embed.get(0).getFooter().getText())).queue();
                                }
                            } catch (Exception exception) {
                                e.getUser().openPrivateChannel().queue(msg -> {
                                    msg.sendMessage(embed.get(0).getAuthor().getName() + " n'est pas dans un salon vocal").queue();
                                });
                                message.clearReactions().queue();
                                message.addReaction("⬇️").queue();
                                message.addReaction("✅").queue();
                            }
                        }
                    }if(e.getReactionEmote().getEmoji().equals("✅")){
                        message.editMessage(EmbedMessage.close(embed.get(0).getAuthor().getName(), embed.get(0).getFields().get(0), embed.get(0).getFooter().getText())).queue();
                        message.delete().queueAfter(5, TimeUnit.SECONDS);
                    }

                }
            }
        }



    }

}
