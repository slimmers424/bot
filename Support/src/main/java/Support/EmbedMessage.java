package Support;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmbedMessage {

    public static MessageEmbed get(String author, int queue, String description){
        EmbedBuilder embed = new EmbedBuilder();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        embed.setAuthor(author);
        embed.setColor(Color.red);
        embed.setTitle("Requête d'aide de: " + author);
        embed.addField("Question?: ", description, true);
        embed.addField("\t      \tStatut", "\t      \tEn attente", true);
        embed.setFooter("Création: " + formatter.format(date));

        return embed.build();
    }

    public static MessageEmbed answer(String author, MessageEmbed.Field description, String footer){
        EmbedBuilder answer = new EmbedBuilder();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");


        answer.setAuthor(author);
        answer.setColor(Color.red);
        answer.setTitle("Requête d'aide de: " + author);
        answer.addField(description);
        answer.addField("\t      \tStatut", " \t      \tEn train de répondre", true);
        answer.setFooter(footer + "\t            \tRéponse: " + formatter.format(date));

        return answer.build();
    }

    public static MessageEmbed close(String author, MessageEmbed.Field description, String footer){
        EmbedBuilder close = new EmbedBuilder();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");


        close.setAuthor(author);
        close.setColor(Color.red);
        close.setTitle("Requête d'aide de: " + author);
        close.addField(description);
        close.addField("\t      \tStatut", " \t      \tFermé", true);
        close.setFooter(footer);

        return close.build();
    }
}
