package uk.gov.justice.digital.oasys.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderLink;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderSummary;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderLinkRepository;
import uk.gov.justice.digital.oasys.jpa.repository.SimpleOffenderRepository;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class OffenderServiceTest {

    @Mock
    SimpleOffenderRepository simpleOffenderRepository;

    @Mock
    OffenderLinkRepository offenderLinkRepository;

    OffenderService service;

    @BeforeEach
    public void setup() {
        service = new OffenderService(simpleOffenderRepository, offenderLinkRepository);
    }

    @Test
    public void shouldReturnNullMergedOffenderPKWhenOffenderHasNotBeenMerged() {
        var offender = Optional.ofNullable(OffenderSummary.builder()
                .limitedAccessOffender("N")
                .offenderPk(1L).build());
        when(simpleOffenderRepository.getOffender("OASYS", "1")).thenReturn(offender);
        when(offenderLinkRepository.findMergedOffender(1L)).thenReturn(Optional.empty());

        var result = service.getOffender("OASYS", "1");

        assertThat(result.getOasysOffenderId()).isEqualTo(1L);
        assertThat(result.getMergedOasysOffenderId()).isNull();
    }

    @Test
    public void shouldReturnMergedOffenderPKWhenOffenderHasMerged() {
        var offender = Optional.ofNullable(OffenderSummary.builder()
                .limitedAccessOffender("N")
                .mergeIndicated("Y")
                .offenderPk(1L).build());

        var mergedOffender = Optional.ofNullable(OffenderSummary.builder()
                .limitedAccessOffender("N")
                .mergeIndicated("N")
                .offenderPk(2L).build());

        var linkedOffender = Optional.ofNullable(OffenderLink.builder().mergedOffenderPK(2L).build());

        when(simpleOffenderRepository.getOffender("oasysOffenderId", "1")).thenReturn(offender);
        when(simpleOffenderRepository.getOffender("oasysOffenderId", "2")).thenReturn(mergedOffender);

        when(offenderLinkRepository.findMergedOffender(1L)).thenReturn(linkedOffender);

        var result = service.getOffender("oasysOffenderId", "1");

        assertThat(result.getOasysOffenderId()).isEqualTo(2L);
        assertThat(result.getMergedOasysOffenderId()).isEqualTo(1L);

        verify(simpleOffenderRepository, times(1)).getOffender(eq("oasysOffenderId"), eq("2"));
        verify(offenderLinkRepository, times(1)).findMergedOffender(eq(1L));
    }

    @Test
    public void shouldReturnMergedOffenderPKWhenOffenderHasMergedTwice() {
        var offender = Optional.ofNullable(OffenderSummary.builder()
                .limitedAccessOffender("N")
                .mergeIndicated("Y")
                .offenderPk(1L).build());

        var mergedOffender = Optional.ofNullable(OffenderSummary.builder()
                .limitedAccessOffender("N")
                .mergeIndicated("N")
                .offenderPk(3L).build());

        var linkedOffender1 = Optional.ofNullable(OffenderLink.builder().mergedOffenderPK(2L).build());
        var linkedOffender2 = Optional.ofNullable(OffenderLink.builder().mergedOffenderPK(3L).build());

        when(simpleOffenderRepository.getOffender("oasysOffenderId", "1")).thenReturn(offender);
        when(simpleOffenderRepository.getOffender("oasysOffenderId", "3")).thenReturn(mergedOffender);

        when(offenderLinkRepository.findMergedOffender(1L)).thenReturn(linkedOffender1);
        when(offenderLinkRepository.findMergedOffender(2L)).thenReturn(linkedOffender2);


        var result = service.getOffender("oasysOffenderId", "1");

        assertThat(result.getOasysOffenderId()).isEqualTo(3L);
        assertThat(result.getMergedOasysOffenderId()).isEqualTo(1L);

        verify(simpleOffenderRepository, times(1)).getOffender(eq("oasysOffenderId"), eq("3"));
        verify(offenderLinkRepository, times(1)).findMergedOffender(eq(1L));
        verify(offenderLinkRepository, times(1)).findMergedOffender(eq(2L));
    }

    @Test
    public void shouldReturnMergedOffenderPKWhenOffenderHasMergedThreeTimes() {
        var offender = Optional.ofNullable(OffenderSummary.builder()
                .limitedAccessOffender("N")
                .mergeIndicated("Y")
                .offenderPk(1L).build());

        var mergedOffender = Optional.ofNullable(OffenderSummary.builder()
                .limitedAccessOffender("N")
                .mergeIndicated("N")
                .offenderPk(4L).build());

        var linkedOffender1 = Optional.ofNullable(OffenderLink.builder().mergedOffenderPK(2L).build());
        var linkedOffender2 = Optional.ofNullable(OffenderLink.builder().mergedOffenderPK(3L).build());
        var linkedOffender3 = Optional.ofNullable(OffenderLink.builder().mergedOffenderPK(4L).build());

        when(simpleOffenderRepository.getOffender("oasysOffenderId", "1")).thenReturn(offender);
        when(simpleOffenderRepository.getOffender("oasysOffenderId", "4")).thenReturn(mergedOffender);

        when(offenderLinkRepository.findMergedOffender(1L)).thenReturn(linkedOffender1);
        when(offenderLinkRepository.findMergedOffender(2L)).thenReturn(linkedOffender2);
        when(offenderLinkRepository.findMergedOffender(3L)).thenReturn(linkedOffender3);

        var result = service.getOffender("oasysOffenderId", "1");

        assertThat(result.getOasysOffenderId()).isEqualTo(4L);
        assertThat(result.getMergedOasysOffenderId()).isEqualTo(1L);

        verify(simpleOffenderRepository, times(1)).getOffender(eq("oasysOffenderId"), eq("4"));
        verify(offenderLinkRepository, times(1)).findMergedOffender(eq(1L));
        verify(offenderLinkRepository, times(1)).findMergedOffender(eq(2L));
        verify(offenderLinkRepository, times(1)).findMergedOffender(eq(3L));
    }

}