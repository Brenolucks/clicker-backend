package dev.brenolucks.clicker_backend.service.clicker;

import dev.brenolucks.clicker_backend.domain.dto.clicker.ClickerRequestDTO;
import dev.brenolucks.clicker_backend.domain.dto.clicker.ClickerResponseDTO;
import dev.brenolucks.clicker_backend.exceptions.clicker.ClickOverException;
import dev.brenolucks.clicker_backend.exceptions.user.UserNotExistException;
import dev.brenolucks.clicker_backend.repositories.UsersRepository;
import dev.brenolucks.clicker_backend.utils.ClickerUtils;
import dev.brenolucks.clicker_backend.utils.MailUtils;
import dev.brenolucks.clicker_backend.utils.PicsumUtils;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClickerService implements IClickerService {
    private final ClickerUtils clickerUtils;
    private final UsersRepository usersRepository;
    private final MailUtils mailUtils;
    private final PicsumUtils picsumUtils;

    public ClickerService(ClickerUtils clickerUtils, UsersRepository usersRepository,
                          MailUtils mailUtils,
                          PicsumUtils picsumUtils) {
        this.clickerUtils = clickerUtils;
        this.usersRepository = usersRepository;
        this.mailUtils = mailUtils;
        this.picsumUtils = picsumUtils;
    }

    @Override
    public ClickerResponseDTO coreClicker(ClickerRequestDTO clickerRequestDTO) throws MessagingException, IOException {
        var user = usersRepository.findByUsername(clickerRequestDTO.username())
                .orElseThrow(() -> new UserNotExistException("User with this name doesn't exist!"));

        var newRandomNumberClick = clickerUtils.generateRandomNUmber();
        var attempts = user.getAttempts() + 1;
        var newAvailableClick = user.getAvaliableClick() - 1;
        var image = picsumUtils.downloadSingleImageFromPicsum();

        if(newRandomNumberClick == user.getRandomNumber()) {
            mailUtils.sendSimpleMessage(user.getEmail(), "Prize", "Congratulation, you won the game!!", image);
            user.setAvaliableClick(newAvailableClick);
            user.setAttempts(attempts);
            user.setWinner(true);
            usersRepository.save(user);

            return new ClickerResponseDTO("Congrats you won the game! Your prize is sending in your email.");
        } else {
            if(newAvailableClick < 0) throw new ClickOverException("Your clicks it's over, buy more!");

            user.setAvaliableClick(newAvailableClick);
            user.setAttempts(attempts);
            usersRepository.save(user);

            return new ClickerResponseDTO("Oh no, you were not lucky this time, try again.");
        }
    }
}
