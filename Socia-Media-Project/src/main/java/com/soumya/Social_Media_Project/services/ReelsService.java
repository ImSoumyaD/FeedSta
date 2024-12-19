package com.soumya.Social_Media_Project.services;

import com.soumya.Social_Media_Project.Exceptions.ReelsException;
import com.soumya.Social_Media_Project.models.Reels;
import com.soumya.Social_Media_Project.models.User;

import java.util.List;

public interface ReelsService {
    Reels createReels(Reels reel, User user);
    List<Reels> findAllReels();
    List<Reels> findUserReels(Integer userId) throws ReelsException;
    String deleteReel(Integer reelId,Integer userId) throws ReelsException;
}
