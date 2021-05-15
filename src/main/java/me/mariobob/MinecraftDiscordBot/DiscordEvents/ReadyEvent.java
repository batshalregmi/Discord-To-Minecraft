package me.mariobob.MinecraftDiscordBot.DiscordEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class ReadyEvent extends ListenerAdapter {
    private final MinecraftDiscordBot plugin;

    @Override
    public void onReady(@NotNull net.dv8tion.jda.api.events.ReadyEvent event){
        TextChannel textChannel = plugin.returnOrCreate(plugin.getMinecraftDiscordChannelName());
        if(textChannel != null){
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            EmbedBuilder em = new EmbedBuilder();
            em.setDescription("Server is Online! :white_check_mark:");
            em.setColor(Color.GREEN);
            em.setFooter(formatter.format(date));
            textChannel.sendMessage(em.build()).queue();
        }else{
            plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
        }
    }
}
