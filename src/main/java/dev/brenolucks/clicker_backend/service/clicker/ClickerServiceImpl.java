package dev.brenolucks.clicker_backend.service.clicker;

import dev.brenolucks.clicker_backend.exceptions.clicker.ClickOverException;
import dev.brenolucks.clicker_backend.exceptions.user.UserNotExistException;
import dev.brenolucks.clicker_backend.repositories.UsersRepository;
import dev.brenolucks.clicker_backend.utils.ClickerUtils;
import org.springframework.stereotype.Service;

@Service
public class ClickerServiceImpl implements ClickerService {
    private final ClickerUtils clickerUtils;
    private final UsersRepository usersRepository;

    public ClickerServiceImpl(ClickerUtils clickerUtils, UsersRepository usersRepository) {
        this.clickerUtils = clickerUtils;
        this.usersRepository = usersRepository;
    }

    @Override
    public void coreClicker(String username) {
        var user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotExistException("User with this name doesn't exist!"));

        var newRandomNumberClick = clickerUtils.generateRandomNUmber();

        var attempts = user.getAttempts() + 1;

        if(newRandomNumberClick == user.getRandomNumber()) {
            //send email with photo
            System.out.println(String.format("Equal Number: %s, %s", newRandomNumberClick, user.getRandomNumber()));
            throw new RuntimeException("Congrats you won the game! Your prize is sending in your email.");
        } else {
            var newAvailableClick = user.getAvaliableClick() - 1;
            if(newAvailableClick < 0) throw new ClickOverException("Your clicks it's over, buy more!");

            user.setAvaliableClick(newAvailableClick);
            user.setAttempts(attempts);
            usersRepository.save(user);

            //logic for buy more clicks when the actual clicks avaliable its over
        }
    }
}
