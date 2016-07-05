package com.leetcode;

import java.util.*;

/**
 * 355. Design Twitter
 Design a simplified version of Twitter where users can post tweets,
 follow/unfollow another user and is able to see the 10 most recent tweets
 in the user's news feed. Your design should support the following methods:

 postTweet(userId, tweetId): Compose a new tweet.
 getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed.
                      Each item in the news feed must be posted by users who the user followed or by the user herself.
                      Tweets must be ordered from most recent to least recent.
 follow(followerId, followeeId): Follower follows a followee.
 unfollow(followerId, followeeId): Follower unfollows a followee.

 Example:
 Twitter twitter = new Twitter();

 // User 1 posts a new tweet (time = 5).
 twitter.postTweet(1, 5);

 // User 1's news feed should return a list with 1 tweet time -> [5].
 twitter.getNewsFeed(1);

 // User 1 follows user 2.
 twitter.follow(1, 2);

 // User 2 posts a new tweet (time = 6).
 twitter.postTweet(2, 6);

 // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 // Tweet time 6 should precede tweet time 5 because it is posted after tweet time 5.
 twitter.getNewsFeed(1);

 // User 1 unfollows user 2.
 twitter.unfollow(1, 2);

 // User 1's news feed should return a list with 1 tweet time -> [5],
 // since user 1 is no longer following user 2.
 twitter.getNewsFeed(1);
 */
class Twitter {

    private Map<Integer, Set<Integer>> follows;
    private Map<Integer, List<Post>> posts;

    private int time = 0;

    private static class Post implements Comparable<Post> {
        Post(int timeId, int tweetId) {
            this.timeId = timeId;
            this.tweetId = tweetId;
        }

        private int timeId;
        private int tweetId;

        @Override
        public int compareTo(Post other) {
            return this.timeId - other.timeId;
        }
    }

    private void initIfNotExists(int userId) {
        if (!follows.containsKey(userId)) {
            follows.put(userId, new HashSet<>());
            posts.put(userId, new ArrayList<>());
        }
    }

    /** Initialize your data structure here. */
    public Twitter() {
        follows = new HashMap<>();
        posts = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        initIfNotExists(userId);
        posts.get(userId).add(new Post(time++, tweetId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        initIfNotExists(userId);

        // Top 10
        PriorityQueue<Post> pq = new PriorityQueue<>();
        Set<Integer> usersToCheck = new HashSet<>(follows.get(userId));
        usersToCheck.add(userId);
        for (int followeeId : usersToCheck) {
            List<Post> postLists = posts.get(followeeId);
            for (int i = postLists.size() - 1; i >= Math.max(postLists.size() - 10, 0); i--) {
                pq.offer(postLists.get(i));
                if (pq.size() > 10) {
                    pq.poll();
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            list.add(pq.poll().tweetId);
        }

        for (int left = 0, right = list.size() - 1; left < right; left++, right--) {
            int tmp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, tmp);
        }


        return list;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        initIfNotExists(followerId);
        initIfNotExists(followeeId);
        follows.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        initIfNotExists(followerId);
        initIfNotExists(followeeId);
        if (follows.get(followerId).contains(followeeId)) {
            follows.get(followerId).remove(followeeId);
        }
    }
}

public class DesignTwitter {
    /**
     * Your Twitter object will be instantiated and called as such:
     * Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId);
     * List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId);
     * obj.unfollow(followerId,followeeId);
     */
    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        // User 1 posts a new tweet (time = 5).
        twitter.postTweet(1, 5);

        // User 1's news feed should return a list with 1 tweet time -> [5].
        System.out.println(twitter.getNewsFeed(1));

        // User 1 follows user 2.
        twitter.follow(1, 2);

        // User 2 posts a new tweet (time = 6).
        twitter.postTweet(2, 6);

        // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
        // Tweet time 6 should precede tweet time 5 because it is posted after tweet time 5.
        System.out.println(twitter.getNewsFeed(1));

        // User 1 unfollows user 2.
        twitter.unfollow(1, 2);

        // User 1's news feed should return a list with 1 tweet time -> [5],
        // since user 1 is no longer following user 2.
        System.out.println(twitter.getNewsFeed(1));
    }
}
