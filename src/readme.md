signup maruthi maruthi@gmail.com Maru@123  // second param is name, third param is gamil and last param is password
login maruthi@gmail.com  // 
follow 2 // here 2 is the id of the user you want to follow
post This is sample post // here post is Command and rest is post description
comment 1 This is sample comment // here 1 is the post on which you want to comment on and rest is cooment
upvote 1 // 1 is id of the post for which you want to upvote
downvote 1 // 1 is the id of the post for which you want to downvote
commentUpvote 1 // here 1 is the id of the comment for which you want to upvote
commentDownvote 2 // here 2 is the id of the comment for which you want to downvote
feed // this will fetch the feed of the logged in user


**important design**
1.Even we shard our database and chosen key as user1_id,we can efficiently answer follow and followed by queries
2.we are using comment_id as a self reference to id field. so that we can have tree like structure.
3.we are using command design pattern to handle all other commands
4.we are using user_id in most of the table for future extensibility and tacking
