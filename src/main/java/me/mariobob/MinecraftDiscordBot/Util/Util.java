package me.mariobob.MinecraftDiscordBot.Util;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;


public class Util {

    public static net.dv8tion.jda.api.entities.MessageEmbed createEmbed(String title, String description, Color color, String footer, String author) {
        EmbedBuilder em = new EmbedBuilder();
        em.setDescription(description);
        em.setColor(color);
        em.setTitle(title);
        em.setFooter(footer);
        em.setAuthor(author);
        return em.build();

    }
}
