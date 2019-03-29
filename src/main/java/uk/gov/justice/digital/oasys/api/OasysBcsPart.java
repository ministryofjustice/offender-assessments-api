package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OasysBcsPart {
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

    private OasysUser bcsPartUser;
    private OasysUser part1CheckedUser;
}
