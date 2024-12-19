package com.soumya.Social_Media_Project.servicesimpl;

import com.soumya.Social_Media_Project.Exceptions.ReelsException;
import com.soumya.Social_Media_Project.models.Reels;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.repo.ReelsRepository;
import com.soumya.Social_Media_Project.services.ReelsService;
import com.soumya.Social_Media_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReelsServiceImpl implements ReelsService {
    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserService userService;

    @Override
    public Reels createReels(Reels reel, User user){
        Reels newReel = new Reels();
        newReel.setTitle(reel.getTitle());
        newReel.setUser(user);
        newReel.setVideo(reel.getVideo());
        return reelsRepository.save(newReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) throws ReelsException {
        try {
            userService.findUserById(userId);
            return reelsRepository.findByUserId(userId);
        }catch (Exception e){
            throw new ReelsException(e.getMessage());
        }
    }

    @Override
    public String deleteReel(Integer reelId, Integer userId) throws ReelsException {
        try {
            Optional<Reels> reelById = reelsRepository.findById(reelId);
            if (reelById.isEmpty()) throw new Exception("Reel not exists...");
            Reels reel = reelById.get();
            if (reel.getUser().getId() != userId){
                throw new Exception("you can not delete other user's reels");
            }

            reelsRepository.delete(reel);
            return "Reel deleted successfully";
        }catch (Exception e){
            throw new ReelsException(e.getMessage());
        }
    }
}
