package com.example.designnewsfeed;

import com.example.designnewsfeed.commands.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mock.web.MockHttpSession;


import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class DesignNewsFeedApplication implements CommandLineRunner  {
	private CommandRegistry commandRegistry;
	private ExcludeAuthCommandRegistry excludeAuthCommandRegistry;
	private SignupCommand signupCommand;
	private LoginCommand loginCommand;
	private FollowCommand followCommand;
	private PostCommand postCommand;
	private CommentCommand commentCommand;
	private UpvoteCommand upvoteCommand;
	private DownvoteCommand downvoteCommand;
	private CommentUpvoteCommand commentUpvoteCommand;
	private CommentDownvoteCommand commentDownvoteCommand;
	private CommentOnCommentCommand commentOnCommentCommand;
	private FeedCommand feedCommand;

	public DesignNewsFeedApplication(CommandRegistry commandRegistry, ExcludeAuthCommandRegistry excludeAuthCommandRegistry, SignupCommand signupCommand, LoginCommand loginCommand, FollowCommand followCommand, PostCommand postCommand, CommentCommand commentCommand, UpvoteCommand upvoteCommand, DownvoteCommand downvoteCommand, CommentUpvoteCommand commentUpvoteCommand, CommentDownvoteCommand commentDownvoteCommand, CommentOnCommentCommand commentOnCommentCommand, FeedCommand feedCommand) {
		this.commandRegistry = commandRegistry;
		this.excludeAuthCommandRegistry = excludeAuthCommandRegistry;
		this.signupCommand = signupCommand;
		this.loginCommand = loginCommand;
		this.followCommand = followCommand;
		this.postCommand = postCommand;
		this.commentCommand = commentCommand;
		this.upvoteCommand = upvoteCommand;
		this.downvoteCommand = downvoteCommand;
		this.commentUpvoteCommand = commentUpvoteCommand;
		this.commentDownvoteCommand = commentDownvoteCommand;
		this.commentOnCommentCommand = commentOnCommentCommand;
		this.feedCommand = feedCommand;
	}

	public static void main(String[] args) {
		SpringApplication.run(DesignNewsFeedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// registering the commands
		excludeAuthCommandRegistry.register(signupCommand);
		commandRegistry.register(loginCommand);
		commandRegistry.register(followCommand);
		commandRegistry.register(postCommand);
		commandRegistry.register(commentCommand);
		commandRegistry.register(upvoteCommand);
		commandRegistry.register(downvoteCommand);
		commandRegistry.register(commentUpvoteCommand);
		commandRegistry.register(commentDownvoteCommand);
		commandRegistry.register(commentOnCommentCommand);
		commandRegistry.register(feedCommand);

		MockHttpSession session = new MockHttpSession();
		Scanner sc = new Scanner(System.in);
		while (true){
			String input = sc.nextLine();
			excludeAuthCommandRegistry.execute(input);
			commandRegistry.execute(input,session);
		}
	}
}
