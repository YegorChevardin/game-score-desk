package ua.com.kn921g.games.gamescoredesk.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.kn921g.games.gamescoredesk.common.GameBoardExceptionMessages;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.BadRequest400Exception;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.Unauthorized401Exception;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.impl.DataNotFoundException;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.impl.DataNotValidException;
import ua.com.kn921g.games.gamescoredesk.generated.dto.ScoresInformationDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserFullResponseDto;
import ua.com.kn921g.games.gamescoredesk.models.entities.User;
import ua.com.kn921g.games.gamescoredesk.models.enums.ScoreType;
import ua.com.kn921g.games.gamescoredesk.models.mappers.EntityToUserFullInformationMapper;
import ua.com.kn921g.games.gamescoredesk.repositories.UserRepository;
import ua.com.kn921g.games.gamescoredesk.service.ScoreService;
import ua.com.kn921g.games.gamescoredesk.service.security.AuthoritiesService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {
  private final UserRepository userRepository;
  private final AuthoritiesService authoritiesService;
  private final EntityToUserFullInformationMapper entityToUserFullInformationMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public ScoresInformationDto findScoresInformation(Integer page, Integer size, String scoreType) {
    PageRequest pageRequest = PageRequest.of(page, size, Sort.by(scoreType).descending());
    Page<User> scores = userRepository.findAll(pageRequest);

    User currentUser = findCurrentUserEntity();
    Integer userPlace;
    if (ScoreType.LAST_SCORE.getName().equalsIgnoreCase(scoreType)) {
      userPlace = userRepository.findUserPlaceByLastScore(currentUser.getLastScore());
    } else if (ScoreType.TOTAL_SCORE.getName().equalsIgnoreCase(scoreType)) {
      userPlace = userRepository.findUserPlaceByTotalScore(currentUser.getTotalScore());
    } else {
      throw new BadRequest400Exception(
          GameBoardExceptionMessages.SCORE_TYPE_IS_NOT_VALID.getMessage());
    }

    return ScoresInformationDto.builder()
        .users(scores.stream().map(entityToUserFullInformationMapper::entityToDto).toList())
        .playerPlace(userPlace)
        .size(size)
        .page(page)
        .build();
  }

  @Override
  @Transactional
  public UserFullResponseDto resetScore(String password) {
    User currentUser = findCurrentUserEntity();

    if (!passwordEncoder.matches(password, currentUser.getPassword())) {
      throw new Unauthorized401Exception(
          GameBoardExceptionMessages.CREDENTIALS_NOT_VALID.getMessage());
    }

    currentUser.setLastScore(0);
    currentUser.setTotalScore(0);
    userRepository.save(currentUser);

    return entityToUserFullInformationMapper.entityToDto(currentUser);
  }

  @Override
  @Transactional
  public UserFullResponseDto setScore(Integer newScore) {
    if (newScore <= 0) {
      throw new DataNotValidException(GameBoardExceptionMessages.SCORE_IS_NOT_VALID.getMessage());
    }
    User currentUser = findCurrentUserEntity();

    currentUser.setLastScore(newScore);
    currentUser.setTotalScore(currentUser.getTotalScore() + newScore);
    userRepository.save(currentUser);

    return entityToUserFullInformationMapper.entityToDto(currentUser);
  }

  private User findCurrentUserEntity() {
    return userRepository
        .findById(UUID.fromString(authoritiesService.receiveCurrentUserId()))
        .orElseThrow(
            () ->
                new DataNotFoundException(GameBoardExceptionMessages.USER_NOT_FOUND.getMessage()));
  }
}
