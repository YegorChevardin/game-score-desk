package ua.com.kn921g.games.gamescoredesk.models.mappers;

import org.mapstruct.Mapper;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserResponseDto;
import ua.com.kn921g.games.gamescoredesk.models.User;

@Mapper
public interface EntityToUserResponseMapper {
    UserResponseDto entityToDto(User entity);
}
