package me.mariobob.MinecraftDiscordBot.DiscordEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import me.mariobob.MinecraftDiscordBot.Util.Util;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@RequiredArgsConstructor
public class ReadyEvent extends ListenerAdapter {
    private final MinecraftDiscordBot plugin;

    @Override
    public void onReady(@NotNull net.dv8tion.jda.api.events.ReadyEvent event){
        TextChannel textChannel = plugin.returnOrCreate(plugin.getMinecraftDiscordChannelName());
        if(textChannel != null){
//            EmbedBuilder em = new EmbedBuilder();
//            em.setDescription("Server is Online! :white_check_mark:");
//            em.setColor(Color.GREEN);
//            em.setFooter(plugin.timestamp());
//            textChannel.sendMessage(em.build()).queue();
            textChannel.sendMessage(Util.EmbedBuilder(null, "Server is Online! :white_check_mark:", Color.GREEN, MinecraftDiscordBot.getPlugin().timestamp())).queue();
        }else{
            plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
        }
    }
}
