package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OasysBcsPart;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Value
@Builder(access = AccessLevel.PRIVATE)
public class OasysBcsPartDto {
    String bcsPart;
    String bcsPartStatus;
    String part1CheckedInd;
    LocalDateTime part1CheckedDate;
    String bcsPartUserArea;
    String bcsPartUserPosition;
    LocalDateTime bcsPartCompDate;
    LocalDateTime createDate;
    LocalDateTime lastupdDate;
    String createUser;
    String lastupdUser;
    String praComplete;
    String praCompUser;
    LocalDateTime praCompDate;
    String lockIncompleteReason;
    String lockIncompleteOtherText;
    OasysUserDto bcsPartUser;
    OasysUserDto part1CheckedUser;

    public static List<OasysBcsPartDto> from(Collection<OasysBcsPart> oasysBcsParts) {
        return Optional.ofNullable(oasysBcsParts)
                .map(obpo -> oasysBcsParts
                        .stream()
                        .map(OasysBcsPartDto::from)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private static OasysBcsPartDto from(OasysBcsPart oasysBcsPart) {
        return Optional.ofNullable(oasysBcsPart)
                .map(oasysBcsPart1 -> OasysBcsPartDto.builder()
                        .bcsPartCompDate(oasysBcsPart.getCreateDate())
                        .bcsPart(oasysBcsPart.getBcsPart())
                        .bcsPartStatus(oasysBcsPart.getBcsPartStatus())
                        .bcsPartUserArea(oasysBcsPart.getBcsPartUserArea())
                        .bcsPartUserPosition(oasysBcsPart.getBcsPartUserPosition())
                        .createDate(oasysBcsPart.getCreateDate())
                        .createUser(oasysBcsPart.getCreateUser())
                        .lastupdDate(oasysBcsPart.getLastupdDate())
                        .lastupdUser(oasysBcsPart.getLastupdUser())
                        .lockIncompleteOtherText(oasysBcsPart.getLockIncompleteOtherText())
                        .lockIncompleteReason(oasysBcsPart.getLockIncompleteReason())
                        .part1CheckedDate(oasysBcsPart.getPart1CheckedDate())
                        .part1CheckedInd(oasysBcsPart.getPart1CheckedInd())
                        .praCompDate(oasysBcsPart.getPraCompDate())
                        .praComplete(oasysBcsPart.getPraComplete())
                        .praCompUser(oasysBcsPart.getPraCompUser())
                        .bcsPartUser(OasysUserDto.from(oasysBcsPart.getBcsPartUser()))
                        .part1CheckedUser(OasysUserDto.from(oasysBcsPart.getPart1CheckedUser()))
                        .build()
                )
                .orElse(null);
    }
}
