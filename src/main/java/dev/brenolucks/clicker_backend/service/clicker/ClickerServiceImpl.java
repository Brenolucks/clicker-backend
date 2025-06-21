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
        var user = usersRepository.findByUsername(username);

        var newRandomNumberClick = clickerUtils.generateRandomNUmber();

        if(user.isPresent()) throw new UserNotExistException("User with this name doesn't exist!");

        if(newRandomNumberClick == user.get().getRandomNumber()) {
            //send email with photo
            System.out.println(String.format("Equal Number: %s, %s", newRandomNumberClick, user.get().getRandomNumber()));
            throw new RuntimeException("Congrats you won the game! Your prize is sending in your email.");
        } else {
            var newAvailableClick = user.get().getAvaliableClick() - 1;
            if(newAvailableClick < 0) throw new ClickOverException("Your clicks it's over, buy more!");

            var userUpdated = user.get();
            userUpdated.setAvaliableClick(newAvailableClick);
            usersRepository.save(userUpdated);

            //logic for buy more clicks when the actual clicks avaliable its over
        }
    }
}
