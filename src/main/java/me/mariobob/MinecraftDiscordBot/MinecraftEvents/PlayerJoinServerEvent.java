package me.mariobob.MinecraftDiscordBot.MinecraftEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import me.mariobob.MinecraftDiscordBot.Util.Util;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;

@RequiredArgsConstructor
public class PlayerJoinServerEvent implements Listener {
    private final MinecraftDiscordBot plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        TextChannel textChannel = this.plugin.returnOrCreate(this.plugin.getMinecraftDiscordChannelName());
        if (textChannel != null) {
            EmbedBuilder em = new EmbedBuilder();
            em.setTitle(event.getPlayer().getDisplayName() + " has joined the server!");
            em.setColor(Color.GREEN);
            em.setFooter("Joined at " + plugin.timestamp(), "https://crafatar.com/avatars/" + event.getPlayer().getUniqueId());
            textChannel.sendMessage(em.build()).queue();

            textChannel.sendMessage(Util.EmbedBuilder(event.getPlayer().getDisplayName() + " has joined the server!", "Server is Online! :white_check_mark:", Color.GREEN, MinecraftDiscordBot.getPlugin().timestamp())).queue();


//            textChannel.getManager().setTopic(Bukkit.getOnlinePlayers().size() + " player(s) online!").queue();
        }else{
            plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
        }
    }
}
