package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import uk.gov.justice.digital.oasys.jpa.entity.OasysBcsPart;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class OasysBcsPartDto {
    private String bcsPart;
    private String bcsPartStatus;
    private String part1CheckedInd;
    private LocalDateTime part1CheckedDate;
    private String bcsPartUserArea;
    private String bcsPartUserPosition;
    private LocalDateTime bcsPartCompDate;
    private LocalDateTime createDate;
    private LocalDateTime lastupdDate;
    private String createUser;
    private String lastupdUser;
    private String praComplete;
    private String praCompUser;
    private LocalDateTime praCompDate;
    private String lockIncompleteReason;
    private String lockIncompleteOtherText;

    private OasysUserDto bcsPartUser;
    private OasysUserDto part1CheckedUser;

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
