package ua.com.kn921g.games.gamescoredesk.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.kn921g.games.gamescoredesk.common.GameBoardExceptionMessages;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.Unauthorized401Exception;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.impl.DataExistException;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.impl.DataNotFoundException;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserFullResponseDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserRegisterRequestDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserResponseDto;
import ua.com.kn921g.games.gamescoredesk.models.User;
import ua.com.kn921g.games.gamescoredesk.models.mappers.EntityToUserFullInformationMapper;
import ua.com.kn921g.games.gamescoredesk.models.mappers.EntityToUserResponseMapper;
import ua.com.kn921g.games.gamescoredesk.repositories.UserRepository;
import ua.com.kn921g.games.gamescoredesk.service.ProfileService;
import ua.com.kn921g.games.gamescoredesk.service.security.AuthoritiesService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
  private final AuthoritiesService authoritiesService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final EntityToUserFullInformationMapper entityToUserFullInformationMapper;
  private final EntityToUserResponseMapper entityToUserResponseMapper;

  @Override
  @Transactional
  public void deleteUser(String password) {
    User user = findCurrentUserEntity();

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new Unauthorized401Exception(
          GameBoardExceptionMessages.CREDENTIALS_NOT_VALID.getMessage());
    }

    userRepository.delete(user);
  }

  @Override
  public UserFullResponseDto findUserInformation() {
    return entityToUserFullInformationMapper.entityToDto(findCurrentUserEntity());
  }

  @Override
  @Transactional
  public UserResponseDto updateUser(String oldPassword, UserRegisterRequestDto updateDto) {
    User user = findCurrentUserEntity();

    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
      throw new Unauthorized401Exception(
          GameBoardExceptionMessages.CREDENTIALS_NOT_VALID.getMessage());
    }

    Optional<User> potentialConflictUser = userRepository.findByUsername(updateDto.username());
    if (potentialConflictUser.isPresent()
        && !potentialConflictUser.get().getId().equals(user.getId())) {
      throw new DataExistException(GameBoardExceptionMessages.USER_EXISTS.getMessage());
    }

    user.setPassword(passwordEncoder.encode(updateDto.password()));
    user.setUsername(updateDto.username());
    userRepository.save(user);

    return entityToUserResponseMapper.entityToDto(user);
  }

  private User findCurrentUserEntity() {
    return userRepository
        .findById(UUID.fromString(authoritiesService.receiveCurrentUserId()))
        .orElseThrow(
            () ->
                new DataNotFoundException(GameBoardExceptionMessages.USER_NOT_FOUND.getMessage()));
  }
}
