package ua.com.kn921g.games.gamescoredesk.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserRegisterRequestDto;
import ua.com.kn921g.games.gamescoredesk.models.User;

@Mapper
public interface UserRegisterRequestToEntityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "totalScore", ignore = true)
    @Mapping(target = "lastScore", ignore = true)
    User dtoToEntity(UserRegisterRequestDto dto);
}
