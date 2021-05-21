package me.mariobob.MinecraftDiscordBot.DiscordEvents;

import lombok.RequiredArgsConstructor;
import me.mariobob.MinecraftDiscordBot.MinecraftDiscordBot;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@RequiredArgsConstructor
public class MessageSendEvent extends ListenerAdapter {
    private final MinecraftDiscordBot plugin;


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        TextChannel textChannel = plugin.returnOrCreate(plugin.getMinecraftDiscordChannelName());
        if (event.getChannel() == textChannel) {
            if (!event.getAuthor().isBot()) {
                plugin.getServer().broadcastMessage(event.getAuthor().getName() + " » " + event.getMessage().getContentRaw());
            }
        } else {
            plugin.getServer().getLogger().severe("Minecraft channel not found!! Make sure it exists and is in the config.yml file.");
        }
    }
}
