package com.soumya.Social_Media_Project.controller;

import com.soumya.Social_Media_Project.Exceptions.ReelsException;
import com.soumya.Social_Media_Project.models.Reels;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.services.ReelsService;
import com.soumya.Social_Media_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reels")
public class ReelsController {
    @Autowired
    UserService userService;
    @Autowired
    ReelsService reelsService;

    @PostMapping("/create")
    public Reels createReel(@RequestHeader("Authorization") String jwt, @RequestBody Reels reel) throws ReelsException {
        try {
            User user = userService.getUserFromJwt(jwt);
            return reelsService.createReels(reel,user);
        }catch (Exception e){
            throw new ReelsException(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Reels> getAllReels(){
        return reelsService.findAllReels();
    }

    @GetMapping("/user/{userId}")
    public List<Reels> findByUserId(@PathVariable Integer userId) throws ReelsException {
        return reelsService.findUserReels(userId);
    }

    @DeleteMapping("/delete/{reelId}")
    public String deleteReel(@RequestHeader("Authorization") String jwt,@PathVariable Integer reelId) throws ReelsException {
        try {
            User user = userService.getUserFromJwt(jwt);
            return reelsService.deleteReel(reelId,user.getId());
        }catch (Exception e){
            throw new ReelsException(e.getMessage());
        }
    }

}
