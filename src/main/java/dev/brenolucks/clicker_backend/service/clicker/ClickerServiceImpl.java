package dev.brenolucks.clicker_backend.service.clicker;

import dev.brenolucks.clicker_backend.exceptions.clicker.ClickOverException;
import dev.brenolucks.clicker_backend.exceptions.user.UserNotExistException;
import dev.brenolucks.clicker_backend.repositories.UsersRepository;
import dev.brenolucks.clicker_backend.utils.ClickerUtils;
import dev.brenolucks.clicker_backend.utils.MailUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class ClickerServiceImpl implements ClickerService {
    private final ClickerUtils clickerUtils;
    private final UsersRepository usersRepository;
    private final MailUtils mailUtils;

    public ClickerServiceImpl(ClickerUtils clickerUtils, UsersRepository usersRepository, MailUtils mailUtils) {
        this.clickerUtils = clickerUtils;
        this.usersRepository = usersRepository;
        this.mailUtils = mailUtils;
    }

    @Override
    public void coreClicker(String username) throws MessagingException {
        var user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotExistException("User with this name doesn't exist!"));

        var newRandomNumberClick = clickerUtils.generateRandomNUmber();

        var attempts = user.getAttempts() + 1;
        var newAvailableClick = user.getAvaliableClick() - 1;

        if(newRandomNumberClick == user.getRandomNumber()) {
            //send email with photo
            mailUtils.sendSimpleMessage("janna.thresh@gmail.com", "Teste", "Teste envio de email com o SMTP");
            user.setAvaliableClick(newAvailableClick);
            user.setAttempts(attempts);
            usersRepository.save(user);

            throw new RuntimeException("Congrats you won the game! Your prize is sending in your email.");
        } else {
            if(newAvailableClick < 0) throw new ClickOverException("Your clicks it's over, buy more!");

            user.setAvaliableClick(newAvailableClick);
            user.setAttempts(attempts);
            usersRepository.save(user);

            //logic for buy more clicks when the actual clicks avaliable its over
        }
    }
}
