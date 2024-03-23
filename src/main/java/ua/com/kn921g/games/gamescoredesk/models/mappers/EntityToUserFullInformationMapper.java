package ua.com.kn921g.games.gamescoredesk.models.mappers;

import org.mapstruct.Mapper;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserFullResponseDto;
import ua.com.kn921g.games.gamescoredesk.models.entities.User;

@Mapper
public interface EntityToUserFullInformationMapper {
    UserFullResponseDto entityToDto(User entity);
}
