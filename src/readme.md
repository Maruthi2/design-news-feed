1. signup <name> <email> <password>: Creates a new user with the provided name, email, and password.
2. login <email>: Logs in a user with the provided email.
3. follow <user_id>: Follows the user with the provided user ID.
4. post <description>: Creates a new post with the provided description.
5. comment <post_id> <comment>: Adds a new comment to the post with the provided post ID.
6. upvote <post_id>: Upvotes the post with the provided post ID.
7. downvote <post_id>: Downvotes the post with the provided post ID.
8. commentUpvote <comment_id>: Upvotes the comment with the provided comment ID.
9. commentDownvote <comment_id>: Downvotes the comment with the provided comment ID.
10. feed: Fetches the feed of the logged-in user.


**important design**
1. Even we shard our database and chosen key as user1_id,we can efficiently answer follow and followed by queries
2. we are using comment_id as a self reference to id field. so that we can have tree like structure.
3. we are using command design pattern to handle all other commands
4. we are using user_id in most of the table for future extensibility and tacking
