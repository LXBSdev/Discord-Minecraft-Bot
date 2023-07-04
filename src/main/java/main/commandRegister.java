package main;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class commandRegister extends ListenerAdapter {

        @Override
        public void onGuildReady(@Nonnull GuildReadyEvent event) {
                List<CommandData> commandData = new ArrayList<>();

                event.getGuild().updateCommands().addCommands(commandData).queue();
        }

        @Override
        public void onGuildJoin(@Nonnull GuildJoinEvent event) {
                List<CommandData> commandData = new ArrayList<>();

                event.getGuild().updateCommands().addCommands(commandData).queue();
        }
}
