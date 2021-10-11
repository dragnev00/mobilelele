package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.models.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.models.entity.UserEntity;
import bg.softuni.mobilelele.models.entity.UserRoleEntity;
import bg.softuni.mobilelele.models.entity.enums.UserRoleEnum;
import bg.softuni.mobilelele.models.service.UserLoginServiceModel;
import bg.softuni.mobilelele.models.service.UserRegistrationServiceModel;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import bg.softuni.mobilelele.service.UserService;
import bg.softuni.mobilelele.user.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           UserRoleRepository userRoleRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.currentUser = currentUser;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setActive(true);

            admin.setUserRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            UserEntity pesho = new UserEntity();
            pesho
                    .setUsername("pesho")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Pesho")
                    .setLastName("Petrov")
                    .setActive(true);

            pesho.setUserRoles(Set.of(userRole));
            userRepository.save(pesho);
        }
    }

    private void initializeRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    @Override
    public boolean login(UserLoginBindingModel loginBindingModel) {

        UserLoginServiceModel loginServiceModel = new UserLoginServiceModel().
                setUsername(loginBindingModel.getUsername()).
                setRawPassword(loginBindingModel.getPassword());
                
        Optional<UserEntity> userEntityOpt =
                userRepository.findByUsername(loginServiceModel.getUsername());

        if (userEntityOpt.isEmpty()) {
            logout();
            return false;
        } else {
            boolean success = passwordEncoder.matches(
                    loginServiceModel.getRawPassword(),
                    userEntityOpt.get().getPassword());

            if (success) {
                UserEntity loggedInUser = userEntityOpt.get();
                initUserSession(loggedInUser);
            }

            return success;
        }
    }

    @Override
    public void logout() {
        currentUser.clean();
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();

        newUser
                .setUsername(userRegistrationServiceModel.getUsername())
                .setFirstName(userRegistrationServiceModel.getFirstName())
                .setLastName(userRegistrationServiceModel.getLastName())
                .setActive(true)
                .setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()))
                .setUserRoles(Set.of(userRole));

        newUser = userRepository.save(newUser);

        initUserSession(newUser);
    }

    @Override
    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).
                isEmpty();
    }

    private void initUserSession(UserEntity user) {
        currentUser.
                setLoggedIn(true).
                setUserName(user.getUsername()).
                setFirstName(user.getFirstName()).
                setLastName(user.getLastName());

        user.getUserRoles().
                forEach(r -> currentUser.addRole(r.getRole()));
    }
}
