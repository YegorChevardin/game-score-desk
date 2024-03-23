package ua.com.kn921g.games.gamescoredesk.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.com.kn921g.games.gamescoredesk.models.mappers.EntityToUserFullInformationMapper;
import ua.com.kn921g.games.gamescoredesk.models.mappers.EntityToUserResponseMapper;
import ua.com.kn921g.games.gamescoredesk.models.mappers.UserRegisterRequestToEntityMapper;

@Configuration
public class MapperConfig {
  @Bean
  public UserRegisterRequestToEntityMapper userRegisterRequestToEntityMapper() {
    return Mappers.getMapper(UserRegisterRequestToEntityMapper.class);
  }

  @Bean
  public EntityToUserResponseMapper entityToUserResponseMapper() {
    return Mappers.getMapper(EntityToUserResponseMapper.class);
  }

  @Bean
  public EntityToUserFullInformationMapper entityToUserFullInformationMapper() {
    return Mappers.getMapper(EntityToUserFullInformationMapper.class);
  }
}
