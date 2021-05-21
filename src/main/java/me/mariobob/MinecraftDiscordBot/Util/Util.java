package me.mariobob.MinecraftDiscordBot.Util;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;


public class Util {

    public static net.dv8tion.jda.api.entities.MessageEmbed createEmbed(String title, String description, Color color, String footer, String author, Image image) {
        EmbedBuilder em = new EmbedBuilder();
        em.setDescription(description);
        em.setColor(color);
        em.setTitle(title);
        em.setFooter(footer);
        em.setAuthor(author);
        em.setImage(String.valueOf(image));
        return em.build();

    }
}
