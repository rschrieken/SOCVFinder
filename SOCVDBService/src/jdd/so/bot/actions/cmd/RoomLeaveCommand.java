package jdd.so.bot.actions.cmd;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import fr.tunaki.stackoverflow.chat.event.PingMessageEvent;
import jdd.so.bot.ChatRoom;
import jdd.so.bot.actions.BotCommand;

public class RoomLeaveCommand extends BotCommand {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RoomLeaveCommand.class);

	@Override
	public String getMatchCommandRegex() {
		return "(?i)(leave)";
	}

	@Override
	public int getRequiredAccessLevel() {
		return BotCommand.ACCESS_LEVEL_RO;
	}

	@Override
	public String getCommandName() {
		return "leave room";
	}

	@Override
	public String getCommandDescription() {
		return "Tell me to leave the room";
	}

	@Override
	public String getCommandUsage() {
		return "leave";
	}

	@Override
	public void runCommand(ChatRoom room, PingMessageEvent event) {
		CompletableFuture<Long> mId = room.replyTo(event.getMessageId(), "I'm leaving this room, I'll be back if rebooted");
		mId.thenAccept(new Consumer<Long>() {

			@Override
			public void accept(Long t) {
				room.leave();
			}
		});
		
	}
	
	public static void main(String[] args) {
		
		String regex = "(?i)(add \\[.*?\\])";
		Pattern p = Pattern.compile(regex);
		System.out.println(p.matcher("add [java]").find()); 
	}

}
