package dev.brenolucks.clicker_backend.service.clicker;

import dev.brenolucks.clicker_backend.domain.dto.user.UserRequestDTO;
import dev.brenolucks.clicker_backend.domain.model.Users;
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
    public int checkNumberIsEqualDatabase(String username) {
        var newRandomNumberClick = clickerUtils.generateRandomNUmber();
        var numberStored = usersRepository.findRandomNumberByUsername(username);

        if(newRandomNumberClick == numberStored.get().getRandomNumber()) {
            //send email with photo
            System.out.println(String.format("Equal Number: %s, %s", newRandomNumberClick, numberStored.get().getRandomNumber()));
        } else {
            var newAvailableClick = numberStored.get().getAvaliableClick() - 1;
            if(newAvailableClick < 0) throw new RuntimeException("Your clicks its over, buy more!");

            var user = numberStored.get();
            user.setAvaliableClick(newAvailableClick);
            usersRepository.save(user);

            System.out.println(String.format("Not equal number: %s, %s", newRandomNumberClick, numberStored.get().getRandomNumber()));
        }

        return 0;
    }
}
