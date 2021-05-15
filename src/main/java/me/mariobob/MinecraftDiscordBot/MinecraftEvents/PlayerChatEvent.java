package me.mariobob.MinecraftDiscordBot.MinecraftEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


@RequiredArgsConstructor
public class PlayerChatEvent implements Listener {
    private final MinecraftDiscordBot plugin;

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event){
        if(event.isAsynchronous()) {
            TextChannel textChannel = this.plugin.returnOrCreate(this.plugin.getMinecraftDiscordChannelName());
            if (textChannel != null) {
                textChannel.sendMessage(event.getPlayer().getDisplayName() + " » " + event.getMessage()).queue();
            }else{
                plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
            }
        }
    }
}
