package com.project.userservice.users.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.userservice.feigns.companyFeign;
import com.project.userservice.feigns.photoFeign;
import com.project.userservice.security.JwtTokenUtil;
import com.project.userservice.users.data.DTO.*;
import com.project.userservice.users.data.enums.loginStatus;
import com.project.userservice.users.data.enums.registerStatus;
import com.project.userservice.users.data.enums.role;
import com.project.userservice.users.data.user;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class userService {
    @Autowired
    private userRepo userRepo;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private photoFeign photoFeign;

    @Autowired
    private companyFeign companyFeign;

    public registerResponseDTO registerNewUser(userRegisterDTO userRegisterDTO){
        if(userRepo.findByEmail(userRegisterDTO.getUser_email())!=null){
            return new registerResponseDTO(registerStatus.USER_EXISTS,"User with this email address is already in the system",null);
        }
        else {
            user newUser = new user(userRegisterDTO.getUser_nickname(),
                    role.BROKER,userRegisterDTO.getUser_email(),
                    userRegisterDTO.getUser_phoneNumber(),
                    userRegisterDTO.getUser_socials(),null,
                    userRegisterDTO.getUser_company(), BCrypt.hashpw(userRegisterDTO.getUser_pinCode(),BCrypt.gensalt()),false);
            userRepo.save(newUser);
            return new registerResponseDTO(registerStatus.SUCCESSFULLY_REGISTERED,"The new user has been successfully registered, wait for confirmation from the manager to gain access to the system",
                    objectMapper.convertValue(newUser, userDTO.class));

        }
    }

    public loginResponseDTO loginUser(loginDTO loginDTO){
        user loginUser = userRepo.findByEmail(loginDTO.getEmail());
        if(loginUser == null){
            return new loginResponseDTO(loginStatus.FAILURE,"Could not find a user with this email",null,null,null);
        }
        else {
            if(!loginUser.getActivated()){
                return new loginResponseDTO(loginStatus.ACCOUNT_DISABLED,"This user has not yet been activated",null,null,null);
            }
            if (loginUser.getBan_till() != null &&loginUser.getBan_till().isAfter(LocalDateTime.now())){
                return new loginResponseDTO(loginStatus.LOCKED,"Account blocked until"+loginUser.getBan_till().toString(),null,null,null);
            }
            if(BCrypt.checkpw(loginDTO.getPinCode(),loginUser.getUser_pinCode())){
                String user_token = jwtTokenUtil.generateToken(loginUser);
                String session_id = UUID.randomUUID().toString();
                loginUser.setUser_activeSession(session_id);
                userRepo.save(loginUser);
                return new loginResponseDTO(loginStatus.SUCCESS,"Successful authorization",session_id,user_token,objectMapper.convertValue(loginUser,userDTO.class));
            }
            else {
                return new loginResponseDTO(loginStatus.FAILURE,"Bad credentials",null,null,null);
            }
        }
    }

    public void updateUserAvatar(String email, MultipartFile avatar){
        CompletableFuture.runAsync(() -> {
            photoFeign.updateUserAvatar(email,avatar);
        });
    }
    public void setUserAvatar(String avatarId, String email){
        user userAvatar = userRepo.findByEmail(email);
        userAvatar.setUser_photo(avatarId);
        userRepo.save(userAvatar);
    }

    public userProfileDataDTO getUserProfileData(String email){
        user request_user = userRepo.findByEmail(email);
        userProfileDataDTO userProfileDataDTO = new userProfileDataDTO();
        if(request_user.getUser_role().equals(role.BROKER)){
            userProfileDataDTO.setUser_data(objectMapper.convertValue(request_user,userDTO.class));
            userProfileDataDTO.setUser_team(userRepo.findByCompanyAndActivatedIsTrue(request_user.getCompany()).stream().map(user->objectMapper.convertValue(user,userDTO.class)).collect(Collectors.toList()));
            if(request_user.getUser_photo()!=null) {
                userProfileDataDTO.setUser_photo(photoFeign.getPhotoById(request_user.getUser_photo()));
            }
            return userProfileDataDTO;
        }
        else {
            userProfileDataDTO.setUser_data(objectMapper.convertValue(request_user,userDTO.class));
            userProfileDataDTO.setUser_team(userRepo.findByCompanyAndActivatedIsTrue(request_user.getCompany()).stream().map(user->objectMapper.convertValue(user,userDTO.class)).collect(Collectors.toList()));
            userProfileDataDTO.setCompany_capacity(companyFeign.getCompanyCapacity(request_user.getCompany()));
            userProfileDataDTO.setNew_users(userRepo.findByCompanyAndActivatedIsFalse(request_user.getCompany()).stream().map(user->objectMapper.convertValue(user,userDTO.class)).collect(Collectors.toList()));
            if(request_user.getUser_photo()!=null) {
                userProfileDataDTO.setUser_photo(photoFeign.getPhotoById(request_user.getUser_photo()));
            }
            return userProfileDataDTO;
        }
    }
    public void processNewRegistration(String userId, String action,String email) {
        Optional<user> optionalUser = userRepo.findById(userId);

        if (optionalUser.isPresent()) {
            user processedUser = optionalUser.get();

            if ("approve".equals(action)) {
                Integer capacity = companyFeign.getCompanyCapacity(processedUser.getCompany());
                if(capacity+1>userRepo.countByCompany(processedUser.getCompany())) {
                    processedUser.setActivated(true);
                    userRepo.save(processedUser);
                }
                else {
                    throw new NoSuchElementException("There is no space in company");
                }
            } else if ("deny".equals(action)) {
                userRepo.deleteById(userId);
            } else {
                throw new IllegalArgumentException("Invalid action. Supported actions are 'approve' or 'deny'.");
            }
        } else {
            throw new NoSuchElementException("User not found with ID: " + userId);
        }
    }
    public void deleteUser(String user_id){
        userRepo.deleteById(user_id);
    }
    public user getUserByEmail(String email){
        return userRepo.findByEmail(email);
    }
}
