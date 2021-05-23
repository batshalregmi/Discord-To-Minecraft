package me.mariobob.MinecraftDiscordBot.DiscordEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import me.mariobob.MinecraftDiscordBot.Util.Util;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@RequiredArgsConstructor
public class ReadyEvent extends ListenerAdapter {
    private final MinecraftDiscordBot plugin;

    @Override
    public void onReady(@NotNull net.dv8tion.jda.api.events.ReadyEvent event) {
        TextChannel textChannel = plugin.returnOrCreate(plugin.getMinecraftDiscordChannelName());
        if (textChannel != null) {
            textChannel.sendMessage(Util.createEmbed(null, "Server is Online! :white_check_mark:", Color.GREEN, MinecraftDiscordBot.getPlugin().timestamp(), null)).queue();
        } else {
            plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
        }
    }
}
