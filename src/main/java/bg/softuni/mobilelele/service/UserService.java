package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.models.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.models.service.UserLoginServiceModel;
import bg.softuni.mobilelele.models.service.UserRegistrationServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void initializeUsersAndRoles();

    boolean login(UserLoginBindingModel loginBindingModel);

    void logout();

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

    boolean isUserNameFree(String username);
}
